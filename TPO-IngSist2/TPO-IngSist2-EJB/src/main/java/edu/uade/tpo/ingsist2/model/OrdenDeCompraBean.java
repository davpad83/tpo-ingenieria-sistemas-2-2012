package edu.uade.tpo.ingsist2.model;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.ItemSolicitudCompraRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCompraRequest;

/**
 * Session Bean implementation class OrdenDeCompra
 */
@Stateless
public class OrdenDeCompraBean implements OrdenDeCompra {

	private static final Logger LOGGER = Logger
			.getLogger(OrdenDeCompraBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	@EJB
	private OficinaDeVenta oficinaVenta;
	@EJB
	private Cotizacion cotizacion;

	/**
	 * Valida la solicitud de comprar enviada por la ODV. 
	 * Omite la Solicitud completa si: 
	 * 		1) La lista de items no existe o esta vacia. 
	 * 		2) La oficina de venta no existe (validada con el id recibido)
	 * 		3) Todos los items fueron omitidos.
	 * Omite un item si: 
	 * 		1) No existe la cotizacion asociada (validada con el idPedido de la ODV y la
	 * 		   ODV) 
	 * 		2) La cantidad del item es menor a 1. 
	 * 		3) El codigo skf esta vacio o es nulo. 
	 * 		4) El pais esta vacio o es nulo.
	 */
	public boolean validarSolicitudCompra(SolicitudCompraRequest request) {
		LOGGER.info("Validando Solicitud de Compra ...");
		boolean esValido = true;
		if (!oficinaVenta.existe(request.getIdODV())) {
			esValido = false;
			LOGGER.error("No existe la oficina de ventas con id: "
					+ request.getIdODV());
		}

		if (request.getItems() == null || request.getItems().isEmpty()) {
			LOGGER.warn("La solicitud de compra no contiene items.");
			esValido = false;
		} else {
			List<ItemSolicitudCompraRequest> tempList = new ArrayList<ItemSolicitudCompraRequest>();
			for (ItemSolicitudCompraRequest ivo : request.getItems()) {
				if (ivo.getCantidad() < 1) {
					LOGGER.warn("Un item contiene cantidad 0, por lo que "
							+ "no sera procesado. Omitiendo item (" + ivo.getSKF()
							+ ")");
				} else if (ivo.getSKF() == null || ivo.getSKF().isEmpty()) {
					LOGGER.warn("El item no tiene codigo skf o es nulo, sera omitido.");
				} else if (ivo.getPais() == null || ivo.getPais().isEmpty()) {
					LOGGER.warn("El item no tiene pais o es nulo, sera omitido.");
				} else if (!cotizacion.existe(ivo.getIdPedidoCotODV(), request.getIdODV(), ivo.getRodamiento())) {
					LOGGER.warn("La cotizacion no existe en un item de "
							+ "la solicitud para idPedido de ODV"
							+ ivo.getIdPedidoCotODV() + " y ODV id "
							+ request.getIdODV() + ". Omitiendo item (" + ivo.getSKF()
							+ ")");
				} else {
					tempList.add(ivo);
				}
			}
			
			if (tempList.isEmpty()) {
				LOGGER.warn("Todos los items recibidos fueron omitidos. La orden de compra no se guardara.");
				esValido = false;
			}
			request.setItems(tempList);
		}
		
		if (esValido)
			LOGGER.info("Validacion exitosa!");
		else
			LOGGER.error("La solicitud de compra es invalida.");
		return esValido;
	}

	@Override
	public OrdenDeCompraEntity guardarOrdenDeCompra(OrdenDeCompraEntity oce) {
		LOGGER.info("Procesando guardar orden de compra.");

		OrdenDeCompraEntity ocGuardada = null;
		try {
			ocGuardada = (OrdenDeCompraEntity) entityManager.merge(oce);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al guardar la orden de compra.");
			LOGGER.error(e);
		}
		LOGGER.info("Orden de Compra guardada con id: "
				+ ocGuardada.getIdOrden());
		return ocGuardada;
	}

	@Override
	public OrdenDeCompraEntity verificarPendientes(OrdenDeCompraEntity oce) {
		LOGGER.info("Verificando los items pendiendes de la OC "
				+ oce.getIdOrden() + " ...");
		boolean completa = true;
		for (ItemRodamientoEntity ire : oce.getItems()) {
			if (ire.getPendientes() > 0) {
				completa = false;
				LOGGER.info("El item con id " + ire.getId() + " tiene "
						+ ire.getPendientes() + " entregas pendientes.");
			} else
				LOGGER.info("El item con id " + ire.getId()
						+ " no tiene entregas pendientes.");
		}
		if (completa)
			oce.setEstado("Completa");
		else
			oce.setEstado("Pendiente");
		return oce;
	}

	@Override
	public OrdenDeCompraEntity getOrdenDeCompra(int id) {
		LOGGER.info("Buscando pedido con id " + id);
		OrdenDeCompraEntity oc = null;
		try {
			oc = entityManager.find(OrdenDeCompraEntity.class, id);
		} catch (NoResultException nre){
			LOGGER.warn("No se encontro la orden de compra que coincida con los datos de entrada.");
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar la orden de compra.");
			e.printStackTrace();
			return null;
		} finally {
			if (oc != null)
				LOGGER.info("la orden de compra con id " + id
						+ " se ha encontrado");
		}
		return oc;
	}

	@Override
	public ItemRodamientoEntity getItemRodamiento(int idOC,
			RodamientoEntity rodPedido) {
		LOGGER.info("Buscando item Rodamiento para OC " + idOC + "(SKF:"
				+ rodPedido.getCodigoSKF() + "|Marca:" + rodPedido.getMarca()
				+ "|Pais:" + rodPedido.getPais() + ")");
		ItemRodamientoEntity ire = null;
		try {
			ire = (ItemRodamientoEntity) entityManager
					.createQuery(
						"FROM ItemRodamientoEntity "+
						"WHERE id ="+
							"(SELECT IR.id "+
							"FROM OrdenDeCompraEntity OC "
									+ "		JOIN OC.items IR"
									+ " WHERE OC.idOrden = :idOC "
									+ "		AND IR.cotizacion.rodamiento.codigoSKF = :codigo"
									+ "		AND IR.cotizacion.rodamiento.pais = :pais"
									+ "		AND IR.cotizacion.rodamiento.marca = :marca)")
					.setParameter("idOC", idOC)
					.setParameter("marca", rodPedido.getMarca())
					.setParameter("codigo", rodPedido.getCodigoSKF())
					.setParameter("pais", rodPedido.getPais())
					.getSingleResult();
		} catch (NoResultException nre) {
			LOGGER.info("No se ha encontrado la orden de compra con id "
					+ ire.getId());
			return null;
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar el item.");
			e.printStackTrace();
			return null;
		}
		if (ire != null) {
			LOGGER.info("El itemRodamiento con id " + ire.getId()
					+ " se ha encontrado");
		}
		return ire;
	}
}

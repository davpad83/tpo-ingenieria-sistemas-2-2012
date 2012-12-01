package edu.uade.tpo.ingsist2.model;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.view.vo.ItemVO;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCompraRequest;

/**
 * Session Bean implementation class OrdenDeCompra
 */
@Stateless
public class OrdenDeCompraBean implements OrdenDeCompra {

	private static final Logger LOGGER = Logger.getLogger(OrdenDeCompraBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	@EJB
	private OficinaDeVenta oficinaVenta;
	@EJB
	private Cotizacion cotizacion;
	
	public boolean validarSolicitudCompra(SolicitudCompraRequest oc) {
		LOGGER.info("Validando Solicitud de Compra ...");
		boolean esValido = true;
		if(oc.getItems()==null || oc.getItems().isEmpty()){
			LOGGER.error("La solicitud de compra es invalida, no contiene items.");
			esValido = false;
		}
		if(!oficinaVenta.existe(oc.getIdODV())){
			esValido = false;
			LOGGER.error("No existe la oficina de ventas con id: "+oc.getIdODV());
		}
		for(ItemVO ivo : oc.getItems()){
			if(!cotizacion.existe(ivo.getId())){
				LOGGER.warn("La cotizacion no existe en un item de la solicitud.");
				oc.getItems().remove(ivo);
			} else if(ivo.getCantidad() <0){
				LOGGER.warn("Un item contiene cantidad 0, por lo que no sera procesado.");
				oc.getItems().remove(ivo);
			}
		}
		if(esValido)
			LOGGER.info("Validacion exitosa!");
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
	public void verificarPendientes(OrdenDeCompraEntity oce) {
		LOGGER.info("Verificando los items pendiendes de la OC "
				+ oce.getIdOrden() + " ...");
		boolean completa = true;
		for (ItemRodamientoEntity ire : oce.getItems()) {
			if (ire.getPendientes() > 0) {
				completa = false;
				LOGGER.debug("El item con id " + ire.getId() + " tiene "
						+ ire.getPendientes() + " entregas pendientes.");
			} else
				LOGGER.debug("El item con id " + ire.getId()
						+ " no tiene entregas pendientes.");
		}
		if(completa)
			oce.setEstado("Completa");
		else 
			oce.setEstado("Pendiente");
	}
	
	@Override
	public OrdenDeCompraEntity getOrdenDeCompra(int id) {
		LOGGER.info("Buscando pedido con id " + id);
		OrdenDeCompraEntity oc = null;
		try {
			oc = entityManager.find(OrdenDeCompraEntity.class, id);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar la orden de compra.");
			e.printStackTrace();
			return null;
		} finally {
			if (oc != null)
				LOGGER.info("la orden de compra con id " + id
						+ " se ha encontrado");
			else {
				LOGGER.info("No se ha encontrado la orden de compra con id " + id);
				return null;
			}
		}
		return oc;
	}
}

package edu.uade.tpo.ingsist2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import edu.uade.tpo.ingsist2.model.entities.CotizacionEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemListaEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.ListaPreciosEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;

/**
 * Session Bean implementation class CotizacionBean
 */
@Stateless
public class CotizacionBean implements Cotizacion {

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	private static final Logger LOGGER = Logger.getLogger(CotizacionBean.class);

	public ItemListaEntity getItemListaConMenorPrecioConMarca(
			RodamientoEntity rod) {
		LOGGER.info("Buscando item lista con menor precio para codigoSKF: "
				+ rod.getCodigoSKF() + ", pais: " + rod.getPais()
				+ " y marca: " + rod.getMarca());
		ItemListaEntity itEncontrado = null;

		Query query = null;
		try {
			query = entityManager
					.createQuery(
							"SELECT IL "
									+ "FROM ItemListaEntity IL "
									+ "WHERE IL.precio = "
									+ "(SELECT MIN(IL2.precio) "
									+ " FROM ItemListaEntity IL2 "
									+ " WHERE IL2.rodamiento.codigoSKF = :codigo "
									+ "AND IL2.rodamiento.pais = :pais "
									+ "AND IL2.rodamiento.marca = :marca"
									+ ") "
									+ "AND IL.rodamiento.codigoSKF = :codigo "
									+ "AND IL.rodamiento.pais = :pais "
									+ "AND IL.rodamiento.marca = :marca")
					.setParameter("codigo", rod.getCodigoSKF())
					.setParameter("marca", rod.getMarca())
					.setParameter("pais", rod.getPais());
			itEncontrado = (ItemListaEntity) query.getSingleResult();
		} catch (NoResultException nre) {
			LOGGER.warn("El query no devolvio ningun resultado.");
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar el item lista.");
			LOGGER.error(e);
		} finally {
			if (itEncontrado == null) {
				LOGGER.info("No existe el item lista");
				return null;
			} else
				LOGGER.info("Se ha encontrado el item lista para el rodamiento.");
		}
		return itEncontrado;
	}

	@SuppressWarnings("unchecked")
	public List<ItemListaEntity> getItemsListaConMenorPrecioSinMarca(
			RodamientoEntity rod) {
		LOGGER.info("Buscando items en listas con menor precio para codigoSKF: "
				+ rod.getCodigoSKF() + ", pais: " + rod.getPais());
		List<ItemListaEntity> listaResultado = null;

		try {
			String sqlString = "SELECT IL.id, IL.precio, IL.rodamiento_id ,IL.items_idlista "
					+ "FROM ItemLista IL INNER JOIN rodamiento ROD on (IL.rodamiento_id = ROD.id)  WHERE IL.precio = "
					+ "(SELECT distinct MIN(IL2.precio) FROM ItemLista IL2 inner join rodamiento ROD2 on (IL2.rodamiento_id = ROD2.id) "
					+ "WHERE ROD2.codigoSKF = ROD.codigoSKF AND ROD2.pais = ROD.pais AND ROD2.marca = rod.marca "
					+ "GROUP BY ROD2.marca) "
					+ "AND ROD.codigoSKF = :codigo AND rod.pais = :pais";

			Query q = entityManager.createNativeQuery(sqlString,
					ItemListaEntity.class);
			q.setParameter("codigo", rod.getCodigoSKF());
			q.setParameter("pais", rod.getPais());
			listaResultado = (List<ItemListaEntity>) q.getResultList();
		} catch (NoResultException nre) {
			LOGGER.warn("El query no devolvio ningun resultado.");
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar los items en listas");
		} finally {
			if (listaResultado == null) {
				LOGGER.info("No existe ningun item lista");
				return null;
			} else
				LOGGER.info("Se ha encontrado los items lista para el rodamiento sin marca.");
		}
		return listaResultado;
	}

	@Override
	public CotizacionEntity getCotizacion(int idCot) {
		LOGGER.info("Buscando Cotizacion con id " + idCot);
		CotizacionEntity cot = null;
		try {
			cot = entityManager.find(CotizacionEntity.class, idCot);
		} catch (NoResultException nre) {
			LOGGER.warn("No se encontro la cotizacion que coincida con los datos de entrada.");
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar la cotizacion.");
			LOGGER.error(e);
		} finally {
			if (cot != null)
				LOGGER.info("Se ha encontrado la cotizacion, su id es: "
						+ cot.getId());
			else {
				LOGGER.info("No se ha encontrado la cotizacion con id " + idCot);
				return null;
			}
		}
		return cot;
	}

	@Override
	public int verificarStock(ItemRodamientoEntity ire) {
		RodamientoEntity rod = ire.getCotizacion().getRodamiento();
		if (rod.hayStockSuficiente(ire.getCantidad()))
			return rod.getStock();
		else
			return 0;
	}

	@Override
	public boolean validarVigenciaLista(ListaPreciosEntity lista) {
		boolean valida = true;
		if (lista.getVigenciaDesde().after(new Date())
				|| lista.getVigenciaHasta().before(new Date()))
			valida = false;
		return valida;
	}

	@Override
	public int guardarCotizacion(CotizacionEntity c) {
		LOGGER.info("Procesando guardar cotizacion con id Pedido de Cotizacion: "
				+ c.getIdRecibidoODV());

		CotizacionEntity cGuardado = null;
		try {
			cGuardado = (CotizacionEntity) entityManager.merge(c);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al guardar la cotizacion");
			LOGGER.error(e);
		}
		LOGGER.info("Cotizacion guardada con id: " + cGuardado.getId());
		return cGuardado.getId();
	}

	@Override
	public boolean existe(int idPedidoCotODV, int idODV, RodamientoEntity rod) {
		CotizacionEntity cot = getCotizacion(idPedidoCotODV, idODV, rod);
		return cot != null;
	}

	public CotizacionEntity getCotizacion(int idPedidoCotODV, int idODV,
			RodamientoEntity rod) {
		LOGGER.info("Buscando cotizacion por idPedidoCotizacionODV "
				+ idPedidoCotODV + ", idODV " + idODV + " y Rodamiento: ["
				+ rod.getCodigoSKF() + "|" + rod.getMarca() + "|"
				+ rod.getPais() + "]");
		CotizacionEntity cot = null;
		try {
			cot = (CotizacionEntity) entityManager
					.createQuery(
							"FROM CotizacionEntity COT "
									+ "WHERE COT.idRecibidoODV = :idPedidoCot "
									+ "	AND COT.odv.id = :idODV"
									+ "	AND COT.rodamiento.codigoSKF = :codigo"
									+ "	AND COT.rodamiento.pais = :pais"
									+ "	AND COT.rodamiento.marca = :marca")
					.setParameter("idPedidoCot", idPedidoCotODV)
					.setParameter("idODV", idODV)
					.setParameter("marca", rod.getMarca())
					.setParameter("codigo", rod.getCodigoSKF())
					.setParameter("pais", rod.getPais()).getSingleResult();
		} catch (NoResultException nre) {
			LOGGER.warn("La cotizacion no existe.");
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar la cotizacion");
			LOGGER.error(e);
		}
		if (cot != null)
			LOGGER.info("La cotizacion fue encontrada y su id interno es "
					+ cot.getId());
		return cot;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ItemRodamientoEntity> getItemsCotizados(int idCotizacion) {
		LOGGER.info("Buscando items cotizados por id de cotizacion: "
				+ idCotizacion);

		ArrayList<ItemRodamientoEntity> itemsCotizados = null;
		Query query = entityManager.createQuery(
				"SELECT IR " + " FROM ItemRodamientoEntity IR"
						+ " WHERE IR.cotizacion.id = :idCot").setParameter(
				"idCot", idCotizacion);
		try {
			itemsCotizados = (ArrayList<ItemRodamientoEntity>) query
					.getResultList();
		} catch (NoResultException nre) {
			LOGGER.warn("No se encontro ningun item rodamiento.");
		} catch (Exception e) {
			LOGGER.error("Hubo un error al obtener los items cotizados");
			LOGGER.error(e);
		}
		LOGGER.info("Se obtuvieron " + itemsCotizados.size()
				+ " items cotizados.");
		return itemsCotizados;
	}

	@Override
	public boolean validarRequest(SolicitudCotizacionRequest cotRequest) {
		boolean esValido = true;
		RodamientoEntity rodEnt = new RodamientoEntity();
		rodEnt.setVO(cotRequest.getRodamiento());
		if(existe(cotRequest.getIdPedidoCotizacion(), cotRequest.getIdODV(), rodEnt)){
			LOGGER.warn("El id de pedido de cotizacion (ODV) "+cotRequest.getIdPedidoCotizacion()+" ya existe para la odv "+cotRequest.getIdODV());
			esValido = false;
		}
		
		if (esValido)
			LOGGER.info("Validacion exitosa!");
		else
			LOGGER.warn("La solicitud de cotizacion es invalida.");
		return esValido;
	}

}

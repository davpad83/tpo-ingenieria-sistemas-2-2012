package edu.uade.tpo.ingsist2.model;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;

/**
 * Session Bean implementation class CotizacionBean
 */
@Stateless
public class CotizacionBean implements Cotizacion {

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	private static final Logger LOGGER = Logger.getLogger(CotizacionBean.class);

	@Override
	public SolicitudCotizacionResponse procesarSolicitudCotizacion(
			SolicitudCotizacionRequest scr) {
		return null;
	}

	public ItemListaEntity getItemListaConMenorPrecioConMarca(
			RodamientoEntity rod) {
		LOGGER.info("Buscando item lista con menor precio para codigoSKF: "
				+ rod.getCodigoSKF() + ", pais: " + rod.getPais()
				+ " y marca: " + rod.getMarca());
		ItemListaEntity itEncontrado = null;

		try {
			Query query = entityManager
					.createQuery(
							"SELECT IL " 
							+"FROM ItemListaEntity IL"
							+ " WHERE IL.precio = "
									+ "(SELECT MIN(IL2.precio) "
										+ " FROM ItemListaEntity IL2"
										+ " WHERE IL2.rodamiento.codigoSKF = :codigo "
											+ "AND IL2.rodamiento.marca = :marca "
											+ "AND IL2.rodamiento.pais = :pais "
//										+ "GROUP BY IL2.rodamiento.marca)"
											)
					.setParameter("codigo", rod.getCodigoSKF())
					.setParameter("marca", rod.getMarca())
					.setParameter("pais", rod.getPais());
			LOGGER.debug("Executing query: " + query.toString());
			itEncontrado = (ItemListaEntity) query.getSingleResult();
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar el item lista.");
		}
		LOGGER.info("ItemLista encontrado, su id es: " + itEncontrado.getId());

		return itEncontrado;
	}

	@SuppressWarnings("unchecked")
	public List<ItemListaEntity> getItemsListaConMenorPrecioSinMarca(
			RodamientoEntity rod) {
		LOGGER.info("Buscando items en listas con menor precio para codigoSKF: "
				+ rod.getCodigoSKF() + ", pais: " + rod.getPais());
		List<ItemListaEntity> listaResultado = null;

		try {
			/*
			 * listaResultado= entityManager.createQuery(
			 * "select i, min(i.precio) from ItemListaEntity i where i.rodamiento.pais=:pais and i.rodamiento.codigoSKF=:codigo "
			 * + "group by i.rodamiento.marca")
			 */
			// listaResultado=
			// entityManager.createQuery("select i from ItemListaEntity i where i.rodamiento.pais=:pais and i.rodamiento.codigoSKF=:codigo "
			// +
			// "group by i.rodamiento.marca")
			// .setParameter("codigo", rod.getCodigoSKF())
			// .setParameter("pais", rod.getPais())
			// .getResultList();

			Query query = entityManager
					.createQuery(
							"SELECT IL FROM ItemListaEntity IL"
									+ " WHERE IL.rodamiento.marca IN "
									+ "(SELECT IL2.rodamiento.marca, MIN(IL2.precio) "
										+ " FROM ItemListaEntity IL2"
										+ " WHERE IL2.rodamiento.codigoSKF = :codigo "
										+ "AND IL2.rodamiento.pais = :pais "
										+ "GROUP BY IL2.rodamiento.marca)")
					.setParameter("codigo", rod.getCodigoSKF())
					.setParameter("pais", rod.getPais());

		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar los items en listas");
		}

		return listaResultado;
	}

	@Override
	public CotizacionEntity getCotizacion(int idCot) {
		LOGGER.info("Buscando Cotizacion con id " + idCot);
		CotizacionEntity cot = null;
		try {
			cot = entityManager.find(CotizacionEntity.class, idCot);
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
		RodamientoEntity rod = ire.getRodamiento();
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
	public void guardarCotizacion(CotizacionEntity c) {
		LOGGER.info("Procesando guardar cotizacion con id Pedido de Cotizacion: "
				+ c.getIdPedidoCotizacion());

		CotizacionEntity cGuardado = null;
		try {
			cGuardado = (CotizacionEntity) entityManager.merge(c);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al guardar la cotizacion");
			LOGGER.error(e);
		}
		LOGGER.info("Cotizacion guardada con id: " + cGuardado.getId());
	}

	@Override
	public boolean existe(int id) {
		return getCotizacion(id) == null;
	}
}

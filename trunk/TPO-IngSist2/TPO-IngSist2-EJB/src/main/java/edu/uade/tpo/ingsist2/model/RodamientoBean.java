package edu.uade.tpo.ingsist2.model;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;

/**
 * Session Bean implementation class RodamientoBean
 */
@Stateless
public class RodamientoBean implements Rodamiento {

	private static final Logger LOGGER = Logger.getLogger(RodamientoBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	@Override
	public void guardarRodamiento(RodamientoEntity r) {
		LOGGER.info("Procesando guardar rodamiento con codigoSKF "
				+ r.getCodigoSKF());
		RodamientoEntity rGuardado = null;
		try {
			rGuardado = (RodamientoEntity) entityManager.merge(r);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al guardar el rodamiento");
			e.printStackTrace();
		}
		LOGGER.info("Rodamiento guardado con id: " + rGuardado.getId());
		entityManager.flush();
	}

	@Override
	public void eliminarRodamiento(int id) {
		LOGGER.info("Procesando eliminar rodamiento con id: " + id);
		try {
			RodamientoEntity r = entityManager.find(RodamientoEntity.class, id);
			entityManager.remove(r);
		} catch (Exception e) {
			LOGGER.error("Hubo un error intentando eliminar el rodamiento con id "
					+ id);
			LOGGER.error(e);
		}
		LOGGER.info("El rodamiento con id " + id
				+ " se ha eliminado con exito.");
	}

	@Override
	public RodamientoEntity getRodamiento(int id) {
		LOGGER.info("Buscando Rodamiento con id " + id);
		RodamientoEntity r = null;
		try {
			r = entityManager.find(RodamientoEntity.class, id);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar el rodamiento.");
			LOGGER.error(e);
			return null;
		} finally {
			if (r != null)
				LOGGER.info("El Rodamiento con id " + id
						+ " se ha encontrado, su codigoSKF es "
						+ r.getCodigoSKF());
			else {
				LOGGER.info("No se ha encontrado el rodamiento con id " + id);
				return null;
			}
		}
		return r;
	}

	@Override
	public RodamientoEntity getRodamientoCotizacionConMarca(String skf,
			String pais, String marca) {
		LOGGER.info("Buscando Rodamiento con Codigo SKF: " + skf + " Pais: "
				+ pais + " y Marca: " + marca);
		RodamientoEntity rBean = null;
		
		Query query = entityManager
				.createQuery(
						"select r " + "from RodamientoEntity r "
								+ "where r.codigoSKF=:codigo "
								+ "	and r.pais=:pais "
								+ "	and r.marca=:marca")
								.setParameter("codigo", skf).setParameter("pais", pais)
								.setParameter("marca", marca);
		
		try {
			 rBean = (RodamientoEntity) query.getSingleResult();
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar el rodamiento.");
			LOGGER.error(e);
			LOGGER.error(query.toString());
		} finally {
			if (rBean == null) {
				LOGGER.info("No existe el rodamiento solicitado");
				return null;
			} else
				LOGGER.info("Se ha encontrado el rodamiento.");
		}
		return rBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public RodamientoEntity getRodamientosCotizacionSinMarca(String skf,
			String pais) {
		LOGGER.info("Buscando Rodamiento con Codigo SKF: " + skf + " y Pais: "
				+ pais);

		List<RodamientoEntity> lrBean = null;

		try {
			lrBean = (List<RodamientoEntity>) entityManager
					.createQuery(
							"select r from RodamientoEntity r where r.codigoSKF=:codigo and r.pais=:pais")
					.setParameter("codigo", skf).setParameter("pais", pais)
					.getResultList();

		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar el rodamiento");
			LOGGER.error(e);
		} finally {
			if (lrBean.isEmpty()) {
				LOGGER.info("No existe el rodamiento solicitado");
				return null;
			} else
				LOGGER.info("Se ha encontrado el rodamiento.");
		}
		return lrBean.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<RodamientoEntity> getRodamientos() {
		LOGGER.info("Buscando lista de Rodamientos");
		ArrayList<RodamientoEntity> listaResultado = null;
		try {
			listaResultado = (ArrayList<RodamientoEntity>) entityManager
					.createQuery(
							"FROM " + RodamientoEntity.class.getSimpleName())
					.getResultList();
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar todos los rodamientos.");
			LOGGER.error(e);
			return null;
		} finally {
			if (listaResultado == null || listaResultado.isEmpty()) {
				LOGGER.info("No se han encontrado instancias de Rodamientos");
				return null;
			} else
				LOGGER.info("Se han encontrado " + listaResultado.size()
						+ " instancias de Rodamientos");
		}
		return listaResultado;
	}

	@Override
	public RodamientoEntity getRodamiento(String codSKF, String marca,
			String pais) {
		LOGGER.info("Buscando Rodamiento por codigo: " + codSKF + ", marca: "
				+ marca + " y pais: " + pais);
		RodamientoEntity rodResultado = null;
		try {
			rodResultado = (RodamientoEntity) entityManager
					.createQuery(
							"FROM "
									+ RodamientoEntity.class.getSimpleName()
									+ " WHERE codigoSKF = :codSKF AND marca = :marca AND pais = :pais")
					.setParameter("codSKF", codSKF)
					.setParameter("marca", marca).setParameter("pais", pais)
					.getSingleResult();
		} catch (NoResultException nre){
			LOGGER.warn("No se ha encontrado el rodamiento.");
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar el rodamientos.");
			LOGGER.error(e);
			return null;
		} finally {
			if (rodResultado != null)
				LOGGER.info("Se ha encontrado el rodamiento y su id es: "
						+ rodResultado.getId());
		}
		return rodResultado;
	}
}

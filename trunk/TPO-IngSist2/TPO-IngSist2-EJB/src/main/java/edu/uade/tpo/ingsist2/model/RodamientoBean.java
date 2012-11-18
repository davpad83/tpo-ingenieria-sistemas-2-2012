package edu.uade.tpo.ingsist2.model;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;

/**
 * Session Bean implementation class RodamientoBean
 */
@Stateless
public class RodamientoBean implements Rodamiento {

	private static Logger logger = Logger.getLogger(RodamientoBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;
	
	@Override
	public void guardarRodamiento(RodamientoEntity r) {
		logger.info("Procesando guardar rodamiento con codigoSKF " + r.getCodigoSKF());
		RodamientoEntity rGuardado = null;
		try {
			rGuardado = (RodamientoEntity) entityManager.merge(r);
		} catch (Exception e) {
			logger.error("Hubo un error al guardar el rodamiento");
			e.printStackTrace();
		}
		logger.info("Rodamiento guardado con id: " + rGuardado.getId());
	}

	@Override
	public void eliminarRodamiento(int id) {
		logger.info("Procesando eliminar rodamiento con id: " + id);
		try {
			RodamientoEntity r = entityManager.find(RodamientoEntity.class, id);
			entityManager.remove(r);
		} catch (Exception e) {
			logger.error("Hubo un error intentando eliminar el rodamiento con id "
					+ id);
			e.printStackTrace();
		}
		logger.info("El rodamiento con id " + id + " se ha eliminado con exito.");
	}

	@Override
	public RodamientoEntity getRodamiento(int id) {
		logger.info("Buscando Rodamiento con id " + id);
		RodamientoEntity r = null;
		try {
			r = entityManager.find(RodamientoEntity.class, id);
		} catch (Exception e) {
			logger.error("Hubo un error al buscar el rodamiento.");
			e.printStackTrace();
			return null;
		} finally {
			if (r != null)
				logger.info("El Rodamiento con id " + id
						+ " se ha encontrado, su codigoSKF es " + r.getCodigoSKF());
			else {
				logger.info("No se ha encontrado el rodamiento con id " + id);
				return null;
			}
		}
		return r;
	}

	@Override
	public ArrayList<RodamientoEntity> getRodamientos() {
		logger.info("Buscando lista de Rodamientos");
		ArrayList<RodamientoEntity> listaResultado = null;
		try {
			listaResultado = (ArrayList<RodamientoEntity>) entityManager.createQuery(
					"FROM " + RodamientoEntity.class.getSimpleName()).getResultList();
		} catch (Exception e) {
			logger.error("Hubo un error al buscar todos los rodamientos.");
			e.printStackTrace();
			return null;
		} finally {
			if (listaResultado == null || listaResultado.isEmpty()) {
				logger.info("No se han encontrado instancias de Rodamientos");
				return null;
			} else
				logger.info("Se han encontrado " + listaResultado.size()
						+ " instancias de Rodamientos");
		}
		return listaResultado;
	}

}

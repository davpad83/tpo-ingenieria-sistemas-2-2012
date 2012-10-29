package edu.uade.tpo.ingsist2.model.util;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

public class QueryHelper {
	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	protected static Logger logger;

	@SuppressWarnings("unchecked")
	public <T> List<T> getListaEntidades(Class<T> claseEntidad) {
		logger.info("Buscando lista de " + claseEntidad.getSimpleName());
		List<T> listaResultado = null;
		listaResultado = (List<T>) entityManager.createQuery(
				"FROM " + claseEntidad.getSimpleName()).getResultList();
		logger.info("Se han encontrado " + listaResultado.size()
				+ " instancias de " + claseEntidad.getSimpleName());
		return listaResultado;
	}

	@SuppressWarnings("unchecked")
	public <T> T getEntidad(Class<T> claseEntidad, Serializable id) {
		logger.info("Buscando proveedor con id: " + id);
		Class<T> c = (Class<T>) entityManager.find(claseEntidad, id);
		if (c == null)
			logger.info("No se pudo encontrar el proveedor con id" + id);
		else 
			logger.info("Proveedor encontrado.");
		return (T) c;
	}
}

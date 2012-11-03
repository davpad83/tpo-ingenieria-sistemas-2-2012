package edu.uade.tpo.ingsist2.session.util;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;


@Stateless
public class QueryHelperBean implements QueryHelper{
	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	protected static Logger logger;

	@SuppressWarnings("unchecked")
	public <T> List<T> getListaEntidades(Class<T> claseEntidad) {
		logger.info("Buscando lista de " + claseEntidad.getSimpleName());
		List<T> listaResultado = null;
		listaResultado = (ArrayList<T>) entityManager.createQuery(
				"FROM " + claseEntidad.getSimpleName()).getResultList();
		if(listaResultado == null)
			listaResultado = new ArrayList<T>();
		logger.info("Se han encontrado " + listaResultado.size()
				+ " instancias de " + claseEntidad.getSimpleName());
		return listaResultado;
	}

	@SuppressWarnings("unchecked")
	public <T> T getEntidad(Class<T> claseEntidad, Serializable id) {
		logger.info("Buscando proveedor con id: " + id);
		Class<T> c = (Class<T>) entityManager.find(claseEntidad.getClass(), id);
		if (c == null)
			logger.info("No se pudo encontrar el proveedor con id" + id);
		else 
			logger.info("Proveedor encontrado.");
		return (T) c;
	}
}

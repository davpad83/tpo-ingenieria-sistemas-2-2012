package edu.uade.tpo.ingsist2.controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.ListaPreciosEntity;
import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;

/**
 * Session Bean implementation class ListaProveedorBean
 */
@Stateless
public class ListaPreciosControllerBean implements ListaPreciosController {

	private static final Logger LOGGER = Logger
			.getLogger(ListaPreciosControllerBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	@Override
	public void agregarListaProveedor(ListaPreciosVO lpvo) {
		if (validarListaProveedorRequest(lpvo)) {
			LOGGER.info("La Lista de precios es VALIDA. Guardando ...");
			
			ListaPreciosEntity lp = new ListaPreciosEntity();
			lp.setVO(lpvo);

			ListaPreciosEntity lpGuardado = entityManager.merge(lp);
			
			if(lpGuardado.getIdLista() > 0)
				LOGGER.info("La Lista de precios fue guardada con EXITO.");
		}
	}

	private boolean validarListaProveedorRequest(ListaPreciosVO lpr) {
		boolean valid = true;
		String logPrefix = "Error de validacion de ListaPrecios ";
		if( lpr.getItems() == null){
			LOGGER.error(logPrefix + "- La lista de items es nula. ");
			valid = false;
		} else if( lpr.getItems().isEmpty()) { 
			LOGGER.error(logPrefix + "- La lista de items esta vacia.");
			valid = false;
		}
		if (lpr.getProveedor() == null) {
			LOGGER.error(logPrefix + "- El proveedor es nulo.");
			valid = false;
		}
		return valid;
	}
}

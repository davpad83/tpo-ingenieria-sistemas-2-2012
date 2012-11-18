package edu.uade.tpo.ingsist2.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.ListaPreciosEntity;
import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;

/**
 * Session Bean implementation class ListaPreciosBean
 */
@Stateless
public class ListaPreciosBean implements ListaPrecios {

	private static final Logger LOGGER = Logger
			.getLogger(ListaPreciosBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;


	@Override
	public void agregarListaProveedor(ListaPreciosEntity lp) {
		if (validarListaProveedorRequest(lp)) {
			LOGGER.info("La Lista de precios es VALIDA. Guardando ...");
			
			ListaPreciosEntity lpGuardado = entityManager.merge(lp);
			if(lpGuardado.getIdLista() > 0)
				LOGGER.info("La Lista de precios fue guardada con EXITO. Su id es "+lpGuardado.getIdLista());
		}
	}
	
	private boolean validarListaProveedorRequest(ListaPreciosEntity lpr) {
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

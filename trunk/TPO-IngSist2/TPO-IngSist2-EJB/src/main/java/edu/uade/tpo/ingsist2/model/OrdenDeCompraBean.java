package edu.uade.tpo.ingsist2.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.model.entities.ProveedorEntity;


/**
 * Session Bean implementation class OrdenDeCompra
 */
@Stateless
public class OrdenDeCompraBean implements OrdenDeCompra {

	private static final Logger LOGGER = Logger.getLogger(ProveedorBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;
	
	
	public boolean validarOrdenDeCompra(OrdenDeCompraEntity oc){
		//TODO IMPLEMENTAR
		return false;
	}

	@Override
	public void guardarOrdenDeCompra(OrdenDeCompraEntity oce) {
		LOGGER.info("Procesando guardar orden de compra.");
		
		OrdenDeCompraEntity ocGuardada = null;
		try {
			ocGuardada = (OrdenDeCompraEntity) entityManager.merge(oce);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al guardar la orden de compra.");
			LOGGER.error(e);
		}
		LOGGER.info("Orden de Compra guardada con id: " + ocGuardada.getIdOrden());		
	}
}

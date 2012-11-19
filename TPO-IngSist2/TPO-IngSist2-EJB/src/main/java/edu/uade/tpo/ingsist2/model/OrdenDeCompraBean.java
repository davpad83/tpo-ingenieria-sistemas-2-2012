package edu.uade.tpo.ingsist2.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
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

	public boolean validarOrdenDeCompra(OrdenDeCompraEntity oc) {
		// TODO IMPLEMENTAR
		return false;
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
}

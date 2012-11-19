package edu.uade.tpo.ingsist2.model;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;

@Local
public interface OrdenDeCompra {

	public boolean validarOrdenDeCompra(OrdenDeCompraEntity oc);

	public OrdenDeCompraEntity guardarOrdenDeCompra(OrdenDeCompraEntity oce);

	public void verificarPendientes(OrdenDeCompraEntity oce);
	
}

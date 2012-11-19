package edu.uade.tpo.ingsist2.model;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;

@Local
public interface OrdenDeCompra {

	public boolean validarOrdenDeCompra(OrdenDeCompraEntity oc);

	public void guardarOrdenDeCompra(OrdenDeCompraEntity oce);
	
}

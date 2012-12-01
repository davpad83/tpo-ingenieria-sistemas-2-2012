package edu.uade.tpo.ingsist2.model;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCompraRequest;

@Local
public interface OrdenDeCompra {

	public boolean validarSolicitudCompra(SolicitudCompraRequest oc);

	public OrdenDeCompraEntity guardarOrdenDeCompra(OrdenDeCompraEntity oce);

	public void verificarPendientes(OrdenDeCompraEntity oce);
	
	public OrdenDeCompraEntity getOrdenDeCompra(int id);
}

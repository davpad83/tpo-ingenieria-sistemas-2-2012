package edu.uade.tpo.ingsist2.model;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCompraRequest;

@Local
public interface OrdenDeCompra {

	public boolean validarSolicitudCompra(SolicitudCompraRequest oc);

	public int guardarOrdenDeCompra(OrdenDeCompraEntity oce);

	public OrdenDeCompraEntity verificarPendientes(OrdenDeCompraEntity oce);
	
	public OrdenDeCompraEntity getOrdenDeCompra(int id);
}

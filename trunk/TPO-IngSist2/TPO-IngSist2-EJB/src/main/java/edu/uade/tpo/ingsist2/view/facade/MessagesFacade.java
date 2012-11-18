package edu.uade.tpo.ingsist2.view.facade;

import javax.ejb.Remote;

import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;
import edu.uade.tpo.ingsist2.view.vo.OrdenDeCompraVO;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodamientosVO;

@Remote
public interface MessagesFacade {
	
	public void agregarListaProveedor(ListaPreciosVO lpr);
	
	public void recibirEnvioProveedor(RecepcionRodamientosVO lpr);

	public void recibirSolicitudCompraRodamientos(OrdenDeCompraVO oc);
	
}
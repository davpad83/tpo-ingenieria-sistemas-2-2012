package edu.uade.tpo.ingsist2.view.facade;

import javax.ejb.Remote;

import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodamientosVO;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCompraRequest;

@Remote
public interface MessagesFacade {
	
	public void agregarListaProveedor(ListaPreciosVO lpr);
	
	public void recibirEnvioProveedor(RecepcionRodamientosVO lpr);

	public void recibirSolicitudCompraRodamientos(SolicitudCompraRequest request);
	
}

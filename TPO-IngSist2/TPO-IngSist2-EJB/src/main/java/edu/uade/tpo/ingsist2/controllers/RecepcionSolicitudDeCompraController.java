package edu.uade.tpo.ingsist2.controllers;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.view.vo.OrdenDeCompraVO;

@Local
public interface RecepcionSolicitudDeCompraController {

	public void procesarSolicitudDeCompra(OrdenDeCompraVO ocvo);
	
}

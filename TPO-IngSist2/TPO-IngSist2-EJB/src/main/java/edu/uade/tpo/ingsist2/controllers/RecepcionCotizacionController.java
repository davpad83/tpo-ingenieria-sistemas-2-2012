package edu.uade.tpo.ingsist2.controllers;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;


@Local
public interface RecepcionCotizacionController {
	
	public SolicitudCotizacionResponse procesarSolicitudCotizacion (SolicitudCotizacionRequest scr);
	

}

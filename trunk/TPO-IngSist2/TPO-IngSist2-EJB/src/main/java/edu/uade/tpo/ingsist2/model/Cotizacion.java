package edu.uade.tpo.ingsist2.model;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;

@Local
public interface Cotizacion {

	public SolicitudCotizacionResponse procesarSolicitudCotizacion (SolicitudCotizacionRequest scr);
	
}

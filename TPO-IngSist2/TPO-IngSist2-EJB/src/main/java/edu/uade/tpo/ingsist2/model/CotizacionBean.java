package edu.uade.tpo.ingsist2.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.controllers.AdministrarCotizacionesBean;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;

/**
 * Session Bean implementation class CotizacionBean
 */
@Stateless
public class CotizacionBean implements Cotizacion {

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;
	
	private static final Logger logger = Logger
			.getLogger(AdministrarCotizacionesBean.class);
	
	@Override
	public SolicitudCotizacionResponse procesarSolicitudCotizacion(
			SolicitudCotizacionRequest scr) {
		// TODO Auto-generated method stub
		return null;
	}


}

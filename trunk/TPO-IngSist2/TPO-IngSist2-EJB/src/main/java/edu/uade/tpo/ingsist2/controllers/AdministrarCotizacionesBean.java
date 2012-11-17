package edu.uade.tpo.ingsist2.controllers;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;

/**
 * Session Bean implementation class AdministrarCotizacionesBean
 */
@Stateless
public class AdministrarCotizacionesBean implements AdministrarCotizaciones {
	
	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	
	
    public AdministrarCotizacionesBean() {
        // TODO Auto-generated constructor stub
    }
    
	

}

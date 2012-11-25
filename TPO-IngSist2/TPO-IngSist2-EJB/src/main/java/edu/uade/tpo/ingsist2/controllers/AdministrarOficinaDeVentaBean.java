package edu.uade.tpo.ingsist2.controllers;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.uade.tpo.ingsist2.model.OficinaDeVenta;
import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.view.vo.OficinaDeVentaVO;

/**
 * Session Bean implementation class AdministrarOficinaDeVentaBean
 */
@Stateless
public class AdministrarOficinaDeVentaBean implements AdministrarOficinaDeVenta {

	@EJB
	private OficinaDeVenta oficinaVenta;
	
	public void guardarOficinaDeVenta(OficinaDeVentaVO odvvo){
		OficinaDeVentaEntity odve = new OficinaDeVentaEntity();
		odve.setVO(odvvo);
		oficinaVenta.guardarOficinaDeVenta(odve);
	}
}

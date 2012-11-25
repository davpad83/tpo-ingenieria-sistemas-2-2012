package edu.uade.tpo.ingsist2.controllers;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.view.vo.OficinaDeVentaVO;

@Local
public interface AdministrarOficinaDeVenta {

	public void guardarOficinaDeVenta(OficinaDeVentaVO odvvo);
	
}

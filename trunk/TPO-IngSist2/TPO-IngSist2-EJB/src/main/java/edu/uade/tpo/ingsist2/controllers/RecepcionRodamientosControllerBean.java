package edu.uade.tpo.ingsist2.controllers;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.view.vo.RecepcionRodProveedorRequest;

/**
 * Session Bean implementation class RecepcionRodamientosController
 */
@Stateless
public class RecepcionRodamientosControllerBean implements RecepcionRodamientosController {

	private static final Logger LOGGER = Logger.getLogger(RecepcionRodamientosControllerBean.class);

	@Override
	public void RecibirRodamientos(RecepcionRodProveedorRequest rodamientos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EnviarRemito() {
		// TODO Auto-generated method stub
		
	}
}

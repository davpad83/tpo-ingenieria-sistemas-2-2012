package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodProveedorRequest;

@Local
public interface RecepcionRodamientosController {

	public void RecibirRodamientos(RecepcionRodProveedorRequest rodamientos);
	
	public void EnviarRemito();
}

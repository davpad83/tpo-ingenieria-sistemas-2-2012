package edu.uade.tpo.ingsist2.controllers;

import javax.ejb.Local;


import edu.uade.tpo.ingsist2.view.vo.RecepcionRodamientosVO;

@Local
public interface RecepcionRodamientosController {

	void recibirEnvioProveedor(RecepcionRodamientosVO rodamientos);

}

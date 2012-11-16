package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.OrdenDeCompra;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodamientosVO;

@Local
public interface RecepcionRodamientosController {

	void recibirEnvioProveedor(RecepcionRodamientosVO rodamientos);

	void EnviarRemito(OrdenDeCompra oc);
}

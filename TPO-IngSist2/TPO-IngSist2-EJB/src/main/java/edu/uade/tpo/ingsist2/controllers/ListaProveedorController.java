package edu.uade.tpo.ingsist2.controllers;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.view.vo.RecepcionRodProveedorRequest;

@Local
public interface ListaProveedorController {

	public void agregarListaProveedor(RecepcionRodProveedorRequest lpr);
}

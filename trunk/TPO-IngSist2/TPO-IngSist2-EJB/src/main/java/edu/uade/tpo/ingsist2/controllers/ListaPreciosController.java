package edu.uade.tpo.ingsist2.controllers;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;

@Local
public interface ListaPreciosController {

	public void agregarListaProveedor(ListaPreciosVO lpvo);
	
}

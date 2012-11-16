package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.view.vo.OrdenDeCompraVO;


@Local
public interface AdministrarOrdenDeCompra {
	
	public void guardarOrdenCompra(OrdenDeCompraVO p);
	
	public void eliminarOrdenCompra (int id);
	
	public OrdenDeCompraVO getOrdenCompra(int id);

	public ArrayList<OrdenDeCompraVO> getOrdenesDeCompra();
}

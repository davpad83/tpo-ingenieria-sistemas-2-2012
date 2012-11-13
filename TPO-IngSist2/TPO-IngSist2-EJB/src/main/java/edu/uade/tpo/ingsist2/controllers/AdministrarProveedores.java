package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;

@Local
public interface AdministrarProveedores {

	public void guardarProveedor(ProveedorVO p);
	
	public void eliminarProveedor(int id);
	
	public ProveedorVO getProveedor(int id);

	public ArrayList<ProveedorVO> getProveedores();
}

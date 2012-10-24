package edu.uade.tpo.ingsist2.facade;

import java.util.ArrayList;

import javax.ejb.Remote;

import edu.uade.tpo.ingsist2.entities.vo.ProveedorVO;

@Remote
public interface Facade {

	/*==================================*/
	/*			ABM de Proveedores		*/
	/*==================================*/
	
	public void guardarProveedor(ProveedorVO p);
	
	public void eliminarProveedor(int id);
	
	public ProveedorVO getProveedor(int id);

	public ArrayList<ProveedorVO> getProveedores();
	
	public void solicitarPreciosRodamiento();
	
	public void recibirOrdenDeCompra();
	
	public void recibirRodamiento();
	
	public void recibirListaProveedor();

	
}

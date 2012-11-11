package edu.uade.tpo.ingsist2.facade;

import java.util.ArrayList;

import javax.ejb.Remote;

import edu.uade.tpo.ingsist2.entities.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.entities.vo.RodamientoVO;

@Remote
public interface AdminFacade {

	/*==================================*/
	/*			ABM de Proveedores		*/
	/*==================================*/
	
	public void guardarProveedor(ProveedorVO p);
	
	public void eliminarProveedor(int id);
	
	public ProveedorVO getProveedor(int id);

	public ArrayList<ProveedorVO> getProveedores();
	
	/*==================================*/
	/*			ABM de Rodamientosss		*/
	/*==================================*/
	
	public void guardarRodamiento(RodamientoVO r);
	
	public void eliminarRodamiento(int id);
	
	public RodamientoVO getRodamiento(int id);
	
	public ArrayList<RodamientoVO> getRodamientos();
	
}

package edu.uade.tpo.ingsist2.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import edu.uade.tpo.ingsist2.entities.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.entities.vo.RodamientoVO;
import edu.uade.tpo.ingsist2.model.OrdenDeCompra;
import edu.uade.tpo.ingsist2.session.AdministrarProveedores;
import edu.uade.tpo.ingsist2.session.AdministrarRodamientos;

@Stateless
public class FacadeBean implements Facade {
	
	@EJB
	private AdministrarProveedores adminProve;
	@EJB
	private AdministrarRodamientos adminRod;
	
	public FacadeBean() {
	}

	@Override
	public void guardarProveedor(ProveedorVO p) {
		adminProve.guardarProveedor(p);
	}

	@Override
	public void eliminarProveedor(int id) {
		adminProve.eliminarProveedor(id);
	}

	@Override
	public ProveedorVO getProveedor(int id) {
		return adminProve.getProveedor(id);
	}

	@Override
	public ArrayList<ProveedorVO> getProveedores() {
		return adminProve.getProveedores();
	}

	@Override
	public void guardarRodamiento(RodamientoVO r) {
		adminRod.guardarRodamiento(r);
	}

	@Override
	public void eliminarRodamiento(int id) {
		adminRod.eliminarRodamiento(id);
	}

	@Override
	public RodamientoVO getRodamiento(int id) {
		return adminRod.getRodamiento(id);
	}

	@Override
	public ArrayList<RodamientoVO> getRodamientos() {
		return adminRod.getRodamientos();
	}

	@Override
	public void recibirOrdenDeCompra(OrdenDeCompra oc) {
		// TODO Auto-generated method stub
		
	}
}
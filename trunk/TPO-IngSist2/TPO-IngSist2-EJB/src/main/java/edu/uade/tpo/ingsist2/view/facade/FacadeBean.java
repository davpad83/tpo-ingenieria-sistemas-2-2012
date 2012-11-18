package edu.uade.tpo.ingsist2.view.facade;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import edu.uade.tpo.ingsist2.controllers.AdministrarCotizaciones;
import edu.uade.tpo.ingsist2.controllers.AdministrarProveedores;
import edu.uade.tpo.ingsist2.controllers.AdministrarRodamientos;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.view.vo.RodamientoVO;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;

@Stateless
@WebService 
(name="getCotizacionRodamiento", serviceName="getCotizacionRodamiento")
public class FacadeBean implements Facade {
	
	@EJB
	private AdministrarProveedores adminProve;
	@EJB
	private AdministrarRodamientos adminRod;
	@EJB 
	AdministrarCotizaciones adminCot;
	
	/*===========ÊABM PROVEEDORES ===========*/
	
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

	/*===========ÊABM RODAMIENTOS ===========*/
	
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
	
	/*===========ÊWEB METHODS ===========*/
	
	@Override
	@WebMethod
	public SolicitudCotizacionResponse recibirSolicitudCotizacion (SolicitudCotizacionRequest scr) {
		return adminCot.procesarSolicitudCotizacion(scr);
 	}

}

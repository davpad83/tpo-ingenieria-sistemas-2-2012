package edu.uade.tpo.ingsist2.facade;

import java.util.ArrayList;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;

import edu.uade.tpo.ingsist2.entities.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.entities.vo.RodamientoVO;
import edu.uade.tpo.ingsist2.model.OrdenDeCompra;

@Remote
@WebService(serviceName="CPRFacadeWS")
public interface Facade {

	/*==================================*/
	/*			ABM de Proveedores		*/
	/*==================================*/
	
	public void guardarProveedor(ProveedorVO p);
	
	public void eliminarProveedor(int id);
	
	public ProveedorVO getProveedor(int id);

	public ArrayList<ProveedorVO> getProveedores();
	
	/*==================================*/
	/*			ABM de Rodamientos		*/
	/*==================================*/
	
	public void guardarRodamiento(RodamientoVO r);
	
	public void eliminarRodamiento(int id);
	
	public RodamientoVO getRodamiento(int id);
	
	public ArrayList<RodamientoVO> getRodamientos();
	
	/*==================================*/
	/*			Web Service methods		*/
	/*==================================*/
	
	@WebMethod
	public void recibirOrdenDeCompra(OrdenDeCompra oc);
	
}

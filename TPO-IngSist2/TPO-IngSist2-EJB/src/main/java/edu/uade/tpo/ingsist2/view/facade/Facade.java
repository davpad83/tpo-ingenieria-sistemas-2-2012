package edu.uade.tpo.ingsist2.view.facade;

import java.util.ArrayList;

import javax.ejb.Remote;

import edu.uade.tpo.ingsist2.view.vo.OficinaDeVentaVO;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.view.vo.RodamientoVO;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;

@Remote
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
	/*			ABM de ODV				*/
	/*==================================*/
	
	public void guardarOficinaDeVenta(OficinaDeVentaVO odv);
	
	/*====================================================================*/
	/*   Web Service methods (Solicitud de Cotizaciones de Rodamientos)	  */
	/*====================================================================*/
	
	public SolicitudCotizacionResponse recibirSolicitudCotizacion(SolicitudCotizacionRequest scr);
	
	
	
}

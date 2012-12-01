package edu.uade.tpo.ingsist2.view.bd;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import edu.uade.tpo.ingsist2.view.facade.Facade;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.view.vo.RodamientoVO;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;

public class BusinessDelegate {

	private Facade adminFacade = null;
	
	private static BusinessDelegate instancia = null;

	public static BusinessDelegate getInstancia(){
		if(instancia==null)
			instancia = new BusinessDelegate();
		return instancia;
	}
	
	public BusinessDelegate(){
		conectar();
	}
	
	@SuppressWarnings("unchecked")
	private void conectar() {
		String naming = "TPO-IngSist2-EAR/FacadeBean/remote";

		Hashtable props = new Hashtable();
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");
		
		InitialContext ic;
		try {
			ic = new InitialContext(props);
			adminFacade = (Facade) ic.lookup(naming);
		} catch (NamingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean isConnectionWorking(){
		if(adminFacade!=null)
			return true;
		return false;
	}
	
	public void guardarProveedor(ProveedorVO p){
		adminFacade.guardarProveedor(p);
	}
	
	public void eliminarProveedor(int id){
		adminFacade.eliminarProveedor(id);
	}
	
	public ProveedorVO getProveedor(int id){
		return adminFacade.getProveedor(id);
	}
	
	public ArrayList<ProveedorVO> getProveedores(){
		return adminFacade.getProveedores();
	}

	public RodamientoVO getRodamiento(int idRodamiento) {
		return adminFacade.getRodamiento(idRodamiento);
	}

	public ArrayList<RodamientoVO> getRodamientos() {
		return adminFacade.getRodamientos();
	}

	public void guardarRodamiento(RodamientoVO rvo) {
		adminFacade.guardarRodamiento(rvo);
	}

	public void eliminarRodamiento(int idRodamiento) {
		adminFacade.eliminarRodamiento(idRodamiento);
	}
	
	
	
	// TEST WS
	

	public void generateInitialData(){
		adminFacade.generateInitialData();
	}
	
	public SolicitudCotizacionResponse recibirSolicitudCotizacion(SolicitudCotizacionRequest rq) {
		return adminFacade.recibirSolicitudCotizacion(rq);
	}
	
	
}

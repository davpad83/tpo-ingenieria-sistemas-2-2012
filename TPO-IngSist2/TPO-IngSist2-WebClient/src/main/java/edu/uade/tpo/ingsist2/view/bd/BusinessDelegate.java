package edu.uade.tpo.ingsist2.view.bd;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import edu.uade.tpo.ingsist2.facade.AdminFacade;
import edu.uade.tpo.ingsist2.entities.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.entities.vo.RodamientoVO;

public class BusinessDelegate {

	private AdminFacade adminFacade = null;
	
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
		String naming = "TPO-IngSist2-EAR/AdminFacadeBean/remote";

		Hashtable props = new Hashtable();
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");
		
		InitialContext ic;
		try {
			ic = new InitialContext(props);
			adminFacade = (AdminFacade) ic.lookup(naming);
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
}

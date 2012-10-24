package edu.uade.tpo.ingsist2.view.bd;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

import edu.uade.tpo.ingsist2.entities.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.facade.Facade;

public class BusinessDelegate {

	Facade facade = null;

	public void BusinessDelegate(){
		conectar();
	}
	
	@SuppressWarnings("unchecked")
	private void conectar() {
		String naming = "TPO-IngSis2-EAR/Facade/Remote";

		Hashtable props = new Hashtable();
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		props.put(InitialContext.PROVIDER_URL, "jnp://localhost:8080");
		
		InitialContext ic;
		try {
			ic = new InitialContext(props);
			facade = (Facade) ic.lookup(naming);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void guardarProveedor(ProveedorVO p){
		facade.guardarProveedor(p);
	}
	
	public void eliminarProveedor(int id){
		facade.eliminarProveedor(id);
	}
	
	public ProveedorVO getProveedor(int id){
		return facade.getProveedor(id);
	}
	
	public ArrayList<ProveedorVO> getProveedores(){
		return facade.getProveedores();
	}
}

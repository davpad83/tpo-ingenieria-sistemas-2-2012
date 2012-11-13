package edu.uade.tpo.ingsist2.view.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.uade.tpo.ingsist2.controllers.ListaProveedorController;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodProveedorRequest;

@Stateless
public class MessagesFacadeBean implements MessagesFacade {

	@EJB
	private ListaProveedorController listaProveedor;
	
	public MessagesFacadeBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void agregarListaProveedor(RecepcionRodProveedorRequest lpr) {
		listaProveedor.agregarListaProveedor(lpr);
	}

}

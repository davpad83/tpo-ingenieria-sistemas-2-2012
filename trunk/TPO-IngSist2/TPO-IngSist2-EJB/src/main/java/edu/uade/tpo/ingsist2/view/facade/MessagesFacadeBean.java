package edu.uade.tpo.ingsist2.view.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.uade.tpo.ingsist2.controllers.ListaPreciosController;
import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodProveedorRequest;

@Stateless
public class MessagesFacadeBean implements MessagesFacade {

	@EJB
	private ListaPreciosController listaProveedor;
	
	public MessagesFacadeBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void agregarListaProveedor(ListaPreciosVO lpr) {
		listaProveedor.agregarListaProveedor(lpr);
	}

}

package edu.uade.tpo.ingsist2.view.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.uade.tpo.ingsist2.controllers.ListaPreciosController;
import edu.uade.tpo.ingsist2.controllers.RecepcionRodamientosController;
import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodamientosVO;

@Stateless
public class MessagesFacadeBean implements MessagesFacade {

	@EJB
	private ListaPreciosController listaProveedor;
	
	@EJB
	private RecepcionRodamientosController EnvioProveedor;
	
	public MessagesFacadeBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void agregarListaProveedor(ListaPreciosVO lpr) {
		listaProveedor.agregarListaProveedor(lpr);
	}

	@Override
	public void recibirEnvioProveedor(RecepcionRodamientosVO lpr) {
		EnvioProveedor.recibirEnvioProveedor(lpr);
		
	}

}

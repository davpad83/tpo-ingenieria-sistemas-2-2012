package edu.uade.tpo.ingsist2.view.facade;

import javax.ejb.Remote;

import edu.uade.tpo.ingsist2.view.vo.RecepcionRodProveedorRequest;

@Remote
public interface MessagesFacade {
	
	public void agregarListaProveedor(RecepcionRodProveedorRequest lpr);

}

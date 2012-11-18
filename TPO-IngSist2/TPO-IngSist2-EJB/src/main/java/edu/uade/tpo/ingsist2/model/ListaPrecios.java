package edu.uade.tpo.ingsist2.model;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.ListaPreciosEntity;

@Local
public interface ListaPrecios {

	public void agregarListaProveedor(ListaPreciosEntity lpvo);
	
}

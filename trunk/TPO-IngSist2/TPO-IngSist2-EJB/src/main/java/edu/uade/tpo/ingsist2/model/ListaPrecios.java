package edu.uade.tpo.ingsist2.model;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.ListaPreciosEntity;

@Local
public interface ListaPrecios {

	public void agregarListaPrecios(ListaPreciosEntity lpvo);
	
	public ListaPreciosEntity getListaPrecioPorIdItemLista(int idItemLista);
}

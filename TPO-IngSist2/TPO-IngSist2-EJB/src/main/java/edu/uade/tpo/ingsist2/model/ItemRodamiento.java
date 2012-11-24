package edu.uade.tpo.ingsist2.model;

import javax.ejb.Local;


import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;

@Local
public interface ItemRodamiento {
	
	public void guardarItemRodamientoCotizacion(ItemRodamientoEntity ir);

}

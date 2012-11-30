package edu.uade.tpo.ingsist2.model;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;

@Local
public interface OficinaDeVenta {

	public void guardarOficinaDeVenta(OficinaDeVentaEntity odve);

	public OficinaDeVentaEntity getOficina(int idODV);

	public boolean existe(int idODV);
	
}

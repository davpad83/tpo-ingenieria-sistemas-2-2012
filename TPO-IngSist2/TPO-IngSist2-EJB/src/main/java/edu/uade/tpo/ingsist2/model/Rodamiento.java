package edu.uade.tpo.ingsist2.model;

import java.util.ArrayList;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;

@Local
public interface Rodamiento {

	public void guardarRodamiento(RodamientoEntity r);
	
	public void eliminarRodamiento(int id);
	
	public RodamientoEntity getRodamiento(int id);
	
	public ArrayList<RodamientoEntity> getRodamientos();
	
}

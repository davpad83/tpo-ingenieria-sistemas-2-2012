package edu.uade.tpo.ingsist2.model;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.RemitoEntity;

@Local
public interface Remito {

	public void enviarRemito(RemitoEntity remito, OficinaDeVentaEntity odv);
	
}

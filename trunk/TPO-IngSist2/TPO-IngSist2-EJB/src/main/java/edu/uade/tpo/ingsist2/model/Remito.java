package edu.uade.tpo.ingsist2.model;


import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.RemitoEntity;
import edu.uade.tpo.ingsist2.view.vo.RemitoResponse;

@Local
public interface Remito {

	public void enviarRemito(RemitoEntity remito, OficinaDeVentaEntity odv);

	public void enviarRemito(RemitoResponse remito);
	
}

package edu.uade.tpo.ingsist2.model;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.view.vo.OficinaDeVentaVO;

@Local
public interface OficinaDeVenta {

	public void guardarOficinaDeVenta(OficinaDeVentaVO odv);

	public OficinaDeVentaEntity getOficina(int idODV);

	public boolean existe(int idODV);
	
}

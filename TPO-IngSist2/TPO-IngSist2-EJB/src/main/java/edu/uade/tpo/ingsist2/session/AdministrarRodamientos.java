package edu.uade.tpo.ingsist2.session;

import java.util.ArrayList;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.entities.vo.RodamientoVO;

@Local
public interface AdministrarRodamientos {

	public void guardarRodamiento(RodamientoVO r);
	
	public void eliminarRodamiento(int id);
	
	public RodamientoVO getRodamiento(int id);
	
	public ArrayList<RodamientoVO> getRodamientos();

}

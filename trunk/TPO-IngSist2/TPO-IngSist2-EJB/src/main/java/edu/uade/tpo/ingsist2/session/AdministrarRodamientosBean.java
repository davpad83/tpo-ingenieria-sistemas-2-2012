package edu.uade.tpo.ingsist2.session;

import java.util.ArrayList;

import javax.ejb.Stateless;

import edu.uade.tpo.ingsist2.entities.vo.RodamientoVO;

@Stateless
public class AdministrarRodamientosBean implements AdministrarRodamientos {

    public AdministrarRodamientosBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void guardarRodamiento(RodamientoVO r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarRodamiento(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RodamientoVO getRodamiento(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<RodamientoVO> getRodamientos() {
		// TODO Auto-generated method stub
		return null;
	}

}

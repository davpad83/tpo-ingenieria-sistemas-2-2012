package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.uade.tpo.ingsist2.model.Rodamiento;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.RodamientoVO;

@Stateless
public class AdministrarRodamientosBean implements AdministrarRodamientos {


	@EJB
	private Rodamiento rodamiento;
	
    public AdministrarRodamientosBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void guardarRodamiento(RodamientoVO r) {
		RodamientoEntity rBean = new RodamientoEntity();
		rBean.setVO(r);
		rodamiento.guardarRodamiento(rBean);	
	}

	@Override
	public void eliminarRodamiento(int id) {
		rodamiento.eliminarRodamiento(id);
	}

	@Override
	public RodamientoVO getRodamiento(int id) {
		return rodamiento.getRodamiento(id).getVO();
	}

	@Override
	public ArrayList<RodamientoVO> getRodamientos() {
		return RodamientoEntity.getVOList(rodamiento.getRodamientos());
	}

}

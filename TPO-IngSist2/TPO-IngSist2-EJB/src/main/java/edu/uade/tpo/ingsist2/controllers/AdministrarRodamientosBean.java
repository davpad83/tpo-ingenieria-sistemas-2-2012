package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.Rodamiento;
import edu.uade.tpo.ingsist2.model.entities.EntitiesTablesNames;
import edu.uade.tpo.ingsist2.model.entities.ProveedorEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.RodamientoVO;

@Stateless
public class AdministrarRodamientosBean implements AdministrarRodamientos {

	private static Logger LOGGER = Logger
			.getLogger(AdministrarRodamientosBean.class);

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

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<RodamientoVO> getRodamientos() {
		return RodamientoEntity.getVOList(rodamiento.getRodamientos());
	}

}

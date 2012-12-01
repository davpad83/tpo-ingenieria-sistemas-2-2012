package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.CotizacionBean;
import edu.uade.tpo.ingsist2.model.Rodamiento;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.RodamientoVO;

@Stateless
public class AdministrarRodamientosBean implements AdministrarRodamientos {

	private static final Logger LOGGER = Logger.getLogger(AdministrarRodamientosBean.class);

	@EJB
	private Rodamiento rodamiento;
	
	@Override
	public void guardarRodamiento(RodamientoVO r) {
		RodamientoEntity rBean = new RodamientoEntity();
		rBean.setVO(r);
		
		RodamientoEntity rodEncontrado = getRodamiento(rBean.getCodigoSKF(), rBean.getMarca(), rBean.getPais());
		if(rodEncontrado!=null)
			LOGGER.info("El rodamiento ya existe, omitiendo operacion de agregar.");
		else
			rodamiento.guardarRodamiento(rBean);	
	}

	private RodamientoEntity getRodamiento(String codigoSKF, String marca,
			String pais) {
		return rodamiento.getRodamiento(codigoSKF, marca, pais);
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

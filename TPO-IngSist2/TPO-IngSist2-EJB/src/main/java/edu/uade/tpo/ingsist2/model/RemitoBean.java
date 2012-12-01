package edu.uade.tpo.ingsist2.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.CotizacionEntity;
import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.RemitoEntity;
import edu.uade.tpo.ingsist2.model.util.EnviarMensajeHelper;
import edu.uade.tpo.ingsist2.view.jms.JMSQueuesNames;
import edu.uade.tpo.ingsist2.view.vo.RemitoResponse;

@Stateless
public class RemitoBean implements Remito {

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;
	private OficinaDeVenta ODV;
	
	
	private static final Logger LOGGER = Logger.getLogger(RemitoBean.class);
	
	@Override
	public void enviarRemito(RemitoEntity remito, OficinaDeVentaEntity odv) {
		EnviarMensajeHelper emHelper = new EnviarMensajeHelper(odv.getIp(), odv.getPuerto(), odv.getNombreColaRemito());
		
		LOGGER.info("Enviando remito...");
		emHelper.enviarMensaje(remito.getVO().toXML());
	}

	@Override
	public void enviarRemito(RemitoResponse remito) {
		
		OficinaDeVentaEntity odv = ODV.getOficina(remito.getIdODV());
		EnviarMensajeHelper emHelper = new EnviarMensajeHelper(odv.getIp(), odv.getPuerto(), odv.getNombreColaRemito());
		
		LOGGER.info("Enviando remito...");
		emHelper = new EnviarMensajeHelper("127.0.0.1 IP de ODV", 1099, JMSQueuesNames.ENVIAR_REMITO_QUEUE);
		emHelper.enviarMensaje(remito.toXML());
	}
	
	
	public void  guardarRemito (RemitoResponse remito) {
		RemitoEntity r = new RemitoEntity();
		r.setIdRemito(remito.getIdRemito());
		r.setItemsList(remito.getItems());
		r.setOdv(ODV.getOficina(remito.getIdODV()));
		LOGGER.info("Procesando guardar remito con id " + r.getIdRemito());
		RemitoEntity rGuardado = null;
		try {
             rGuardado = (RemitoEntity) entityManager.merge(r);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al guardar el remito");
			LOGGER.error(e);
		}
            LOGGER.info("Remito guardado con id: " + +rGuardado.getIdRemito());
	}

	@Override
	public void guardarRemito(RemitoEntity rem) {
		LOGGER.info("Procesando guardar remito.");

		RemitoEntity rGuardado = null;
		try {
			rGuardado = (RemitoEntity) entityManager.merge(rem);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al guardar el remito.");
			LOGGER.error(e);
		}
		LOGGER.info("Remito guardado con id: " + rGuardado.getIdRemito());
	}

}
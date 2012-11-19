package edu.uade.tpo.ingsist2.model;

import java.util.Hashtable;

import javax.ejb.Stateless;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.RemitoEntity;
import edu.uade.tpo.ingsist2.model.util.EnviarMensajeHelper;
import edu.uade.tpo.ingsist2.view.jms.JMSQueuesNames;

@Stateless
public class RemitoBean implements Remito {

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	private static final Logger LOGGER = Logger.getLogger(RemitoBean.class);

	@Override
	public void enviarRemito(RemitoEntity remito, OficinaDeVentaEntity odv) {
		EnviarMensajeHelper emHelper = new EnviarMensajeHelper(odv.getIp(), odv.getPuerto(), odv.getNombreColaRemito());
		
		emHelper.enviarMensaje(remito.getVO().toXML());
		
	}

	
	
}

package edu.uade.tpo.ingsist2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
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
		persistirRemito(remito);
		
		OficinaDeVentaEntity odv = ODV.getOficina(remito.getIdODV());
		EnviarMensajeHelper emHelper = new EnviarMensajeHelper(odv.getIp(), odv.getPuerto(), odv.getNombreColaRemito());
		
		LOGGER.info("Enviando remito...");
		emHelper.enviarMensaje(remito.toXML());
	}

	private void  persistirRemito(RemitoResponse remito) {
		RemitoEntity r = new RemitoEntity();
		r.setIdRemito(remito.getIdRemito());
		r.setItemsList(remito.getItems());
		r.setOdv(ODV.getOficina(remito.getIdODV()));
		entityManager.persist(remito);
	}

	
	
	
	
	
	
//	
//	@Override
//	public void enviarRemito(List<OrdenDeCompraEntity> remitosAEnviar) {
//		// creo un nuevo remitoResponse con todos los elementos a enviar
//		
//		List <OficinaDeVentaEntity> ODVs = getODVs(remitosAEnviar);	
//		for(OficinaDeVentaEntity i: ODVs){
//			LOGGER.info("Generando remito para ODV: "+i.getId());
//			
//			RemitoEntity remito = new RemitoEntity();
//			remito.setOdv(i);
//			
//			for(OrdenDeCompraEntity o : remitosAEnviar)
//				if(i.getId()==o.getOdv().getId()){
//					remito.setOrdenDeCompra(o);
//					remito.setItems(o.getItems());
//					
//				}
//			
//		}
//			
//				
//	}
//
//	private List<OficinaDeVentaEntity> getODVs(List<OrdenDeCompraEntity> remitosAEnviar) {
//		LOGGER.info("Analizando que ODVs recibiran stock y remito...");
//		List <OficinaDeVentaEntity> aux = new ArrayList<OficinaDeVentaEntity>();
//		for(OrdenDeCompraEntity i: remitosAEnviar){
//			boolean agregar=true;
//			for(OficinaDeVentaEntity o:aux)
//				if(i.getOdv().getId()==o.getId())
//					agregar=false;
//			if(agregar)
//				 aux.add(i.getOdv());
//		}
//		
//		return aux;
//}
//
//	@SuppressWarnings("unused")
//	private boolean fueProcesada(int id, List <Integer> odvProcesada){
//		Integer idOdv = Integer.valueOf(id);
//		for(Integer i: odvProcesada)
//			if(i==idOdv)
//				return true;
//		return false;
//	}
//	
}



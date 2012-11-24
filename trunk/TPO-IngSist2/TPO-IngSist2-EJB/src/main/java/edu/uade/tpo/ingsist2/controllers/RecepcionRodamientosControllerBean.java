package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;


import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.OrdenDeCompra;
import edu.uade.tpo.ingsist2.model.Remito;
import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.model.entities.RemitoEntity;
import edu.uade.tpo.ingsist2.view.vo.ItemRodamientoVO;
import edu.uade.tpo.ingsist2.view.vo.ItemVO;
import edu.uade.tpo.ingsist2.view.vo.OficinaDeVentaVO;
import edu.uade.tpo.ingsist2.view.vo.OrdenDeCompraVO;
import edu.uade.tpo.ingsist2.view.vo.PedidoAbastecimientoVO;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodamientosVO;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodamientosVO.RodamientoListaVO;
import edu.uade.tpo.ingsist2.view.vo.RemitoResponse;
import edu.uade.tpo.ingsist2.view.vo.RodamientoVO;

/**
 * Session Bean implementation class RecepcionRodamientosController
 */
@Stateless
public class RecepcionRodamientosControllerBean implements RecepcionRodamientosController {

	private static final Logger LOGGER = Logger.getLogger(RecepcionRodamientosControllerBean.class);
	@EJB
	private AdministrarPedidosDeAbastecimiento pedidos;
	@EJB
	private AdministrarRodamientos rodamientos;
	@EJB
	private AdministrarOrdenDeCompra ordenesCompra;
	@EJB
	private OrdenDeCompra ocBean;
	@EJB
	private Remito rBean;
	@Override
	
	public void recibirEnvioProveedor(RecepcionRodamientosVO rodamientos) {
		LOGGER.info("Obteniendo ODVs Asociadas");
		List <OficinaDeVentaVO> ODVs = getODVs(rodamientos);	
		
		LOGGER.info("Procesando recepcion de rodamientos");
		for(OficinaDeVentaVO odv: ODVs){
			LOGGER.info("Preparando para procesar remito de ODV: "+odv.getIdODV());
			
			RemitoResponse remito = new RemitoResponse();
			remito.setIdODV(odv.getIdODV());
			List<ItemVO> items = new ArrayList<ItemVO>();
			
			for(RodamientoListaVO envio: rodamientos.getListaRodVO()){
				if(getOdvo(envio).getIdODV() == odv.getIdODV()){
					LOGGER.info("Procesando envio...");
					ItemVO ivo = procesarEnvio(envio);
					items.add(ivo);
				}
			LOGGER.info("Generando remito para ODV: "+odv.getIdODV());
			remito.setItems(items);
			enviarRemito(remito);
			}
		}
		
		
	}
	
	private void enviarRemito(RemitoResponse remito) {
		// TODO Auto-generated method stub
		
	}

	private ItemVO procesarEnvio(RodamientoListaVO envio) {
		ItemVO item = new ItemVO();
		
		return item ;
	}

	//Obtengo ODVs Asociadas
	private List<OficinaDeVentaVO> getODVs(RecepcionRodamientosVO listaEnvio) {
		List <OficinaDeVentaVO> aux = new ArrayList<OficinaDeVentaVO>();
		
		for(RodamientoListaVO envio: listaEnvio.getListaRodVO()){
			OficinaDeVentaVO odvo = getOdvo(envio);
			
			boolean agregar=true;
			for(OficinaDeVentaVO o:aux)
				if(odvo.getIdODV()==o.getIdODV())
					agregar=false;
			if(agregar)
				 aux.add(odvo);
		}
		
		return aux;
	}
	
	private OficinaDeVentaVO getOdvo(RodamientoListaVO envio){
		PedidoAbastecimientoVO p= pedidos.getPedido(envio.getIdPedidoAbastecimiento());
		OficinaDeVentaVO odvo= p.getOcAsociada().getOdv();
		return odvo;
	}
}
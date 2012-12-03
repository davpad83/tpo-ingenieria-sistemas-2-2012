package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;


import edu.uade.tpo.ingsist2.model.OficinaDeVenta;
import edu.uade.tpo.ingsist2.model.OrdenDeCompra;
import edu.uade.tpo.ingsist2.model.PedidoDeAbastecimiento;
import edu.uade.tpo.ingsist2.model.Remito;
import edu.uade.tpo.ingsist2.model.entities.ItemRemitoEntity;
import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.model.entities.PedidoDeAbastecimientoEntity;
import edu.uade.tpo.ingsist2.model.entities.RemitoEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.ItemRemitoVO;
import edu.uade.tpo.ingsist2.view.vo.ItemRodamientoVO;
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
	private PedidoDeAbastecimiento pedidos;
	@EJB
	private AdministrarRodamientos rodamientos;
	@EJB
	private OrdenDeCompra ordenDeCompra;
	@EJB
	private OficinaDeVenta oficinaVenta;
	
	@EJB
	private Remito rBean;
	
	
	
	@Override	
	public void recibirEnvioProveedor(RecepcionRodamientosVO rodamientos) {
		LOGGER.info("==================PROCESANDO RECEPCION DE RODAMIENTO BEGIN==================");
		LOGGER.info("Obteniendo ODVs Asociadas");
		List <OficinaDeVentaEntity> ODVs = getODVs(rodamientos);	
		
		LOGGER.info("Procesando recepcion de rodamientos");
		for(OficinaDeVentaEntity odv: ODVs){
			LOGGER.info("Preparando para procesar remito de ODV: "+odv.getId());
			
			RemitoEntity remito = new RemitoEntity();
//			RemitoResponse remito = new RemitoResponse();
//			remito.setIdODV(odv.getId());
			remito.setOdv(odv);
			
//			List<ItemRemitoVO> items = new ArrayList<ItemRemitoVO>();
			List<ItemRemitoEntity> items = new ArrayList<ItemRemitoEntity>();
			
			for(RodamientoListaVO envio: rodamientos.getListaRodVO()){
				if(getOdvo(envio).getId() == odv.getId()){
					LOGGER.info("Procesando envio...");
					ItemRemitoEntity ivo = procesarEnvio(envio);
					items.add(ivo);
				}
			LOGGER.info("Generando remito para ODV: "+odv.getId());
			remito.setItems(items);
			rBean.enviarRemito(remito, odv);
//			RemitoEntity r = new RemitoEntity();
//			r.setVO(remito);
			rBean.guardarRemito(remito);
			}
		}
		LOGGER.info("==================PROCESANDO RECEPCION DE RODAMIENTO END==================");
	}
	
	private ItemRemitoEntity procesarEnvio(RodamientoListaVO envio) {
		ItemRemitoEntity item = new ItemRemitoEntity();
		int id= envio.getIdPedidoAbastecimiento();
		PedidoAbastecimientoVO pedido = pedidos.getPedido(id).getVO();
		if(pedido == null)
			LOGGER.info("No se encontro el pedido id:"+id);
		
		//Validar que el rodamiento corresponda al pedido
		LOGGER.info("analizando pedido "+id+" - "+envio.getSKF()+ " " +envio.getMarca()+" " + envio.getPais());

		RodamientoVO rod= pedido.getRodamiento();
		
		if(!rod.equals(envio.getRodamiento()))
				LOGGER.info("No coincide el pedido"+id+" con Rodamiento "+envio.getSKF()+ " " +envio.getMarca()+" " + envio.getPais());
		else	{	
			
			/////******Pedido******\\\\\\
			actualizarPedido(pedido, envio);

			/////******Ordenes de compra******\\\\\\
			int consumido = actualizarItemsOCs(pedido, envio);	
			
			/////******Stock******\\\\\\
			int sobrante=envio.getCantidad();
			if(envio.getCantidad()>0)
				actualizarStock(sobrante, pedido);
			
			/////******Generar Remito de compra******\\\\\\
			
			OrdenDeCompraEntity oc = new OrdenDeCompraEntity();
			oc.setVO(pedido.getOcAsociada());
			item.setOcAsociada(oc);
			item.setCantidaEnviada(consumido);
			RodamientoEntity rodam = new RodamientoEntity();
			rodam.setVO(envio.getRodamiento());
			item.setRodamiento(rodam);
		}
		return item ;
	}
	
	
	private void actualizarStock(int consumo, PedidoAbastecimientoVO pedido) {
		RodamientoVO rod = rodamientos.getRodamiento(pedido.getRodamiento().getId());
		rod.setStock(consumo+ rod.getStock());
		rodamientos.guardarRodamiento(rod);
	}
	
	private int actualizarItemsOCs(PedidoAbastecimientoVO pedido,RodamientoListaVO envio) {
		OrdenDeCompraVO oc = ordenDeCompra.getOrdenDeCompra(pedido.getOcAsociada().getIdOrden()).getVO();	
		int sobrante= envio.getCantidad();//total enviado por el proveedor
		int consumido=0;
		
		for(ItemRodamientoVO ir : oc.getItems()){
			if(ir.getRodamiento() == pedido.getRodamiento()){
				
				int pendientes = ir.getPendientes() - sobrante;//Lo que se debe - lo que llega
			
				if(!(pendientes<0)){//si me llega MENOS o satisfago justo la OC
					sobrante=0;
					ir.setPendientes(pendientes);
				}
				
				if(pendientes < 0){//sobra del envio, puedo guardar stock
				sobrante=pendientes*(-1);
				ir.setPendientes(0);
				}
				
				consumido = envio.getCantidad()-sobrante;
				envio.setCantidad(sobrante);
			}
		}
		
		OrdenDeCompraEntity oco = new OrdenDeCompraEntity() ;
		OrdenDeCompraVO ovo = ordenDeCompra.getOrdenDeCompra(pedido.getOcAsociada().getIdOrden()).getVO();
		oco.setVO(ovo);
		ordenDeCompra.verificarPendientes(oco);
		ordenDeCompra.guardarOrdenDeCompra(oco);
		return consumido;
	}
	
	
	
	private void actualizarPedido(PedidoAbastecimientoVO pedido,
			RodamientoListaVO envio) {
		int resto = pedido.getCantidadPendiente() - envio.getCantidad();
		pedido.setCantidadPendiente(resto);
		if(resto == 0)
			
		if(resto < 0)
			LOGGER.error("Inconsistencias entre la cantidad recibida y la pedida");
		
		PedidoDeAbastecimientoEntity pae = new PedidoDeAbastecimientoEntity();
		pae.setVO(pedido);
		pedidos.guardarPedido(pae);
	}
	
	
	
	//Obtengo ODVs Asociadas
	private List<OficinaDeVentaEntity> getODVs(RecepcionRodamientosVO listaEnvio) {
		List <OficinaDeVentaEntity> aux = new ArrayList<OficinaDeVentaEntity>();
		
		for(RodamientoListaVO envio: listaEnvio.getListaRodVO()){
		OficinaDeVentaEntity odvo = getOdvo(envio);
			
			boolean agregar=true;
			for(OficinaDeVentaEntity o:aux){
				if(odvo.getId()==o.getId()){
					agregar=false;
				}
				if(agregar){
					aux.add(odvo);			
				}				 
			}
		}
		
		return aux;
	}
	
	private OficinaDeVentaEntity getOdvo(RodamientoListaVO envio){
		PedidoDeAbastecimientoEntity p = pedidos.getPedido(envio.getIdPedidoAbastecimiento());
		if(p!=null){
			OficinaDeVentaEntity odvo = p.getOcAsociada().getOdv();
			return odvo;			
		}
		return null;		
	}
}
package edu.uade.tpo.ingsist2.controllers;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;


import edu.uade.tpo.ingsist2.model.OrdenDeCompra;
import edu.uade.tpo.ingsist2.model.Remito;
import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.model.entities.RemitoEntity;
import edu.uade.tpo.ingsist2.view.vo.ItemRodamientoVO;
import edu.uade.tpo.ingsist2.view.vo.OrdenDeCompraVO;
import edu.uade.tpo.ingsist2.view.vo.PedidoAbastecimientoVO;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodamientosVO;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodamientosVO.RodamientoListaVO;
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
		
		//Procesar recepcion
		for(RodamientoListaVO r: rodamientos.getListaRodVO()){
			procesarEnvio(r);
		}
	}
	
	private void procesarEnvio(RodamientoListaVO envio) {
		
		LOGGER.info("Procesando recepcion de rodamientos");
		//Buscar el Pedido de Abastecimiento
		int id= envio.getIdPedidoAbastecimiento();
		PedidoAbastecimientoVO pedido = pedidos.getPedido(id);
		
		//Validar que el rodamiento corresponda al pedido
		RodamientoVO rod= pedido.getRodamiento();
		if(rod.equals(envio.getRodamiento())){
			
			//En caso que coincida, se procede a completar el pedido.
			int recibido = envio.getCantidad();
			int pendiente= pedido.getCantidadPendiente(); 
			int diferencia =  pendiente - recibido;
			
			if(diferencia > 0 ){ //me llega menos de lo que necesito
				pedido.setCantidadPendiente(diferencia);
				pedidos.guardarPedido(pedido);
				

			}else{
				//el pedido queda satisfecho
				pedido.setCantidadPendiente(0);
				pedido.setRecibido(true);

				OrdenDeCompraVO oc = ordenesCompra.getOrdenCompra(pedido.getOcAsociada().getIdOrden());
				
				//Actualizo la cantidad de items para el item de la OC
				for(ItemRodamientoVO ir : oc.getItems()){
					if(ir.getRodamiento() == pedido.getRodamiento()){
						ir.setCantidad(0);
					}
				}
				ordenesCompra.guardarOrdenCompra(oc);
				
				OrdenDeCompraEntity oce = new OrdenDeCompraEntity();
				oce.setVO(oc);
				
				//En caso que esten todos los items completos, enviar remito
				ocBean.verificarPendientes(oce);
				if(oce.getEstado().equals("Completa")){
					//Genero Remito para la OC asociada al pedido
				OficinaDeVentaEntity ove = oce.getOdv();
				RemitoEntity re= new RemitoEntity();
				re.setItems(oce.getItems());
				re.setOdv(ove);
				re.setOrdenDeCompra(oce);
				rBean.enviarRemito(re, ove);
				}
				
				
				if (diferencia < 0 ){//recibo mas de lo que necesito
					//Actualizo Stock con el sobrante
					diferencia = diferencia*(-1);
					rod.setStock(rod.getStock()+diferencia);
					rodamientos.guardarRodamiento(rod);
				}
			}
			pedidos.guardarPedido(pedido);
		}
	}

	@Override
	public void EnviarRemito(int idoc) {
		// TODO Auto-generated method stub
		
	}

}

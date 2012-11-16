package edu.uade.tpo.ingsist2.controllers;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;


import edu.uade.tpo.ingsist2.model.OrdenDeCompra;
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
	private AdministrarPedidosDeAbastecimiento pedidos;
	private AdministrarRodamientos rodamientos;
	
	@Override
	public void recibirEnvioProveedor(RecepcionRodamientosVO rodamientos) {
		
		//Procesar recepcion
		for(RodamientoListaVO r: rodamientos.getListaRodVO()){
			procesarEnvio(r);
		}
	}
	
	

	private void procesarEnvio(RodamientoListaVO envio) {
		
		//Buscar el Pedido de Abastecimiento
		int id= envio.getIdPedidoAbastecimiento();
		PedidoAbastecimientoVO pedido = pedidos.getPedido(id);
		
		//Validar que el rodamiento corresponda al pedido
		RodamientoVO rod= pedido.getRodamiento().getVO();
		if(rod.equals(envio.getRodamiento())){
			
			//En caso que coincida, se procede a completar el pedido.
			int recibido = envio.getCantidad();
			int pendiente= pedido.getCantidadPendiente(); 
			int diferencia =  pendiente - recibido;
			
			if(diferencia > 0 ){ //me llega menos de lo que necesito
				pedido.setCantidadPendiente(diferencia);

			}else{
				//el pedido queda satisfecho
				pedido.setCantidadPendiente(0);
				pedido.setRecibido(true);
				
				//Genero Remito para la OC asociada al pedido
				EnviarRemito(pedido.getOcAsociada());
				
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
	public void EnviarRemito(OrdenDeCompra oc) {
		//actualizo el estado de la OC
		
		
		//genero y envio el remito
		
	}
}

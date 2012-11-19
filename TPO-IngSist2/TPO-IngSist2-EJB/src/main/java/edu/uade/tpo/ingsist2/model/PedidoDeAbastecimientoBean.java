package edu.uade.tpo.ingsist2.model;

import javax.ejb.Stateless;

import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.model.entities.PedidoDeAbastecimientoEntity;
import edu.uade.tpo.ingsist2.view.vo.PedidoAbastecimientoVO;

@Stateless
public class PedidoDeAbastecimientoBean implements PedidoDeAbastecimiento {

	@Override
	public void generarPedidoAbastecimiento(OrdenDeCompraEntity oc, ItemRodamientoEntity ire,
			int stockRodamiento) {

		PedidoDeAbastecimientoEntity pedido = new PedidoDeAbastecimientoEntity();
		pedido.setCantidadPedida(stockRodamiento);
		pedido.setCantidadPendiente(stockRodamiento);
		pedido.setOcAsociada(oc);
		pedido.setProveedor(ire.getCotizacion().getLista().getProveedor());
		pedido.setRodamiento(ire.getCotizacion().getRodamiento());
		pedido.setRecibido(false);
		
		
	}


	
}

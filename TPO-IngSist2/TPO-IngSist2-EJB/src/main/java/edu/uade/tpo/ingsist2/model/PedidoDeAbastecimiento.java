package edu.uade.tpo.ingsist2.model;

import java.util.ArrayList;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.model.entities.PedidoDeAbastecimientoEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.RodamientoVO;

@Local
public interface PedidoDeAbastecimiento {

	public PedidoDeAbastecimientoEntity generarPedidoAbastecimiento(
			OrdenDeCompraEntity oc, ItemRodamientoEntity ire,
			int stockRodamiento);

	public PedidoDeAbastecimientoEntity guardarPedido(PedidoDeAbastecimientoEntity p);

	public void eliminarPedido(int id);

	public PedidoDeAbastecimientoEntity getPedido(int id);

	public ArrayList<PedidoDeAbastecimientoEntity> getPedidos();

	public void enviarPedido(PedidoDeAbastecimientoEntity pedido);

	public boolean validarRodamientoPedido(RodamientoEntity rodamiento,
			int idPedidoAbastecimiento);
}

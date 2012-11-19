package edu.uade.tpo.ingsist2.model;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;

@Local
public interface PedidoDeAbastecimiento {

	public void generarPedidoAbastecimiento(OrdenDeCompraEntity oc, ItemRodamientoEntity ire,
			int stockRodamiento);

}

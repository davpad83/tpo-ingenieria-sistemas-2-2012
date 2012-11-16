package edu.uade.tpo.ingsist2.view.vo;

import java.util.List;
import edu.uade.tpo.ingsist2.model.OficinaDeVenta;
import edu.uade.tpo.ingsist2.model.OrdenDeCompra;
import edu.uade.tpo.ingsist2.model.entities.ItemRodamiento;

public class RemitoVO {
private int idRemito;
	private OrdenDeCompraVO ordenDeCompra;
	private OficinaDeVentaVO odv;
	private List<ItemRodamiento> items;
	
	
	public int getIdRemito() {
		return idRemito;
	}
	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}
	public OrdenDeCompraVO getOrdenDeCompra() {
		return ordenDeCompra;
	}
	public void setOrdenDeCompra(OrdenDeCompraVO ordenDeCompra) {
		this.ordenDeCompra = ordenDeCompra;
	}
	public OficinaDeVentaVO getOdv() {
		return odv;
	}
	public void setOdv(OficinaDeVentaVO odv) {
		this.odv = odv;
	}
	public List<ItemRodamiento> getItems() {
		return items;
	}
	public void setItems(List<ItemRodamiento> items) {
		this.items = items;
	}
	
	
	
}

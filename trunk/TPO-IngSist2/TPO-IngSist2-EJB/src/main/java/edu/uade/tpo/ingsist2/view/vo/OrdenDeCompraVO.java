package edu.uade.tpo.ingsist2.view.vo;

import java.util.List;

import javax.persistence.OneToMany;

import edu.uade.tpo.ingsist2.model.OficinaDeVenta;
import edu.uade.tpo.ingsist2.model.entities.ItemRodamiento;

public class OrdenDeCompraVO {
	private int idOrden;
	private String estado;
	private OficinaDeVenta odv;
	private List<ItemRodamiento> items;
	public int getIdOrden() {
		return idOrden;
	}
	public void setIdOrden(int idOrden) {
		this.idOrden = idOrden;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public OficinaDeVenta getOdv() {
		return odv;
	}
	public void setOdv(OficinaDeVenta odv) {
		this.odv = odv;
	}
	public List<ItemRodamiento> getItems() {
		return items;
	}
	public void setItems(List<ItemRodamiento> items) {
		this.items = items;
	}
	
	
}

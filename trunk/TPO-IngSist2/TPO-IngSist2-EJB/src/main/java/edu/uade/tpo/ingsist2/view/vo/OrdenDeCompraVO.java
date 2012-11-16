package edu.uade.tpo.ingsist2.view.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;

public class OrdenDeCompraVO {
	private int idOrden;
	private String estado;
	private OficinaDeVentaEntity odv;
	private List<ItemRodamientoEntity> items;

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

	public OficinaDeVentaEntity getOdv() {
		return odv;
	}

	public void setOdv(OficinaDeVentaEntity odv) {
		this.odv = odv;
	}

	public ArrayList<ItemRodamientoEntity> getItems() {
		return (ArrayList<ItemRodamientoEntity>) items;
	}

	public void setItems(List<ItemRodamientoEntity> items2) {
		this.items = items2;
	}

}

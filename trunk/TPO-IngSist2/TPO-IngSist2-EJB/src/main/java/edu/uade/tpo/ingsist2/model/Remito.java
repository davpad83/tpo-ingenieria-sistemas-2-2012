package edu.uade.tpo.ingsist2.model;

import java.util.List;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.model.entities.ItemLista;
import edu.uade.tpo.ingsist2.model.entities.ItemRodamiento;

@Entity
public class Remito {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int idRemito;
	
	@ManyToOne
	private OrdenDeCompra ordenDeCompra;
	
	@ManyToOne
	private OficinaDeVenta odv;
	
	@OneToMany
	private List<ItemRodamiento> items;
	
	public Remito() {
		super();
	}

	public int getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}

	public OrdenDeCompra getOrdenDeCompra() {
		return ordenDeCompra;
	}

	public void setOrdenDeCompra(OrdenDeCompra ordenDeCompra) {
		this.ordenDeCompra = ordenDeCompra;
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

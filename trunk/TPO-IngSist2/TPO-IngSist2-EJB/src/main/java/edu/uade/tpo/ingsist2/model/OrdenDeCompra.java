package edu.uade.tpo.ingsist2.model;


import java.util.List;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.model.entities.ItemRodamiento;

@Entity
public class OrdenDeCompra {
	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private int idOrden;
	private String estado;
	private OficinaDeVenta odv;
	
	@OneToMany
	private List<ItemRodamiento> items;
	
	
}

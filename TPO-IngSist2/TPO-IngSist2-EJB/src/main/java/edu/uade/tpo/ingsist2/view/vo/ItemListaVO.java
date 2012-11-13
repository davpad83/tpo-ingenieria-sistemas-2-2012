package edu.uade.tpo.ingsist2.view.vo;

import edu.uade.tpo.ingsist2.model.entities.Rodamiento;

public class ItemListaVO {

	private int id;
	private Rodamiento rodamiento;
	private float precio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Rodamiento getRodamiento() {
		return rodamiento;
	}

	public void setRodamiento(Rodamiento rodamiento) {
		this.rodamiento = rodamiento;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
}

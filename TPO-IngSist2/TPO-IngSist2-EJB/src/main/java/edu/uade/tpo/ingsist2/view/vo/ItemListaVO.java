package edu.uade.tpo.ingsist2.view.vo;

import java.io.Serializable;

public class ItemListaVO implements Serializable{

	private static final long serialVersionUID = 3748744848690820794L;
	
	private int id;
	private RodamientoVO rodamiento;
	private float precio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RodamientoVO getRodamiento() {
		return rodamiento;
	}

	public void setRodamiento(RodamientoVO rodamiento) {
		this.rodamiento = rodamiento;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
}

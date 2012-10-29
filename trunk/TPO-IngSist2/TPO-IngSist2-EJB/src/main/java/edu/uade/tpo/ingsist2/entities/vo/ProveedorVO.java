package edu.uade.tpo.ingsist2.entities.vo;

import java.util.Random;

public class ProveedorVO {

	private int id;
	private String cuit;
	private String nombre;
	
	public ProveedorVO() {
		//empty
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCuit() {
		return cuit;
	}
	
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getRandomId() {
		return new Random().nextInt(10);
	}
}

package edu.uade.tpo.ingsist2.entities.vo;

public class ProveedorVO {

	private int id;
	private String cuit;
	private String nombre;
	
	public ProveedorVO() {
		super();
	}

	public ProveedorVO(int id, String cuit, String nombre) {
		this.id = id;
		this.cuit = cuit;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
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
}

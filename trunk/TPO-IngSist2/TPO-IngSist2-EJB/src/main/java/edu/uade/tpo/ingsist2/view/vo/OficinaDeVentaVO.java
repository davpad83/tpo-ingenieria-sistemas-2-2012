package edu.uade.tpo.ingsist2.view.vo;

import com.thoughtworks.xstream.XStream;

public class OficinaDeVentaVO {
	private int idODV;
	private String direccion;
	private String nombre;

	public int getIdODV() {
		return idODV;
	}

	public void setIdODV(int idODV) {
		this.idODV = idODV;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static XStream setXMLParameters(XStream xs, boolean omitId) {
		xs.alias("OficinaDeVenta", OficinaDeVentaVO.class);
		return xs;
	}
}

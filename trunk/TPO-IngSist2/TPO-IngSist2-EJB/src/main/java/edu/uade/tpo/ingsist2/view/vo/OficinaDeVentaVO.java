package edu.uade.tpo.ingsist2.view.vo;

import com.thoughtworks.xstream.XStream;

public class OficinaDeVentaVO {
	private int idODV;
	private String direccion;
	private String nombre;
	private String ip;
	private int puerto;
	private String nombreColaRemito;

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
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public String getNombreColaRemito() {
		return nombreColaRemito;
	}

	public void setNombreColaRemito(String nombreColaRemito) {
		this.nombreColaRemito = nombreColaRemito;
	}

	public static XStream setXMLParameters(XStream xs, boolean omitId) {
		xs.alias("OficinaDeVenta", OficinaDeVentaVO.class);
		return xs;
	}
}

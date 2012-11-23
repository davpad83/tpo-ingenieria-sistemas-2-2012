package edu.uade.tpo.ingsist2.view.vo;

/**
 * Este itemVO es usado por RemitoResponse y SolicitudCompraRequest porque se
 * usa para la integracion con la ODV. Como ambos "items" utilizan los mismos
 * tipos de datos se puede utilizar un item generico para poder interpretar los
 * XML de entrada y generar de salida. Estos se parsean en cada clase con
 * XStream.
 * 
 * @author matiasfavale
 * 
 */

public class ItemVO {

	private int id;
	private String SKF;
	private String pais;
	private String marca;
	private int cantidad;
	private float precio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSKF() {
		return SKF;
	}

	public void setSKF(String sKF) {
		SKF = sKF;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
}

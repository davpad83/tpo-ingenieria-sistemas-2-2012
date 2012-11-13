package edu.uade.tpo.ingsist2.view.vo;

public class CondicionVentaVO {

	private int idCondicion;
	private float interes;
	private String tipo;
	private int cantidadDiasPago;
	private float descuento;

	public int getIdCondicion() {
		return idCondicion;
	}

	public void setIdCondicion(int idCondicion) {
		this.idCondicion = idCondicion;
	}

	public float getInteres() {
		return interes;
	}

	public void setInteres(float interes) {
		this.interes = interes;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCantidadDiasPago() {
		return cantidadDiasPago;
	}

	public void setCantidadDiasPago(int cantidadDiasPago) {
		this.cantidadDiasPago = cantidadDiasPago;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

}

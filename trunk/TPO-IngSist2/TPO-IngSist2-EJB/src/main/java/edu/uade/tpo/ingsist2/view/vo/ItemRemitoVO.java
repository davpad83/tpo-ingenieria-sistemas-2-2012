package edu.uade.tpo.ingsist2.view.vo;

import java.io.Serializable;

public class ItemRemitoVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idordendecompra;
	private String skf;
	private String pais;
	private String marca;
	private int cantidad;

	public String getSKF() {
		return skf;
	}

	public void setSKF(String sKF) {
		skf = sKF;
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

	public void setRodamiento(RodamientoVO rodamiento) {
		this.marca=rodamiento.getMarca();
		this.pais=rodamiento.getPais();
		this.skf=rodamiento.getCodigoSKF();
	}

	public void setIdOrdenDeCompra(int idordendecompra) {
		this.idordendecompra = idordendecompra;
	}

	public int getIdOrdenDeCompra() {
		return idordendecompra;
	}
	
}

package edu.uade.tpo.ingsist2.entities.vo;

import java.io.Serializable;

public class RodamientoVO implements Serializable {
	
	private static final long serialVersionUID = 5442056498221211003L;
	
	private int id;
	private String codigoSKF;
	private String marca;
	private String pais;
	private String caracteristica;
	private int stock;
	
	public RodamientoVO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoSKF() {
		return codigoSKF;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public String getPais() {
		return pais;
	}
	
	public String getCaracteristica() {
		return caracteristica;
	}
	
	public int getStock(){
		return stock;
	}

	public void setCodigoSKF(String codigoSKF) {
		this.codigoSKF = codigoSKF;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}

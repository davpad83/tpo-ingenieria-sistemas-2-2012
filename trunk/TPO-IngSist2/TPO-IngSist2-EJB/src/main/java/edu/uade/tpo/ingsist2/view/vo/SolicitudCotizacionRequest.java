package edu.uade.tpo.ingsist2.view.vo;

public class SolicitudCotizacionRequest {
	
	private int idPedidoCotizacion;
	private int idODV;
	private String SKF;
	private String marca;
	private String pais;
	private int cantidad;
	
	public int getIdPedidoCotizacion() {
		return idPedidoCotizacion;
	}
	public void setIdPedidoCotizacion(int idPedidoCotizacion) {
		this.idPedidoCotizacion = idPedidoCotizacion;
	}
	public int getIdODV() {
		return idODV;
	}
	public void setIdODV(int idODV) {
		this.idODV = idODV;
	}
	public String getSKF() {
		return SKF;
	}
	public void setSKF(String sKF) {
		SKF = sKF;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}

package edu.uade.tpo.ingsist2.view.vo;

import java.util.Date;
import java.util.List;

public class SolicitudCotizacionResponse {
	
	private int idPedidoCotizacion;
	private int idODV;
	private List<RodamientoCotizado> rodamientosCotizados;

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
	
	public class RodamientoCotizado {
		private String SKF;
		private String marca;
		private String pais;
		private double precio;
		private int enStock;
		private String tiempoEstimadoEntrega;
		private Date fechaInicio;
		private Date fechaFin;
		
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
		public double getPrecio() {
			return precio;
		}
		public void setPrecio(double precio) {
			this.precio = precio;
		}
		public int getEnStock() {
			return enStock;
		}
		public void setEnStock(int enStock) {
			this.enStock = enStock;
		}
		public String getTiempoEstimadoEntrega() {
			return tiempoEstimadoEntrega;
		}
		public void setTiempoEstimadoEntrega(String tiempoEstimadoEntrega) {
			this.tiempoEstimadoEntrega = tiempoEstimadoEntrega;
		}
		public Date getFechaInicio() {
			return fechaInicio;
		}
		public void setFechaInicio(Date fechaInicio) {
			this.fechaInicio = fechaInicio;
		}
		public Date getFechaFin() {
			return fechaFin;
		}
		public void setFechaFin(Date fechaFin) {
			this.fechaFin = fechaFin;
		}
	}
}

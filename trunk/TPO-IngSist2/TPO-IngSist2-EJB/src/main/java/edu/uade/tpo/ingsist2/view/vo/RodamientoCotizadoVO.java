package edu.uade.tpo.ingsist2.view.vo;

import java.util.Date;

public class RodamientoCotizadoVO {
		private String SKF;
		private String marca;
		private String pais;
		private float precioCotizado;
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
		public float getPrecioCotizado() {
			return precioCotizado;
		}
		public void setPrecioCotizado(float precioCotizado) {
			this.precioCotizado = precioCotizado;
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

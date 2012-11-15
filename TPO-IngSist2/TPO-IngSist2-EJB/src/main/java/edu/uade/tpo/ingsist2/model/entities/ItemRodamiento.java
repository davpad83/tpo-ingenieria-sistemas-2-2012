package edu.uade.tpo.ingsist2.model.entities;

import javax.persistence.OneToOne;

public class ItemRodamiento {
	private int cantidad;
	private int pendientes;
	@OneToOne
	private Rodamiento rodamiento;
	@OneToOne
	private Cotizacion cotizacion;
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getPendientes() {
		return pendientes;
	}
	public void setPendientes(int pendientes) {
		this.pendientes = pendientes;
	}
	

	public Rodamiento getRodamiento() {
		return rodamiento;
	}
	public void setRodamiento(Rodamiento rodamiento) {
		this.rodamiento = rodamiento;
	}
	public Cotizacion getIdPedidoDeCotizacion() {
		return cotizacion;
	}
	public void setIdPedidoDeCotizacion(Cotizacion idPedidoDeCotizacion) {
		this.cotizacion = idPedidoDeCotizacion;
	}
	
	
}

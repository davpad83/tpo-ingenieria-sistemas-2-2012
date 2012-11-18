package edu.uade.tpo.ingsist2.view.vo;

public class ItemRodamientoVO {
	private int id;
	private int cantidad;
	private int pendientes;
	private RodamientoVO rodamiento;
	private CotizacionVO cotizacion;

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

	public RodamientoVO getRodamiento() {
		return rodamiento;
	}

	public void setRodamiento(RodamientoVO rodamiento) {
		this.rodamiento = rodamiento;
	}

	public CotizacionVO getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(CotizacionVO cotizacion) {
		this.cotizacion = cotizacion;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}

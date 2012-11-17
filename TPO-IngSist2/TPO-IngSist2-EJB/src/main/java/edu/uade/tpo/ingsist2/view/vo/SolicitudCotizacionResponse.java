package edu.uade.tpo.ingsist2.view.vo;

import java.io.Serializable;
import java.util.List;


public class SolicitudCotizacionResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idPedidoCotizacion;
	private int idODV;
	
	private List<RodamientoCotizadoVO> rodamientosCotizados;

	
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
		
	public List<RodamientoCotizadoVO> getRodamientosCotizados() {
		return rodamientosCotizados;
	}
	public void setRodamientosCotizados(
			List<RodamientoCotizadoVO> rodamientosCotizados) {
		this.rodamientosCotizados = rodamientosCotizados;
	}


}

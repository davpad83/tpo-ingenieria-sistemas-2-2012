package edu.uade.tpo.ingsist2.view.vo;

import java.util.Date;
import java.util.Map;

public class CotizacionVO {
	private int id;
	private int idPedidoCotizacion;
	private OficinaDeVentaVO odv;
	
	private Map<RodamientoVO, ListaPreciosVO> rodamiento;
	
	private Date vencimiento;
	private Date fecha;
	private String tiempoEntrega;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPedidoCotizacion() {
		return idPedidoCotizacion;
	}
	public void setIdPedidoCotizacion(int idPedidoCotizacion) {
		this.idPedidoCotizacion = idPedidoCotizacion;
	}
	public OficinaDeVentaVO getOdv() {
		return odv;
	}
	public void setOdv(OficinaDeVentaVO odv) {
		this.odv = odv;
	}
	public Map<RodamientoVO, ListaPreciosVO> getRodamiento() {
		return rodamiento;
	}
	public void setRodamiento(Map<RodamientoVO, ListaPreciosVO> rodamiento) {
		this.rodamiento = rodamiento;
	}
	public Date getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTiempoEntrega() {
		return tiempoEntrega;
	}
	public void setTiempoEntrega(String tiempoEntrega) {
		this.tiempoEntrega = tiempoEntrega;
	}
	
}

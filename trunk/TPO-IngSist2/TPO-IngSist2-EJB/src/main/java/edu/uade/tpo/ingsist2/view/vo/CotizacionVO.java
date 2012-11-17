package edu.uade.tpo.ingsist2.view.vo;

import java.util.Date;
import edu.uade.tpo.ingsist2.model.entities.ListaPreciosEntity;
import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;

public class CotizacionVO {
	
	private int id;
	private int idPedidoCotizacion;
	private OficinaDeVentaEntity odv;
	private RodamientoEntity rodamiento;
	private ListaPreciosEntity lista;
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
	public OficinaDeVentaEntity getOdv() {
		return odv;
	}
	public void setOdv(OficinaDeVentaEntity odv) {
		this.odv = odv;
	}

	public RodamientoEntity getRodamiento() {
		return rodamiento;
	}
	public void setRodamiento(RodamientoEntity rodamiento) {
		this.rodamiento = rodamiento;
	}
	public ListaPreciosEntity getLista() {
		return lista;
	}
	public void setLista(ListaPreciosEntity lista) {
		this.lista = lista;
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

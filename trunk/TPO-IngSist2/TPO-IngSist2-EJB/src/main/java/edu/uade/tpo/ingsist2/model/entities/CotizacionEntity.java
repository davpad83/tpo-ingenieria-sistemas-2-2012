package edu.uade.tpo.ingsist2.model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cotizacion")
public class CotizacionEntity {

	@Id
	private int id;
	private int idPedidoCotizacion;
	private OficinaDeVentaEntity odv;
	
	private Map<RodamientoEntity, ListaPreciosEntity> rodamiento;
	
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
	public Map<RodamientoEntity, ListaPreciosEntity> getRodamiento() {
		return rodamiento;
	}
	public void setRodamiento(Map<RodamientoEntity, ListaPreciosEntity> rodamiento) {
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
	
	public CotizacionEntity getVO(){
		CotizacionEntity pvo = new CotizacionEntity();
		pvo.setFecha(this.fecha);
		pvo.setId(this.id);
		pvo.setIdPedidoCotizacion(this.idPedidoCotizacion);
		pvo.setOdv(this.odv);
		pvo.setRodamiento(this.rodamiento);
		pvo.setTiempoEntrega(this.tiempoEntrega);
		pvo.setVencimiento(this.vencimiento);
		return pvo;
	}
	
	public void setVO(CotizacionEntity p){
		this.fecha = p.getFecha();
		this.id = p.getId();
		this.idPedidoCotizacion = p.getIdPedidoCotizacion();
		this.odv = p.getOdv();
		this.rodamiento = p.getRodamiento();
		this.tiempoEntrega = p.getTiempoEntrega();
		this.vencimiento = p.getVencimiento();
	}

	public static ArrayList<CotizacionEntity> getVOList(ArrayList<CotizacionEntity> ps) {
			ArrayList<CotizacionEntity> pVoList = new ArrayList<CotizacionEntity>();
			for(CotizacionEntity p: ps)
				pVoList.add(p.getVO());
			return pVoList;
	}
	
	
}

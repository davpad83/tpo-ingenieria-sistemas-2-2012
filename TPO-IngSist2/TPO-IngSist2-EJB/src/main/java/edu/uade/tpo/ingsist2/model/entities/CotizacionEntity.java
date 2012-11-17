package edu.uade.tpo.ingsist2.model.entities;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.uade.tpo.ingsist2.view.vo.CotizacionVO;

@Entity
@Table(name=EntitiesTablesNames.COTIZACION)
public class CotizacionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int idPedidoCotizacion;
	
	@ManyToOne
	private OficinaDeVentaEntity odv;
	
	@ManyToOne
	private RodamientoEntity rodamiento;
	
	@ManyToOne
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
	
	
	public CotizacionVO getVO(){
		CotizacionVO pvo = new CotizacionVO();
		pvo.setFecha(this.fecha);
		pvo.setId(this.id);
		
		//FALTA HACER EL VO POR EL MAP RODAMIENTO-PRECIO
		//pvo.setRodamiento(this.rodamiento);
		pvo.setTiempoEntrega(this.tiempoEntrega);
		pvo.setVencimiento(this.vencimiento);
		return pvo;
	}
	
	public void setVO(CotizacionVO p){
		this.fecha = p.getFecha();
		this.idPedidoCotizacion = p.getIdPedidoCotizacion();
		this.odv = p.getOdv();
		this.rodamiento = p.getRodamiento();
		this.lista = p.getLista();
		this.tiempoEntrega = p.getTiempoEntrega();
		this.vencimiento = p.getVencimiento();
	}

	public static ArrayList<CotizacionVO> getVOList(ArrayList<CotizacionEntity> ps) {
			ArrayList<CotizacionVO> pVoList = new ArrayList<CotizacionVO>();
			for(CotizacionEntity p: ps)
			pVoList.add(p.getVO());
			return pVoList;
	}
	
	
}

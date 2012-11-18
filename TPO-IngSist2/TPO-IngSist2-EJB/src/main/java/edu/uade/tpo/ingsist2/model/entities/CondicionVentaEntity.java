package edu.uade.tpo.ingsist2.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.view.vo.CondicionVentaVO;

@Entity
@Table(name=EntitiesTablesNames.CONDICION_VENTA)
public class CondicionVentaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCondicion;
	private float interes;
	private String tipo;
	private int cantidadDiasPago;
	private float descuento;
	
	@ManyToOne
	private ListaPreciosEntity listaPrecio;

	public int getIdCondicion() {
		return idCondicion;
	}

	public void setIdCondicion(int idCondicion) {
		this.idCondicion = idCondicion;
	}

	public float getInteres() {
		return interes;
	}

	public void setInteres(float interes) {
		this.interes = interes;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCantidadDiasPago() {
		return cantidadDiasPago;
	}

	public void setCantidadDiasPago(int cantidadDiasPago) {
		this.cantidadDiasPago = cantidadDiasPago;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public CondicionVentaVO getVO() {
		CondicionVentaVO cvvo = new CondicionVentaVO();
		cvvo.setIdCondicion(this.idCondicion);
		cvvo.setCantidadDiasPago(this.cantidadDiasPago);
		cvvo.setDescuento(this.descuento);
		cvvo.setInteres(this.interes);
		cvvo.setTipo(this.tipo);
		return cvvo;
	}

	public void setVO(CondicionVentaVO cvvo) {
		this.cantidadDiasPago = cvvo.getCantidadDiasPago();
		this.idCondicion = cvvo.getIdCondicion();
		this.descuento = cvvo.getDescuento();
		this.interes = cvvo.getInteres();
		this.tipo = cvvo.getTipo();
	}

	public static List<CondicionVentaVO> getVOList(List<CondicionVentaEntity> lcv) {
		List<CondicionVentaVO> lcvvo = new ArrayList<CondicionVentaVO>();
		for(CondicionVentaEntity cv : lcv)
		lcvvo.add(cv.getVO());
		return lcvvo;
	}
	
	public static List<CondicionVentaEntity> getEntityList(List<CondicionVentaVO> lcvVO){
		List<CondicionVentaEntity> lcve = new ArrayList<CondicionVentaEntity>();
		for(CondicionVentaVO cvvo : lcvVO){
			CondicionVentaEntity cve = new CondicionVentaEntity();
			cve.setVO(cvvo);
			lcve.add(cve);
		}
		return lcve;
	}
}

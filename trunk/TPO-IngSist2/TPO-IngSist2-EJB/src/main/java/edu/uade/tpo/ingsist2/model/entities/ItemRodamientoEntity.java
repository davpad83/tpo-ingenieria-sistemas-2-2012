package edu.uade.tpo.ingsist2.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.uade.tpo.ingsist2.view.vo.ItemRodamientoVO;

@Entity
@Table(name = EntitiesTablesNames.ITEM_RODAMIENTO)
public class ItemRodamientoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int cantidad;
	private int pendientes;

	@ManyToOne
	private RodamientoEntity rodamiento;

	@ManyToOne (cascade = CascadeType.ALL)
	private CotizacionEntity cotizacion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public RodamientoEntity getRodamiento() {
		return rodamiento;
	}
	
	public void setRodamiento(String codigoSKF, String marca, String pais) {
		this.rodamiento.setMarca(marca);
		this.rodamiento.setPais(pais);
		this.rodamiento.setCodigoSKF(codigoSKF);
	}

	public void setRodamiento(RodamientoEntity rodamiento) {
		this.rodamiento = rodamiento;
	}

	public CotizacionEntity getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(CotizacionEntity cotizacion) {
		this.cotizacion = cotizacion;
	}

	private ItemRodamientoVO getVO() {
		ItemRodamientoVO pvo = new ItemRodamientoVO();
		pvo.setId(this.id);
		pvo.setCantidad(this.cantidad);
		pvo.setCotizacion(this.cotizacion.getVO());
		return pvo;
	}

	public static List<ItemRodamientoVO> getVOList(
			List<ItemRodamientoEntity> items) {
		List<ItemRodamientoVO> lirvo = new ArrayList<ItemRodamientoVO>();
		for (ItemRodamientoEntity itvo : items) {
			lirvo.add(itvo.getVO());
		}
		return lirvo;
	}

	public static List<ItemRodamientoEntity> getEntityList(
			ArrayList<ItemRodamientoVO> items) {
		List<ItemRodamientoEntity> lire = new ArrayList<ItemRodamientoEntity>();
		for (ItemRodamientoVO itvo : items) {
			ItemRodamientoEntity ire = new ItemRodamientoEntity();
			ire.setVO(itvo);
			lire.add(ire);
		}
		return lire;
	}

	public void setVO(ItemRodamientoVO itvo) {
		this.id = itvo.getId();
		this.cantidad = itvo.getCantidad();
		if (itvo.getCotizacion() != null) {
			CotizacionEntity ce = new CotizacionEntity();
			ce.setVO(itvo.getCotizacion());
			this.cotizacion = ce;
		}
		this.pendientes = itvo.getPendientes();
		if (itvo.getRodamiento() != null) {
			RodamientoEntity re = new RodamientoEntity();
			re.setVO(itvo.getRodamiento());
			this.rodamiento = re;
		}
	}

	
}

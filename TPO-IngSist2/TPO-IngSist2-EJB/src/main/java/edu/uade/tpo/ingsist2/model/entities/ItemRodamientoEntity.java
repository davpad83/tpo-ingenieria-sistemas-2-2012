package edu.uade.tpo.ingsist2.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.uade.tpo.ingsist2.view.vo.ItemRodamientoVO;

@Entity
@Table(name = "ItemsRodamientosVentas")
public class ItemRodamientoEntity {
	
	@Id
	private int id;
	
	private int cantidad;
	private int pendientes;
	
	@ManyToOne
	private RodamientoEntity rodamiento;
	
	@OneToOne
	private CotizacionEntity cotizacion;

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

	public void setRodamiento(RodamientoEntity rodamiento) {
		this.rodamiento = rodamiento;
	}

	public CotizacionEntity getIdPedidoDeCotizacion() {
		return cotizacion;
	}

	public void setIdPedidoDeCotizacion(CotizacionEntity idPedidoDeCotizacion) {
		this.cotizacion = idPedidoDeCotizacion;
	}

	public static List<ItemRodamientoVO> toVOList(
			ArrayList<ItemRodamientoEntity> items) {
				ArrayList<ItemRodamientoVO> pVoList = new ArrayList<ItemRodamientoVO>();
				for(ItemRodamientoEntity p: items)
					pVoList.add(p.getVO());
				return pVoList;
	}

	private ItemRodamientoVO getVO() {
		ItemRodamientoVO pvo = new ItemRodamientoVO();
		pvo.setId(this.id);
		pvo.setCantidad(this.cantidad);
		pvo.setCotizacion(this.cotizacion.getVO());
		return pvo;
	}
}


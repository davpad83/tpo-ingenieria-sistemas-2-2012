package edu.uade.tpo.ingsist2.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.model.OrdenDeCompra;
import edu.uade.tpo.ingsist2.model.Rodamiento;
import edu.uade.tpo.ingsist2.view.vo.ItemRemitoVO;
import edu.uade.tpo.ingsist2.view.vo.ItemVO;

@Entity
@Table(name = EntitiesTablesNames.ITEM_REMITO)
public class ItemRemitoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int cantidaEnviada;
	
	@ManyToOne
	private OrdenDeCompraEntity ocAsociada;
	
	@ManyToOne
	private RodamientoEntity rodamiento;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidaEnviada() {
		return cantidaEnviada;
	}

	public void setCantidaEnviada(int cantidaEnviada) {
		this.cantidaEnviada = cantidaEnviada;
	}

	public OrdenDeCompraEntity getOcAsociada() {
		return ocAsociada;
	}

	public void setOcAsociada(OrdenDeCompraEntity ocAsociada) {
		this.ocAsociada = ocAsociada;
	}

	public RodamientoEntity getRodamiento() {
		return rodamiento;
	}

	public void setRodamiento(RodamientoEntity rodamiento) {
		this.rodamiento = rodamiento;
	}
	
	public static List<ItemRemitoVO> getVOList(List<ItemRemitoEntity> itemsEntityList) {
		List<ItemRemitoVO> items = new ArrayList<ItemRemitoVO>();
		for(ItemRemitoEntity it : itemsEntityList){
			items.add(it.getVO());
		}
		return items;
	}

	public ItemRemitoVO getVO() {
		ItemRemitoVO itemRemitoVO = new ItemRemitoVO();
		itemRemitoVO.setCantidad(this.cantidaEnviada);
		itemRemitoVO.setIdOrdenDeCompra(this.ocAsociada.getIdOrden());
		itemRemitoVO.setRodamiento(this.rodamiento.getVO());
		return itemRemitoVO;
	}

}

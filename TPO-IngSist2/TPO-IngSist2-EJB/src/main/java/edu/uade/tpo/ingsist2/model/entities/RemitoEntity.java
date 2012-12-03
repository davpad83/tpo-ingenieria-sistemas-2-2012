package edu.uade.tpo.ingsist2.model.entities;

import java.util.List;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.model.OficinaDeVenta;
import edu.uade.tpo.ingsist2.model.OrdenDeCompra;
import edu.uade.tpo.ingsist2.view.vo.ItemRemitoVO;
import edu.uade.tpo.ingsist2.view.vo.RemitoResponse;

@Entity
@Table(name = EntitiesTablesNames.REMITO)
public class RemitoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idRemito;

//	@ManyToOne(cascade = CascadeType.ALL)
//	private OrdenDeCompraEntity ordenDeCompra;

	@ManyToOne(cascade = CascadeType.ALL)
	private OficinaDeVentaEntity odv;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn
	private List<ItemRemitoEntity> items;

	private OficinaDeVenta ODV;
	private OrdenDeCompra oc;
	
	public RemitoEntity() {
		super();
	}

	public int getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}

//	public OrdenDeCompraEntity getOrdenDeCompra() {
//		return ordenDeCompra;
//	}
//
//	public void setOrdenDeCompra(OrdenDeCompraEntity ordenDeCompra) {
//		this.ordenDeCompra = ordenDeCompra;
//	}

	public OficinaDeVentaEntity getOdv() {
		return odv;
	}

	public void setOdv(OficinaDeVentaEntity odv) {
		this.odv = odv;
	}

	public List<ItemRemitoEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemRemitoEntity> items) {
		this.items = items;
	}
	
	public RemitoResponse getVO(){
		RemitoResponse remitoResp = new RemitoResponse();
		remitoResp.setIdRemito(this.idRemito);
		if(this.items!=null)
			remitoResp.setItems(ItemRemitoEntity.getVOList(this.items));
		remitoResp.setIdODV(this.odv.getId());
		return remitoResp;
	}
	
	public void setVO(RemitoResponse remito) {
		this.idRemito = remito.getIdRemito();
		this.odv = ODV.getOficina(remito.getIdODV());
		for(ItemRemitoVO ir: remito.getItems()){
			ItemRemitoEntity ie = new ItemRemitoEntity();
			ie.setCantidaEnviada(ir.getCantidad());
			ie.setRodamiento(ir.getPais(),ir.getMarca(),ir.getSKF());
			ie.setOcAsociada(oc.getOrdenDeCompra(ir.getIdOrdenDeCompra()));
			this.items.add(ie);
		}
		
		
	}

//	public void setItemsList(List<ItemRemitoVO> items) {
//		for(ItemRemitoVO i : items){
//			ItemRodamientoEntity ire = new ItemRodamientoEntity();
//			ire.setCantidad(i.getCantidad());
//			ire.setId(i.getId());
//			ire.setRodamiento(i.getSKF(), i.getMarca(), i.getPais());
//		}
//	}
}

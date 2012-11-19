package edu.uade.tpo.ingsist2.model.entities;

import java.util.List;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.view.vo.RemitoVO;

@Entity
@Table(name = EntitiesTablesNames.REMITO)
public class RemitoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idRemito;

	@ManyToOne(cascade = CascadeType.ALL)
	private OrdenDeCompraEntity ordenDeCompra;

	@ManyToOne(cascade = CascadeType.ALL)
	private OficinaDeVentaEntity odv;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<ItemRodamientoEntity> items;

	public RemitoEntity() {
		super();
	}

	public int getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}

	public OrdenDeCompraEntity getOrdenDeCompra() {
		return ordenDeCompra;
	}

	public void setOrdenDeCompra(OrdenDeCompraEntity ordenDeCompra) {
		this.ordenDeCompra = ordenDeCompra;
	}

	public OficinaDeVentaEntity getOdv() {
		return odv;
	}

	public void setOdv(OficinaDeVentaEntity odv) {
		this.odv = odv;
	}

	public List<ItemRodamientoEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemRodamientoEntity> items) {
		this.items = items;
	}
	
	public RemitoVO getVO(){
		RemitoVO rvo = new RemitoVO();
		rvo.setIdRemito(this.idRemito);
		if(this.items!=null)
			rvo.setItems(ItemRodamientoEntity.getVOList(this.items));
		if(this.ordenDeCompra!=null)
			rvo.setOrdenDeCompra(this.ordenDeCompra.getVO());
		if(this.odv !=null)
			rvo.setOdv(this.odv.getVO());
		return rvo;
	}
}

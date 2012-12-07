package edu.uade.tpo.ingsist2.model.entities;

import java.util.List;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.view.vo.RemitoResponse;

@Entity
@Table(name = EntitiesTablesNames.REMITO)
public class RemitoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idRemito;

	@ManyToOne(cascade = CascadeType.ALL)
	private OficinaDeVentaEntity odv;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn
	private List<ItemRemitoEntity> items;


	public int getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}

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
	
}

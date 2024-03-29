package edu.uade.tpo.ingsist2.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.view.vo.OrdenDeCompraVO;

@Entity
@Table(name = EntitiesTablesNames.ORDEN_DE_COMPRA)
public class OrdenDeCompraEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idOrden;

	private int idRecibidoODV;

	private String estado;

	@ManyToOne
	private OficinaDeVentaEntity odv;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn
	private List<ItemRodamientoEntity> items;

	public int getIdOrden() {
		return idOrden;
	}
	
	public void setIdOrden(int idOrden) {
		this.idOrden = idOrden;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public OrdenDeCompraVO getVO() {
		OrdenDeCompraVO pvo = new OrdenDeCompraVO();
		pvo.setIdOrden(this.idOrden);
		pvo.setIdRecibido(this.idRecibidoODV);
		pvo.setEstado(this.estado);
		pvo.setOdv(this.odv.getVO());
		pvo.setItems(ItemRodamientoEntity.getVOList(this.items));
		return pvo;
	}

	public void setVO(OrdenDeCompraVO p) {
		this.idOrden = p.getIdOrden();
		this.idRecibidoODV = p.getIdRecibido();
		this.estado = p.getEstado();
		this.items = ItemRodamientoEntity.getEntityList(p.getItems());
		OficinaDeVentaEntity odve = new OficinaDeVentaEntity();
		odve.setVO(p.getOdv());
		this.odv = odve;
	}

	public static ArrayList<OrdenDeCompraVO> getVOList(
			ArrayList<OrdenDeCompraEntity> ps) {
		ArrayList<OrdenDeCompraVO> pVoList = new ArrayList<OrdenDeCompraVO>();
		for (OrdenDeCompraEntity p : ps)
			pVoList.add(p.getVO());
		return pVoList;
	}

	public int getIdRecibidoODV() {
		return idRecibidoODV;
	}

	public void setIdRecibidoODV(int idRecibidoODV) {
		this.idRecibidoODV = idRecibidoODV;
	}

	public boolean isPendiente() {
		return "pendiente".equals(estado.toLowerCase());
	}

}

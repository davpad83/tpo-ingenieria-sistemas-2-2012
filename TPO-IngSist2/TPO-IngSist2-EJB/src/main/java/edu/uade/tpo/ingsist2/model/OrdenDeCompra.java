package edu.uade.tpo.ingsist2.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.model.entities.ItemRodamiento;
import edu.uade.tpo.ingsist2.model.entities.PedidoDeAbastecimiento;
import edu.uade.tpo.ingsist2.view.vo.OrdenDeCompraVO;
import edu.uade.tpo.ingsist2.view.vo.PedidoAbastecimientoVO;

@Entity
public class OrdenDeCompra {
	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private int idOrden;
	private String estado;
	private OficinaDeVenta odv;
	@OneToMany
	private List<ItemRodamiento> items;

	public int getIdOrden() {
		return idOrden;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public OficinaDeVenta getOdv() {
		return odv;
	}

	public void setOdv(OficinaDeVenta odv) {
		this.odv = odv;
	}

	public List<ItemRodamiento> getItems() {
		return items;
	}

	public void setItems(List<ItemRodamiento> items) {
		this.items = items;
	}
	
	public OrdenDeCompraVO getVO(){
		OrdenDeCompraVO pvo = new OrdenDeCompraVO();
		pvo.setIdOrden(this.idOrden);
		pvo.setEstado(this.estado);
		pvo.setOdv(this.odv);
		pvo.setItems(this.items);
		return pvo;
	}
	
	public void setVO(OrdenDeCompraVO p){
		this.idOrden = p.getIdOrden();
		this.estado = p.getEstado();
		this.items = p.getItems();
		this.odv = p.getOdv();
	}

	public static ArrayList<OrdenDeCompraVO> getVOList(
			ArrayList<OrdenDeCompra> ps) {
			ArrayList<OrdenDeCompraVO> pVoList = new ArrayList<OrdenDeCompraVO>();
			for(OrdenDeCompra p: ps)
				pVoList.add(p.getVO());
			return pVoList;
	}
		
}

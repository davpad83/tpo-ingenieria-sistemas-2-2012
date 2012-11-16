package edu.uade.tpo.ingsist2.view.vo;

import java.util.List;

public class RemitoVO {
	private int idRemito;
	private OrdenDeCompraVO ordenDeCompra;
	private OficinaDeVentaVO odv;
	private List<ItemRodamientoVO> items;

	public int getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}

	public OrdenDeCompraVO getOrdenDeCompra() {
		return ordenDeCompra;
	}

	public void setOrdenDeCompra(OrdenDeCompraVO ordenDeCompra) {
		this.ordenDeCompra = ordenDeCompra;
	}

	public OficinaDeVentaVO getOdv() {
		return odv;
	}

	public void setOdv(OficinaDeVentaVO odv) {
		this.odv = odv;
	}

	public List<ItemRodamientoVO> getItems() {
		return items;
	}

	public void setItems(List<ItemRodamientoVO> items) {
		this.items = items;
	}

}

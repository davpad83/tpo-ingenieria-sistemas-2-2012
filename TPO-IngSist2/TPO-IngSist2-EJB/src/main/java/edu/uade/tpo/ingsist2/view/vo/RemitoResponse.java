package edu.uade.tpo.ingsist2.view.vo;

import java.util.List;

import com.thoughtworks.xstream.XStream;

public class RemitoResponse {
	private int idRemito;
	private int idOrdenDeCompra;
	private List<ItemRodamientoVO> items;

	public int getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}

	public int getIdOrdenDeCompra() {
		return idOrdenDeCompra;
	}

	public void setIdOrdenDeCompra(int idOrdenDeCompra) {
		this.idOrdenDeCompra = idOrdenDeCompra;
	}

	public List<ItemRodamientoVO> getItems() {
		return items;
	}

	public void setItems(List<ItemRodamientoVO> items) {
		this.items = items;
	}
	
	public String toXML(){
		XStream xs = new XStream();
		xs.alias("Remito", RemitoResponse.class);
		xs.alias("ItemRodamiento", ItemRodamientoVO.class);
		xs.alias("Rodamiento", RodamientoVO.class);
		
		xs.omitField(ItemRodamientoVO.class, "id");
		xs.omitField(ItemRodamientoVO.class, "pendientes");
		xs.omitField(ItemRodamientoVO.class,"cotizacion");
		xs.omitField(RodamientoVO.class, "serialVersionUID");
		xs.omitField(RodamientoVO.class, "id");
		xs.omitField(RodamientoVO.class, "stock");
		return xs.toXML(this);
	}

}

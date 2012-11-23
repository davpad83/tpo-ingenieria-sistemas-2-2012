package edu.uade.tpo.ingsist2.view.vo;

import java.util.List;

import com.thoughtworks.xstream.XStream;

public class RemitoResponse {
	private int idRemito;
	private int idODV;
	private List<ItemVO> items;

	public int getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}

	public List<ItemVO> getItems() {
		return items;
	}

	public void setItems(List<ItemVO> items) {
		this.items = items;
	}

	public String toXML() {
		XStream xs = new XStream();
		xs.alias("Remito", RemitoResponse.class);
		xs.alias("ItemRemito", ItemVO.class);
		xs.aliasField("idOrdenDeCompra", ItemVO.class, "id");

		xs.omitField(ItemVO.class, "precio");
		return xs.toXML(this);
	}

	public int getIdODV() {
		return idODV;
	}

	public void setIdODV(int idODV) {
		this.idODV = idODV;
	}

}

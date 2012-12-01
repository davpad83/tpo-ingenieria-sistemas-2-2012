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
		xs.alias("remito", RemitoResponse.class);
		xs.alias("itemremito", ItemVO.class);
		xs.aliasField("idordendecompra", ItemVO.class, "id");
		xs.aliasField("idremito", RemitoResponse.class, "idRemito");
		xs.aliasField("idodv", RemitoResponse.class, "idODV");
		
		xs.omitField(ItemVO.class, "idOrdenDeCompra");
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

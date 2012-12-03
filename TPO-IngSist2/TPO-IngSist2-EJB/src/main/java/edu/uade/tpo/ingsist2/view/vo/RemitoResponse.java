package edu.uade.tpo.ingsist2.view.vo;

import java.util.List;

import com.thoughtworks.xstream.XStream;

public class RemitoResponse {
	private int idRemito;
	private int idODV;
	private List<ItemRemitoVO> items;

	public int getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}

	public List<ItemRemitoVO> getItems() {
		return items;
	}

	public void setItems(List<ItemRemitoVO> items) {
		this.items = items;
	}

	public String toXML() {
		XStream xs = new XStream();
		xs.alias("remito", RemitoResponse.class);
		xs.alias("itemremito", ItemRemitoVO.class);
		xs.aliasField("idremito", RemitoResponse.class, "idRemito");
		xs.aliasField("idodv", RemitoResponse.class, "idODV");
		
		return xs.toXML(this);
	}

	public int getIdODV() {
		return idODV;
	}

	public void setIdODV(int idODV) {
		this.idODV = idODV;
	}

}

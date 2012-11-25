package edu.uade.tpo.ingsist2.view.vo;

import java.util.List;

import com.thoughtworks.xstream.XStream;

public class SolicitudCompraRequest {

	private int idOrdenDeCompra;
	private int idODV;
	private List<ItemVO> items;

	public int getIdOrdenDeCompra() {
		return idOrdenDeCompra;
	}

	public void setIdOrdenDeCompra(int idPedidoCotizacion) {
		this.idOrdenDeCompra = idPedidoCotizacion;
	}

	public int getIdODV() {
		return idODV;
	}

	public void setIdODV(int idODV) {
		this.idODV = idODV;
	}

	public List<ItemVO> getItems() {
		return items;
	}

	public void setItems(List<ItemVO> items) {
		this.items = items;
	}
	
	public void fromXML(String xml){
		XStream xs = new XStream();
		xs.alias("OrdenDeCompra", SolicitudCompraRequest.class);
		xs.alias("ItemOrdenDeCompra", ItemVO.class);
		xs.aliasField("idPedidoCotizacion", ItemVO.class, "id");
		SolicitudCompraRequest scr = (SolicitudCompraRequest) xs.fromXML(xml);
		this.idODV = scr.getIdODV();
		this.idOrdenDeCompra = scr.getIdOrdenDeCompra();
		this.items = scr.getItems();
	}

	public String toXML() {
		XStream xs = new XStream();
		xs.alias("ordendecompra", SolicitudCompraRequest.class);
		xs.alias("itemordendecompra", ItemVO.class);
		xs.aliasField("idpedidocotizacion", ItemVO.class, "id");
		return xs.toXML(this);
	}
}

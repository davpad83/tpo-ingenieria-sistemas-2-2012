package edu.uade.tpo.ingsist2.view.vo;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class SolicitudCompraRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idOrdenDeCompra;
	private int idODV;
	private List<ItemSolicitudCompraRequest> items;

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

	public List<ItemSolicitudCompraRequest> getItems() {
		return items;
	}

	public void setItems(List<ItemSolicitudCompraRequest> items) {
		this.items = items;
	}
	
	public void fromXML(String xml){
		XStream xs = new XStream();
		xs.alias("ordendecompra", SolicitudCompraRequest.class);
		xs.alias("itemordendecompra", ItemSolicitudCompraRequest.class);
		xs.aliasField("idordendecompra", SolicitudCompraRequest.class, "idOrdenDeCompra");
		xs.aliasField("idpedidocotizacion", ItemSolicitudCompraRequest.class, "idPedidoCotODV");
		xs.aliasField("idodv", SolicitudCompraRequest.class, "idODV");
		SolicitudCompraRequest scr = (SolicitudCompraRequest) xs.fromXML(xml);
		this.idODV = scr.getIdODV();
		this.idOrdenDeCompra = scr.getIdOrdenDeCompra();
		this.items = scr.getItems();
	}

	public String toXML() {
		XStream xs = new XStream();
		xs.alias("ordendecompra", SolicitudCompraRequest.class);
		xs.alias("itemordendecompra", ItemSolicitudCompraRequest.class);
		xs.aliasField("idordendecompra", SolicitudCompraRequest.class, "idOrdenDeCompra");
		xs.aliasField("idpedidocotizacion", ItemSolicitudCompraRequest.class, "idPedidoCotODV");
		xs.aliasField("idodv", SolicitudCompraRequest.class, "idODV");
		return xs.toXML(this);
	}
}

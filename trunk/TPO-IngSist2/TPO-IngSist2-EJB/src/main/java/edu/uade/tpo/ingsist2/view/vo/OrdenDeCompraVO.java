package edu.uade.tpo.ingsist2.view.vo;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class OrdenDeCompraVO {
	private int idOrden;
	private int idRecibido;
	private String estado;
	private OficinaDeVentaVO odv;
	private List<ItemRodamientoVO> items;

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

	public OficinaDeVentaVO getOdv() {
		return odv;
	}

	public void setOdv(OficinaDeVentaVO odv) {
		this.odv = odv;
	}

	public ArrayList<ItemRodamientoVO> getItems() {
		return (ArrayList<ItemRodamientoVO>) items;
	}

	public void setItems(List<ItemRodamientoVO> items) {
		this.items = items;
	}

	public String toXML(boolean omitId){
		XStream xs = new XStream();
		xs.alias("OficinaDeVenta", OficinaDeVentaVO.class);
		xs.alias("ItemRodamiento", ItemRodamientoVO.class);
		xs.alias("Rodamiento", RodamientoVO.class);
		xs.alias("Cotizacion", CotizacionVO.class);
		xs.alias("OrdenDeCompra", OrdenDeCompraVO.class);
		xs.aliasField("idOrdenODV", OrdenDeCompraVO.class, "idRecibido");
		xs.omitField(RodamientoVO.class, "stock");
		xs.omitField(CotizacionVO.class, "id");
		if(omitId){
			xs.omitField(OrdenDeCompraVO.class, "idOrden");
			xs.omitField(OficinaDeVentaVO.class, "idODV");
			xs.omitField(ItemRodamientoVO.class, "id");
			xs.omitField(RodamientoVO.class, "id");
		}
		return xs.toXML(this);
	}
	
	public void fromXML(String textReceived) {
		XStream xs = new XStream();
		xs = setXMLParameters(xs, true);
		//REVISAR LO QUE NOS MANDAN COMO ID QUE NOS MANDA LA ODV
		xs.aliasField("idOC", OrdenDeCompraVO.class, "idRecibido");
		OrdenDeCompraVO ocvo = (OrdenDeCompraVO) xs.fromXML(textReceived);
		this.idOrden = ocvo.getIdOrden();
		this.estado = ocvo.getEstado();
		this.odv = ocvo.getOdv();
		this.items = ocvo.getItems();
	}

	public int getIdRecibido() {
		return idRecibido;
	}

	public void setIdRecibido(int idRecibido) {
		this.idRecibido = idRecibido;
	}

	public static XStream setXMLParameters(XStream xs, boolean omitId) {
		xs.alias("OrdenDeCompra", OrdenDeCompraVO.class);
		xs = OficinaDeVentaVO.setXMLParameters(xs, omitId);
		xs = ItemRodamientoVO.setXMLParameters(xs, omitId);
		xs = RodamientoVO.setXMLParameters(xs, omitId);
		xs = CotizacionVO.setXMLParameters(xs, omitId);
		xs.omitField(RodamientoVO.class, "stock");
		if(omitId)
			xs.omitField(CotizacionVO.class, "id");
		return xs;
	}

}

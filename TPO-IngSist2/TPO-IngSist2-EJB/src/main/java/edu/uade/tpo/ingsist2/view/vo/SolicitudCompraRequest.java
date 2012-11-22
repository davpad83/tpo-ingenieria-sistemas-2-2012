package edu.uade.tpo.ingsist2.view.vo;

import java.util.List;

import com.thoughtworks.xstream.XStream;

public class SolicitudCompraRequest {

	private int idPedidoCotizacion;
	private int idODV;
	private List<ItemRodamientoVO> items;

	public int getIdPedidoCotizacion() {
		return idPedidoCotizacion;
	}

	public void setIdPedidoCotizacion(int idPedidoCotizacion) {
		this.idPedidoCotizacion = idPedidoCotizacion;
	}

	public int getIdODV() {
		return idODV;
	}

	public void setIdODV(int idODV) {
		this.idODV = idODV;
	}

	public List<ItemRodamientoVO> getItems() {
		return items;
	}

	public void setItems(List<ItemRodamientoVO> items) {
		this.items = items;
	}
	
	public String toXML(){
		XStream xs = new XStream();
		xs.alias("SolicitudCompraRequest", SolicitudCompraRequest.class);
		xs.alias("ItemRodamiento",ItemRodamientoVO.class);
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

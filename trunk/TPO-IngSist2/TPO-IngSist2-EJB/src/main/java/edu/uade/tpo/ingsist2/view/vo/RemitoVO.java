package edu.uade.tpo.ingsist2.view.vo;

import java.util.List;

import com.thoughtworks.xstream.XStream;

import edu.uade.tpo.ingsist2.model.Remito;
import edu.uade.tpo.ingsist2.model.Rodamiento;

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

	public String toXML(boolean omitId) {
		XStream xs = new XStream();
		xs = setXMLParameters(xs, omitId);
		return xs.toXML(this);
	}

	public static XStream setXMLParameters(XStream xs, boolean omitId) {
		xs.alias("Remito", RemitoVO.class);
		xs = ItemRodamientoVO.setXMLParameters(xs, omitId);
		xs = OrdenDeCompraVO.setXMLParameters(xs, omitId);
		if(omitId)
			xs.omitField(RemitoVO.class, "idRemito");
		xs.omitField(Remito.class, "odv");	
		return xs;
	}

	public String toXML() {
		XStream xs = new XStream();
		setXMLParameters(xs, true);
		return xs.toXML(this);
	}

}

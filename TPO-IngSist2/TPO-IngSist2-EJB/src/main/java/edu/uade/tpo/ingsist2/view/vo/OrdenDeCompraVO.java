package edu.uade.tpo.ingsist2.view.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import com.thoughtworks.xstream.XStream;

import edu.uade.tpo.ingsist2.model.Cotizacion;
import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;

public class OrdenDeCompraVO {
	private int idOrden;
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
		if(omitId){
			xs.omitField(OrdenDeCompraVO.class, "idOrden");
			xs.omitField(OficinaDeVentaVO.class, "idODV");
			xs.omitField(ItemRodamientoVO.class, "id");
			xs.omitField(RodamientoVO.class, "id");
			xs.omitField(Cotizacion.class, "id");
		}
		
		
		return xs.toXML(this);
	}
	
	public void fromXML(String textReceived) {
		XStream xs = new XStream();
		xs.alias("OficinaDeVenta", OficinaDeVentaVO.class);
		xs.alias("ItemRodamiento", ItemRodamientoVO.class);
		xs.alias("Rodamiento", RodamientoVO.class);
		xs.alias("Cotizacion", CotizacionVO.class);
		OrdenDeCompraVO ocvo = (OrdenDeCompraVO) xs.fromXML(textReceived);
		this.idOrden = ocvo.getIdOrden();
		this.estado = ocvo.getEstado();
		this.odv = ocvo.getOdv();
		this.items = ocvo.getItems();
	}

}

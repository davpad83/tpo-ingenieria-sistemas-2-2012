package edu.uade.tpo.ingsist2.view.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;

/**
 * Este itemVO es usado por RemitoResponse y SolicitudCompraRequest porque se
 * usa para la integracion con la ODV. Como ambos "items" utilizan los mismos
 * tipos de datos se puede utilizar un item generico para poder interpretar los
 * XML de entrada y generar de salida. Estos se parsean en cada clase con
 * XStream.
 * 
 * @author matiasfavale
 * 
 */

public class ItemSolicitudCompraRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idPedidoCotODV;
	private String skf;
	private String pais;
	private String marca;
	private int cantidad;
	private float precio;

	public int getIdPedidoCotODV() {
		return idPedidoCotODV;
	}

	public void setIdPedidoCotODV(int id) {
		this.idPedidoCotODV = id;
	}

	public String getSKF() {
		return skf;
	}

	public void setSKF(String sKF) {
		skf = sKF;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public void setRodamiento(RodamientoVO rodamiento) {
		this.marca=rodamiento.getMarca();
		this.pais=rodamiento.getPais();
		this.skf=rodamiento.getCodigoSKF();
	}

	public static List<ItemSolicitudCompraRequest> getVOList(List<ItemRodamientoEntity> itemsEntityList) {
		List<ItemSolicitudCompraRequest> items = new ArrayList<ItemSolicitudCompraRequest>();
		for(ItemRodamientoEntity it : itemsEntityList){
			items.add(getItemVO(it));
		}
		return items;
	}

	private static ItemSolicitudCompraRequest getItemVO(ItemRodamientoEntity it) {
		ItemSolicitudCompraRequest itemVO = new ItemSolicitudCompraRequest();
		
		itemVO.setCantidad(it.getCantidad());
		itemVO.setIdPedidoCotODV(it.getId());
		itemVO.setMarca(it.getCotizacion().getRodamiento().getMarca());
		itemVO.setSKF(it.getCotizacion().getRodamiento().getCodigoSKF());
		itemVO.setPais(it.getCotizacion().getRodamiento().getPais());
		return itemVO;
	}

	public RodamientoEntity getRodamiento() {
		RodamientoEntity re = new RodamientoEntity();
		re.setMarca(this.marca);
		re.setCodigoSKF(this.skf);
		re.setPais(this.pais);
		return re;
	}
}

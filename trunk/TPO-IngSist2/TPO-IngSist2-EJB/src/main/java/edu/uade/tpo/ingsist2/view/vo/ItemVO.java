package edu.uade.tpo.ingsist2.view.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;

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

public class ItemVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String skf;
	private String pais;
	private String marca;
	private int cantidad;
	private int idOrdenDeCompra;
	private float precio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setIdOrdenDeCompra(int idOrdenDeCompra) {
		this.idOrdenDeCompra = idOrdenDeCompra;
	}

	public int getIdOrdenDeCompra() {
		return idOrdenDeCompra;
	}

	public static List<ItemVO> getVOList(List<ItemRodamientoEntity> itemsEntityList) {
		List<ItemVO> items = new ArrayList<ItemVO>();
		for(ItemRodamientoEntity it : itemsEntityList){
			items.add(getItemVO(it));
		}
		return items;
	}

	private static ItemVO getItemVO(ItemRodamientoEntity it) {
		ItemVO itemVO = new ItemVO();
		
		itemVO.setCantidad(it.getCantidad());
		itemVO.setId(it.getId());
		itemVO.setMarca(it.getRodamiento().getMarca());
		itemVO.setSKF(it.getRodamiento().getCodigoSKF());
		itemVO.setPais(it.getRodamiento().getPais());
		return itemVO;
	}
}

package edu.uade.tpo.ingsist2.view.vo;

import java.util.Date;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import edu.uade.tpo.ingsist2.model.entities.CondicionVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemListaEntity;
import edu.uade.tpo.ingsist2.model.entities.ProveedorEntity;

public class ListaPreciosVO {

	private int idLista;
	private ProveedorVO proveedor;
	private List<ItemListaVO> items;
	private List<CondicionVentaVO> condicionesDeVenta;
	private String nombre;
	private Date vigenciaDesde;
	private Date vigenciaHasta;

	public int getIdLista() {
		return idLista;
	}

	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}

	public ProveedorVO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorVO proveedor) {
		this.proveedor = proveedor;
	}

	public List<ItemListaVO> getItems() {
		return items;
	}

	public void setItems(List<ItemListaVO> items) {
		this.items = items;
	}

	public List<CondicionVentaVO> getCondicionesDeVenta() {
		return condicionesDeVenta;
	}

	public void setCondicionesDeVenta(List<CondicionVentaVO> condicionesDeVenta) {
		this.condicionesDeVenta = condicionesDeVenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getVigenciaDesde() {
		return vigenciaDesde;
	}

	public void setVigenciaDesde(Date vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}

	public Date getVigenciaHasta() {
		return vigenciaHasta;
	}

	public void setVigenciaHasta(Date vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}

}

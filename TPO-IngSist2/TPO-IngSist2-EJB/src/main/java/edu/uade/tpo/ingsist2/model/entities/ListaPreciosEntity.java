package edu.uade.tpo.ingsist2.model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;

@Entity
@Table(name="ListaPrecios")
public class ListaPreciosEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idLista;

	@ManyToOne(cascade=CascadeType.ALL)
	private ProveedorEntity proveedor;

	@OneToMany
	private List<ItemListaEntity> items;

	@OneToMany
	private List<CondicionVentaEntity> condicionesDeVenta;

	private String nombre;
	private Date vigenciaDesde;
	private Date vigenciaHasta;

	public int getIdLista() {
		return idLista;
	}

	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}

	public ProveedorEntity getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorEntity proveedor) {
		this.proveedor = proveedor;
	}

	public List<ItemListaEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemListaEntity> items) {
		this.items = items;
	}

	public List<CondicionVentaEntity> getCondicionesDeVenta() {
		return condicionesDeVenta;
	}

	public void setCondicionesDeVenta(List<CondicionVentaEntity> condicionesDeVenta) {
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
	
	public ListaPreciosVO getVO(){
		ListaPreciosVO lpvo = new ListaPreciosVO();
		lpvo.setIdLista(this.idLista);
		lpvo.setItems(ItemListaEntity.getVOList(this.items));
		lpvo.setNombre(this.nombre);
		lpvo.setVigenciaDesde(this.vigenciaDesde);
		lpvo.setVigenciaHasta(this.vigenciaHasta);
		lpvo.setCondicionesDeVenta(CondicionVentaEntity.getVOList(this.condicionesDeVenta));
		return lpvo;
	}
	
	public void setVO(ListaPreciosVO lpvo){
		this.setIdLista(lpvo.getIdLista());
		//TODO
		
	}
	
	public static List<ListaPreciosVO> getVOList(List<ListaPreciosEntity> llp){
		return null;
	}
}

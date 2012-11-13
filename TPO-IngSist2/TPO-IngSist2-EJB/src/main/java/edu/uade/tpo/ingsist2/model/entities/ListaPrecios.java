package edu.uade.tpo.ingsist2.model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;

@Entity
public class ListaPrecios {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idLista;

	@ManyToOne
	private Proveedor proveedor;

	@OneToMany
	private List<ItemLista> items;

	@OneToMany
	private List<CondicionVenta> condicionesDeVenta;

	private String nombre;
	private Date vigenciaDesde;
	private Date vigenciaHasta;

	public int getIdLista() {
		return idLista;
	}

	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<ItemLista> getItems() {
		return items;
	}

	public void setItems(List<ItemLista> items) {
		this.items = items;
	}

	public List<CondicionVenta> getCondicionesDeVenta() {
		return condicionesDeVenta;
	}

	public void setCondicionesDeVenta(List<CondicionVenta> condicionesDeVenta) {
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
		lpvo.setItems(ItemLista.getVOList(this.items));
		lpvo.setNombre(this.nombre);
		lpvo.setVigenciaDesde(this.vigenciaDesde);
		lpvo.setVigenciaHasta(this.vigenciaHasta);
		lpvo.setCondicionesDeVenta(CondicionVenta.getVOList(this.condicionesDeVenta));
		return lpvo;
	}
	
	public void setVO(ListaPreciosVO lpvo){
		this.setIdLista(lpvo.getIdLista());
		//TODO
		
	}
	
	public static List<ListaPreciosVO> getVOList(List<ListaPrecios> llp){
		return null;
	}
}

package edu.uade.tpo.ingsist2.view.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.thoughtworks.xstream.XStream;

import edu.uade.tpo.ingsist2.model.entities.CondicionVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.ProveedorEntity;

public class ListaPreciosVO implements Serializable {

	private static final long serialVersionUID = 2526758330089665672L;

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

	public void fromXML(String xml) {
		try {
			XStream xs = new XStream();
			xs.alias("ListaPrecios", ListaPreciosVO.class);
			xs.alias("Proveedor", ProveedorVO.class);
			xs.alias("ItemLista", ItemListaVO.class);
			xs.alias("CondicionDeVenta", CondicionVentaVO.class);
			ListaPreciosVO lpvo = (ListaPreciosVO) xs.fromXML(xml);
			this.setCondicionesDeVenta(lpvo.getCondicionesDeVenta());
			this.setIdLista(lpvo.getIdLista());
			this.setNombre(lpvo.getNombre());
			this.setProveedor(lpvo.getProveedor());
			this.setVigenciaDesde(lpvo.getVigenciaDesde());
			this.setVigenciaHasta(lpvo.getVigenciaHasta());
			this.setItems(lpvo.getItems());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toXML(boolean omitID) {
		String xml = "";
		try {
			XStream xs = new XStream();
			xs.alias("ListaPrecios", ListaPreciosVO.class);
			xs.alias("Proveedor", ProveedorVO.class);
			xs.alias("ItemLista", ItemListaVO.class);
			xs.alias("CondicionDeVenta", CondicionVentaVO.class);
			if (omitID) {
				xs.omitField(ListaPreciosVO.class, "idLista");
				xs.omitField(ItemListaVO.class, "id");
				xs.omitField(ProveedorVO.class, "id");
				xs.omitField(CondicionVentaVO.class, "id");
				xs.omitField(RodamientoVO.class, "id");
			}
			xml = xs.toXML(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}

}

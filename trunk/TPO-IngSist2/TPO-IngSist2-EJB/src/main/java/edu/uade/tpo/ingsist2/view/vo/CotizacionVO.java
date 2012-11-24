package edu.uade.tpo.ingsist2.view.vo;

import java.util.Date;

import com.thoughtworks.xstream.XStream;

public class CotizacionVO {

	private int id;
	private int idPedidoCotizacion;
	private OficinaDeVentaVO odv;
	private RodamientoVO rodamiento;
	private ListaPreciosVO lista;
	private Date vencimiento;
	private Date fecha;
	private String tiempoEntrega;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPedidoCotizacion() {
		return idPedidoCotizacion;
	}

	public void setIdPedidoCotizacion(int idPedidoCotizacion) {
		this.idPedidoCotizacion = idPedidoCotizacion;
	}

	public OficinaDeVentaVO getOdv() {
		return odv;
	}

	public void setOdv(OficinaDeVentaVO odv) {
		this.odv = odv;
	}

	public RodamientoVO getRodamiento() {
		return rodamiento;
	}

	public void setRodamiento(RodamientoVO rodamiento) {
		this.rodamiento = rodamiento;
	}

	public ListaPreciosVO getLista() {
		return lista;
	}

	public void setLista(ListaPreciosVO lista) {
		this.lista = lista;
	}

	public Date getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTiempoEntrega() {
		return tiempoEntrega;
	}

	public void setTiempoEntrega(String tiempoEntrega) {
		this.tiempoEntrega = tiempoEntrega;
	}

	public static XStream setXMLParameters(XStream xs, boolean omitId) {
		xs.alias("Cotizacion", CotizacionVO.class);
		xs = OficinaDeVentaVO.setXMLParameters(xs, omitId);
		xs = RodamientoVO.setXMLParameters(xs, omitId);
		if(omitId)
			xs.omitField(CotizacionVO.class, "id");
		return xs;
	}

	@Override
	public String toString() {
		return "CotizacionVO [id=" + id + ", idPedidoCotizacion="
				+ idPedidoCotizacion + ", odv=" + odv.getNombre() + ", rodamiento="
				+ rodamiento.toString() + ", lista=" + lista.toString() + ", vencimiento="
				+ vencimiento + ", fecha=" + fecha + ", tiempoEntrega="
				+ tiempoEntrega + "]";
	}
}

package edu.uade.tpo.ingsist2.view.vo;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;

public class PedidoAbastecimientoVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idPedido;
	private RodamientoVO rodamiento;
	private OrdenDeCompraVO ocAsociada;
	private ProveedorVO proveedor;
	private int cantidadPedida;
	private int cantidadPendiente;
	
	
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public RodamientoVO getRodamiento() {
		return rodamiento;
	}
	public void setRodamiento(RodamientoVO rodamiento) {
		this.rodamiento = rodamiento;
	}
	public OrdenDeCompraVO getOcAsociada() {
		return ocAsociada;
	}
	public void setOcAsociada(OrdenDeCompraVO ocAsociada) {
		this.ocAsociada = ocAsociada;
	}
	public ProveedorVO getProveedor() {
		return proveedor;
	}
	public void setProveedor(ProveedorVO proveedor) {
		this.proveedor = proveedor;
	}
	public int getCantidadPedida() {
		return cantidadPedida;
	}
	public void setCantidadPedida(int cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}
	public int getCantidadPendiente() {
		return cantidadPendiente;
	}
	public void setCantidadPendiente(int cantidadPendiente) {
		this.cantidadPendiente = cantidadPendiente;
	}
	
	public String toXML(boolean omitId){
		XStream xs = new XStream();
		xs.alias("ocAsociada", OrdenDeCompraVO.class);
		xs.alias("Rodamiento", RodamientoVO.class);
		xs.alias("Proveedor", ProveedorVO.class);
		if(omitId){
			xs.omitField(OrdenDeCompraVO.class, "idODV");
			xs.omitField(ProveedorVO.class, "id");
			xs.omitField(RodamientoVO.class, "id");
		}
		xs.omitField(PedidoAbastecimientoVO.class, "idPedido");
		xs.omitField(PedidoAbastecimientoVO.class, "cantidadPendiente");
		xs.omitField(PedidoAbastecimientoVO.class, "ocAsociada");
		return xs.toXML(this);
	}
	
	public void fromXML(String textReceived, boolean omitId) {
		XStream xs = new XStream();
		xs.alias("ocAsociada", OrdenDeCompraVO.class);
		xs.alias("Rodamiento", RodamientoVO.class);
		xs.alias("Proveedor", ProveedorVO.class);
		if(omitId){
			xs.omitField(OrdenDeCompraVO.class, "idODV");
			xs.omitField(ProveedorVO.class, "id");
			xs.omitField(RodamientoVO.class, "id");
		}
		xs.omitField(PedidoAbastecimientoVO.class, "idPedido");
		xs.omitField(PedidoAbastecimientoVO.class, "cantidadPendiente");
		xs.omitField(PedidoAbastecimientoVO.class, "ocAsociada");
		
		PedidoAbastecimientoVO pavo = (PedidoAbastecimientoVO) xs.fromXML(textReceived);
		this.setIdPedido(pavo.getIdPedido());
		this.setRodamiento(pavo.getRodamiento());
		this.setOcAsociada(pavo.getOcAsociada());
		this.setProveedor(pavo.getProveedor());
		this.setCantidadPedida(pavo.getCantidadPedida());
		this.setCantidadPendiente(pavo.getCantidadPendiente());
	}
}

package edu.uade.tpo.ingsist2.view.vo;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;

import edu.uade.tpo.ingsist2.model.entities.PedidoDeAbastecimientoEntity;

public class PedidoAbastecimientoVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idPedido;
	private RodamientoVO rodamiento;
	private boolean recibido;
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
	public boolean isRecibido() {
		return recibido;
	}
	public void setRecibido(boolean recibido) {
		this.recibido = recibido;
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
		xs.omitField(PedidoAbastecimientoVO.class, "recibido");
		xs.omitField(PedidoAbastecimientoVO.class, "cantidadPendiente");
		xs.omitField(PedidoAbastecimientoVO.class, "ocAsociada");
		return xs.toXML(this);
	}
	
	public void fromXML(String textReceived) {
		XStream xs = new XStream();
		xs.alias("ocAsociada", OrdenDeCompraVO.class);
		xs.alias("Rodamiento", RodamientoVO.class);
		xs.alias("Proveedor", ProveedorVO.class);
		PedidoAbastecimientoVO ocvo = (		PedidoAbastecimientoVO) xs.fromXML(textReceived);
		this.idPedido = ocvo.getIdPedido();
		this.proveedor = ocvo.getProveedor();
		this.ocAsociada = ocvo.getOcAsociada();
	}
}

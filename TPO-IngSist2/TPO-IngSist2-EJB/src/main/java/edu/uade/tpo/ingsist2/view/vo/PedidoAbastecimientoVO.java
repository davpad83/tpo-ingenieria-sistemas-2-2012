package edu.uade.tpo.ingsist2.view.vo;

import java.io.Serializable;

import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.model.entities.ProveedorEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;

public class PedidoAbastecimientoVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idPedido;
	private RodamientoEntity rodamiento;
	private boolean recibido;
	private OrdenDeCompraEntity ocAsociada;
	private ProveedorEntity proveedor;
	private int cantidadPedida;
	private int cantidadPendiente;
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public RodamientoEntity getRodamiento() {
		return rodamiento;
	}
	public void setRodamiento(RodamientoEntity rodamiento) {
		this.rodamiento = rodamiento;
	}
	public boolean isRecibido() {
		return recibido;
	}
	public void setRecibido(boolean recibido) {
		this.recibido = recibido;
	}
	public OrdenDeCompraEntity getOcAsociada() {
		return ocAsociada;
	}
	public void setOcAsociada(OrdenDeCompraEntity ocAsociada) {
		this.ocAsociada = ocAsociada;
	}
	public ProveedorEntity getProveedor() {
		return proveedor;
	}
	public void setProveedor(ProveedorEntity proveedor) {
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
	
	
}

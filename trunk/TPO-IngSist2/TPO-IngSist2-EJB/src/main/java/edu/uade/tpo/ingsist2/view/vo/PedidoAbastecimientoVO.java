package edu.uade.tpo.ingsist2.view.vo;

import java.io.Serializable;

import edu.uade.tpo.ingsist2.model.OrdenDeCompra;
import edu.uade.tpo.ingsist2.model.entities.Proveedor;
import edu.uade.tpo.ingsist2.model.entities.Rodamiento;

public class PedidoAbastecimientoVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idPedido;
	private Rodamiento rodamiento;
	private boolean recibido;
	private OrdenDeCompra ocAsociada;
	private Proveedor proveedor;
	private int cantidadPedida;
	private int cantidadPendiente;
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public Rodamiento getRodamiento() {
		return rodamiento;
	}
	public void setRodamiento(Rodamiento rodamiento) {
		this.rodamiento = rodamiento;
	}
	public boolean isRecibido() {
		return recibido;
	}
	public void setRecibido(boolean recibido) {
		this.recibido = recibido;
	}
	public OrdenDeCompra getOcAsociada() {
		return ocAsociada;
	}
	public void setOcAsociada(OrdenDeCompra ocAsociada) {
		this.ocAsociada = ocAsociada;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
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

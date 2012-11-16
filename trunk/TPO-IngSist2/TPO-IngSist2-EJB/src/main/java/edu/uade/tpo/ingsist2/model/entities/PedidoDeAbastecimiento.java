package edu.uade.tpo.ingsist2.model.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.model.OrdenDeCompra;
import edu.uade.tpo.ingsist2.view.vo.PedidoAbastecimientoVO;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;

@Entity
public class PedidoDeAbastecimiento {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int idPedido;
	private Rodamiento rodamiento;
	private boolean recibido;
	private OrdenDeCompra ocAsociada;
	private Proveedor proveedor;
	private int cantidadPedida;
	private int cantidadPendiente;
	
	
	public PedidoDeAbastecimiento() {
		super();
	}

	
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



	public PedidoAbastecimientoVO getVO(){
		PedidoAbastecimientoVO pvo = new PedidoAbastecimientoVO();
		pvo.setIdPedido(this.idPedido);
		pvo.setCantidadPedida(this.cantidadPedida);
		pvo.setCantidadPendiente(this.cantidadPendiente);
		pvo.setOcAsociada(this.ocAsociada);
		pvo.setProveedor(this.proveedor);
		pvo.setRecibido(this.recibido);
		pvo.setRodamiento(this.rodamiento);
		return pvo;
	}
	
	public void setVO(PedidoAbastecimientoVO p){
		this.idPedido = p.getIdPedido();
		this.cantidadPedida = p.getCantidadPedida();
		this.cantidadPendiente = p.getCantidadPendiente();
		this.ocAsociada = p.getOcAsociada();
		this.rodamiento = p.getRodamiento();
		this.recibido = p.isRecibido();
	}


	public static ArrayList<PedidoAbastecimientoVO> getVOList(
			ArrayList<PedidoDeAbastecimiento> ps) {
		ArrayList<PedidoAbastecimientoVO> pVoList = new ArrayList<PedidoAbastecimientoVO>();
		for(PedidoDeAbastecimiento p: ps)
			pVoList.add(p.getVO());
		return pVoList;
	
	}
}

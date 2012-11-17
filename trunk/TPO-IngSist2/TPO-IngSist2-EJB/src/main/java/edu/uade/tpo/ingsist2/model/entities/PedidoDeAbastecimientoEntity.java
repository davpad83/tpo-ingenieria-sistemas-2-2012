package edu.uade.tpo.ingsist2.model.entities;


import java.util.ArrayList;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.view.vo.PedidoAbastecimientoVO;

@Entity
@Table(name=EntitiesTablesNames.PEDIDO_DE_ABASTECIMIENTO)
public class PedidoDeAbastecimientoEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int idPedido;
	
	@ManyToOne
	private RodamientoEntity rodamiento;
	
	private boolean recibido;
	
	@ManyToOne
	private OrdenDeCompraEntity ocAsociada;
	
	@ManyToOne
	private ProveedorEntity proveedor;
	
	private int cantidadPedida;
	private int cantidadPendiente;
	
	
	public PedidoDeAbastecimientoEntity() {
		super();
	}

	
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
		ArrayList<PedidoDeAbastecimientoEntity> ps) {
		ArrayList<PedidoAbastecimientoVO> pVoList = new ArrayList<PedidoAbastecimientoVO>();
		for(PedidoDeAbastecimientoEntity p: ps)
		pVoList.add(p.getVO());
		return pVoList;
	
	}
}

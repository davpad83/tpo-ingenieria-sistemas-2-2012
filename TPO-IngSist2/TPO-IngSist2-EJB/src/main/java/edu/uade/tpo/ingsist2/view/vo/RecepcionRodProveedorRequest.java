package edu.uade.tpo.ingsist2.view.vo;

import java.util.ArrayList;
import java.util.List;

public class RecepcionRodProveedorRequest {

	private List<RodamientoListaVO> listaRodVO;
	
	public RecepcionRodProveedorRequest(){
		listaRodVO = new ArrayList<RodamientoListaVO>();
	}
	
	public List<RodamientoListaVO> getListaRodVO() {
		return listaRodVO;
	}

	public void setListaRodVO(List<RodamientoListaVO> listaRodVO) {
		this.listaRodVO = listaRodVO;
	}

	private class RodamientoListaVO {
		
		private int cantidad;
		private int idPedidoAbastecimiento;
		private String SKF;
		private String marca;
		private String pais;
		
		public int getCantidad() {
			return cantidad;
		}
		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
		public int getIdPedidoAbastecimiento() {
			return idPedidoAbastecimiento;
		}
		public void setIdPedidoAbastecimiento(int idPedidoAbastecimiento) {
			this.idPedidoAbastecimiento = idPedidoAbastecimiento;
		}
		public String getSKF() {
			return SKF;
		}
		public void setSKF(String sKF) {
			SKF = sKF;
		}
		public String getMarca() {
			return marca;
		}
		public void setMarca(String marca) {
			this.marca = marca;
		}
		public String getPais() {
			return pais;
		}
		public void setPais(String pais) {
			this.pais = pais;
		}
	}
	
}

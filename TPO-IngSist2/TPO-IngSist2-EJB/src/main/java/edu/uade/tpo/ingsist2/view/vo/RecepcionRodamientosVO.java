package edu.uade.tpo.ingsist2.view.vo;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class RecepcionRodamientosVO {

	private List<RodamientoListaVO> listaRodVO;
	
	public RecepcionRodamientosVO(){
		listaRodVO = new ArrayList<RodamientoListaVO>();
	}
	public List<RodamientoListaVO> getListaRodVO() {
		return listaRodVO;
	}
	public void setListaRodVO(List<RodamientoListaVO> listaRodVO) {
		this.listaRodVO = listaRodVO;
	}

	public class RodamientoListaVO {
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
		
		public RodamientoVO getRodamiento(){
			RodamientoVO r = new RodamientoVO();
			r.setCodigoSKF(SKF);
			r.setMarca(marca);
			r.setPais(pais);
			return r;			
		}
		
	}
	
	public String toXML() {
		String xml = "";
		try {
			XStream xs = new XStream();
			
			xs.alias("listaRodamientos", RecepcionRodamientosVO.class);
			xs.alias("rodamientoLista", RodamientoListaVO.class);
			
			xs.aliasField("idPedidoAbastecimiento", RecepcionRodamientosVO.RodamientoListaVO.class, "idPedidoAbastecimiento");
			xs.aliasField("SKF", RecepcionRodamientosVO.RodamientoListaVO.class, "SKF");
			xs.aliasField("marca", RecepcionRodamientosVO.RodamientoListaVO.class, "marca");
			xs.aliasField("pais", RecepcionRodamientosVO.RodamientoListaVO.class, "pais");
			xs.aliasField("cantidad", RecepcionRodamientosVO.RodamientoListaVO.class, "cantidad");

			xml = xs.toXML(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}
	
	
	
	public RecepcionRodamientosVO fromXML(String xml) {
		RecepcionRodamientosVO rvo= new RecepcionRodamientosVO();
		try {
			XStream xs = new XStream();
			
			rvo = (RecepcionRodamientosVO) xs.fromXML(xml);			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rvo;
	}
	
}

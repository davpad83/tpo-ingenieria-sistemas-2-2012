package edu.uade.tpo.ingsist2.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.view.vo.RodamientoVO;

@Entity
public class Rodamiento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String codigoSKF;
	private String marca;
	private String pais;

	@Embedded
	private Stock stock;

	public Rodamiento() {
		// empty
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoSKF() {
		return codigoSKF;
	}

	public void setCodigoSKF(String codigoSKF) {
		this.codigoSKF = codigoSKF;
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

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public RodamientoVO getVO() {
		RodamientoVO r = new RodamientoVO();
		r.setId(this.id);
		r.setCodigoSKF(this.codigoSKF);
		r.setMarca(this.marca);
		r.setPais(this.pais);
		r.setStock(this.stock.getCantidad());
		return r;
	}

	public void setVO(RodamientoVO r){
		this.id = r.getId();
		this.codigoSKF = r.getCodigoSKF();
		this.marca = r.getMarca();
		this.pais = r.getPais();
		this.stock.setCantidad(r.getStock());
	}
	
	public static ArrayList<RodamientoVO> getVOList(List<Rodamiento> rList){
		ArrayList<RodamientoVO> rVOList = new ArrayList<RodamientoVO>();
		for(Rodamiento r : rList)
			rVOList.add(r.getVO());
		return rVOList;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoSKF == null) ? 0 : codigoSKF.hashCode());
		result = prime * result + id;
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rodamiento other = (Rodamiento) obj;
		if (codigoSKF == null) {
			if (other.codigoSKF != null)
				return false;
		} else if (!codigoSKF.equals(other.codigoSKF))
			return false;
		if (id != other.id)
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		return true;
	}
}

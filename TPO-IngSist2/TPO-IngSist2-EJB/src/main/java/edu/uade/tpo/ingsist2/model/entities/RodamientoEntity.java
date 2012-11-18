package edu.uade.tpo.ingsist2.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.view.vo.RodamientoVO;

@Entity
@Table(name = EntitiesTablesNames.RODAMIENTO)
public class RodamientoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String codigoSKF;
	private String marca;
	private String pais;
	private int stock;

	public RodamientoEntity() {
		super();
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public RodamientoVO getVO() {
		RodamientoVO r = new RodamientoVO();
		r.setId(this.id);
		r.setCodigoSKF(this.codigoSKF);
		r.setMarca(this.marca);
		r.setPais(this.pais);
		r.setStock(this.stock);
		return r;
	}

	public void setVO(RodamientoVO rodamientoVO) {
		this.id = rodamientoVO.getId();
		this.codigoSKF = rodamientoVO.getCodigoSKF();
		this.marca = rodamientoVO.getMarca();
		this.pais = rodamientoVO.getPais();
		this.stock = rodamientoVO.getStock();
	}

	public static ArrayList<RodamientoVO> getVOList(List<RodamientoEntity> rList) {
		if (rList != null && !rList.isEmpty()) {
			ArrayList<RodamientoVO> rVOList = new ArrayList<RodamientoVO>();
			for (RodamientoEntity r : rList)
				rVOList.add(r.getVO());
			return rVOList;
		} else
			return new ArrayList<RodamientoVO>();
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
		RodamientoEntity other = (RodamientoEntity) obj;
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

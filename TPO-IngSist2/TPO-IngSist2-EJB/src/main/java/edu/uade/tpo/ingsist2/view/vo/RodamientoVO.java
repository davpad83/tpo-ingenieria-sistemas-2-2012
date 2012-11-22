package edu.uade.tpo.ingsist2.view.vo;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;

public class RodamientoVO implements Serializable {
	
	private static final long serialVersionUID = 5442056498221211003L;
	
	private int id;
	private String codigoSKF;
	private String marca;
	private String pais;
	private int stock;
	
	public RodamientoVO() {
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
	
	public String getMarca() {
		return marca;
	}
	
	public String getPais() {
		return pais;
	}
	
	public int getStock(){
		return stock;
	}

	public void setCodigoSKF(String codigoSKF) {
		this.codigoSKF = codigoSKF;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setStock(int stock) {
		this.stock = stock;
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
		result = prime * result + stock;
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
		RodamientoVO other = (RodamientoVO) obj;
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

public static XStream setXMLParameters(XStream xs, boolean omitId) {
		xs.alias("Rodamiento", RodamientoVO.class);
		if(omitId)
			xs.omitField(RodamientoVO.class, "id");
		return xs;
	}

}

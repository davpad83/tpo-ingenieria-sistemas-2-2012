package edu.uade.tpo.ingsist2.view.vo;

import java.io.Serializable;

public class ProveedorVO implements Serializable {

	private static final long serialVersionUID = 7706606121975723534L;

	private int id;
	private String cuit;
	private String nombre;
	private String tiempoDeEntrega;

	public ProveedorVO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTiempoDeEntrega() {
		return tiempoDeEntrega;
	}

	public void setTiempoDeEntrega(String tiempoDeEntrega) {
		this.tiempoDeEntrega = tiempoDeEntrega;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuit == null) ? 0 : cuit.hashCode());
		result = prime * result + id;
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
		ProveedorVO other = (ProveedorVO) obj;
		if (cuit == null) {
			if (other.cuit != null)
				return false;
		} else if (!cuit.equals(other.cuit))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProveedorVO [id=" + id + ", cuit=" + cuit + ", nombre="
				+ nombre + ", tiempoDeEntrega=" + tiempoDeEntrega + "]";
	}
}

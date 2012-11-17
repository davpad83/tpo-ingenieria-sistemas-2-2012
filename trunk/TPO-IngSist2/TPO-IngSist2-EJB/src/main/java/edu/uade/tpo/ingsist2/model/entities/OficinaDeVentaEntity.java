package edu.uade.tpo.ingsist2.model.entities;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.view.vo.OficinaDeVentaVO;

@Entity
@Table(name=EntitiesTablesNames.OFICINA_DE_VENTA)
public class OficinaDeVentaEntity {

	@Id
	private int id;
	
	private String name;
	
	private String direccion;

	public OficinaDeVentaVO getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	
	
}

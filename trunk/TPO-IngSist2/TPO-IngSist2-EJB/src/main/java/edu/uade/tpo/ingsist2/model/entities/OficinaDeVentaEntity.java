package edu.uade.tpo.ingsist2.model.entities;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.view.vo.OficinaDeVentaVO;

@Entity
@Table(name=EntitiesTablesNames.OFICINA_DE_VENTA)
public class OficinaDeVentaEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String nombre;
	private String direccion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public OficinaDeVentaVO getVO() {
		OficinaDeVentaVO odvvo = new OficinaDeVentaVO();
		odvvo.setDireccion(this.direccion);
		odvvo.setIdODV(this.getId());
		odvvo.setNombre(this.getNombre());
		return odvvo;
	}

	public void setVO(OficinaDeVentaVO odv) {
		this.id = odv.getIdODV();
		this.nombre = odv.getNombre();
		this.direccion = odv.getDireccion();
	}
}

package edu.uade.tpo.ingsist2.model.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;

@Entity
@Table(name=EntitiesTablesNames.PROVEEDOR)
public class ProveedorEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String cuit;
	private String nombre;
	
	public ProveedorEntity() {
		super();
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
	
	public ProveedorVO getVO(){
		ProveedorVO pvo = new ProveedorVO();
		pvo.setId(this.id);
		pvo.setCuit(this.cuit);
		pvo.setNombre(this.nombre);
		return pvo;
	}
	
	public void setVO(ProveedorVO p){
		this.id = p.getId();
		this.cuit = p.getCuit();
		this.nombre = p.getNombre();
	}
	
	public static ArrayList<ProveedorVO> getVOList(List<ProveedorEntity> ps){
		ArrayList<ProveedorVO> pVoList = new ArrayList<ProveedorVO>();
		for(ProveedorEntity p: ps)
			pVoList.add(p.getVO());
		return pVoList;
	}
}

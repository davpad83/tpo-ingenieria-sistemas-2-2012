package edu.uade.tpo.ingsist2.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.view.vo.ItemListaVO;

@Entity
@Table(name = "ItemLista")
public class ItemListaEntity {

	@Id
	private int id;

	@ManyToOne
	private RodamientoEntity rodamiento;
	private float precio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RodamientoEntity getRodamiento() {
		return rodamiento;
	}

	public void setRodamiento(RodamientoEntity rodamiento) {
		this.rodamiento = rodamiento;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public ItemListaVO getVO(){
		ItemListaVO ilvo = new ItemListaVO();
		ilvo.setId(this.id);
		ilvo.setPrecio(this.precio);
		ilvo.setRodamiento(this.rodamiento);
		return ilvo;
	}
	
	public void setVO(ItemListaVO lpvo){
		this.id = lpvo.getId();
		this.precio = lpvo.getPrecio();
		this.rodamiento = lpvo.getRodamiento();
	}
	
	public static List<ItemListaVO> getVOList(List<ItemListaEntity> itl){
		List<ItemListaVO> itlvo = new ArrayList<ItemListaVO>();
		for(ItemListaEntity il : itl)
			itlvo.add(il.getVO());
		return itlvo;
	}	
}

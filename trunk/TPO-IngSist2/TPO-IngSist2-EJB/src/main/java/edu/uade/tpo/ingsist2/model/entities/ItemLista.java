package edu.uade.tpo.ingsist2.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.view.vo.ItemListaVO;

@Entity
public class ItemLista {

	@Id
	private int id;

	@ManyToOne
	private Rodamiento rodamiento;
	private float precio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Rodamiento getRodamiento() {
		return rodamiento;
	}

	public void setRodamiento(Rodamiento rodamiento) {
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
	
	public static List<ItemListaVO> getVOList(List<ItemLista> itl){
		List<ItemListaVO> itlvo = new ArrayList<ItemListaVO>();
		for(ItemLista il : itl)
			itlvo.add(il.getVO());
		return itlvo;
	}	
}

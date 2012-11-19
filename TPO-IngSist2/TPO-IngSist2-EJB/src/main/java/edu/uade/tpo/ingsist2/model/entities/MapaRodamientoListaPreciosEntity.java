package edu.uade.tpo.ingsist2.model.entities;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Embeddable
public class MapaRodamientoListaPreciosEntity {

//	@Id 
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	private int id;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn
	private RodamientoEntity rodamiento;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn
	private ListaPreciosEntity listaPrecios;

	public ListaPreciosEntity getListaPrecios() {
		return listaPrecios;
	}

	public void setListaPrecios(ListaPreciosEntity listaPrecios) {
		this.listaPrecios = listaPrecios;
	}

	public RodamientoEntity getRodamiento() {
		return rodamiento;
	}

	public void setRodamiento(RodamientoEntity rodamiento) {
		this.rodamiento = rodamiento;
	}
}

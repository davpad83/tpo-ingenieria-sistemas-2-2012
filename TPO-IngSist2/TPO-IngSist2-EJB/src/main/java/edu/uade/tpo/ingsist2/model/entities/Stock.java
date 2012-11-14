package edu.uade.tpo.ingsist2.model.entities;

import javax.persistence.*;

@Embeddable
public class Stock {

	private Rodamiento rodamiento;
	private int cantidad;
	
	public void aumentarStock(int cant){
		this.cantidad=cantidad + cant;
	}
	
	public void disminuirStock(int cant){
		this.cantidad=cantidad - cant;
	}
	
	public boolean verificarStock(){
		if(cantidad == 0)
			return true;
		return false;
	}

	public Rodamiento getRodamiento() {
		return rodamiento;
	}

	public void setRodamiento(Rodamiento rodamiento) {
		this.rodamiento = rodamiento;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}

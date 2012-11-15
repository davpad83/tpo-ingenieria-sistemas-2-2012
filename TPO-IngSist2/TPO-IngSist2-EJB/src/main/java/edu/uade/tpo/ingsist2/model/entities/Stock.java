package edu.uade.tpo.ingsist2.model.entities;

import javax.persistence.*;

@Embeddable
public class Stock {

	private Rodamiento rodamiento;
	private int cantidadStock;
	
	public void aumentarStock(int cant){
		this.cantidadStock=cantidadStock + cant;
	}
	
	public void disminuirStock(int cant){
		this.cantidadStock=cantidadStock - cant;
	}
	
	public boolean verificarStock(){
		if(cantidadStock == 0)
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
		return cantidadStock;
	}

	public void setCantidad(int cantidad) {
		this.cantidadStock = cantidad;
	}
	
	
}

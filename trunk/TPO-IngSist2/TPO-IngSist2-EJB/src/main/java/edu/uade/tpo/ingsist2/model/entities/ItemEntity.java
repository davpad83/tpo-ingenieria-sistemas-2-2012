package edu.uade.tpo.ingsist2.model.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.uade.tpo.ingsist2.view.vo.ItemVO;
@Entity
@Table(name = EntitiesTablesNames.ITEM_REMITO)
public class ItemEntity {

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
		private int idOrdenDeCompra;
		private int cantidad;
		
		@ManyToOne(cascade=CascadeType.ALL)
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
		
		public void setRodamiento(String SKF, String marca, String Pais) {
			this.rodamiento.setCodigoSKF(SKF);
			this.rodamiento.setMarca(marca);
			this.rodamiento.setPais(Pais);
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}

		public int getCantidad() {
			return cantidad;
		}
		
		public ItemVO getVO(){
			ItemVO ilvo = new ItemVO();
			ilvo.setId(this.id);
			ilvo.setCantidad(this.cantidad);
			ilvo.setMarca(this.rodamiento.getMarca());
			ilvo.setPais(this.rodamiento.getPais());
			ilvo.setSKF(this.rodamiento.getCodigoSKF());
			ilvo.setPrecio(this.precio);
			return ilvo;
		}

		public void setIdOrdenDeCompra(int idOrdenDeCompra) {
			this.idOrdenDeCompra = idOrdenDeCompra;
		}

		public int getIdOrdenDeCompra() {
			return idOrdenDeCompra;
		}
		
}

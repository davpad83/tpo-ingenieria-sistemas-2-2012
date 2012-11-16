package edu.uade.tpo.ingsist2.model.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cotizacion")
public class CotizacionEntity {

	@Id
	private int id;
}

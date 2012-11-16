package edu.uade.tpo.ingsist2.model.entities;

import javax.persistence.*;

import edu.uade.tpo.ingsist2.view.vo.OficinaDeVentaVO;

@Entity
@Table(name="OficinaDeVenta")
public class OficinaDeVentaEntity {

	@Id
	private int id;

	public OficinaDeVentaVO getVO() {
		// TODO Auto-generated method stub
		return null;
	}
}

package edu.uade.tpo.ingsist2.controllers;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.ListaPrecios;
import edu.uade.tpo.ingsist2.model.entities.ListaPreciosEntity;
import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;

/**
 * Session Bean implementation class ListaProveedorBean
 */
@Stateless
public class ListaPreciosControllerBean implements ListaPreciosController {

	private static final Logger LOGGER = Logger
			.getLogger(ListaPreciosControllerBean.class);

	@EJB
	private ListaPrecios listaPrecios;

	@Override
	public void agregarListaProveedor(ListaPreciosVO lpvo) {
		ListaPreciosEntity lp = new ListaPreciosEntity();
		lp.setVO(lpvo);
		listaPrecios.agregarListaPrecios(lp);
	}
}

package edu.uade.tpo.ingsist2.test.model;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.junit.Test;

import edu.uade.tpo.ingsist2.controllers.RecepcionRodamientosController;
import edu.uade.tpo.ingsist2.model.CotizacionBean;
import edu.uade.tpo.ingsist2.model.ListaPreciosBean;
import edu.uade.tpo.ingsist2.model.ProveedorBean;
import edu.uade.tpo.ingsist2.model.Rodamiento;
import edu.uade.tpo.ingsist2.model.RodamientoBean;
import edu.uade.tpo.ingsist2.model.entities.ItemListaEntity;
import edu.uade.tpo.ingsist2.model.entities.ListaPreciosEntity;
import edu.uade.tpo.ingsist2.model.entities.ProveedorEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;

@Stateless
public class CotizacionTestBean{

	private static final Logger LOGGER = Logger.getLogger(CotizacionTestBean.class);
	
	@EJB
	private RodamientoBean rb;
	
	@EJB
	private RodamientoEntity re;
	
	
	@Test
	public void getItemListaConMenorPrecioConMarcaTest(){
		
	    CotizacionBean cb = new CotizacionBean();
		
		re= new RodamientoEntity();
		
		re.setCodigoSKF("LM11749");
		re.setPais("Ferrari");
		re.setMarca("China");
		re.setStock(0);
	
		
		rb= new RodamientoBean();
				
		rb.guardarRodamiento(re);		
		
		ItemListaEntity ite = cb.getItemListaConMenorPrecioConMarca(re);
		if(ite!=null){
			
		LOGGER.debug("El item lista tiene: \nID: " + ite.getId()+"\nPrecio: "+ite.getPrecio());
		
		LOGGER.debug("Buscando Lista de precios por idItemLista");
		
		ListaPreciosBean lpb = new ListaPreciosBean();
		ListaPreciosEntity lpe = lpb.getListaPrecioPorIdItemLista(ite.getId());
		
		if(lpe !=null)
			LOGGER.debug("La lista de precios fue encontrada y su id es: "+lpe.getIdLista());
		else 
			LOGGER.debug("La lista de precios no pudo ser encontrada.");
		} else {
			LOGGER.error("El item lista no fue encontrado.");
		}     
	}
}

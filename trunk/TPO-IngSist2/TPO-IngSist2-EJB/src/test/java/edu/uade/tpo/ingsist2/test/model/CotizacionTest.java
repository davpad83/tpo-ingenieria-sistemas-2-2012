package edu.uade.tpo.ingsist2.test.model;

import org.apache.log4j.Logger;
import org.junit.Test;

import edu.uade.tpo.ingsist2.model.CotizacionBean;
import edu.uade.tpo.ingsist2.model.ListaPreciosBean;
import edu.uade.tpo.ingsist2.model.entities.ItemListaEntity;
import edu.uade.tpo.ingsist2.model.entities.ListaPreciosEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;

public class CotizacionTest {

	private static final Logger LOGGER = Logger.getLogger(CotizacionTest.class);
	
	@Test
	public void getItemListaConMenorPrecioConMarcaTest(){
		CotizacionBean cb = new CotizacionBean();
		RodamientoEntity re = new RodamientoEntity();
		re.setCodigoSKF("LM11749");
		re.setPais("Ferrari");
		re.setMarca("China");
		re.setStock(0);
		
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

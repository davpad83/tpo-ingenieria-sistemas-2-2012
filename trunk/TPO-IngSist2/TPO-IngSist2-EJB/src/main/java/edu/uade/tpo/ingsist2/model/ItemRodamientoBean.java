package edu.uade.tpo.ingsist2.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;

/**
 * Session Bean implementation class ItemRodamiento
 */

@Stateless(name = "ItemRodamientoBean")
public class ItemRodamientoBean implements ItemRodamiento {

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	private static final Logger LOGGER = Logger.getLogger(ItemRodamientoBean.class);
	
	
    public ItemRodamientoBean() {
        // TODO Auto-generated constructor stub
    }
    
    
    
    public void guardarItemRodamientoCotizacion(ItemRodamientoEntity ir){
    	LOGGER.info("Procesando guardar el item rodamiento");
		ItemRodamientoEntity irGuardado = null;
    	
		try {
            irGuardado = (ItemRodamientoEntity) entityManager.merge(ir);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al guardar el item rodamiento");
			LOGGER.error(e);
		}
          LOGGER.info("Item rodamiento guardada con id: " + irGuardado.getId());
    	
    	
    	
    }

}

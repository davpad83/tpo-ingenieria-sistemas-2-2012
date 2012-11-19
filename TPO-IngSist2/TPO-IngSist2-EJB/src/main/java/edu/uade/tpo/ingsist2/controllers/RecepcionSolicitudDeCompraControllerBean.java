package edu.uade.tpo.ingsist2.controllers;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.Cotizacion;
import edu.uade.tpo.ingsist2.model.OrdenDeCompra;
import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.view.vo.OrdenDeCompraVO;

/**
 * Session Bean implementation class RecepcionSolicitudDeCompraControllerBean
 */
@Stateless
public class RecepcionSolicitudDeCompraControllerBean implements RecepcionSolicitudDeCompraController {

	private static final Logger LOGGER = Logger.getLogger(RecepcionSolicitudDeCompraControllerBean.class);

	@EJB
	private OrdenDeCompra ordenDeCompra;
	
	@EJB
	private Cotizacion cotizacion;
	
	@Override
	public void procesarSolicitudDeCompra(OrdenDeCompraVO ocvo) {
		/**
		 * Implementacion:
		 * 
		 * 1) Valido la OC.
		 * 2) La Registro.
		 * 3) Itero entre los ItemRodamiento
		 * 		3.1) Busco la cotizacion relacionada a ese item.
		 * 		3.2) 
		 * 
		 */
		OrdenDeCompraEntity oce = new OrdenDeCompraEntity();
		oce.setVO(ocvo);
		if(ordenDeCompra.validarOrdenDeCompra(oce)){
			ordenDeCompra.guardarOrdenDeCompra(oce);
			
			for(ItemRodamientoEntity ire : oce.getItems()){

//				cotizacion.verificarStock(ire);
				
				
			}
		}		
	}
	
	

	
	
}

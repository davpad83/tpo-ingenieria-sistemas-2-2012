package edu.uade.tpo.ingsist2.controllers;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.view.vo.RecepcionRodProveedorRequest;

/**
 * Session Bean implementation class ListaProveedorBean
 */
@Stateless
public class ListaProveedorControllerBean implements ListaProveedorController {

	private static final Logger LOGGER = Logger.getLogger(ListaProveedorControllerBean.class);
	
	@Override
    public void agregarListaProveedor(RecepcionRodProveedorRequest lpr) {
    	if(validarListaProveedorRequest(lpr)){
    		
    	}
    }
    
    private boolean validarListaProveedorRequest(RecepcionRodProveedorRequest lpr){
    	//TODO completar
		return true;
    }
}

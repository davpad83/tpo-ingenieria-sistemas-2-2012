package edu.uade.tpo.ingsist2.facade;

import javax.ejb.Stateless;

@Stateless
public class FacadeBean implements Facade {

    public FacadeBean() {
    }

	@Override
	public String testingFacade() {
		return "Prueba exitosa";
	}

}

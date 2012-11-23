package edu.uade.tpo.ingsist2.integration;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import edu.uade.tpo.ingsist2.view.facade.Facade;
import edu.uade.tpo.ingsist2.view.facade.FacadeBean;

@RunWith(Arquillian.class)
public class HappyScenariosIntegrationTests {

	@EJB
	private Facade facade;
	
	@Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClass(FacadeBean.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "TPO-IngSist2-EAR.ear");
    }
	
	@Test
	public void enviarCotizacionTest(){
//		cot1 = MockDataGenerator.getRandomCotizacionVO();
//		cot2 = MockDataGenerator.getRandomCotizacionVO();
//		cot3 = MockDataGenerator.getRandomCotizacionVO();
		//Enviar la cotizacion al ws transformandola a XML.
		
		facade.guardarOficinaDeVenta(null);
		
		Assert.fail("Not yet implemented");
	}
}

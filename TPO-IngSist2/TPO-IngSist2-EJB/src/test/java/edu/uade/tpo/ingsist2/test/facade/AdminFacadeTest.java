package edu.uade.tpo.ingsist2.test.facade;
import java.util.ArrayList;

import javax.ejb.EJB;

import org.junit.Test;

import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.facade.Facade;
import edu.uade.tpo.ingsist2.view.facade.FacadeBean;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;
import junit.framework.TestCase;


public class AdminFacadeTest extends TestCase {

	@EJB
	private Facade af;

	@Test
	public void testGetListaProveedores(){
		ArrayList<ProveedorVO> prove = af.getProveedores();
		
		assertTrue(prove.isEmpty());
	}	
}

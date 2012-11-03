package edu.uade.tpo.ingsist2.test.facade;
import java.util.ArrayList;

import javax.ejb.EJB;

import org.junit.Test;

import edu.uade.tpo.ingsist2.entities.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.facade.AdminFacade;
import edu.uade.tpo.ingsist2.facade.AdminFacadeBean;
import edu.uade.tpo.ingsist2.mock.MockDataGenerator;
import junit.framework.TestCase;


public class AdminFacadeTest extends TestCase {

	@EJB
	private AdminFacade af;

	@Test
	public void testGetListaProveedores(){
		ArrayList<ProveedorVO> prove = af.getProveedores();
		
		assertTrue(prove.isEmpty());
	}
	
}

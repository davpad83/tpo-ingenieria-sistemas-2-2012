package edu.uade.tpo.ingsist2.test.facade;
import java.util.ArrayList;

import org.junit.Test;

import edu.uade.tpo.ingsist2.entities.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.facade.AdminFacade;
import edu.uade.tpo.ingsist2.facade.AdminFacadeBean;
import edu.uade.tpo.ingsist2.mock.MockDataGenerator;
import junit.framework.TestCase;


public class AdminFacadeTest extends TestCase {

	@Test
	public void testGetListaProveedores(){
		AdminFacade af = new AdminFacadeBean();
		ArrayList<ProveedorVO> prove = af.getProveedores();
		
		assertEquals(MockDataGenerator.getListaProveedorVOMock(), af.getProveedores());
	}
}

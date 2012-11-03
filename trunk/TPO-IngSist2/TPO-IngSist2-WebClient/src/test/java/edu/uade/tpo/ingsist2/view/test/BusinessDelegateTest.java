package edu.uade.tpo.ingsist2.view.test;

import org.apache.log4j.Logger;
import org.hibernate.annotations.TypeDef;
import org.junit.Test;

import edu.uade.tpo.ingsist2.entities.Proveedor;
import edu.uade.tpo.ingsist2.entities.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.view.bd.BusinessDelegate;

import junit.framework.TestCase;

public class BusinessDelegateTest extends TestCase {

	Logger logger = Logger.getLogger(BusinessDelegateTest.class);
	
	@Test
	public void testConnection(){
		BusinessDelegate bd = new BusinessDelegate();
		
		assertTrue(bd.isConnectionWorking());
	}
	
	@Test
	public void testGetProveedores(){
		BusinessDelegate bd = new BusinessDelegate();
		
		assertTrue(bd.getProveedores()==null || bd.getProveedores().isEmpty());
	}
	
	@Test
	public void testAgregarProveedor(){
		BusinessDelegate bd = new BusinessDelegate();

		ProveedorVO p = new ProveedorVO();
		p.setId(1);
		p.setCuit("123115141");
		p.setNombre("Sarlanga");
		bd.guardarProveedor(p);
		
		ProveedorVO pObtenido = bd.getProveedor(1);
		
		assertNotNull(pObtenido);
		
		assertTrue(pObtenido instanceof ProveedorVO);
		
//		assertEquals(p, bd.getProveedor(1));
	}
	
	@Test
	public void testEliminarProveedor(){
		BusinessDelegate bd = new BusinessDelegate();
		
		bd.eliminarProveedor(1);
		assertNull(bd.getProveedor(1));
	}
}

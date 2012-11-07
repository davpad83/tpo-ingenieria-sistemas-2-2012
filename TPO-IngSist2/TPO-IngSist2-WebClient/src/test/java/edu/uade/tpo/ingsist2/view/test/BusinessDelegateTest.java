package edu.uade.tpo.ingsist2.view.test;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.hibernate.annotations.TypeDef;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.uade.tpo.ingsist2.view.bd.BusinessDelegate;
import edu.uade.tpo.ingsist2.entities.vo.ProveedorVO;


public class BusinessDelegateTest {

	Logger logger = Logger.getLogger(BusinessDelegateTest.class);
	
	BusinessDelegate bd;
	
	@Before
	public void setupTest(){
		bd = new BusinessDelegate();
	}
	
	@Test
	public void testConnection(){
		assertTrue(bd.isConnectionWorking());
	}
	
	@Test
	public void testGetProveedores(){
		assertTrue(bd.getProveedores()==null || bd.getProveedores().isEmpty());
	}
	
	@Test
	public void testAgregarProveedor(){
		ProveedorVO p = new ProveedorVO();
		p.setId(1);
		p.setCuit("123115141");
		p.setNombre("Sarlanga");
		
		bd.guardarProveedor(p);
		
		ProveedorVO pObtenido = (ProveedorVO) bd.getProveedor(1);
		
		assertNotNull(pObtenido);
		assertTrue(pObtenido instanceof ProveedorVO);
//		assertEquals(p, bd.getProveedor(1));
	}
	
	@Test
	public void testEliminarProveedor(){
		bd.eliminarProveedor(1);
		assertNull(bd.getProveedor(1));
	}
}

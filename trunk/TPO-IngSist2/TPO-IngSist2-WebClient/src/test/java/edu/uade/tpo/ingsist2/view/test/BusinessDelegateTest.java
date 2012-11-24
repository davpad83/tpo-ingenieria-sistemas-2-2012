package edu.uade.tpo.ingsist2.view.test;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.uade.tpo.ingsist2.view.bd.BusinessDelegate;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;


public class BusinessDelegateTest {

	Logger logger = Logger.getLogger(BusinessDelegateTest.class);
	
	private BusinessDelegate bd;
	
	@Before
	public void setupTest(){
		bd = new BusinessDelegate();
	}
	
	@After
	public void desp(){
		System.out.println("se hizo desp");
	}
	
	@Test
	public void testConnection(){
		assertTrue(bd.isConnectionWorking());
	}
	
	@Test
	public void testGetProveedores(){
		assertTrue(bd.getProveedores()==null || bd.getProveedores().isEmpty());
	}
	
	// TEST WS
	
	@Test
	public void testGetCotizacion(){
		
		SolicitudCotizacionRequest sr = new SolicitudCotizacionRequest();
		SolicitudCotizacionResponse scresp = new SolicitudCotizacionResponse();		
		
		sr.setIdPedidoCotizacion(150);
		sr.setIdODV(3);
		sr.setSKF("IKL675");
		sr.setMarca("");
		sr.setPais("Holanda");
		sr.setCantidad(125);
		
		scresp  =  bd.recibirSolicitudCotizacion(sr);
		System.out.println("termino con idpedido: "+scresp.getIdPedidoCotizacion());
		System.out.println("con precios: ");
		
		for(int i=0;i<scresp.getRodamientosCotizados().size();i++){

		System.out.println("marca: "+scresp.getRodamientosCotizados().get(i).getMarca());
		System.out.println("precio: "+scresp.getRodamientosCotizados().get(i).getPrecioCotizado());
			
		}
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
		assertEquals(p, bd.getProveedor(1));
	}
	
	@Test
	public void testEliminarProveedor(){
		bd.eliminarProveedor(1);
		assertNull(bd.getProveedor(1));
	}
}

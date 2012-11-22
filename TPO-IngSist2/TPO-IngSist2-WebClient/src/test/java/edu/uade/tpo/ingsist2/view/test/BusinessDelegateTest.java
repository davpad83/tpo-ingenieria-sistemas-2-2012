package edu.uade.tpo.ingsist2.view.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.bd.BusinessDelegate;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.view.vo.RodamientoCotizadoVO;
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
		
		sr.setIdPedidoCotizacion(88);
		sr.setIdODV(5);
		sr.setSKF("IKL90");
		sr.setPais("brasil");
		sr.setMarca("mika");
		sr.setCantidad(40);
		
		RodamientoCotizadoVO rcbo = new RodamientoCotizadoVO();
		rcbo.setFechaFin(MockDataGenerator.getRandomFechaVencimiento());
		rcbo.setFechaInicio(new Date());
		rcbo.setEnStock(10);
		rcbo.setPrecioCotizado(134);
		rcbo.setTiempoEstimadoEntrega("3 semanas");
		
		ArrayList<RodamientoCotizadoVO> listrcVO = new ArrayList<RodamientoCotizadoVO>();
		listrcVO.add(rcbo);
		
		scresp  =  bd.recibirSolicitudCotizacion(sr);
		System.out.println("termino con idpedido: "+scresp.getIdPedidoCotizacion());
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

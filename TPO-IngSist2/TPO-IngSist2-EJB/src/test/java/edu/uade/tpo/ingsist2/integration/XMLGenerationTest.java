package edu.uade.tpo.ingsist2.integration;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Test;


import edu.uade.tpo.ingsist2.view.vo.ItemVO;
import edu.uade.tpo.ingsist2.view.vo.RemitoResponse;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCompraRequest;

public class XMLGenerationTest {

	Logger LOGGER = Logger.getLogger(XMLGenerationTest.class);
	
	@Test
	public void ordenDeCompraXMLTest(){
		SolicitudCompraRequest scr = new SolicitudCompraRequest();
		scr.setIdODV(10);
		scr.setIdOrdenDeCompra(4);
		ArrayList<ItemVO> livo = new ArrayList<ItemVO>();
		ItemVO ivo = new ItemVO();
		ivo.setPrecio(135.35f);
		ivo.setId(4);
		ivo.setMarca("Renault");
		ivo.setPais("Jordania");
		ivo.setSKF("12312DSA");
		livo.add(ivo);
		
		scr.setItems(livo);
		LOGGER.debug("\n"+scr.toXML());
	}
	
	@Test
	public void remitoXMLTest(){
		RemitoResponse rr = new RemitoResponse();
		rr.setIdRemito(10);
		rr.setIdODV(6);
		ArrayList<ItemVO> lrem = new ArrayList<ItemVO>();
		ItemVO ivo = new ItemVO();
		ivo.setId(4);
		ivo.setMarca("Renault");
		ivo.setPais("Jordania");
		ivo.setSKF("12312DSA");
		lrem.add(ivo);
		
		rr.setItems(lrem);
		LOGGER.debug("\n"+rr.toXML());
	}
	
}

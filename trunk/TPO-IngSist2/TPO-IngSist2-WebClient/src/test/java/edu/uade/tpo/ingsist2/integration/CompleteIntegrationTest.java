package edu.uade.tpo.ingsist2.integration;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.uade.tpo.ingsist2.model.util.EnviarMensajeHelper;
import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.jms.JMSQueuesNames;
import edu.uade.tpo.ingsist2.view.vo.CotizacionVO;
import edu.uade.tpo.ingsist2.view.vo.ItemRodamientoVO;
import edu.uade.tpo.ingsist2.view.vo.OrdenDeCompraVO;

public class CompleteIntegrationTest {

	private EnviarMensajeHelper emHelper;
	private CotizacionVO cot1;
	private CotizacionVO cot2;
	private CotizacionVO cot3;
	
	@Before
	public void prepararTest() {
		 emHelper = new EnviarMensajeHelper("127.0.0.1", "1099", JMSQueuesNames.ENVIAR_ORDEN_COMPRA_QUEUE);
	}	
	
	@After
	public void finalizarTest() {
		emHelper.cerrarConexion();
	}
	
	@Test
	public void enviarCotizacionTest(){
		cot1 = MockDataGenerator.getRandomCotizacionVO();
		cot2 = MockDataGenerator.getRandomCotizacionVO();
		cot3 = MockDataGenerator.getRandomCotizacionVO();
		//Enviar la cotizacion al ws transformandola a XML.
	}
	
	@Test
	public void enviarOCCompletaTest(){
		OrdenDeCompraVO ocvo = new OrdenDeCompraVO();
		ocvo.setEstado("Nueva");
		ocvo.setItems(new ArrayList<ItemRodamientoVO>());
		ItemRodamientoVO item1 = new ItemRodamientoVO();
		item1.setRodamiento(cot1.getRodamiento());
//		item1.setCantidad(cot1.get);
//		ocvo.getItems().add();
	}
	
	@Test
	public void enviarMercaderiaDelProveedorTest(){
		
	}
}

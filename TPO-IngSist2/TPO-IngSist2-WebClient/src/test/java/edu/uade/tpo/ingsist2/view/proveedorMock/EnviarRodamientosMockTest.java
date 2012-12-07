package edu.uade.tpo.ingsist2.view.proveedorMock;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.uade.tpo.ingsist2.model.util.EnviarMensajeHelper;
import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.jms.JMSQueuesNames;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodamientosVO;

public class EnviarRodamientosMockTest {
	
	private static final Logger LOGGER = Logger.getLogger(EnviarRodamientosMockTest.class);

	EnviarMensajeHelper emHelper;

	@Before
	public void prepararTest() {
		 emHelper = new EnviarMensajeHelper("127.0.0.1", 1099, JMSQueuesNames.RECEPCION_RODAMIENTOS_QUEUE);
	}

	@Test
	public void basicJMSTest() {
		emHelper.enviarMensaje("TEST - Testeando la cola de mensajes");
	}
	
	
	/**
	 * Se envia una nueva lista de precios a Casa Central * 
	 */
	
	@Test
	public void enviarRodamiento(){
		
		RecepcionRodamientosVO recep = MockDataGenerator.getRandomListaRodamientoVO(25);
		LOGGER.info("Enviando Rodamientos a Casa central....");		
		LOGGER.info("PROVEEDOR MOCK - Rodamientos Enviados: \n"+recep.toXML());
		emHelper.enviarMensaje(recep.toXML());
	}



	@After
	public void finalizarTest() {
		emHelper.cerrarConexion();
	}
}
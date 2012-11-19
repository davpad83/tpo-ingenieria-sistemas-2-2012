package edu.uade.tpo.ingsist2.view.odvMock;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.uade.tpo.ingsist2.model.util.EnviarMensajeHelper;
import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.jms.JMSQueuesNames;
import edu.uade.tpo.ingsist2.view.vo.OrdenDeCompraVO;

public class ODVMockTest {

	private EnviarMensajeHelper emHelper;
	
	@Before
	public void prepararTest() {
		 emHelper = new EnviarMensajeHelper("127.0.0.1", "1099", JMSQueuesNames.ENVIAR_ORDEN_COMPRA_QUEUE);
	}	
	
	@After
	public void finalizarTest() {
		emHelper.cerrarConexion();
	}
	
	@Test
	public void enviarOrdenDeCompraTest(){
		OrdenDeCompraVO ocvo = MockDataGenerator.getRandomOrdenDeCompraVO();
		emHelper.enviarMensaje(ocvo.toXML(true));
	}
	
}

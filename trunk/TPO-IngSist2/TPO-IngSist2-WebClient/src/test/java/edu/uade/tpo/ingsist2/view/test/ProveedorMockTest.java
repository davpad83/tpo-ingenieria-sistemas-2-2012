package edu.uade.tpo.ingsist2.view.test;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Queue;

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

import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;

public class ProveedorMockTest {

	private QueueSession qSession;
	
	@SuppressWarnings("unchecked")
	@Before
	public void prepararTest(){
		Hashtable props = new Hashtable();

		props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");

		try {
			InitialContext ctx = new InitialContext(props);

			// buscar la Connection Factory en JNDI
			QueueConnectionFactory qfactory = (QueueConnectionFactory) ctx
					.lookup("ConnectionFactory");

			// buscar la Cola en JNDI
			Queue queue = (Queue) ctx.lookup("queue/testQueue");

			// crear la connection y la session a partir de la connection
			QueueConnection qCon = qfactory.createQueueConnection();
			qSession = qCon.createQueueSession(false,
					Session.AUTO_ACKNOWLEDGE);

			// crear un producer para enviar mensajes usando la session
//			QueueSender qSender = qSession.createSender(queue);

			// crear un mensaje de tipo text y setearle el contenido
			TextMessage message = qSession.createTextMessage();
			message.setText("Producto21,10");

			// enviar el mensaje
//			qSender.send(message);

		} catch (Exception e) {
			System.out.println("Error al efectuar pedido: " + e);
		}
	}
	
	@Test
	public void enviarNuevaListaPrecios(){
		MockDataGenerator.getListaPreciosVOMock();
		
//		TextMessage message = qSession.createTextMessage();
		
	}
	
	private String fromListaPreciosToVOMock(){
		return null;
	}
	
	@After
	public void enviarMensaje(){
		
	}
	
}

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

import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.jms.JMSQueuesNames;
import edu.uade.tpo.ingsist2.view.vo.OrdenDeCompraVO;

public class ODVMockTest {

	
	private QueueSession qSession;
	private QueueSender qSender;
	private QueueConnection connection;

	@Before
	public void prepararTest() {
		Hashtable<String, String> props = new Hashtable<String, String>();

		props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");

		try {
			InitialContext ctx = new InitialContext(props);

			// buscar la Connection Factory en JNDI
			QueueConnectionFactory qfactory = (QueueConnectionFactory) ctx
					.lookup("ConnectionFactory");

			// buscar la Cola en JNDI
			Queue queue = (Queue) ctx.lookup("queue/" + JMSQueuesNames.ENVIAR_ORDEN_COMPRA_QUEUE);

			// crear la connection y la session a partir de la connection
			connection = qfactory.createQueueConnection();

			qSession = connection.createQueueSession(false,
					Session.AUTO_ACKNOWLEDGE);

			// crear un producer para enviar mensajes usando la session
			qSender = qSession.createSender(queue);

			// crear un mensaje de tipo text y setearle el contenido
			// TextMessage message = qSession.createTextMessage();
			// message.setText("Producto21,10");

			// enviar el mensaje
			// qSender.send(message);

		} catch (Exception e) {
			System.out.println("Error al crear la conexion: " + e);
			e.printStackTrace();
		}
	}	
	
	@After
	public void finalizarTest() {
		if (connection != null) {
			try {
				connection.close();
				qSender.close();
				qSession.close();
			} catch (JMSException e) {
			}
		}
	}
	
	@Test
	public void enviarOrdenDeCompraTest(){
		
		OrdenDeCompraVO ocvo = MockDataGenerator.getRandomOrdenDeCompraVO();
		
		try {
			TextMessage message = qSession.createTextMessage();
			message.setText(ocvo.toXML(true));
			qSender.send(message);
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

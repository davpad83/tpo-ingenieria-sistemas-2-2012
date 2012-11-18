package edu.uade.tpo.ingsist2.view.test;

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

import com.thoughtworks.xstream.XStream;

import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;

public class ProveedorMockTest {

	private QueueSession qSession;
	private QueueSender qSender;
	private QueueConnection connection;

	@SuppressWarnings("unchecked")
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
			Queue queue = (Queue) ctx.lookup("queue/EnviarListaPrecios");

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

	@Test
	public void basicJMSTest(){
		try {
			TextMessage message = qSession.createTextMessage();
			message.setText("TEST - Testeando la cola de mensajes");
			qSender.send(message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void enviarNuevaListaPrecios() {
		ListaPreciosVO listaPrecios = MockDataGenerator
				.getRandomListaPreciosVO();
		try {
			TextMessage message = qSession.createTextMessage();
			message.setText(listaPrecios.toXML(true));
			qSender.send(message);
		} catch (JMSException e) {
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
}

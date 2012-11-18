package edu.uade.tpo.ingsist2.view.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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
import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;

public class ProveedorMockTest {

	private QueueSession qSession;
	private QueueSender qSender;
	private QueueConnection connection;
	private static final String PATH_TO_WEB_PROJECT = "/Users/matiasfavale/Documents/WorkspaceJuno/TPO-IngSist2/TPO-IngSist2-WebClient";

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
	public void basicJMSTest() {
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
				.getRandomListaPreciosVO(30);
		try {
			TextMessage message = qSession.createTextMessage();
			message.setText(listaPrecios.toXML(true));
			qSender.send(message);
			salvarListaPreciosVO(listaPrecios);
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void enviarListasCreadas() {
		int numero = 0;
		File file = null;
		do {
			file = new File(PATH_TO_WEB_PROJECT + "/src/test/ListaPrecios"
					+ numero + ".xml");
			if (file.exists()) {
				numero += 1;
				try {
					TextMessage message = qSession.createTextMessage();
					message.setText(readFileToString(file));
					qSender.send(message);
				} catch (JMSException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} while (file.exists());
	}

	@SuppressWarnings("resource")
	private String readFileToString(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			result.append(line);
			result.append("\n");
		}
		return result.toString();
	}

	private void salvarListaPreciosVO(ListaPreciosVO listaPrecios) {
		int numero = 0;

		File file = new File(PATH_TO_WEB_PROJECT + "/src/test/ListaPrecios"
				+ numero + ".xml");
		while (file.exists()) {
			numero += 1;
			file = new File(PATH_TO_WEB_PROJECT + "/src/test/ListaPrecios"
					+ numero + ".xml");
		}
		try {
			Writer writer = new BufferedWriter(new FileWriter(file));
			writer.write(listaPrecios.toXML(true));
			writer.close();
		} catch (IOException e) {
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

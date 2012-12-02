package edu.uade.tpo.ingsist2.model.util;

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
import org.apache.log4j.Logger;

public class EnviarMensajeHelper {

	private QueueSession qSession;
	private QueueSender qSender;
	private QueueConnection connection;
	private static final Logger LOGGER = Logger
			.getLogger(EnviarMensajeHelper.class);

	public EnviarMensajeHelper(String ip, int puerto, String queueName) {
		inicializar(ip, puerto, queueName);
	}

	private void inicializar(String ip, int puerto, String queueName) {
		LOGGER.info("=====CONEXION A LA COLA "+queueName+" BEGIN=====");
		LOGGER.info("=Inicializando contexto de conexion ...=");
		Hashtable<String, String> props = new Hashtable<String, String>();

		props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		props.put(InitialContext.PROVIDER_URL, "jnp://" + ip + ":" + puerto);

		try {
			InitialContext ctx = new InitialContext(props);

			// buscar la Connection Factory en JNDI
			QueueConnectionFactory qfactory = (QueueConnectionFactory) ctx
					.lookup("ConnectionFactory");

			// buscar la Cola en JNDI
			Queue queue = (Queue) ctx.lookup("queue/" + queueName);

			// crear la connection y la session a partir de la connection
			connection = qfactory.createQueueConnection();

			qSession = connection.createQueueSession(false,
					Session.AUTO_ACKNOWLEDGE);

			// crear un producer para enviar mensajes usando la session
			qSender = qSession.createSender(queue);

		} catch (Exception e) {
			LOGGER.error("Error al crear la conexion: " + e);
			LOGGER.error(e);
		}
		LOGGER.info("=Contexto inicializado=");
	}

	private boolean isConnectionWorking() {
		LOGGER.info("=Verificando conexion a la cola... =");
		boolean isWorking = true;
		if (connection == null && qSession == null) {
			LOGGER.warn("La conexion a la cola no funciona.");
			isWorking = false;
		} else { 
			LOGGER.info("=La conexion ha sido establecida exitosamente=");
		}
		return isWorking;
	}

	public void enviarMensaje(String mensaje) {
		if (isConnectionWorking()) {
			LOGGER.info("=Enviando mensaje ...=");
			TextMessage tMessaje;
			try {
				tMessaje = qSession.createTextMessage();
				tMessaje.setText(mensaje);
				qSender.send(tMessaje);
			} catch (JMSException e) {
				LOGGER.error("Hubo un error al enviar el mensaje.");
				LOGGER.error(e);
			}
			LOGGER.info("=El mensaje ha sido enviado.=");
		}
	}

	public void cerrarConexion() {
		LOGGER.info("=====CONEXION A LA COLA END=====");
		try {
			qSession.close();
			connection.close();
			qSender.close();
		} catch (JMSException e) {
			LOGGER.error("Se produjo un error el cerrar la conexion.");
			LOGGER.error(e);
		}
	}

}

package edu.uade.tpo.ingsist2.view.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;

import edu.uade.tpo.ingsist2.view.facade.MessagesFacade;
import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;

@MessageDriven(
	name="ListaPreciosProveedor",
	activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/" + JMSQueuesNames.LISTA_PRECIOS_QUEUE) 
	}
)
public class ListaPreciosProveedorMDB implements MessageListener {

	@EJB
	private MessagesFacade messagesFacade;

	private static final Logger LOGGER = Logger
			.getLogger(ListaPreciosProveedorMDB.class);

	/**
	 * Se recibe un mensaje de texto en formato XML con los datos necesarios
	 * para agregar una Lista de Proveedor.
	 * 
	 */
	public void onMessage(Message message) {
		TextMessage ts = (TextMessage) message;
		String textReceived = "";
		try {
			textReceived = ts.getText();
		} catch (JMSException e1) {
			e1.printStackTrace();
		}
		if (textReceived.startsWith("TEST")) {
			LOGGER.debug("This is a test message, the message received is: "
					+ textReceived);
		} else {
			LOGGER.info(textReceived);
			ListaPreciosVO lpr = new ListaPreciosVO();
			lpr.fromXML(textReceived);
			messagesFacade.agregarListaProveedor(lpr);
		}
	}
}

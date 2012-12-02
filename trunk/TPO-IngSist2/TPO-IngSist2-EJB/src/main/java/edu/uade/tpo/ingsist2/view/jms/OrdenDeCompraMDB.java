package edu.uade.tpo.ingsist2.view.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.view.facade.MessagesFacade;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCompraRequest;

/**
 * Message-Driven Bean implementation class for: OrdenDeCompraMDB
 * 
 */
@MessageDriven(name="OrdenDeCompra",
	activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/"+JMSQueuesNames.ENVIAR_ORDEN_COMPRA_QUEUE) 
	}
)
public class OrdenDeCompraMDB implements MessageListener {

	@EJB
	private MessagesFacade messagesFacade;

	private static final Logger LOGGER = Logger
			.getLogger(OrdenDeCompraMDB.class);

	public void onMessage(Message message) {
		TextMessage ts = (TextMessage) message;
		String textReceived = "";
		try {
			textReceived = ts.getText();
		} catch (JMSException e1) {
			e1.printStackTrace();
		}
		LOGGER.info("Orden de compra recibida: \n" + textReceived);
		SolicitudCompraRequest request = new SolicitudCompraRequest();
		request.fromXML(textReceived);
		messagesFacade.recibirSolicitudCompraRodamientos(request);
	}

}

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
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodamientosVO;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination" , propertyValue = "queue/"+JMSQueuesNames.RECEPCION_RODAMIENTOS_QUEUE)
})
public class ListaRecepcionRodamientosQueue implements MessageListener {

	@EJB
	MessagesFacade messagesFacade;
	
	private static final Logger LOGGER = Logger.getLogger(ListaRecepcionRodamientosQueue.class);
	
	/**
	 * Se recibe un mensaje de texto en formato XML con los datos necesarios de rodamientos 
	 *  para actualizar stock y completar pedidos pendientes
	 * 
	 */
	public void onMessage(Message message) {
		TextMessage ts = (TextMessage) message;
		String textReceived = "";
		RecepcionRodamientosVO lpr = new RecepcionRodamientosVO();
		try{			
			textReceived = ts.getText();
			
		} catch (JMSException e){
			e.printStackTrace();
		}
		if (textReceived.startsWith("TEST")) {
			LOGGER.info("This is a test message, the message received is: "+ textReceived);
		}
		else {
			lpr=lpr.fromXML(textReceived);
			messagesFacade.recibirEnvioProveedor(lpr);
		}
	}

}
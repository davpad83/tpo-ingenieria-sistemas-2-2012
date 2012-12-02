package edu.uade.tpo.ingsist2.view.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.jboss.logging.Logger;

/**
 * Message-Driven Bean implementation class for: RecibirRemitosODVMockMDB
 *
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination" , propertyValue = "queue/"+JMSQueuesNames.ENVIAR_REMITO_QUEUE)
})
public class RecibirRemitosODVMockMDB implements MessageListener {

	private static final Logger LOGGER = Logger.getLogger(RecibirRemitosODVMockMDB.class);
	
    public void onMessage(Message message) {

    	TextMessage ts = (TextMessage) message;
		String textReceived = "";
		try {
			textReceived = ts.getText();
		} catch (JMSException e1) {
			e1.printStackTrace();
		}
		
		LOGGER.info("Remito recibido: \n"+textReceived);
    }

}

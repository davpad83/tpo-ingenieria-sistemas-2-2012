package edu.uade.tpo.ingsist2.integration;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"
		) })
public class ODVRemitoQueueMock implements MessageListener {

    public void onMessage(Message message) {
        // TODO Auto-generated method stub
        
    }
}

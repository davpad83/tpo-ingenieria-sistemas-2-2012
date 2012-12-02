package edu.uade.tpo.ingsist2.view.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.util.EnviarMensajeHelper;
import edu.uade.tpo.ingsist2.view.vo.PedidoAbastecimientoVO;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodamientosVO;

/**
 * Message-Driven Bean implementation class for: RecibirPedidosProveedorMockMDB
 *
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination" , propertyValue = "queue/"+JMSQueuesNames.RECIBIR_PEDIDOS_PROVE_MOCK)
})

public class RecibirPedidosProveedorMockMDB implements MessageListener {

	private static final Logger LOGGER = Logger.getLogger(RecibirPedidosProveedorMockMDB.class);
	
    public void onMessage(Message message) {
    	EnviarMensajeHelper emHelper = new EnviarMensajeHelper("127.0.0.1", 1099, JMSQueuesNames.RECEPCION_RODAMIENTOS_QUEUE);
    	
    	TextMessage ts = (TextMessage) message;
		String textReceived = "";
		try {
			textReceived = ts.getText();
		} catch (JMSException e1) {
			e1.printStackTrace();
		}
		
		PedidoAbastecimientoVO pedido = new PedidoAbastecimientoVO();
		pedido.fromXML(textReceived);
		
		RecepcionRodamientosVO recepcion = new RecepcionRodamientosVO();
		
//		recepcion.setListaRodVO(listaRodVO);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		emHelper.enviarMensaje(pedido.toXML(true));
//		emHelper.cerrarConexion();
    }

}

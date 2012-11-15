package edu.uade.tpo.ingsist2.view.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.thoughtworks.xstream.XStream;

import edu.uade.tpo.ingsist2.view.facade.MessagesFacade;
import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodProveedorRequest;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination" , propertyValue = "queue/EnviarListaProveedor")
})
public class ListaProveedorQueue implements MessageListener {

	@EJB
	MessagesFacade messagesFacade;
	
	/**
	 * Se recibe un mensaje de texto en formato XML con los datos necesarios
	 * para agregar una Lista de Proveedor.
	 * 
	 */
	public void onMessage(Message message) {
		try{
			TextMessage ts = (TextMessage) message;
			ListaPreciosVO lpr = deXMLAListaProveedorRequest(ts.getText());
			
			messagesFacade.agregarListaProveedor(lpr);
		} catch (JMSException e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private ListaPreciosVO deXMLAListaProveedorRequest(String message){
		XStream xs = new XStream();
		return (ListaPreciosVO) xs.fromXML(message);
	}
}

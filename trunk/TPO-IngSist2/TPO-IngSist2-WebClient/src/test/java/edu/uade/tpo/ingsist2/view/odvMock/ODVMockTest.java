package edu.uade.tpo.ingsist2.view.odvMock;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

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

import edu.uade.tpo.ingsist2.model.util.EnviarMensajeHelper;
import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.jms.JMSQueuesNames;
import edu.uade.tpo.ingsist2.view.vo.CotizacionVO;
import edu.uade.tpo.ingsist2.view.vo.ItemRodamientoVO;
import edu.uade.tpo.ingsist2.view.vo.OrdenDeCompraVO;
import edu.uade.tpo.ingsist2.view.vo.RodamientoVO;

public class ODVMockTest {

	private EnviarMensajeHelper emHelper;
	
	@Before
	public void prepararTest() {
		 emHelper = new EnviarMensajeHelper("127.0.0.1", 1099, JMSQueuesNames.ENVIAR_ORDEN_COMPRA_QUEUE);
	}	
	
	@After
	public void finalizarTest() {
		emHelper.cerrarConexion();
	}
	
	@Test
	public void enviarOrdenDeCompraTest(){
		OrdenDeCompraVO ocvo = MockDataGenerator.getRandomOrdenDeCompraVO();
		emHelper.enviarMensaje(ocvo.toXML(true));
	}
	
	@Test
	public void enviarOrdenDeCompraHardTest(){
		OrdenDeCompraVO ocvo = new OrdenDeCompraVO();
		
		ocvo.setIdOrden(0);
		
		List<ItemRodamientoVO> lirvo = new ArrayList<ItemRodamientoVO>();
		ItemRodamientoVO irvo = new ItemRodamientoVO();
		irvo.setCantidad(5);
		CotizacionVO cot = new CotizacionVO();
		cot.setId(1);
		
		irvo.setCotizacion(cot);
		RodamientoVO rod = new RodamientoVO();
		rod.setId(5);
		rod.setMarca("ZKL");
		rod.setCodigoSKF("22310 CCW33");
		rod.setPais("Japon");
		irvo.setRodamiento(rod);
		
		lirvo.add(irvo);
		ocvo.setItems(lirvo);
		
		emHelper.enviarMensaje(ocvo.toXML(true));
	}
}

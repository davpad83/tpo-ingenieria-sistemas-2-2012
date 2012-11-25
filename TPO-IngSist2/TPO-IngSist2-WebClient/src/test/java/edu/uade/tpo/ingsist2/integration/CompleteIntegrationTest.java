package edu.uade.tpo.ingsist2.integration;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.xml.ws.WebServiceRef;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uade.tpo.ingsist2.model.util.EnviarMensajeHelper;
import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.bd.BusinessDelegate;
import edu.uade.tpo.ingsist2.view.facade.Facade;
import edu.uade.tpo.ingsist2.view.jms.JMSQueuesNames;
import edu.uade.tpo.ingsist2.view.vo.CotizacionVO;
import edu.uade.tpo.ingsist2.view.vo.ItemRodamientoVO;
import edu.uade.tpo.ingsist2.view.vo.ItemVO;
import edu.uade.tpo.ingsist2.view.vo.OrdenDeCompraVO;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCompraRequest;

public class CompleteIntegrationTest {

	Logger LOGGER = LoggerFactory.getLogger(CompleteIntegrationTest.class);

	private EnviarMensajeHelper emHelper;

	private static final String wsdlLocation = "http://10.10.90.135:8080/TPO-IngSist2-EAR-TPO-IngSist2-EJB-0.0.1-SNAPSHOT/FacadeBean?wsdl";

	@WebServiceRef(wsdlLocation = wsdlLocation)
	static GetCotizacionRodamiento_Service getRodService;

	private GetCotizacionRodamiento web_service;

	private edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse response1;
	private edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse response2;
	private edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse response3;

	@Before
	public void prepararTest() {
		emHelper = new EnviarMensajeHelper("127.0.0.1", 1099,
				JMSQueuesNames.ENVIAR_ORDEN_COMPRA_QUEUE);
		// web_service = getRodService.getGetCotizacionRodamientoPort();
	}

	@After
	public void finalizarTest() {
		emHelper.cerrarConexion();
	}

	@Test
	public void enviarCotizacionTest() {
		edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest mockSolic1 = MockDataGenerator
				.getRandomSolicitudCotizacionRequestConMarca();
		edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest mockSolic2 = MockDataGenerator
				.getRandomSolicitudCotizacionRequestConMarca();
		edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest mockSolic3 = MockDataGenerator
				.getRandomSolicitudCotizacionRequestConMarca();
//		edu.uade.tpo.ingsist2.integration.SolicitudCotizacionRequest scr1 = convertSolicitud(mockSolic1);
//		edu.uade.tpo.ingsist2.integration.SolicitudCotizacionRequest scr2 = convertSolicitud(mockSolic2);
//		edu.uade.tpo.ingsist2.integration.SolicitudCotizacionRequest scr3 = convertSolicitud(mockSolic3);

		mockSolic1.setIdODV(1);
		mockSolic2.setIdODV(1);
		mockSolic3.setIdODV(1);
		
		mockSolic1.setMarca("STEYR");
		mockSolic1.setPais("Reino Unido");
		mockSolic1.setSKF("6200 ZZ");
		
		mockSolic2.setMarca("FAG");
		mockSolic2.setPais("Alemania");
		mockSolic2.setSKF("6200 2RS");

		mockSolic3.setPais("SKF");
		mockSolic3.setSKF("NJ 208 EMC3");
		
		LOGGER.info("Solicitud de Cotizacion 1", mockSolic1.toString());
		LOGGER.info("Solicitud de Cotizacion 2", mockSolic2.toString());
		LOGGER.info("Solicitud de Cotizacion 3", mockSolic3.toString());
		LOGGER.info("Enviando cotizaciones . . .");

		// SolicitudCotizacionResponse response1 = web_service
		// .recibirSolicitudCotizacion(scr1);
		// SolicitudCotizacionResponse response2 = web_service
		// .recibirSolicitudCotizacion(scr2);
		// SolicitudCotizacionResponse response3 = web_service
		// .recibirSolicitudCotizacion(scr3);

		BusinessDelegate bd = BusinessDelegate.getInstancia();
		response1 = bd.recibirSolicitudCotizacion(mockSolic1);
		response2 = bd.recibirSolicitudCotizacion(mockSolic2);
		response3 = bd.recibirSolicitudCotizacion(mockSolic3);

		assertEquals(mockSolic1.getIdODV(), response1.getIdODV());
		assertEquals(mockSolic2.getIdODV(), response2.getIdODV());
		assertEquals(mockSolic3.getIdODV(), response3.getIdODV());

		assertEquals(mockSolic1.getIdPedidoCotizacion(),
				response1.getIdPedidoCotizacion());
		assertEquals(mockSolic2.getIdPedidoCotizacion(),
				response2.getIdPedidoCotizacion());
		assertEquals(mockSolic3.getIdPedidoCotizacion(),
				response3.getIdPedidoCotizacion());

		assertNotNull(response1.getRodamientosCotizados());
		assertNotNull(response2.getRodamientosCotizados());
		assertNotNull(response3.getRodamientosCotizados());
		
		assertTrue(response1.getRodamientosCotizados().size() == 1);
		assertTrue(response2.getRodamientosCotizados().size() == 1);
		assertTrue(response3.getRodamientosCotizados().size() >= 1);

		for (edu.uade.tpo.ingsist2.view.vo.RodamientoCotizadoVO rcvo : response3
				.getRodamientosCotizados()) {
			assertNotNull(rcvo.getMarca());
			assertFalse(rcvo.getMarca().isEmpty());
			assertTrue(rcvo.getPrecioCotizado() > 0);
		}
	}

	private edu.uade.tpo.ingsist2.integration.SolicitudCotizacionRequest convertSolicitud(
			edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest randomSolicitudCotizacionRequest) {
		edu.uade.tpo.ingsist2.integration.SolicitudCotizacionRequest scr = new edu.uade.tpo.ingsist2.integration.SolicitudCotizacionRequest();
		scr.setCantidad(randomSolicitudCotizacionRequest.getCantidad());
		scr.setIdODV(randomSolicitudCotizacionRequest.getIdODV());
		scr.setIdPedidoCotizacion(randomSolicitudCotizacionRequest
				.getIdPedidoCotizacion());
		scr.setMarca(randomSolicitudCotizacionRequest.getMarca());
		scr.setPais(randomSolicitudCotizacionRequest.getMarca());
		scr.setSKF(randomSolicitudCotizacionRequest.getSKF());
		return scr;
	}

	@Test
	public void enviarOCCompletaTest() {
		SolicitudCompraRequest ocvo = new SolicitudCompraRequest();
		ocvo.setIdODV(response1.getIdODV());
		ocvo.setIdOrdenDeCompra(MockDataGenerator.getRandomId());
		ArrayList<ItemVO> items = new ArrayList<ItemVO>();

		edu.uade.tpo.ingsist2.view.vo.RodamientoCotizadoVO rodCot1 = response1
				.getRodamientosCotizados().get(0);
		
	
		ocvo.setItems(items);

		// item1.setCantidad(cot1.get);
		// ocvo.getItems().add();
	}

	@Test
	public void enviarMercaderiaDelProveedorTest() {

	}
}

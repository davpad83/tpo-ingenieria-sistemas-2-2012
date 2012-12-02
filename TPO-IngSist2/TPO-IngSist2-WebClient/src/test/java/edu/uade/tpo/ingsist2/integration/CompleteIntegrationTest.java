package edu.uade.tpo.ingsist2.integration;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uade.tpo.ingsist2.model.util.EnviarMensajeHelper;
import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.bd.BusinessDelegate;
import edu.uade.tpo.ingsist2.view.jms.JMSQueuesNames;
import edu.uade.tpo.ingsist2.view.vo.*;

public class CompleteIntegrationTest {

	Logger LOGGER = LoggerFactory.getLogger(CompleteIntegrationTest.class);

	private EnviarMensajeHelper solicitudCompraQueue;

	private SolicitudCotizacionResponse response1;
	private SolicitudCotizacionResponse response2;
	private SolicitudCotizacionResponse response3;
	private SolicitudCotizacionResponse response4;

	@Before
	public void prepararTest() {
		solicitudCompraQueue = new EnviarMensajeHelper("127.0.0.1", 1099,
				JMSQueuesNames.ENVIAR_ORDEN_COMPRA_QUEUE);
		// web_service = getRodService.getGetCotizacionRodamientoPort();
	}

	@After
	public void finalizarTest() {
		solicitudCompraQueue.cerrarConexion();
	}

	@Test
	public void generateInitialData() {
		BusinessDelegate bd = BusinessDelegate.getInstancia();
		bd.generateInitialData();
	}

	@Test
	public void enviarCotizacionTest() {

		SolicitudCotizacionRequest mockSolic1 = solicitudes.get(0);
		SolicitudCotizacionRequest mockSolic2 = solicitudes.get(1);
		SolicitudCotizacionRequest mockSolic3 = solicitudes.get(2);
		SolicitudCotizacionRequest mockSolic4 = solicitudes.get(3);

		BusinessDelegate bd = BusinessDelegate.getInstancia();
		response1 = bd.recibirSolicitudCotizacion(mockSolic1);
		response2 = bd.recibirSolicitudCotizacion(mockSolic2);
		response3 = bd.recibirSolicitudCotizacion(mockSolic3);
		response4 = bd.recibirSolicitudCotizacion(mockSolic4);

		assertEquals(mockSolic1.getIdODV(), response1.getIdODV());
		assertEquals(mockSolic2.getIdODV(), response2.getIdODV());
		assertEquals(mockSolic3.getIdODV(), response3.getIdODV());
		assertEquals(mockSolic4.getIdODV(), response4.getIdODV());

		assertEquals(mockSolic1.getIdPedidoCotizacion(),
				response1.getIdPedidoCotizacion());
		assertEquals(mockSolic2.getIdPedidoCotizacion(),
				response2.getIdPedidoCotizacion());
		assertEquals(mockSolic3.getIdPedidoCotizacion(),
				response3.getIdPedidoCotizacion());
		assertEquals(mockSolic4.getIdPedidoCotizacion(),
				response4.getIdPedidoCotizacion());

		assertNotNull(response1.getRodamientosCotizados());
		assertNotNull(response2.getRodamientosCotizados());
		assertNotNull(response3.getRodamientosCotizados());
		assertNotNull(response4.getRodamientosCotizados());

		assertTrue(response1.getRodamientosCotizados().size() == 1);
		assertTrue(response2.getRodamientosCotizados().size() == 1);
		assertTrue(response3.getRodamientosCotizados().size() >= 1);
		assertTrue(response4.getRodamientosCotizados().size() >= 1);

		for (RodamientoCotizadoVO rcvo : response1.getRodamientosCotizados()) {
			assertNotNull(rcvo.getMarca());
			assertFalse(rcvo.getMarca().isEmpty());
			assertTrue(rcvo.getPrecioCotizado() > 0);
		}
		for (RodamientoCotizadoVO rcvo : response2.getRodamientosCotizados()) {
			assertNotNull(rcvo.getMarca());
			assertFalse(rcvo.getMarca().isEmpty());
			assertTrue(rcvo.getPrecioCotizado() > 0);
		}
		for (RodamientoCotizadoVO rcvo : response3.getRodamientosCotizados()) {
			assertNotNull(rcvo.getMarca());
			assertFalse(rcvo.getMarca().isEmpty());
			assertTrue(rcvo.getPrecioCotizado() > 0);
		}
		for (RodamientoCotizadoVO rcvo : response4.getRodamientosCotizados()) {
			assertNotNull(rcvo.getMarca());
			assertFalse(rcvo.getMarca().isEmpty());
			assertTrue(rcvo.getPrecioCotizado() > 0);
		}
	}

	@Test
	public void enviarOCCompletaTest() {
		SolicitudCompraRequest ocvo = MockDataGenerator
				.getControlledSolicitudCompraRequest();

		solicitudCompraQueue.enviarMensaje(ocvo.toXML());
	}

	// @Test
	public void enviarMercaderiaDelProveedorTest() {

	}
}

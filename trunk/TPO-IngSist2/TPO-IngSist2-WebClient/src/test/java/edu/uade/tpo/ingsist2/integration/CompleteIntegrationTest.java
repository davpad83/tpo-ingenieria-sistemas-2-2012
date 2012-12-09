package edu.uade.tpo.ingsist2.integration;

import static org.junit.Assert.*;

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

	@Before
	public void prepararTest() {
		// web_service = getRodService.getGetCotizacionRodamientoPort();
	}

	@After
	public void finalizarTest() {
		
	}

	@Test
	public void generateInitialData() {
		BusinessDelegate bd = BusinessDelegate.getInstancia();
		bd.generateInitialData();
	}

	@Test
	public void enviarCotizacionTest() {

		List<SolicitudCotizacionRequest> solicitudes = MockDataGenerator
				.getControlledSolicitudCotizacionRequestList();
		
		BusinessDelegate bd = BusinessDelegate.getInstancia();
		testCotizacionConMarcaResponse(solicitudes.get(0), bd.recibirSolicitudCotizacion(solicitudes.get(0)));
		testCotizacionConMarcaResponse(solicitudes.get(1), bd.recibirSolicitudCotizacion(solicitudes.get(1)));
		testCotizacionSinMarcaResponse(solicitudes.get(2), bd.recibirSolicitudCotizacion(solicitudes.get(2)));
		testCotizacionSinMarcaResponse(solicitudes.get(3), bd.recibirSolicitudCotizacion(solicitudes.get(3)));

	}

	private void testCotizacionSinMarcaResponse(
			SolicitudCotizacionRequest mockSolic3, SolicitudCotizacionResponse response) {
		assertEquals(mockSolic3.getIdODV(), response.getIdODV());
		assertEquals(mockSolic3.getIdPedidoCotizacion(),
				response.getIdPedidoCotizacion());
		assertNotNull(response.getRodamientosCotizados());
		assertTrue(response.getRodamientosCotizados().size() >= 1);
		for (RodamientoCotizadoVO rcvo : response.getRodamientosCotizados()) {
			assertNotNull(rcvo.getMarca());
			assertFalse(rcvo.getMarca().isEmpty());
			assertTrue(rcvo.getPrecioCotizado() > 0);
		}
	}

	private void testCotizacionConMarcaResponse(SolicitudCotizacionRequest mockSolic1, SolicitudCotizacionResponse response) {
		assertEquals(mockSolic1.getIdODV(), response.getIdODV());
		assertEquals(mockSolic1.getIdPedidoCotizacion(),
				response.getIdPedidoCotizacion());
		assertNotNull(response.getRodamientosCotizados());
		assertTrue(response.getRodamientosCotizados().size() == 1);
		for (RodamientoCotizadoVO rcvo : response.getRodamientosCotizados()) {
			assertNotNull(rcvo.getMarca());
			assertFalse(rcvo.getMarca().isEmpty());
			assertTrue(rcvo.getPrecioCotizado() > 0);
		}
	}

	@Test
	public void enviarOCCompletaTest() {
		List<SolicitudCompraRequest> requests = MockDataGenerator.getControlledSolicitudCompraRequestList();

		EnviarMensajeHelper solicitudCompraQueue = new EnviarMensajeHelper("127.0.0.1", 1099,
				JMSQueuesNames.ENVIAR_ORDEN_COMPRA_QUEUE);
		
		for(SolicitudCompraRequest scr : requests){
			solicitudCompraQueue.enviarMensaje(scr.toXML());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		solicitudCompraQueue.cerrarConexion();
	}

}
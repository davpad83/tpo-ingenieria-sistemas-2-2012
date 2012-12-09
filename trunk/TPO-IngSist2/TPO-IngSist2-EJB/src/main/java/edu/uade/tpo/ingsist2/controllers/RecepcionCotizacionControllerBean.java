package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.Cotizacion;
import edu.uade.tpo.ingsist2.model.ItemRodamiento;
import edu.uade.tpo.ingsist2.model.ListaPrecios;
import edu.uade.tpo.ingsist2.model.Proveedor;
import edu.uade.tpo.ingsist2.model.Rodamiento;
import edu.uade.tpo.ingsist2.model.entities.CotizacionEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemListaEntity;
import edu.uade.tpo.ingsist2.model.entities.ListaPreciosEntity;
import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.vo.RodamientoCotizadoVO;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;

@Stateless
public class RecepcionCotizacionControllerBean implements
		RecepcionCotizacionController {

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	@EJB
	private Rodamiento rodamiento;

	@EJB
	private Cotizacion cotizacion;

	@EJB
	private ItemRodamiento itemRodamiento;

	@EJB
	private ListaPrecios listaprecio;

	@EJB
	private Proveedor proveedor;

	private static final Logger LOGGER = Logger
			.getLogger(RecepcionCotizacionControllerBean.class);

	private SolicitudCotizacionResponse cotResponse;

	public SolicitudCotizacionResponse procesarSolicitudCotizacion(
			SolicitudCotizacionRequest cotRequest) {
		LOGGER.info("Solicitud de cotizacion recibida: \n"
				+ cotRequest.toString());
		LOGGER.info("==================PROCESANDO SOLICITUD DE COTIZACION BEGIN==================");
		LOGGER.info("Request enviado de ODV: " + cotRequest.getIdODV());

		cotResponse = new SolicitudCotizacionResponse();
		cotResponse.setIdPedidoCotizacion(cotRequest.getIdPedidoCotizacion());
		cotResponse.setIdODV(cotRequest.getIdODV());
		cotResponse
				.setRodamientosCotizados(new ArrayList<RodamientoCotizadoVO>());
		RodamientoEntity rodEntity = new RodamientoEntity();

		if (cotizacion.validarRequest(cotRequest)) {
			if (cotRequest.getMarca() == null
					|| cotRequest.getMarca().isEmpty()) {
				rodEntity = rodamiento.getRodamientosCotizacionSinMarca(
						cotRequest.getSKF(), cotRequest.getPais());
				if (rodEntity != null) {
					procesarSinMarca(rodEntity, cotRequest);
				} else {
					cotResponse.setIdPedidoCotizacion(-1);
				}
			} else {
				rodEntity = rodamiento.getRodamientoCotizacionConMarca(
						cotRequest.getSKF(), cotRequest.getPais(),
						cotRequest.getMarca());
				if (rodEntity != null) {
					procesarConMarca(rodEntity, cotRequest);
				} else {
					cotResponse.setIdPedidoCotizacion(-1);
				}
			}
			LOGGER.info("Enviando respuesta: \n" + cotResponse.toString());
		} else {
			LOGGER.error("Omitiendo la solicitud de cotizacion completa.");
		}
		LOGGER.info("==================PROCESANDO SOLICITUD DE COTIZACION END==================");
		return cotResponse;

	}

	public void procesarConMarca(RodamientoEntity r,
			SolicitudCotizacionRequest solicitudCotRequest) {
		LOGGER.info("Procesando Cotizacion con Marca ");

		RodamientoCotizadoVO rodamCotizado = new RodamientoCotizadoVO();

		rodamCotizado.setSKF(r.getCodigoSKF());
		rodamCotizado.setPais(r.getPais());
		rodamCotizado.setMarca(r.getMarca());
		rodamCotizado.setEnStock(r.getStock());

		ItemListaEntity it = new ItemListaEntity();
		try {
			it = cotizacion.getItemListaConMenorPrecioConMarca(r);
			String tiempoDeEntrega = proveedor.getTiempoDeEntrega(it.getId());
			rodamCotizado.setPrecioCotizado(it.getPrecio());
			LOGGER.info("Se cotizo el rodamiento en $"
					+ rodamCotizado.getPrecioCotizado());

			if (solicitudCotRequest.getCantidad() > rodamCotizado.getEnStock()) {
				rodamCotizado.setTiempoEstimadoEntrega(tiempoDeEntrega);
			} else {
				rodamCotizado.setTiempoEstimadoEntrega("inmediato");
			}
			rodamCotizado.setFechaInicio(new Date());
			rodamCotizado.setFechaFin(MockDataGenerator
					.getRandomFechaVencimiento());

		} catch (Exception e) {
			LOGGER.error("Hubo un error al procesar la cotizacion con marca");
			e.printStackTrace();
		}

		cotResponse.getRodamientosCotizados().add(rodamCotizado);

		CotizacionEntity cot = new CotizacionEntity();
		cot.setIdRecibidoODV(solicitudCotRequest.getIdPedidoCotizacion());
		OficinaDeVentaEntity ofe = new OficinaDeVentaEntity();
		ofe.setId(solicitudCotRequest.getIdODV());
		cot.setOdv(ofe);
		cot.setRodamiento(r);

		int idlista = listaprecio.getIdListaPrecioPorIdItemLista(it.getId());
		ListaPreciosEntity lp = new ListaPreciosEntity();
		lp.setIdLista(idlista);
		cot.setLista(lp);

		cot.setCantidad(solicitudCotRequest.getCantidad());
		cot.setFecha(rodamCotizado.getFechaInicio());
		cot.setTiempoEntrega(rodamCotizado.getTiempoEstimadoEntrega());
		cot.setVencimiento(rodamCotizado.getFechaFin());

		cotizacion.guardarCotizacion(cot);
	}

	public void procesarSinMarca(RodamientoEntity rod,
			SolicitudCotizacionRequest screq) {
		LOGGER.info("Procesando Cotizacion sin Marca ");

		RodamientoCotizadoVO rcVO;
		List<ItemListaEntity> listaResultado = cotizacion
				.getItemsListaConMenorPrecioSinMarca(rod);

		for (int i = 0; i < listaResultado.size(); i++) {

			rcVO = new RodamientoCotizadoVO();

			rcVO.setSKF(listaResultado.get(i).getRodamiento().getCodigoSKF());
			rcVO.setPais(listaResultado.get(i).getRodamiento().getPais());
			rcVO.setEnStock(listaResultado.get(i).getRodamiento().getStock());
			rcVO.setMarca(listaResultado.get(i).getRodamiento().getMarca());
			rcVO.setPrecioCotizado(listaResultado.get(i).getPrecio());

			LOGGER.info("Se cotizo el rodamiento con CodigoSKF: "
					+ rcVO.getSKF() + " Marca: " + rcVO.getMarca()
					+ " y Pais: " + rcVO.getPais() + " en $"
					+ rcVO.getPrecioCotizado());

			if (screq.getCantidad() > rcVO.getEnStock()) {
				rcVO.setTiempoEstimadoEntrega(proveedor
						.getTiempoDeEntrega(listaResultado.get(i).getId()));
			} else {
				rcVO.setTiempoEstimadoEntrega("inmediato");
			}
			rcVO.setFechaInicio(new Date());
			rcVO.setFechaFin(MockDataGenerator.getRandomFechaVencimiento());

			cotResponse.getRodamientosCotizados().add(rcVO);
		}

		CotizacionEntity ct = new CotizacionEntity();
		ct.setIdRecibidoODV(screq.getIdPedidoCotizacion());
		OficinaDeVentaEntity ofe = new OficinaDeVentaEntity();
		ofe.setId(screq.getIdODV());
		ct.setOdv(ofe);

		int idlista = 0;

		for (int j = 0; j < listaResultado.size(); j++) {

			idlista = listaprecio.getIdListaPrecioPorIdItemLista(listaResultado
					.get(j).getId());
			ListaPreciosEntity lp = new ListaPreciosEntity();
			lp.setIdLista(idlista);
			ct.setLista(lp);
			ct.setRodamiento(listaResultado.get(j).getRodamiento());

			ct.setCantidad(screq.getCantidad());
			ct.setFecha(cotResponse.getRodamientosCotizados().get(j)
					.getFechaInicio());
			ct.setTiempoEntrega(cotResponse.getRodamientosCotizados().get(j)
					.getTiempoEstimadoEntrega());
			ct.setVencimiento(cotResponse.getRodamientosCotizados().get(j)
					.getFechaFin());

			cotizacion.guardarCotizacion(ct);

		}
	}
}

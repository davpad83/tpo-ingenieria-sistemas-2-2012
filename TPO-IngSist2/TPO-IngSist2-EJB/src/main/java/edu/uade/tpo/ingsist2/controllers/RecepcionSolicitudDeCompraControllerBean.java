package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.Cotizacion;
import edu.uade.tpo.ingsist2.model.ItemRodamiento;
import edu.uade.tpo.ingsist2.model.OficinaDeVenta;
import edu.uade.tpo.ingsist2.model.OrdenDeCompra;
import edu.uade.tpo.ingsist2.model.PedidoDeAbastecimiento;
import edu.uade.tpo.ingsist2.model.Remito;
import edu.uade.tpo.ingsist2.model.Rodamiento;
import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.model.entities.PedidoDeAbastecimientoEntity;
import edu.uade.tpo.ingsist2.model.entities.RemitoEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.ItemVO;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCompraRequest;

/**
 * Session Bean implementation class RecepcionSolicitudDeCompraControllerBean
 */
@Stateless
public class RecepcionSolicitudDeCompraControllerBean implements
		RecepcionSolicitudDeCompraController {

	private static final Logger LOGGER = Logger
			.getLogger(RecepcionSolicitudDeCompraControllerBean.class);

	@EJB
	private OrdenDeCompra ordenDeCompra;

	@EJB
	private OficinaDeVenta oficinaVenta;

	@EJB
	private Rodamiento rodamiento;

	@EJB
	private Cotizacion cotizacion;

	@EJB
	private ItemRodamiento itemRodamiento;

	@EJB
	private PedidoDeAbastecimiento pedidoAbastecimiento;

	@EJB
	private Remito remito;

	@Override
	public void procesarSolicitudDeCompra(SolicitudCompraRequest request) {
		LOGGER.info("==================PROCESANDO SOLICITUD DE COMPRA BEGIN==================");
		LOGGER.info("Request enviado de ODV: " + request.getIdODV());

		if (ordenDeCompra.validarSolicitudCompra(request)) {
			OrdenDeCompraEntity ocGuardada = fromRequestToOCEntity(request);
			int idOCGuardada = ordenDeCompra.guardarOrdenDeCompra(ocGuardada);
			ocGuardada.setIdOrden(idOCGuardada);

			RemitoEntity rem = null;
			for (ItemRodamientoEntity ire : ocGuardada.getItems()) {

				RodamientoEntity rodActual = ire.getCotizacion()
						.getRodamiento();

				int stockActual = rodActual.getStock();
				int stockSolicitado = ire.getCantidad();
				if (cotizacion.validarVigenciaLista(ire.getCotizacion()
						.getLista())) {
					LOGGER.info("El item cotizado con id " + ire.getId()
							+ " esta en vigencia, verificando stock...");

					// NO HAY STOCK PARA ENVIAR NINGUNO.
					if (stockActual == 0) {
						LOGGER.info("No hay stock para el rodamiento (cod: "
								+ rodActual.getCodigoSKF() + ")");
						nuevoPedidoDeAbastecimiento(ocGuardada, ire,
								stockSolicitado);

						// HAY STOCK PARA ENVIAR EL ITEM COMPLETO
					} else if (stockActual >= stockSolicitado) {
						LOGGER.info("Hay stock suficiente para el rodamiento (cod: "
								+ rodActual.getCodigoSKF()
								+ ") | Solicitado: "
								+ stockSolicitado + " | Actual: " + stockActual);
						if (rem == null) {
							rem = inicializarRemito(ocGuardada);
						}
						rem.getItems().add(ire);

						ire.setPendientes(0);
						ire.getRodamiento().disminuirStock(stockSolicitado);
						// HAY STOCK PARA ENVIAR PARCIALMENTE
					} else {
						LOGGER.info("No hay stock suficiente para el rodamiento (cod: "
								+ rodActual.getCodigoSKF()
								+ "), pueden entregarse "
								+ stockActual
								+ " ahora.");
						if (rem == null) {
							rem = inicializarRemito(ocGuardada);
						}
						ire.getRodamiento().disminuirStock(stockSolicitado);

						/* Genero un nuevo item rodamiento para el remito */
						rem.getItems().add(ire);

						ire.setPendientes(ire.getPendientes() - stockSolicitado);
						nuevoPedidoDeAbastecimiento(ocGuardada, ire,
								stockSolicitado);
					}
				} else {
					LOGGER.info("El item cotizado con id " + ire.getId()
							+ " ya no esta en vigencia");
				}
			}
			if (rem != null) {
				RemitoEntity remitoGuardado = remito.guardarRemito(rem);
				remito.enviarRemito(remitoGuardado, ocGuardada.getOdv());
				ocGuardada = ordenDeCompra.verificarPendientes(ocGuardada);
				ordenDeCompra.guardarOrdenDeCompra(ocGuardada);
			} else {
				LOGGER.warn("No se genero ningun remito en esta operacion, ya que no hay stock disponible.");
			}
		} else {
			LOGGER.error("Omitiendo la solicitud completa.");
		}
		LOGGER.info("==================PROCESANDO SOLICITUD DE COMPRA END==================");
	}

	private void nuevoPedidoDeAbastecimiento(OrdenDeCompraEntity ocGuardada,
			ItemRodamientoEntity ire, int stockSolicitado) {
		LOGGER.info("Creando nuevo pedido de abastecimiento. SKF: "
				+ ire.getRodamiento().getCodigoSKF() + "|Pais: "
				+ ire.getRodamiento().getPais() + "|Marca: "
				+ ire.getRodamiento().getMarca() + "|Cant:" + stockSolicitado
				+ "|Prove: "
				+ ire.getCotizacion().getLista().getProveedor().getNombre()
				+ "(Id:"
				+ ire.getCotizacion().getLista().getProveedor().getId() + ")");
		PedidoDeAbastecimientoEntity nuevoPedidoDeAbastecimiento = pedidoAbastecimiento
				.generarPedidoAbastecimiento(ocGuardada, ire,
						stockSolicitado * 2);
		pedidoAbastecimiento.guardarPedido(nuevoPedidoDeAbastecimiento);
		pedidoAbastecimiento.enviarPedido(nuevoPedidoDeAbastecimiento);
	}

	private RemitoEntity inicializarRemito(OrdenDeCompraEntity ocGuardada) {
		RemitoEntity rem;
		rem = new RemitoEntity();
		rem.setOdv(ocGuardada.getOdv());
		rem.setOrdenDeCompra(ocGuardada);
		rem.setItems(new ArrayList<ItemRodamientoEntity>());
		return rem;
	}

	private OrdenDeCompraEntity fromRequestToOCEntity(
			SolicitudCompraRequest request) {

		OrdenDeCompraEntity oce = new OrdenDeCompraEntity();
		oce.setIdRecibidoODV(request.getIdOrdenDeCompra());
		oce.setEstado("Nueva");
		ArrayList<ItemRodamientoEntity> listItems = new ArrayList<ItemRodamientoEntity>();
		for (ItemVO itReq : request.getItems()) {
			ArrayList<ItemRodamientoEntity> itemsCotizacion = cotizacion
					.getItemsCotizados(itReq.getId());
			for (ItemRodamientoEntity itemCot : itemsCotizacion) {
				if (itemCot.getRodamiento().getMarca().equals(itReq.getMarca())) {
					itemCot.setCantidad(itReq.getCantidad());
					listItems.add(itemCot);
				}
			}
		}
		oce.setItems(listItems);
		oce.setOdv(oficinaVenta.getOficina(request.getIdODV()));
		return oce;
	}
}

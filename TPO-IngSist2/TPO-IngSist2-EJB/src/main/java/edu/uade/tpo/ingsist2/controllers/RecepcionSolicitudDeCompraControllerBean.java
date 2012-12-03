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
import edu.uade.tpo.ingsist2.model.entities.CotizacionEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemRemitoEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.model.entities.PedidoDeAbastecimientoEntity;
import edu.uade.tpo.ingsist2.model.entities.RemitoEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.ItemSolicitudCompraRequest;
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
			OrdenDeCompraEntity oce = fromRequestToOCEntity(request);
			OrdenDeCompraEntity ocGuardada = ordenDeCompra
					.guardarOrdenDeCompra(oce);

			RemitoEntity rem = null;
			for (ItemRodamientoEntity ire : ocGuardada.getItems()) {

				RodamientoEntity rodActual = ire.getCotizacion()
						.getRodamiento();

				int stockActual = rodActual.getStock();
				int stockSolicitado = ire.getCantidad();
				RodamientoEntity rodamiento = ire.getCotizacion().getRodamiento();
				
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
						// Actualizo el stock
						rodamiento.disminuirStock(stockSolicitado);

						ire.setPendientes(0);
						ItemRemitoEntity itemRemito = new ItemRemitoEntity();
						itemRemito.setCantidaEnviada(stockSolicitado);
						itemRemito.setOcAsociada(ocGuardada);
						itemRemito.setRodamiento(rodamiento);
						
						rem.getItems().add(itemRemito);

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
						// Actualizo el stock
						rodamiento.disminuirStock(stockActual);

						ire.setPendientes(stockSolicitado - stockActual);
						
						ItemRemitoEntity itemRemito = new ItemRemitoEntity();
						itemRemito.setCantidaEnviada(stockActual);
						itemRemito.setOcAsociada(ocGuardada);
						itemRemito.setRodamiento(rodamiento);
						rem.getItems().add(itemRemito);

						nuevoPedidoDeAbastecimiento(ocGuardada, ire,
								stockSolicitado);
					}
				} else {
					LOGGER.info("El item cotizado con id " + ire.getId()
							+ " ya no esta en vigencia, se ha omitido.");
				}
			}
			if (rem != null) {
				RemitoEntity remitoGuardado = remito.guardarRemito(rem);
				remito.enviarRemito(remitoGuardado, ocGuardada.getOdv());
			} else {
				LOGGER.warn("No se genero ningun remito en esta operacion, ya que no hay stock disponible.");
			}
			ocGuardada = ordenDeCompra.verificarPendientes(ocGuardada);
			ordenDeCompra.guardarOrdenDeCompra(ocGuardada);
		} else {
			LOGGER.error("Omitiendo la solicitud completa.");
		}
		LOGGER.info("==================PROCESANDO SOLICITUD DE COMPRA END==================");
	}

	private synchronized void nuevoPedidoDeAbastecimiento(
			OrdenDeCompraEntity ocGuardada, ItemRodamientoEntity ire,
			int stockSolicitado) {
		LOGGER.info("Creando nuevo pedido de abastecimiento. SKF: "
				+ ire.getCotizacion().getRodamiento().getCodigoSKF() + "|Pais: "
				+ ire.getCotizacion().getRodamiento().getPais() + "|Marca: "
				+ ire.getCotizacion().getRodamiento().getMarca() + "|Cant:" + stockSolicitado
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
		rem.setItems(new ArrayList<ItemRemitoEntity>());
		return rem;
	}

	private OrdenDeCompraEntity fromRequestToOCEntity(
			SolicitudCompraRequest request) {

		OrdenDeCompraEntity oce = new OrdenDeCompraEntity();
		oce.setIdRecibidoODV(request.getIdOrdenDeCompra());
		oce.setEstado("Nueva");
		ArrayList<ItemRodamientoEntity> listItems = new ArrayList<ItemRodamientoEntity>();
		for (ItemSolicitudCompraRequest itReq : request.getItems()) {
			CotizacionEntity cot = cotizacion.getCotizacion(itReq.getId());
			if (cot.getRodamiento().getMarca().equals(itReq.getMarca())
					&& cot.getRodamiento().getPais().equals(itReq.getPais())
					&& cot.getRodamiento().getCodigoSKF()
							.equals(itReq.getSKF())) {
				ItemRodamientoEntity item = new ItemRodamientoEntity();
				item.setCantidad(itReq.getCantidad());
				item.setCotizacion(cot);
				item.setPendientes(itReq.getCantidad());
				listItems.add(item);
			} else {
				LOGGER.warn("Los datos del rodamiento no coinciden con el de la cotizacion, omitiendo item (SKF: "
						+ itReq.getSKF()
						+ "|Marca: "
						+ itReq.getMarca()
						+ "|Pais: " + itReq.getPais() + ")");
			}
		}
		oce.setItems(listItems);
		oce.setOdv(oficinaVenta.getOficina(request.getIdODV()));
		return oce;
	}
}

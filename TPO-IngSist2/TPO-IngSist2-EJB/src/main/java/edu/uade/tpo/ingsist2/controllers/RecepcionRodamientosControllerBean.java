package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.OficinaDeVenta;
import edu.uade.tpo.ingsist2.model.OrdenDeCompra;
import edu.uade.tpo.ingsist2.model.PedidoDeAbastecimiento;
import edu.uade.tpo.ingsist2.model.Remito;
import edu.uade.tpo.ingsist2.model.Rodamiento;
import edu.uade.tpo.ingsist2.model.entities.ItemRemitoEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.model.entities.PedidoDeAbastecimientoEntity;
import edu.uade.tpo.ingsist2.model.entities.RemitoEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodamientosVO;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodamientosVO.RodamientoListaVO;

/**
 * Session Bean implementation class RecepcionRodamientosController
 */
@Stateless
public class RecepcionRodamientosControllerBean implements
		RecepcionRodamientosController {

	private static final Logger LOGGER = Logger
			.getLogger(RecepcionRodamientosControllerBean.class);
	@EJB
	private PedidoDeAbastecimiento pedidos;
	@EJB
	private OrdenDeCompra ordenDeCompra;
	@EJB
	private OficinaDeVenta oficinaVenta;
	@EJB
	private Rodamiento rodamiento;
	@EJB
	private Remito remito;

	private ArrayList<PedidoDeAbastecimientoEntity> pedidosAbast;

	@Override
	public void recibirEnvioProveedor(RecepcionRodamientosVO rodamientos) {
		LOGGER.info("==================PROCESANDO RECEPCION DE RODAMIENTO BEGIN==================");
		pedidosAbast = pedidos.getPedidos();
		LOGGER.info("Obteniendo ODVs Asociadas");

		for (OficinaDeVentaEntity odv : getListaODVsUnicas(rodamientos)) {
			LOGGER.info("Generando remito para la ODV: " + odv.getId());

			RemitoEntity remitoAEnviar = new RemitoEntity();
			remitoAEnviar.setOdv(odv);

			List<ItemRemitoEntity> itemsRemito = new ArrayList<ItemRemitoEntity>();
			for (RodamientoListaVO rodam : rodamientos.getListaRodVO()) {
				if (isItemEnvioAsociadoAODV(odv, rodam)) {
					ItemRemitoEntity ivo = procesarItemRemito(rodam);
					if (ivo != null)
						itemsRemito.add(ivo);
				}
			}
			remitoAEnviar.setItems(itemsRemito);
			LOGGER.info("Remito generado exitosamente.");

			remitoAEnviar = remito.guardarRemito(remitoAEnviar);
			remito.enviarRemito(remitoAEnviar, odv);
		}
		LOGGER.info("==================PROCESANDO RECEPCION DE RODAMIENTO END==================");
	}

	private boolean isItemEnvioAsociadoAODV(OficinaDeVentaEntity odv,
			RodamientoListaVO rodam) {
		return getODVAsociadaAPedido(rodam.getIdPedidoAbastecimiento()).getId() == odv
				.getId();
	}

	private ItemRemitoEntity procesarItemRemito(RodamientoListaVO envio) {
		ItemRemitoEntity itemRemito = new ItemRemitoEntity();
		int idPedidoAbast = envio.getIdPedidoAbastecimiento();

		RodamientoEntity rodPedido = rodamiento.getRodamiento(envio.getSKF(),
				envio.getMarca(), envio.getPais());

		LOGGER.info("Procesando rodamiento recibido [SKF: " + envio.getSKF()
				+ "|" + envio.getMarca() + "|" + envio.getPais() + "]");
		LOGGER.info("Validando item del envio ...");
		if (!pedidos.validarRodamientoPedido(rodPedido, idPedidoAbast)) {
			LOGGER.info("No coincide el pedido " + idPedidoAbast
					+ " con Rodamiento " + envio.getSKF() + "|"
					+ envio.getMarca() + "|" + envio.getPais()
					+ ". Omitiendo item.");
			itemRemito = null;
		} else {
			LOGGER.info("El rodamiento del item es valido.");
			PedidoDeAbastecimientoEntity pedidoAbast = getPedido(idPedidoAbast);

			actualizarPedidoAbast(pedidoAbast, envio);

			if (pedidoAbast.getOcAsociada().isPendiente()) {
				LOGGER.info("La orden de compra "
						+ pedidoAbast.getOcAsociada().getIdOrden()
						+ " esta pendiente. Procesando...");

				ItemRodamientoEntity itemRodAsociadoAPedido = ordenDeCompra
						.getItemRodamiento(pedidoAbast.getOcAsociada()
								.getIdOrden(), rodPedido);

				if (itemRodAsociadoAPedido != null) {
					int cantidadAEnviar = 0;
					LOGGER.info("Procesando el itemRodamiento de la OC con id: "
							+ itemRodAsociadoAPedido.getId());
					if (itemRodAsociadoAPedido.getPendientes() > 0) {
						LOGGER.info("El item tiene pendientes. Agregando item al remito..");
						int cantRecibida = envio.getCantidad();
						int diferencia = itemRodAsociadoAPedido.getPendientes()
								- cantRecibida;

						if (diferencia >= 0) {
							LOGGER.info("Se ha recibido menor mercaderia de la pendiente. Actualizando pendientes a "+ diferencia);
							cantidadAEnviar = envio.getCantidad();
							itemRodAsociadoAPedido.setPendientes(diferencia);
						} else if (diferencia < 0) {
							cantidadAEnviar = itemRodAsociadoAPedido
									.getPendientes();
							itemRodAsociadoAPedido.setPendientes(0);

							LOGGER.info("Se ha recibido mayor mercaderia de la pendiente. Aumentando stock por "
									+ diferencia * (-1));
							rodPedido.aumentarStock(diferencia * (-1));
							rodamiento.guardarRodamiento(rodPedido);
						}
						itemRemito.setCantidaEnviada(cantidadAEnviar);
						itemRemito.setOcAsociada(pedidoAbast.getOcAsociada());
						itemRemito.setRodamiento(rodPedido);

						OrdenDeCompraEntity oc = ordenDeCompra
								.verificarPendientes(pedidoAbast
										.getOcAsociada());
						ordenDeCompra.guardarOrdenDeCompra(oc);
					} else {
						LOGGER.info("El item de la OC no tiene pendientes. Aumentando stock por "
								+ envio.getCantidad());
						rodPedido.aumentarStock(envio.getCantidad());
						rodamiento.guardarRodamiento(rodPedido);
						itemRemito = null;
					}
				} else {
					LOGGER.warn("El rodamiento recibido no tiene ningun rodamiento "
							+ "asociado al id de Pedido de Abastecimiento recibido.");
					itemRemito = null;
				}

			} else {
				LOGGER.info("La OC para este item recibido esta completa. Aumentando stock por "
						+ envio.getCantidad());
				rodPedido.aumentarStock(envio.getCantidad());
				rodamiento.guardarRodamiento(rodPedido);
				itemRemito = null;
			}
		}
		return itemRemito;
	}

	private PedidoDeAbastecimientoEntity getPedido(int idPedidoAbast) {
		for (PedidoDeAbastecimientoEntity p : pedidosAbast)
			if (p.getIdPedido() == idPedidoAbast)
				return p;
		return null;
	}

	private void actualizarPedidoAbast(PedidoDeAbastecimientoEntity pedido,
			RodamientoListaVO envio) {
		int resto = pedido.getCantidadPendiente() - envio.getCantidad();

		LOGGER.info("Actualizando pedido de abastecimiento ("
				+ pedido.getIdPedido() + "), cantidad pendiente: " + resto);
		if (resto < 0)
			LOGGER.error("Se recibio " + envio.getCantidad()
					+ " y el pedido tenia " + pedido.getCantidadPendiente()
					+ " pendientes para id de Pedido " + pedido.getIdPedido());

		pedido.setCantidadPendiente(resto);
		pedidos.guardarPedido(pedido);
	}

	private List<OficinaDeVentaEntity> getListaODVsUnicas(
			RecepcionRodamientosVO recepcionRodamientos) {
		List<OficinaDeVentaEntity> odvsUnicas = new ArrayList<OficinaDeVentaEntity>();

		OficinaDeVentaEntity odvo = getODVAsociadaAPedido(recepcionRodamientos
				.getListaRodVO().get(0).getIdPedidoAbastecimiento());
		if (odvo != null)
			odvsUnicas.add(odvo);

		for (RodamientoListaVO envio : recepcionRodamientos.getListaRodVO()) {
			odvo = getODVAsociadaAPedido(envio.getIdPedidoAbastecimiento());
			if (!odvsUnicas.contains(odvo)) {
				odvsUnicas.add(odvo);
			}
		}
		return odvsUnicas;
	}

	private OficinaDeVentaEntity getODVAsociadaAPedido(
			int idPedidoAbastecimiento) {

		OficinaDeVentaEntity odv = null;
		for (PedidoDeAbastecimientoEntity p : pedidosAbast) {
			if (p.getIdPedido() == idPedidoAbastecimiento) {
				odv = (p.getOcAsociada().getOdv());
				break;
			}
		}
		return odv;
	}
}
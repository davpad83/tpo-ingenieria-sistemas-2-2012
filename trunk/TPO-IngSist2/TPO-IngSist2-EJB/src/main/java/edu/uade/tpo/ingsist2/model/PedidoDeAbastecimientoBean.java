package edu.uade.tpo.ingsist2.model;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.model.entities.PedidoDeAbastecimientoEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.model.util.EnviarMensajeHelper;
import edu.uade.tpo.ingsist2.view.jms.JMSQueuesNames;

@Stateless
public class PedidoDeAbastecimientoBean implements PedidoDeAbastecimiento {

	private static final Logger LOGGER = Logger
			.getLogger(PedidoDeAbastecimientoBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	@Override
	public PedidoDeAbastecimientoEntity generarPedidoAbastecimiento(OrdenDeCompraEntity oc, ItemRodamientoEntity ire,int stockRodamiento) {

		PedidoDeAbastecimientoEntity pedido = new PedidoDeAbastecimientoEntity();
		pedido.setCantidadPedida(stockRodamiento);
		pedido.setCantidadPendiente(stockRodamiento);
		pedido.setOcAsociada(oc);
		pedido.setProveedor(ire.getCotizacion().getLista().getProveedor());
		pedido.setRodamiento(ire.getCotizacion().getRodamiento());
		return pedido;
	}

	@Override
	public PedidoDeAbastecimientoEntity guardarPedido(PedidoDeAbastecimientoEntity p) {
		LOGGER.info("Guardando Pedido de abastecimiento ...");
		PedidoDeAbastecimientoEntity pGuardado = null;
		try {
			pGuardado = (PedidoDeAbastecimientoEntity) entityManager.merge(p);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al guardar el Pedido");
			LOGGER.error(e);
		}
		LOGGER.info("Pedido guardado con id: " + pGuardado.getIdPedido());
		return pGuardado;
	}

	@Override
	public void eliminarPedido(int id) {
		LOGGER.info("Procesando eliminar Pedido con id: " + id);
		try {
			PedidoDeAbastecimientoEntity p = entityManager.find(
					PedidoDeAbastecimientoEntity.class, id);
			entityManager.remove(p);
		} catch (Exception e) {
			LOGGER.error("Hubo un error intentando eliminar el Pedido de Abastecimiento con id "
					+ id);
			LOGGER.error(e);
		}
		LOGGER.info("El Pedido de Abastecimiento con id " + id
				+ " se ha eliminado con exito.");
	}

	@Override
	public PedidoDeAbastecimientoEntity getPedido(int id) {
		LOGGER.info("Buscando pedido con id " + id);
		PedidoDeAbastecimientoEntity p = null;
		try {
			p = entityManager.find(PedidoDeAbastecimientoEntity.class, id);
		} catch (NoResultException nre){
			LOGGER.warn("No se encontro el pedido de abastecimiento que coincida con los datos de entrada.");
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar el pedido.");
			LOGGER.error(e);
			return null;
		} finally {
			if (p != null)
				LOGGER.info("El pedido con id " + id + " se ha encontrado");
		}
		return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PedidoDeAbastecimientoEntity> getPedidos() {
		LOGGER.info("Buscando pedidos activos");
		ArrayList<PedidoDeAbastecimientoEntity> listaResultado = null;
		try {
			listaResultado = (ArrayList<PedidoDeAbastecimientoEntity>) entityManager
					.createQuery(
							"FROM "
									+ PedidoDeAbastecimientoEntity.class
											.getSimpleName()).getResultList();
		} catch (NoResultException nre){
			LOGGER.warn("No se encontraron pedidos de abastecimiento.");
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar todos los pedidos.");
			LOGGER.error(e);
		} finally {
			if (listaResultado != null && !listaResultado.isEmpty())
				LOGGER.info("Se han encontrado " + listaResultado.size()
						+ " pedidos");
		}
		return listaResultado;
	}

	@Override
	public void enviarPedido(PedidoDeAbastecimientoEntity pedido) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		LOGGER.info("Enviando pedido de abastecimiento a proveedor...");
		EnviarMensajeHelper emHelper = new EnviarMensajeHelper("127.0.0.1",
				1099, JMSQueuesNames.RECIBIR_PEDIDOS_PROVE_MOCK);
		 emHelper.enviarMensaje(pedido.getVO().toXML(true));
		 emHelper.cerrarConexion();
	}

	@Override
	public boolean validarRodamientoPedido(RodamientoEntity rodamiento,
			int idPedidoAbastecimiento) {
		RodamientoEntity rodPedido = getPedido(idPedidoAbastecimiento).getRodamiento();
		
		return rodamiento.equals(rodPedido);
	}
}

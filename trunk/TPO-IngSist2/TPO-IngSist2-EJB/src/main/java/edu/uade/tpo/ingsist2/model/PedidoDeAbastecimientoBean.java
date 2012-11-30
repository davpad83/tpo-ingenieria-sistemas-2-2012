package edu.uade.tpo.ingsist2.model;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.model.entities.PedidoDeAbastecimientoEntity;
import edu.uade.tpo.ingsist2.view.vo.PedidoAbastecimientoVO;

@Stateless
public class PedidoDeAbastecimientoBean implements PedidoDeAbastecimiento {

	private static final Logger LOGGER = Logger.getLogger(PedidoDeAbastecimientoBean.class);
	
	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;
	
	@Override
	public void generarPedidoAbastecimiento(OrdenDeCompraEntity oc, ItemRodamientoEntity ire,
			int stockRodamiento) {

		PedidoDeAbastecimientoEntity pedido = new PedidoDeAbastecimientoEntity();
		pedido.setCantidadPedida(stockRodamiento);
		pedido.setCantidadPendiente(stockRodamiento);
		pedido.setOcAsociada(oc);
		pedido.setProveedor(ire.getCotizacion().getLista().getProveedor());
		pedido.setRodamiento(ire.getCotizacion().getRodamiento());
		pedido.setRecibido(false);
	}

	@Override
	public void guardarPedido(PedidoDeAbastecimientoEntity p) {
		LOGGER.info("Guardando Pedido " + p.getIdPedido());
		PedidoDeAbastecimientoEntity pGuardado = null;
		try {
			pGuardado = (PedidoDeAbastecimientoEntity) entityManager.merge(p);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al guardar el Pedido");
			e.printStackTrace();
		}
		LOGGER.info("Pedido guardado con id: " + pGuardado.getIdPedido());
	}
	
	@Override
	public void eliminarPedido(int id) {
		LOGGER.info("Procesando eliminar Pedido con id: " + id);
		try {
			PedidoDeAbastecimientoEntity p = entityManager.find(PedidoDeAbastecimientoEntity.class, id);
			entityManager.remove(p);
		} catch (Exception e) {
			LOGGER.error("Hubo un error intentando eliminar el Pedido de Abastecimiento con id "
					+ id);
			e.printStackTrace();
		}
		LOGGER.info("El Pedido de Abastecimiento con id " + id + " se ha eliminado con exito.");
	}

	@Override
	public PedidoDeAbastecimientoEntity getPedido(int id) {
		LOGGER.info("Buscando pedido con id " + id);
		PedidoDeAbastecimientoEntity p = null;
		try {
			p = entityManager.find(PedidoDeAbastecimientoEntity.class, id);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar el pedido.");
			e.printStackTrace();
			return null;
		} finally {
			if (p != null)
				LOGGER.info("El pedido con id " + id
						+ " se ha encontrado");
			else {
				LOGGER.info("No se ha encontrado el pedido con id " + id);
				return null;
			}
		}
		return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PedidoDeAbastecimientoEntity> getPedidos() {
		LOGGER.info("Buscando pedidos activos");
		ArrayList<PedidoDeAbastecimientoEntity> listaResultado = null;
		try {
			listaResultado = (ArrayList<PedidoDeAbastecimientoEntity>) entityManager.createQuery(
					"FROM " + PedidoDeAbastecimientoEntity.class.getSimpleName()).getResultList();
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar todos los pedidos.");
			e.printStackTrace();
			return null;
		} finally {
			if (listaResultado == null || listaResultado.isEmpty()) {
				LOGGER.info("No se han encontrado pedidos");
				return null;
			} else
				LOGGER.info("Se han encontrado " + listaResultado.size()
						+ " pedidos");
		}
		return listaResultado;
	}
}

package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.PedidoDeAbastecimiento;
import edu.uade.tpo.ingsist2.model.entities.Proveedor;
import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.facade.FacadeBean;
import edu.uade.tpo.ingsist2.view.vo.PedidoAbastecimientoVO;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;

@Stateless
public class AdministrarPedidosDeAbastecimientoBean implements AdministrarPedidosDeAbastecimiento {

	private static Logger logger = Logger
			.getLogger(AdministrarPedidosDeAbastecimientoBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	public AdministrarPedidosDeAbastecimientoBean() {
		// empty
	}

	@Override
	public void guardarPedido(PedidoAbastecimientoVO p) {
		logger.info("Guardando Pedido " + p.getIdPedido());
		PedidoDeAbastecimiento pBean = new PedidoDeAbastecimiento();
		pBean.setVO(p);
		PedidoDeAbastecimiento pGuardado = null;
		try {
			pGuardado = (PedidoDeAbastecimiento) entityManager.merge(pBean);
		} catch (Exception e) {
			logger.error("Hubo un error al guardar el Pedido");
			e.printStackTrace();
		}
		logger.info("Pedido guardado con id: " + pGuardado.getIdPedido());
	}

	@Override
	public void eliminarPedido(int id) {
		logger.info("Procesando eliminar Pedido con id: " + id);
		try {
			PedidoDeAbastecimiento p = entityManager.find(PedidoDeAbastecimiento.class, id);
			entityManager.remove(p);
		} catch (Exception e) {
			logger.error("Hubo un error intentando eliminar el Pedido de Abastecimiento con id "
					+ id);
			e.printStackTrace();
		}
		logger.info("El Pedido de Abastecimiento con id " + id + " se ha eliminado con exito.");
	}

	@Override
	public PedidoAbastecimientoVO getPedido(int id) {
		logger.info("Buscando pedido con id " + id);
		PedidoDeAbastecimiento p = null;
		try {
			p = entityManager.find(PedidoDeAbastecimiento.class, id);
		} catch (Exception e) {
			logger.error("Hubo un error al buscar el pedido.");
			e.printStackTrace();
			return null;
		} finally {
			if (p != null)
				logger.info("El pedido con id " + id
						+ " se ha encontrado");
			else {
				logger.info("No se ha encontrado el pedido con id " + id);
				return null;
			}
		}
		return p.getVO();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PedidoAbastecimientoVO> getPedidosActivos() {
		logger.info("Buscando pedidos activos");
		ArrayList<PedidoDeAbastecimiento> listaResultado = null;
		try {
			listaResultado = (ArrayList<PedidoDeAbastecimiento>) entityManager.createQuery(
					"FROM PedidoDeAbastecimiento").getResultList();
		} catch (Exception e) {
			logger.error("Hubo un error al buscar todos los pedidos.");
			e.printStackTrace();
			return null;
		} finally {
			if (listaResultado == null || listaResultado.isEmpty()) {
				logger.info("No se han encontrado pedidos");
				return null;
			} else
				logger.info("Se han encontrado " + listaResultado.size()
						+ " pedidos");
		}
		return PedidoDeAbastecimiento.getVOList(listaResultado);
	}

	
}

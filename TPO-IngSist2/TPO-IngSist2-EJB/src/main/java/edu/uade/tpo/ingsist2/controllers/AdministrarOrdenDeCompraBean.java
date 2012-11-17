package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;


import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.view.vo.OrdenDeCompraVO;

/**
 * Session Bean implementation class AdministrarCotizacionesBean
 */
@Stateless
public class AdministrarOrdenDeCompraBean implements AdministrarOrdenDeCompra {

	private static Logger logger = Logger
	.getLogger(AdministrarOrdenDeCompraBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	public AdministrarOrdenDeCompraBean() {
	// empty
	}

	@Override
	public void guardarOrdenCompra(OrdenDeCompraVO p) {
		logger.info("Guardando Orden " + p.getIdOrden());
		OrdenDeCompraEntity pBean = new OrdenDeCompraEntity();
		pBean.setVO(p);
		OrdenDeCompraEntity pGuardado = null;
		try {
			pGuardado = (OrdenDeCompraEntity) entityManager.merge(pBean);
		} catch (Exception e) {
			logger.error("Hubo un error al guardar la orden");
			e.printStackTrace();
		}
		logger.info("Orden guardada con id: " + pGuardado.getIdOrden());
		
	}

	@Override
	public void eliminarOrdenCompra(int id) {
		logger.info("Procesando eliminar Orden con id: " + id);
		try {
			OrdenDeCompraEntity p = entityManager.find(OrdenDeCompraEntity.class, id);
			entityManager.remove(p);
		} catch (Exception e) {
			logger.error("Hubo un error intentando eliminar la orden con id "
					+ id);
			e.printStackTrace();
		}
		logger.info("La OC con id " + id + " se ha eliminado con exito.");
		
	}

	@Override
	public OrdenDeCompraVO getOrdenCompra(int id) {
		logger.info("Buscando pedido con id " + id);
		OrdenDeCompraEntity p = null;
		try {
			p = entityManager.find(OrdenDeCompraEntity.class, id);
		} catch (Exception e) {
			logger.error("Hubo un error al buscar la orden de compra.");
			e.printStackTrace();
			return null;
		} finally {
			if (p != null)
				logger.info("la orden de compra con id " + id
						+ " se ha encontrado");
			else {
				logger.info("No se ha encontrado la orden de compra con id " + id);
				return null;
			}
		}
		return p.getVO();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<OrdenDeCompraVO> getOrdenesDeCompra() {
		logger.info("Buscando pedidos activos");
		ArrayList<OrdenDeCompraEntity> listaResultado = null;
		try {
			listaResultado = (ArrayList<OrdenDeCompraEntity>) entityManager.createQuery(
					"FROM " + OrdenDeCompraEntity.class.getSimpleName()).getResultList();
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
		return OrdenDeCompraEntity.getVOList(listaResultado);
	}


}

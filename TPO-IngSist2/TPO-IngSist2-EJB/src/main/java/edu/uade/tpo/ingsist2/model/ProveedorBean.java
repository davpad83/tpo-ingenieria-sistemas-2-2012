package edu.uade.tpo.ingsist2.model;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.controllers.AdministrarProveedoresBean;
import edu.uade.tpo.ingsist2.model.entities.ProveedorEntity;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;

/**
 * Session Bean implementation class ProveedorBean
 */
@Stateless
public class ProveedorBean implements Proveedor {

	private static Logger logger = Logger.getLogger(ProveedorBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;
	
	@Override
	public void guardarProveedor(ProveedorEntity p) {
		logger.info("Procesando guardar proveedor con cuit " + p.getCuit());
		
		ProveedorEntity pGuardado = null;
		try {
			pGuardado = (ProveedorEntity) entityManager.merge(p);
		} catch (Exception e) {
			logger.error("Hubo un error al guardar el proveedor");
			e.printStackTrace();
		}
		logger.info("Proveedor guardado con id: " + pGuardado.getId());
	}

	@Override
	public void eliminarProveedor(int id) {
		logger.info("Procesando eliminar proveedor con id: " + id);
		try {
			ProveedorEntity p = entityManager.find(ProveedorEntity.class, id);
			entityManager.remove(p);
		} catch (Exception e) {
			logger.error("Hubo un error intentando eliminar el proveedor con id "
					+ id);
			e.printStackTrace();
		}
		logger.info("El proveedor con id " + id + " se ha eliminado con exito.");
	}

	@Override
	public ProveedorEntity getProveedor(int id) {
		logger.info("Buscando Proveedor con id " + id);
		ProveedorEntity p = null;
		try {
			p = entityManager.find(ProveedorEntity.class, id);
		} catch (Exception e) {
			logger.error("Hubo un error al buscar el proveedor.");
			e.printStackTrace();
			return null;
		} finally {
			if (p != null)
				logger.info("El Proveedor con id " + id
						+ " se ha encontrado, su cuit es " + p.getCuit());
			else {
				logger.info("No se ha encontrado el proveedor con id " + id);
				return null;
			}
		}
		return p;
	}

	public ArrayList<ProveedorEntity> getProveedores() {

		logger.info("Buscando lista de Proveedores");
		ArrayList<ProveedorEntity> listaResultado = null;
		try {
			listaResultado = (ArrayList<ProveedorEntity>) entityManager
					.createQuery(
							"FROM " + ProveedorEntity.class.getSimpleName())
					.getResultList();
		} catch (Exception e) {
			logger.error("Hubo un error al buscar todos los proveedores.");
			e.printStackTrace();
			return null;
		} finally {
			if (listaResultado == null || listaResultado.isEmpty()) {
				logger.info("No se han encontrado instancias de Proveedores");
				return null;
			} else
				logger.info("Se han encontrado " + listaResultado.size()
						+ " instancias de Proveedores");
		}
		return listaResultado;
	}

	@Override
	public ProveedorEntity getProveedorPorNombre(String nombre) {
		logger.info("Buscando Proveedores por nombre: " + nombre);
		ProveedorEntity prove = null;
		try {
			prove = (ProveedorEntity) entityManager
					.createQuery(
							"FROM " + ProveedorEntity.class.getSimpleName()
									+ " WHERE nombre = :nomProve")
					.setParameter("nomProve", nombre).getSingleResult();
		} catch (Exception e) {
			logger.error("Hubo un error al buscar el proveedor.");
			e.printStackTrace();
			return null;
		} finally {
			if (prove == null) {
				logger.info("No se ha encontrado el Proveedor con nombre "
						+ nombre);
				return null;
			} else
				logger.info("Se ha encontrado el proveedor.");
		}
		return prove;
	}
}

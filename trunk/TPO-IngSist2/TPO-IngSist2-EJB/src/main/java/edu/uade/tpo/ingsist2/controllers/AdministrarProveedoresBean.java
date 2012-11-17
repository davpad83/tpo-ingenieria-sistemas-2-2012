package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.ProveedorEntity;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;

@Stateless
public class AdministrarProveedoresBean implements AdministrarProveedores {

	private static Logger logger = Logger
			.getLogger(AdministrarProveedoresBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	public AdministrarProveedoresBean() {
		// empty
	}

	@Override
	public void guardarProveedor(ProveedorVO p) {
		logger.info("Procesando guardar proveedor con cuit " + p.getCuit());
		ProveedorEntity pBean = new ProveedorEntity();
		pBean.setVO(p);
		ProveedorEntity pGuardado = null;
		try {
			pGuardado = (ProveedorEntity) entityManager.merge(pBean);
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
	public ProveedorVO getProveedor(int id) {
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
		return p.getVO();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ProveedorVO> getProveedores() {
		logger.info("Buscando lista de Proveedores");
		ArrayList<ProveedorEntity> listaResultado = null;
		try {
			listaResultado = (ArrayList<ProveedorEntity>) entityManager.createQuery(
					"FROM ProveedorEntity").getResultList();
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
		return ProveedorEntity.getVOList(listaResultado);
	}
}

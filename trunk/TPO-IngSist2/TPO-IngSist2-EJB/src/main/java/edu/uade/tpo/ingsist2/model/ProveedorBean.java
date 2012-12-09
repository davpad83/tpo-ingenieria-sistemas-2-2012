package edu.uade.tpo.ingsist2.model;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.ProveedorEntity;

/**
 * Session Bean implementation class ProveedorBean
 */
@Stateless
public class ProveedorBean implements Proveedor {

	private static final Logger LOGGER = Logger.getLogger(ProveedorBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	@Override
	public void guardarProveedor(ProveedorEntity p) {
		LOGGER.info("Procesando guardar proveedor con cuit " + p.getCuit());

		ProveedorEntity pGuardado = null;
		try {
			pGuardado = (ProveedorEntity) entityManager.merge(p);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al guardar el proveedor");
			LOGGER.error(e);
		}
		LOGGER.info("Proveedor guardado con id: " + pGuardado.getId());
	}

	@Override
	public void eliminarProveedor(int id) {
		LOGGER.info("Procesando eliminar proveedor con id: " + id);
		try {
			ProveedorEntity p = entityManager.find(ProveedorEntity.class, id);
			entityManager.remove(p);
		} catch (Exception e) {
			LOGGER.error("Hubo un error intentando eliminar el proveedor con id "
					+ id);
			LOGGER.error(e);
		}
		LOGGER.info("El proveedor con id " + id + " se ha eliminado con exito.");
	}

	@Override
	public ProveedorEntity getProveedor(int id) {
		LOGGER.info("Buscando Proveedor con id " + id);
		ProveedorEntity p = null;
		try {
			p = entityManager.find(ProveedorEntity.class, id);
		} catch (NoResultException nre){
			LOGGER.warn("No se encontro el proveedor que coincida con los datos de entrada.");
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar el proveedor.");
			LOGGER.error(e);
		} finally {
			if (p != null)
				LOGGER.info("El Proveedor con id " + id
						+ " se ha encontrado, su cuit es " + p.getCuit());
			else {
				LOGGER.info("No se ha encontrado el proveedor con id " + id);
				return null;
			}
		}
		return p;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ProveedorEntity> getProveedores() {

		LOGGER.info("Buscando lista de Proveedores");
		ArrayList<ProveedorEntity> listaResultado = null;
		try {
			listaResultado = (ArrayList<ProveedorEntity>) entityManager
					.createQuery(
							"FROM " + ProveedorEntity.class.getSimpleName())
					.getResultList();
		} catch (NoResultException nre){
			LOGGER.warn("No se ha encontrado ningun proveedor.");
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar todos los proveedores.");
			LOGGER.error(e);
		} finally {
			if (listaResultado != null || !listaResultado.isEmpty())
				LOGGER.info("Se han encontrado " + listaResultado.size()
						+ " instancias de Proveedores");
		}
		return listaResultado;
	}

	@Override
	public ProveedorEntity getProveedorPorNombre(String nombre) {
		LOGGER.info("Buscando Proveedores por nombre: " + nombre);
		ProveedorEntity prove = null;
		try {
			prove = (ProveedorEntity) entityManager
					.createQuery(
							"FROM " + ProveedorEntity.class.getSimpleName()
									+ " WHERE nombre = :nomProve")
					.setParameter("nomProve", nombre).getSingleResult();
		} catch (NoResultException nre){
			LOGGER.warn("No se encontro el proveedor que coincida con los datos de entrada.");
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar el proveedor.");
			LOGGER.error(e);
		} finally {
			if (prove != null) 
				LOGGER.info("Se ha encontrado el proveedor.");
		}
		return prove;
	}

	public ProveedorEntity getProveedorPorCuit(String cuit) {
		LOGGER.info("Buscando Proveedores por cuit: " + cuit);
		ProveedorEntity prove = null;
		try {
			prove = (ProveedorEntity) entityManager
					.createQuery(
							"FROM " + ProveedorEntity.class.getSimpleName()
									+ " WHERE cuit = :cuitProve")
					.setParameter("cuitProve", cuit).getSingleResult();
		} catch (NoResultException nre){
			LOGGER.warn("No se encontro el proveedor que coincida con los datos de entrada.");
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar el proveedor.");
			LOGGER.error(e);
		} finally {
			if (prove != null) 
				LOGGER.info("Se ha encontrado el proveedor.");
		}
		return prove;
	}

	@Override
	public String getTiempoDeEntrega(int idItemLista) {
		LOGGER.info("Buscando tiempo de entrega para itemListas con id: "+idItemLista);
		Query query = entityManager.createQuery(
				"SELECT distinct LP.proveedor.tiempoDeEntrega "
						+ " FROM ListaPreciosEntity LP join LP.items IL"
						+ " WHERE IL.id = :idItem").setParameter("idItem",
				idItemLista);
		String tpoEntrega = "";
		try {
			tpoEntrega = (String) query.getSingleResult();
		} catch (NoResultException nre){
			LOGGER.warn("No se encontro el tiempo de entrega");
		} catch (Exception e) {
			LOGGER.error("Hubo un error al ejecutar query.");
			LOGGER.error(e);
		} finally {
			if(!tpoEntrega.isEmpty())
				LOGGER.info("El tiempo de entrega encontrado es de \""+tpoEntrega+"\"");
		}
		return tpoEntrega;
	}
}

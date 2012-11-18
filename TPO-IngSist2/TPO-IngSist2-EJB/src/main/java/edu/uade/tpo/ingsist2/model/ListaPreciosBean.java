package edu.uade.tpo.ingsist2.model;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.ItemListaEntity;
import edu.uade.tpo.ingsist2.model.entities.ListaPreciosEntity;
import edu.uade.tpo.ingsist2.model.entities.ProveedorEntity;
import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;

/**
 * Session Bean implementation class ListaPreciosBean
 */
@Stateless
public class ListaPreciosBean implements ListaPrecios {

	private static final Logger LOGGER = Logger
			.getLogger(ListaPreciosBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	@EJB
	private Proveedor proveedor;

	@Override
	public void agregarListaPrecios(ListaPreciosEntity lp) {
		if (validarListaPreciosRequest(lp)) {
			LOGGER.info("La Lista de precios es VALIDA. Guardando ...");

			try {
				ListaPreciosEntity lpGuardado = entityManager.merge(lp);
				if (lpGuardado.getIdLista() > 0)
					LOGGER.info("La Lista de precios fue guardada con EXITO. Su id es "
							+ lpGuardado.getIdLista());
			} catch (Exception e) {
				LOGGER.error("Hubo un error al guardar la lista de precios");
				LOGGER.error(e);
			}
		}
	}

	@Override
	public ListaPreciosEntity getListaPrecioPorIdItemLista(int idItemLista) {
		LOGGER.info("Buscando Lista de Precios por idItemLista " + idItemLista);
		ListaPreciosEntity listaEncontrada = null;
		try {
			listaEncontrada = (ListaPreciosEntity) entityManager
					.createQuery(
							"FROM " + ListaPreciosEntity.class.getSimpleName()
									+ "LP WHERE LP.items.id = :idItem")
					.setParameter("idItem", idItemLista).getSingleResult();
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar el item lista.");
			LOGGER.error(e);
		}
		LOGGER.info("Lista de Precios encontrada, su id es: "
				+ listaEncontrada.getIdLista());

		return listaEncontrada;
	}

	private boolean validarListaPreciosRequest(ListaPreciosEntity lpr) {
		boolean valid = true;
		String logPrefix = "Error de validacion de ListaPrecios ";
		if (lpr.getItems() == null) {
			LOGGER.error(logPrefix + "- La lista de items es nula. ");
			valid = false;
		} else if (lpr.getItems().isEmpty()) {
			LOGGER.error(logPrefix + "- La lista de items esta vacia.");
			valid = false;
		}
		ProveedorEntity prove = proveedor.getProveedorPorCuit(lpr.getProveedor().getCuit());
		if (prove == null) {
			LOGGER.error(logPrefix + "- El proveedor es nulo.");
			valid = false;
		} else
			lpr.getProveedor().setId(prove.getId());
		return valid;
	}
}

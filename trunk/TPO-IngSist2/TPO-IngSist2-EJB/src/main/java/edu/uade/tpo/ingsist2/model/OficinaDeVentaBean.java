package edu.uade.tpo.ingsist2.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.CotizacionEntity;
import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.view.vo.OficinaDeVentaVO;

@Stateless
public class OficinaDeVentaBean implements OficinaDeVenta {
	
	private static final Logger LOGGER = Logger
			.getLogger(ListaPreciosBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	public void guardarOficinaDeVenta(OficinaDeVentaVO odv){
		LOGGER.info("LLEGO EL MENSAJE!");
	}

	@Override
	public OficinaDeVentaEntity getOficina(int idODV) {
		LOGGER.info("Buscando Oficina de Venta con id " + idODV);
		OficinaDeVentaEntity ofi = null;
		try {
			ofi = entityManager.find(OficinaDeVentaEntity.class, idODV);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar la oficina.");
			LOGGER.error(e);
		} finally {
			if (ofi != null)
				LOGGER.info("Se ha encontrado la oficina, su id es: " + ofi.getId());
			else {
				LOGGER.info("No se ha encontrado la oficina con id " + idODV);
				return null;
			}
		}
		return ofi;
	}

	@Override
	public boolean existe(int idODV) {
		return getOficina(idODV) == null;
	}
	
}

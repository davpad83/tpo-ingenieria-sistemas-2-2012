package edu.uade.tpo.ingsist2.model;

import java.util.Date;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import edu.uade.tpo.ingsist2.controllers.AdministrarCotizacionesBean;
import edu.uade.tpo.ingsist2.model.entities.CotizacionEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemListaEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.ListaPreciosEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;

/**
 * Session Bean implementation class CotizacionBean
 */
@Stateless
public class CotizacionBean implements Cotizacion {

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	private static final Logger LOGGER = Logger.getLogger(CotizacionBean.class);

	@Override
	public SolicitudCotizacionResponse procesarSolicitudCotizacion(
			SolicitudCotizacionRequest scr) {
		// TODO Auto-generated method stub
		return null;
	}

	public ItemListaEntity getItemListaConMenorPrecioConMarca(
			RodamientoEntity rod) {
		if (rod.getMarca() == null || rod.getMarca().isEmpty())
			return getItemListaConMenorPrecioSinMarca(rod);
		LOGGER.info("Buscando item lista con menor precio para codigoSKF: "
				+ rod.getCodigoSKF() + ", pais: " + rod.getPais()
				+ " y marca: " + rod.getMarca());
	         ItemListaEntity itEncontrado = null;
		
		try {
			Query query = entityManager
					.createQuery(
							"SELECT IL FROM ItemListaEntity IL" 
							+" WHERE IL.precio = "
									+ "(SELECT MIN(IL2.precio) " 
									+ " FROM ItemListaEntity IL2" 
									+ " WHERE IL2.rodamiento.codigoSKF = :codigo " 
										+ "AND IL2.rodamiento.marca = :marca " 

										+ "AND IL2.rodamiento.pais = :pais"
										+ "GROUP BY IL2.rodamiento.marca)"

										+ "AND IL2.rodamiento.pais = :pais)")
					.setParameter("codigo", rod.getCodigoSKF())
					.setParameter("marca", rod.getMarca())
					.setParameter("pais", rod.getPais());
			LOGGER.debug("Executing query: " + query.toString());
			itEncontrado = (ItemListaEntity) query.getSingleResult();
		} catch (HibernateException he){
			he.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Hubo un error al buscar el item lista.");
			e.printStackTrace();
		}
		LOGGER.info("ItemLista encontrado, su id es: " + itEncontrado.getId());

		return itEncontrado;
	}

	public ItemListaEntity getItemListaConMenorPrecioSinMarca(
			RodamientoEntity rod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cotizacion getCotizacion(CotizacionEntity cotizacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int verificarStock(ItemRodamientoEntity ire) {
		RodamientoEntity rod = ire.getRodamiento();
		if(rod.hayStockSuficiente(ire.getCantidad()))
			return rod.getStock();
		else
			return 0;
	}

	@Override
	public boolean validarVigenciaLista(ListaPreciosEntity lista) {
		boolean valida = true;
		if(lista.getVigenciaDesde().after(new Date()) || lista.getVigenciaHasta().before(new Date()))
			valida = false;
		return valida;
	}
}

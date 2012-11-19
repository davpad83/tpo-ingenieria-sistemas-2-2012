package edu.uade.tpo.ingsist2.model;

import java.util.Map;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.CotizacionEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemListaEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.ListaPreciosEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;

@Local
public interface Cotizacion {

	public SolicitudCotizacionResponse procesarSolicitudCotizacion(
			SolicitudCotizacionRequest scr);

	public ItemListaEntity getItemListaConMenorPrecioConMarca(
			RodamientoEntity rod);

	public ItemListaEntity getItemListaConMenorPrecioSinMarca(
			RodamientoEntity rod);

	public Cotizacion getCotizacion(CotizacionEntity cotizacion);

	/**
	 * Verifica el stock actual basado en el rodamiento y cantidad
	 * contenidos en el ItemRodamiento recibido.
	 * Devuelve 0 si no hay stock.
	 * Devuelve la cantidad de stock actual si hay stock suficiente.
	 * 
	 * 
	 * @param ire
	 * @return
	 */
	
	public int verificarStock(ItemRodamientoEntity ire, Map<RodamientoEntity, ListaPreciosEntity> mapaRodEnt);
}

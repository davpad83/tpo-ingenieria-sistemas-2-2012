package edu.uade.tpo.ingsist2.model;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.ItemListaEntity;
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
}

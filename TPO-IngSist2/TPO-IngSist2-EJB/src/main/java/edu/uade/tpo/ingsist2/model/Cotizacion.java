package edu.uade.tpo.ingsist2.model;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.CotizacionEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemListaEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.ListaPreciosEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;

@Local
public interface Cotizacion {
	

	public ItemListaEntity getItemListaConMenorPrecioConMarca(RodamientoEntity rod);

	public List<ItemListaEntity> getItemsListaConMenorPrecioSinMarca(RodamientoEntity rod);

	public CotizacionEntity getCotizacion(int idCot);
	
	public CotizacionEntity getCotizacion(int idPedidoCotODV, int idODV, RodamientoEntity rod);
	
	public int guardarCotizacion(CotizacionEntity cotizacion);

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
	
	public int verificarStock(ItemRodamientoEntity ire);

	/**
	 * Devuelve verdadero si la lista de precios esta en vigencia
	 * y falso si no lo esta.
	 * 
	 * @param lista
	 * @return
	 */
	public boolean validarVigenciaLista(ListaPreciosEntity lista);

	public boolean existe(int idPedidoCotODV, int idODV, RodamientoEntity rod);

	public ArrayList<ItemRodamientoEntity> getItemsCotizados(int id);

	public boolean validarRequest(SolicitudCotizacionRequest cotRequest);
}

package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.Cotizacion;
import edu.uade.tpo.ingsist2.model.OficinaDeVenta;
import edu.uade.tpo.ingsist2.model.OrdenDeCompra;
import edu.uade.tpo.ingsist2.model.PedidoDeAbastecimiento;
import edu.uade.tpo.ingsist2.model.Remito;
import edu.uade.tpo.ingsist2.model.Rodamiento;
import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.model.entities.RemitoEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.ItemVO;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCompraRequest;

/**
 * Session Bean implementation class RecepcionSolicitudDeCompraControllerBean
 */
@Stateless
public class RecepcionSolicitudDeCompraControllerBean implements
		RecepcionSolicitudDeCompraController {

	private static final Logger LOGGER = Logger
			.getLogger(RecepcionSolicitudDeCompraControllerBean.class);

	@EJB
	private OrdenDeCompra ordenDeCompra;

	@EJB
	private OficinaDeVenta oficinaVenta;
	
	@EJB
	private Rodamiento rodamiento;
	
	@EJB
	private Cotizacion cotizacion;

	@EJB
	private PedidoDeAbastecimiento pedidoAbastecimiento;
	
	@EJB
	private Remito remito;
	
	@Override
	public void procesarSolicitudDeCompra(SolicitudCompraRequest request) {
		LOGGER.info("==================PROCESANDO SOLICITUD DE COMPRA BEGIN==================");
		LOGGER.info("Request enviado de ODV: " + request.getIdODV());
		
		if (ordenDeCompra.validarSolicitudCompra(request)) {
			OrdenDeCompraEntity oce = fromRequestToOCEntity(request);
			OrdenDeCompraEntity ocGuardada = ordenDeCompra.guardarOrdenDeCompra(oce);

			RemitoEntity rem = null;
			for (ItemRodamientoEntity ire : ocGuardada.getItems()) {

				// cotizacion.verificarStock(ire);
				RodamientoEntity rodActual = ire.getCotizacion()
						.getRodamiento();

				int stockSolicitado = rodActual.getStock();
				if (cotizacion.validarVigenciaLista(ire.getCotizacion()
						.getLista())) {
					LOGGER.info("El item cotizado con id " + ire.getId()
							+ " esta en vigencia, verificando stock...");
					
					//HAY STOCK PARA ENVIAR EL ITEM COMPLETO
					if (stockSolicitado <= ire.getCantidad()) {
						LOGGER.info("Hay stock suficiente para el rodamiento (cod: "
								+ rodActual.getCodigoSKF() + ") | Solicitado: "+stockSolicitado+" | Actual: "+ire.getCantidad());
						if(rem == null){
							rem = new RemitoEntity();
							rem.setOdv(ocGuardada.getOdv());
							rem.setOrdenDeCompra(ocGuardada);
							rem.setItems(new ArrayList<ItemRodamientoEntity>());
						}
						rem.getItems().add(ire);
						ire.setPendientes(0);
						
					//NO HAY STOCK PARA ENVIAR NINGUNO.
					} else if (stockSolicitado == 0) {
						LOGGER.info("No hay stock para el rodamiento (cod: "
								+ rodActual.getCodigoSKF() + ")");
						pedidoAbastecimiento.generarPedidoAbastecimiento(ocGuardada, ire, stockSolicitado*2);
					//SE PUEDEN ENVIAR ALGUNOS AHORA Y HAY QUE PEDIR MAS PARA EL RESTO.
					} else {
						LOGGER.info("No hay stock suficiente para el rodamiento (cod: "
								+ rodActual.getCodigoSKF()
								+ "), pueden entregarse "
								+ stockSolicitado
								+ " ahora.");
						if(rem == null){
							rem = new RemitoEntity();
							rem.setOdv(ocGuardada.getOdv());
							rem.setOrdenDeCompra(ocGuardada);
							rem.setItems(new ArrayList<ItemRodamientoEntity>());
						}
						rem.getItems().add(ire);
						ire.setPendientes(ire.getPendientes()-stockSolicitado);
						pedidoAbastecimiento.generarPedidoAbastecimiento(ocGuardada, ire, stockSolicitado*2);
					}
				} else {
					LOGGER.info("El item cotizado con id " + ire.getId()
							+ " ya no esta en vigencia");
				}
			}
			if(rem != null){
				RemitoEntity remitoGuardado = remito.guardarRemito(rem);
				remito.enviarRemito(remitoGuardado,ocGuardada.getOdv());
				ordenDeCompra.verificarPendientes(ocGuardada);
				ordenDeCompra.guardarOrdenDeCompra(ocGuardada);
			} else {
				LOGGER.warn("No se genero ningun remito en esta operacion, ya que no hay stock disponible.");
			}
		} else {
			LOGGER.error("La orden de compra es invalida.");
		}
		LOGGER.info("==================PROCESANDO SOLICITUD DE COMPRA END==================");
	}
	
	private OrdenDeCompraEntity fromRequestToOCEntity(SolicitudCompraRequest request) {
		
		OrdenDeCompraEntity oce = new OrdenDeCompraEntity();
		oce.setIdRecibido(request.getIdOrdenDeCompra());
		oce.setEstado("Nueva");
		ArrayList<ItemRodamientoEntity> listItems = new ArrayList<ItemRodamientoEntity>();
		for(ItemVO itReq : request.getItems()){
			ArrayList<ItemRodamientoEntity> itemsCotizacion = cotizacion.getItemsCotizados(itReq.getId());
			for(ItemRodamientoEntity itemCot : itemsCotizacion ){
				if(itemCot.getRodamiento().getMarca().equals(itReq.getMarca())){
					itemCot.setCantidad(itReq.getCantidad());
					listItems.add(itemCot);
				}
			}
		}
		oce.setItems(listItems);
		oce.setOdv(oficinaVenta.getOficina(request.getIdODV()));
		return oce;
	}
}

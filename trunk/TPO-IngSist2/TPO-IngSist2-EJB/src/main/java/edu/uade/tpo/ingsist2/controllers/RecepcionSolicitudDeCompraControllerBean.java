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
import edu.uade.tpo.ingsist2.view.vo.OrdenDeCompraVO;
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

		if (ordenDeCompra.validarSolicitudCompra(request)) {
			OrdenDeCompraEntity oce = fromRequestToOCEntity(request);
			OrdenDeCompraEntity ocGuardada = ordenDeCompra.guardarOrdenDeCompra(oce);

			RemitoEntity rem = null;
			for (ItemRodamientoEntity ire : ocGuardada.getItems()) {

				// cotizacion.verificarStock(ire);
				RodamientoEntity rodActual = ire.getCotizacion()
						.getRodamiento();

				int stockRodamiento = rodActual.getStock();
				if (cotizacion.validarVigenciaLista(ire.getCotizacion()
						.getLista())) {
					LOGGER.info("El item cotizado con id " + ire.getId()
							+ " esta en vigencia, verificando stock...");
					
					if (stockRodamiento >= ire.getCantidad()) {
						LOGGER.info("Hay stock suficiente para el rodamiento (cod: "
								+ rodActual.getCodigoSKF() + ")");
						if(rem == null){
							rem = new RemitoEntity();
							rem.setOdv(ocGuardada.getOdv());
							rem.setOrdenDeCompra(ocGuardada);
							rem.setItems(new ArrayList<ItemRodamientoEntity>());
						}
						rem.getItems().add(ire);
						ire.setPendientes(0);
					} else if (stockRodamiento == 0) {
						LOGGER.info("No hay stock para el rodamiento (cod: "
								+ rodActual.getCodigoSKF() + ")");
						pedidoAbastecimiento.generarPedidoAbastecimiento(ocGuardada, ire, stockRodamiento);
					} else {
						LOGGER.info("No hay stock suficiente para el rodamiento (cod: "
								+ rodActual.getCodigoSKF()
								+ "), pueden entregarse "
								+ stockRodamiento
								+ " ahora.");
						if(rem == null){
							rem = new RemitoEntity();
							rem.setOdv(ocGuardada.getOdv());
							rem.setOrdenDeCompra(ocGuardada);
							rem.setItems(new ArrayList<ItemRodamientoEntity>());
						}
						rem.getItems().add(ire);
						ire.setPendientes(ire.getPendientes()-stockRodamiento);
						pedidoAbastecimiento.generarPedidoAbastecimiento(ocGuardada, ire, stockRodamiento*2);
					}
				} else {
					LOGGER.info("El item cotizado con id " + ire.getId()
							+ " ya no esta en vigencia");
				}
			}
			if(rem != null){
				remito.enviarRemito(rem,ocGuardada.getOdv());
				ordenDeCompra.verificarPendientes(ocGuardada);
				ordenDeCompra.guardarOrdenDeCompra(ocGuardada);
			}
		} else {
			LOGGER.error("La orden de compra es invalida.");
		}
	}

	private OrdenDeCompraEntity fromRequestToOCEntity(
			SolicitudCompraRequest request) {
		OrdenDeCompraEntity oce = new OrdenDeCompraEntity();
		oce.setIdRecibido(request.getIdOrdenDeCompra());
		oce.setEstado("Nueva");
		ArrayList<ItemRodamientoEntity> listItems = new ArrayList<ItemRodamientoEntity>();
		for(ItemVO itReq : request.getItems()){
			ItemRodamientoEntity iteOC = new ItemRodamientoEntity();
			iteOC.setCantidad(itReq.getCantidad());
			iteOC.setPendientes(itReq.getCantidad());
			iteOC.setRodamiento(rodamiento.getRodamiento(itReq.getSKF(), itReq.getMarca(), itReq.getPais()));
			//Si no existe la cotizacion del Item, lo omite.
			if(!cotizacion.existe(itReq.getId()))
				continue;
			iteOC.setCotizacion(cotizacion.getCotizacion(itReq.getId()));
			listItems.add(iteOC);
		}
		oce.setOdv(oficinaVenta.getOficina(request.getIdODV()));
		return oce;
	}

}

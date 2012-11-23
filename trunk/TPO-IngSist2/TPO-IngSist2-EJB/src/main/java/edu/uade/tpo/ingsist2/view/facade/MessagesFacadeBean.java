package edu.uade.tpo.ingsist2.view.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.uade.tpo.ingsist2.controllers.ListaPreciosController;
import edu.uade.tpo.ingsist2.controllers.RecepcionRodamientosController;
import edu.uade.tpo.ingsist2.controllers.RecepcionSolicitudDeCompraController;
import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;
import edu.uade.tpo.ingsist2.view.vo.OrdenDeCompraVO;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodamientosVO;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCompraRequest;

@Stateless
public class MessagesFacadeBean implements MessagesFacade {

	@EJB
	private ListaPreciosController listaProveedor;
	
	@EJB
	private RecepcionRodamientosController envioProveedor;
	
	@EJB
	private RecepcionSolicitudDeCompraController recepcionSolicitudCompra;
	
	@Override
	public void agregarListaProveedor(ListaPreciosVO lpr) {
		listaProveedor.agregarListaProveedor(lpr);
	}

	@Override
	public void recibirEnvioProveedor(RecepcionRodamientosVO lpr) {
		envioProveedor.recibirEnvioProveedor(lpr);
		
	}

	@Override
	public void recibirSolicitudCompraRodamientos(SolicitudCompraRequest request) {
		recepcionSolicitudCompra.procesarSolicitudDeCompra(request);
	}

}

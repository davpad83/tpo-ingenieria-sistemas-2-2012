package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.Cotizacion;
import edu.uade.tpo.ingsist2.model.ListaPrecios;
import edu.uade.tpo.ingsist2.model.Rodamiento;
import edu.uade.tpo.ingsist2.model.entities.CotizacionEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemListaEntity;
import edu.uade.tpo.ingsist2.model.entities.ListaPreciosEntity;
import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.RodamientoCotizadoVO;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;

@Stateless
public class AdministrarCotizacionesBean implements AdministrarCotizaciones {

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;
	
	@EJB
	private Rodamiento rodamiento;
	
	@EJB
	private Cotizacion cotizacion;
	
	@EJB
	private ListaPrecios listaprecio;
	
	private static final Logger LOGGER = Logger
			.getLogger(AdministrarCotizacionesBean.class);

	private SolicitudCotizacionResponse scresp;
	
    
	public AdministrarCotizacionesBean() {
    }
    
    
	
    public SolicitudCotizacionResponse procesarSolicitudCotizacion (SolicitudCotizacionRequest screq) {
    	
    	scresp = new SolicitudCotizacionResponse(); 
    	scresp.setIdPedidoCotizacion(screq.getIdPedidoCotizacion());
    	scresp.setIdODV(screq.getIdODV());
    	RodamientoEntity r = new RodamientoEntity();
    	
    if(screq.getMarca()==null || screq.getMarca().isEmpty()){    
    	r=rodamiento.getRodamientosCotizacionSinMarca(screq.getSKF(), screq.getPais());
    		if (r!=null){
    				procesarSinMarca(r,screq);
    		}
    		else{
    			scresp.setIdPedidoCotizacion(-1);
    		}    		
    }
    else
    {
    	r= rodamiento.getRodamientoCotizacionConMarca(screq.getSKF(), screq.getPais(),screq.getMarca());
    		if (r!=null){
				procesarConMarca(r,screq);
    		}
    		else
    		{
    			scresp.setIdPedidoCotizacion(-1);
    		}
    }   
    
    return scresp;    
    
    }   
    
    
    
	public void procesarConMarca (RodamientoEntity r, SolicitudCotizacionRequest screq) {
	    LOGGER.info("Procesando Cotizacion con Marca ");
	    
		RodamientoCotizadoVO rcVO = new RodamientoCotizadoVO();
		ItemListaEntity it = new ItemListaEntity();

		rcVO.setSKF(r.getCodigoSKF());
		rcVO.setPais(r.getPais());
		rcVO.setMarca(r.getMarca());
		rcVO.setEnStock(r.getStock());

		try {
			it=cotizacion.getItemListaConMenorPrecioConMarca(r);
			rcVO.setPrecioCotizado(it.getPrecio());

			if (screq.getCantidad() > rcVO.getEnStock()) {
				rcVO.setTiempoEstimadoEntrega("3 semanas");
			}
			rcVO.setFechaFin(null);
			rcVO.setFechaInicio(null);
			
		} catch (Exception e) {
			LOGGER.error("Hubo un error al procesar la cotizacion con marca");
			e.printStackTrace();
		}

		ArrayList<RodamientoCotizadoVO> listrcVO = new ArrayList<RodamientoCotizadoVO>();
		listrcVO.add(rcVO);
		scresp.setRodamientosCotizados(listrcVO);
		
		CotizacionEntity ct = new CotizacionEntity();
		ct.setIdPedidoCotizacion(screq.getIdPedidoCotizacion());		
		OficinaDeVentaEntity ofe = new OficinaDeVentaEntity();
		ofe.setId(screq.getIdODV());
		ct.setOdv(ofe);
		ct.setRodamiento(r);		
		
		int idlista= listaprecio.getIdListaPrecioPorIdItemLista(it.getId());
		ListaPreciosEntity lp =new ListaPreciosEntity();
		lp.setIdLista(idlista);
		ct.setLista(lp);		 
		
		ct.setFecha(rcVO.getFechaInicio());
		ct.setTiempoEntrega(rcVO.getTiempoEstimadoEntrega());
		ct.setVencimiento(rcVO.getFechaFin());  	
				
		cotizacion.guardarCotizacion(ct);
		//FALTA GUARDAR EL ITEM RODAMIENTO-COT
	}
	
	
	
	public void procesarSinMarca (RodamientoEntity r, SolicitudCotizacionRequest screq) {
		LOGGER.info("Procesando Cotizacion sin Marca ");
		
		RodamientoCotizadoVO rcVO = new RodamientoCotizadoVO();
		List<RodamientoCotizadoVO> listrcVO = new ArrayList<RodamientoCotizadoVO>();
		List<ItemListaEntity> listaResultado = null;

		try {
			listaResultado=cotizacion.getItemsListaConMenorPrecioSinMarca(r);		
			
			for (int i = 0; i < listaResultado.size(); i++) {

				rcVO.setSKF(r.getCodigoSKF());
				rcVO.setPais(r.getPais());
				rcVO.setEnStock(r.getStock());
				rcVO.setPrecioCotizado(listaResultado.get(i).getPrecio());
				rcVO.setMarca(listaResultado.get(i).getRodamiento().getMarca());				

				if (screq.getCantidad() > rcVO.getEnStock()) {
					rcVO.setTiempoEstimadoEntrega("por jemplo la semana que viene");
				}
				rcVO.setFechaFin(null);
				rcVO.setFechaInicio(null);
				listrcVO.add(rcVO);
			}
			scresp.setRodamientosCotizados(listrcVO);
			
		} catch (Exception e) {
			LOGGER.error("Hubo un error al procesar la cotizacion sin marca");
			e.printStackTrace();
		}
				
		CotizacionEntity ct = new CotizacionEntity();
		OficinaDeVentaEntity ofe = new OficinaDeVentaEntity();

		for (int i = 0; i < listaResultado.size(); i++) {
			
			ct.setIdPedidoCotizacion(screq.getIdPedidoCotizacion());
			ofe.setId(screq.getIdODV());
			ct.setOdv(ofe);
			ct.setRodamiento(r);
						
			int idlista= listaprecio.getIdListaPrecioPorIdItemLista(listaResultado.get(i).getId());
			ListaPreciosEntity lp =new ListaPreciosEntity();
			lp.setIdLista(idlista);
			ct.setLista(lp);		 
			
			ct.setFecha(rcVO.getFechaInicio());
			ct.setTiempoEntrega(rcVO.getTiempoEstimadoEntrega());
			ct.setVencimiento(rcVO.getFechaFin());  	
					
			cotizacion.guardarCotizacion(ct);
			//FALTA GUARDAR EL ITEM RODAMIENTO-COT
		}
	}
	

}

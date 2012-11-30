package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.Cotizacion;
import edu.uade.tpo.ingsist2.model.ItemRodamiento;
import edu.uade.tpo.ingsist2.model.ListaPrecios;
import edu.uade.tpo.ingsist2.model.Rodamiento;
import edu.uade.tpo.ingsist2.model.entities.CotizacionEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemListaEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemRodamientoEntity;
import edu.uade.tpo.ingsist2.model.entities.ListaPreciosEntity;
import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.vo.RodamientoCotizadoVO;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;

@Stateless
public class RecepcionCotizacionControllerBean implements RecepcionCotizacionController {

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;
	
	@EJB
	private Rodamiento rodamiento;
	
	@EJB
	private Cotizacion cotizacion;
	
	@EJB
	private ItemRodamiento itemRodamiento;
	
	@EJB
	private ListaPrecios listaprecio;
	
	private static final Logger LOGGER = Logger
			.getLogger(RecepcionCotizacionControllerBean.class);

	private SolicitudCotizacionResponse scresp;
    
	
    public SolicitudCotizacionResponse procesarSolicitudCotizacion (SolicitudCotizacionRequest screq) {
    	
    	scresp = new SolicitudCotizacionResponse(); 
    	scresp.setIdPedidoCotizacion(screq.getIdPedidoCotizacion());
    	scresp.setIdODV(screq.getIdODV());
    	scresp.setRodamientosCotizados(new ArrayList<RodamientoCotizadoVO>());
    	RodamientoEntity r = new RodamientoEntity();
    	
    if(screq.getMarca()==null || screq.getMarca().isEmpty()){    
    	r = rodamiento.getRodamientosCotizacionSinMarca(screq.getSKF(), screq.getPais());
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
		int pendientes=0;

		rcVO.setSKF(r.getCodigoSKF());
		rcVO.setPais(r.getPais());
		rcVO.setMarca(r.getMarca());
		rcVO.setEnStock(r.getStock());

		try {
			it=cotizacion.getItemListaConMenorPrecioConMarca(r);
			rcVO.setPrecioCotizado(it.getPrecio());

			if (screq.getCantidad() > rcVO.getEnStock()) {
				rcVO.setTiempoEstimadoEntrega(MockDataGenerator.getRandomCantidad() +" semanas");
				pendientes= screq.getCantidad()-rcVO.getEnStock();
			}
			rcVO.setFechaInicio(new Date());
			rcVO.setFechaFin(MockDataGenerator.getRandomFechaVencimiento());
			
		} catch (Exception e) {
			LOGGER.error("Hubo un error al procesar la cotizacion con marca");
			e.printStackTrace();
		}
		
		scresp.getRodamientosCotizados().add(rcVO);
		
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
	
		ItemRodamientoEntity ir = new ItemRodamientoEntity();		
		
		ir.setCantidad(screq.getCantidad());	
		ir.setPendientes(pendientes);
		ir.setRodamiento(r);
		ir.setCotizacion(ct);
		
		itemRodamiento.guardarItemRodamientoCotizacion(ir);
	}
	
	
	
	public void procesarSinMarca (RodamientoEntity rod, SolicitudCotizacionRequest screq) {
		LOGGER.info("Procesando Cotizacion sin Marca ");
		
		RodamientoCotizadoVO rcVO;
		List<ItemListaEntity> listaResultado = null;
		List<Integer> pendientes = new ArrayList<Integer>();

		try {
			listaResultado=cotizacion.getItemsListaConMenorPrecioSinMarca(rod);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al procesar la cotizacion sin marca");
			e.printStackTrace();
		}
		
		for (int i = 0; i < listaResultado.size(); i++) {
			
			    rcVO = new RodamientoCotizadoVO();

				rcVO.setSKF(listaResultado.get(i).getRodamiento().getCodigoSKF());
				rcVO.setPais(listaResultado.get(i).getRodamiento().getPais());
				rcVO.setEnStock(listaResultado.get(i).getRodamiento().getStock());
				rcVO.setPrecioCotizado(listaResultado.get(i).getPrecio());
				rcVO.setMarca(listaResultado.get(i).getRodamiento().getMarca());		

				if (screq.getCantidad() > rcVO.getEnStock()) {
					rcVO.setTiempoEstimadoEntrega(MockDataGenerator.getRandomCantidad()+" semanas");
					pendientes.add(screq.getCantidad()-rcVO.getEnStock());
				}
				else{
					pendientes.add(0);
				}
				rcVO.setFechaInicio(new Date());
				rcVO.setFechaFin(MockDataGenerator.getRandomFechaVencimiento());
				
				scresp.getRodamientosCotizados().add(rcVO);
			}
		
		CotizacionEntity ct = new CotizacionEntity();
		OficinaDeVentaEntity ofe = new OficinaDeVentaEntity();
		ct.setIdPedidoCotizacion(screq.getIdPedidoCotizacion());
		ofe.setId(screq.getIdODV());
		ct.setOdv(ofe);
		ItemRodamientoEntity ir = new ItemRodamientoEntity();	
		int idlista=0;

		for (int j = 0; j < listaResultado.size(); j++) {
						
			idlista= listaprecio.getIdListaPrecioPorIdItemLista(listaResultado.get(j).getId());
			ListaPreciosEntity lp =new ListaPreciosEntity();
			lp.setIdLista(idlista);
			ct.setLista(lp);
			ct.setRodamiento(listaResultado.get(j).getRodamiento());
			
			ct.setFecha(scresp.getRodamientosCotizados().get(j).getFechaInicio());
			ct.setTiempoEntrega(scresp.getRodamientosCotizados().get(j).getTiempoEstimadoEntrega());
			ct.setVencimiento(scresp.getRodamientosCotizados().get(j).getFechaFin());
			
			ir.setCantidad(screq.getCantidad());
			ir.setPendientes(pendientes.get(j));
			ir.setRodamiento(listaResultado.get(j).getRodamiento());
			ir.setCotizacion(ct);
			
			itemRodamiento.guardarItemRodamientoCotizacion(ir);
		}
	}
}

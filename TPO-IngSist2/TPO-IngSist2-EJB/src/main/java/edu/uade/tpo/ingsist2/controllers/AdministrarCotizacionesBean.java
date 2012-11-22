package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.Cotizacion;
import edu.uade.tpo.ingsist2.model.Rodamiento;
import edu.uade.tpo.ingsist2.model.entities.CotizacionEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemListaEntity;
import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.CotizacionVO;
import edu.uade.tpo.ingsist2.view.vo.OficinaDeVentaVO;
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
	
	private static final Logger LOGGER = Logger
			.getLogger(AdministrarCotizacionesBean.class);

	private SolicitudCotizacionResponse scresp;
	
    
	public AdministrarCotizacionesBean() {
    }
    
    
    public SolicitudCotizacionResponse procesarSolicitudCotizacion (SolicitudCotizacionRequest screq) {
    	
    	scresp = new SolicitudCotizacionResponse(); 
    	scresp.setIdPedidoCotizacion(screq.getIdPedidoCotizacion());
    	scresp.setIdODV(screq.getIdODV());
    	
    if(screq.getMarca()==null || screq.getMarca().isEmpty()){    		
    		if (rodamiento.getRodamientosCotizacionSinMarca(screq.getSKF(), screq.getPais())!=null){
    				procesarSinMarca(rodamiento.getRodamientosCotizacionSinMarca(screq.getSKF(), screq.getPais()),screq);
    		}
    		else{
    			scresp.setIdPedidoCotizacion(-1);
    		}    		
    }
    else
    {
    		if (rodamiento.getRodamientoCotizacionConMarca(screq.getSKF(), screq.getPais(),screq.getMarca())!=null){
				procesarConMarca(rodamiento.getRodamientoCotizacionConMarca(screq.getSKF(), screq.getPais(),screq.getMarca()),screq);
    		}
    		else
    		{
    			scresp.setIdPedidoCotizacion(-1);
    		}
    }   
    
    return scresp;    
    
    }   
    
    
	@SuppressWarnings("unchecked")
	public void procesarConMarca (RodamientoEntity r, SolicitudCotizacionRequest screq) {
	    LOGGER.info("Procesando Cotizacion con Marca ");
	    
		RodamientoCotizadoVO rcVO = new RodamientoCotizadoVO();
		List<ItemListaEntity> iBeans = null;

		rcVO.setSKF(r.getCodigoSKF());
		rcVO.setPais(r.getPais());
		rcVO.setMarca(r.getMarca());
		rcVO.setEnStock(r.getStock());

		try {
			iBeans = entityManager
					.createQuery(
							"select i from ItemListaEntity i where i.rodamiento.marca=:marca and i.rodamiento.pais=:pais"
									+ "and  i.rodamiento.codigoSKF=:codigo order by i.precio")
					.setParameter("marca", rcVO.getMarca())
					.setParameter("codigo", rcVO.getSKF())
					.setParameter("pais", rcVO.getPais()).getResultList();
			rcVO.setPrecioCotizado(iBeans.get(0).getPrecio());

			if (screq.getCantidad() > rcVO.getEnStock()) {
				rcVO.setTiempoEstimadoEntrega("por ejemplo la semana que viene");
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
		ct.setFecha(rcVO.getFechaInicio());
		ct.setTiempoEntrega(rcVO.getTiempoEstimadoEntrega());
		ct.setVencimiento(rcVO.getFechaFin());		
				
		cotizacion.guardarCotizacion(ct);	
	}

	@SuppressWarnings("unchecked")
	public void procesarSinMarca (RodamientoEntity r, SolicitudCotizacionRequest screq) {
		LOGGER.info("Procesando Cotizacion sin Marca ");
		
		RodamientoCotizadoVO rcVO = new RodamientoCotizadoVO();
		ArrayList<RodamientoCotizadoVO> listrcVO = new ArrayList<RodamientoCotizadoVO>();
		List<ItemListaEntity> listaResultado = null;

		try {
			listaResultado = (ArrayList<ItemListaEntity>) entityManager
					.createQuery(
							"select i from ItemListaEntity i where i.rodamiento.pais=:pais"
									+ "and i.rodamiento.codigoSKF=:codigo and i.precio in (select min(i.precio) from ItemListaEntity i group by i.rodamiento.marca)")
					.setParameter("codigo", rcVO.getSKF())
					.setParameter("pais", rcVO.getPais()).getResultList();

			for (int i = 0; i < listaResultado.size(); i++) {

				rcVO.setSKF(r.getCodigoSKF());
				rcVO.setPais(r.getPais());
				rcVO.setEnStock(r.getStock());
				rcVO.setMarca(listaResultado.get(i).getRodamiento().getMarca());
				rcVO.setPrecioCotizado(listaResultado.get(i).getPrecio());

				if (screq.getCantidad() > rcVO.getEnStock()) {
					rcVO.setTiempoEstimadoEntrega("porjemplo la semana que viene");
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

		for (int i = 0; i < listaResultado.size(); i++) {

			CotizacionVO ctVO = new CotizacionVO();
			ctVO.setIdPedidoCotizacion(screq.getIdPedidoCotizacion());
			OficinaDeVentaVO ofe = new OficinaDeVentaVO();
			ofe.setIdODV(screq.getIdODV());
			ctVO.setOdv(ofe);
			ctVO.setRodamiento(listaResultado.get(i).getRodamiento().getVO());
			ctVO.setFecha(listrcVO.get(i).getFechaInicio());
			ctVO.setTiempoEntrega(listrcVO.get(i).getTiempoEstimadoEntrega());
			ctVO.setVencimiento(listrcVO.get(i).getFechaFin());


		}
	}
	

}

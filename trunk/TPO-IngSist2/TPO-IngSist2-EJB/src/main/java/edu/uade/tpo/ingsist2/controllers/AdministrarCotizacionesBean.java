package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.CotizacionEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemListaEntity;
import edu.uade.tpo.ingsist2.model.entities.OficinaDeVentaEntity;
import edu.uade.tpo.ingsist2.model.entities.OrdenDeCompraEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.view.vo.CotizacionVO;
import edu.uade.tpo.ingsist2.view.vo.RodamientoCotizadoVO;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;

/**
 * Session Bean implementation class AdministrarCotizacionesBean
 */
@Stateless
public class AdministrarCotizacionesBean implements AdministrarCotizaciones {
	
	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;
	
	private static final Logger logger = Logger
			.getLogger(ListaPreciosControllerBean.class);

	private SolicitudCotizacionResponse scresp;
	
    
	public AdministrarCotizacionesBean() {
        // empty
    }
    
    
    public SolicitudCotizacionResponse procesarSolicitudCotizacion (SolicitudCotizacionRequest screq) {
    	
    	scresp = new SolicitudCotizacionResponse(); 
    	scresp.setIdPedidoCotizacion(screq.getIdPedidoCotizacion());
    	scresp.setIdODV(screq.getIdODV());
    	
		RodamientoEntity rBean= (RodamientoEntity) entityManager.createQuery("select r from RodamientoEntity r where r.codigoSKF=:codigo")
		.setParameter("codigo", screq.getSKF());
    	
    	if(screq.getMarca()==null || screq.getMarca().isEmpty())
    		procesarSinMarca(rBean,screq );
    	else
    		procesarConMarca(rBean, screq);   	
    	
    	return scresp;
    }
    
    
	public void procesarConMarca (RodamientoEntity r, SolicitudCotizacionRequest screq) {
	    logger.info("Procesando Cotizacion con Marca ");
	    
		RodamientoCotizadoVO rcVO = new RodamientoCotizadoVO();
		ItemListaEntity iBean = new ItemListaEntity();
				
		rcVO.setSKF(r.getCodigoSKF());
		rcVO.setPais(r.getPais());
		rcVO.setMarca(r.getMarca());		
		rcVO.setEnStock(r.getStock());
				
		try {
			
			iBean= (ItemListaEntity) entityManager.createQuery("select i from ItemListaEntity i where i.rodamiento.marca=:marca and " +
					"i.precio = (select min(i.precio) from ItemListaEntity i")
			.setParameter("marca", rcVO.getMarca())
			.getSingleResult();
			
			rcVO.setPrecioCotizado(iBean.getPrecio());
			rcVO.setTiempoEstimadoEntrega("la semana que viene");
			rcVO.setFechaFin(null);
			rcVO.setFechaInicio(null);
			
			
		} catch (Exception e) {
			logger.error("Hubo un error al procesar la cotizacion con marca");
			e.printStackTrace();
		}
				
		ArrayList<RodamientoCotizadoVO> listrcVO = new ArrayList<RodamientoCotizadoVO>();
		listrcVO.add(rcVO);		
		scresp.setRodamientosCotizados(listrcVO);
		
		CotizacionVO ctVO = new CotizacionVO();
		ctVO.setIdPedidoCotizacion(screq.getIdPedidoCotizacion());
		OficinaDeVentaEntity ofe = new OficinaDeVentaEntity();
		ofe.setId(screq.getIdODV());
		ctVO.setOdv(ofe);
		ctVO.setRodamiento(r);
		ctVO.setLista(iBean.getListaPrecio());
		ctVO.setFecha(rcVO.getFechaInicio());
		ctVO.setTiempoEntrega(rcVO.getTiempoEstimadoEntrega());
		ctVO.setVencimiento(rcVO.getFechaFin());		
				
		guardarCotizacion(ctVO);
		
	}
	
	
	public void procesarSinMarca (RodamientoEntity r, SolicitudCotizacionRequest screq) {
		logger.info("Procesando Cotizacion sin Marca ");
		
		OrdenDeCompraEntity pBean = new OrdenDeCompraEntity();
		

		OrdenDeCompraEntity pGuardado = null;
		try {
			pGuardado = (OrdenDeCompraEntity) entityManager.merge(pBean);
		} catch (Exception e) {
			logger.error("Hubo un error al guardar la orden");
			e.printStackTrace();
		}
		logger.info("Orden guardada con id: " + pGuardado.getIdOrden());
		
	}
	
	
	
	public float obtenerPrecioMasBajoXMarca (RodamientoEntity rod) {
		
		return 45;
		
	}
	
		
	
	public void guardarCotizacion (CotizacionVO cVO) {
		logger.info("Procesando guardar cotizacion con idPedido" + cVO.getIdPedidoCotizacion() + "y ODV numero" + cVO.getOdv().getId());
		
		CotizacionEntity cBean = new CotizacionEntity();
		cBean.setVO(cVO);
		
		CotizacionEntity cGuardada = null;
		try {
			cGuardada = (CotizacionEntity) entityManager.merge(cBean);
		} catch (Exception e) {
			logger.error("Hubo un error al guardar la cotizacion");
			e.printStackTrace();
		}
		logger.info("Cotizacion guardada con id: " + cGuardada.getId());
	}
    

}

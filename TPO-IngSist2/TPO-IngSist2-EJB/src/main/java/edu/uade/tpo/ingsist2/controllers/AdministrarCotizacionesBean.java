package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.entities.CotizacionEntity;
import edu.uade.tpo.ingsist2.model.entities.ItemListaEntity;
import edu.uade.tpo.ingsist2.model.entities.RodamientoEntity;
import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.vo.CotizacionVO;
import edu.uade.tpo.ingsist2.view.vo.OficinaDeVentaVO;
import edu.uade.tpo.ingsist2.view.vo.RodamientoCotizadoVO;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;

@Stateless
public class AdministrarCotizacionesBean implements AdministrarCotizaciones {

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	private static final Logger LOGGER = Logger
			.getLogger(AdministrarCotizacionesBean.class);

	private SolicitudCotizacionResponse scresp;

	public AdministrarCotizacionesBean() {
		// empty
	}

	public SolicitudCotizacionResponse procesarSolicitudCotizacion(
			SolicitudCotizacionRequest screq) {

		LOGGER.debug("Procesar solicitud cotizacion: \n"
				+ "Datos: \nCantidad: " + screq.getCantidad()
				+ ", IdPedCotiz: " + screq.getIdPedidoCotizacion()
				+ ", Marca: " + screq.getMarca() + ", Pais: " + screq.getPais()
				+ ", SKF: " + screq.getSKF());

		scresp = new SolicitudCotizacionResponse();
		scresp.setIdPedidoCotizacion(screq.getIdPedidoCotizacion());
		scresp.setIdODV(screq.getIdODV());

		RodamientoEntity rBean = null;
//		try {
//			rBean = (RodamientoEntity) entityManager
//					.createQuery(
//							"from " + "RodamientoEntity r "
//									+ "where r.codigoSKF=:codigo and "
//									+ "r.pais=:pais and r.marca= :marca")
//					.setParameter("codigo", screq.getSKF())
//					.setParameter("pais", screq.getPais())
//					.setParameter("marca", screq.getMarca()).getSingleResult();
//
//			if (rBean == null) {
//				LOGGER.info("No existe el rodamiento solicitado");
//				scresp.setIdPedidoCotizacion(-1);
//				return scresp;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//HARDCOREO
		RodamientoEntity re = new RodamientoEntity();
		re.setCodigoSKF("22310 EKW33");
		re.setMarca("SKF");
		re.setPais("Argentina");
		re.setStock(0);
		re.setId(6);
		rBean = re;

		RodamientoCotizadoVO rcbo = new RodamientoCotizadoVO();
		rcbo.setFechaFin(MockDataGenerator.getRandomFechaVencimiento());
		rcbo.setFechaInicio(new Date());
		rcbo.setEnStock(10);
		rcbo.setPrecioCotizado(134);
		rcbo.setMarca(re.getMarca());
		rcbo.setSKF(re.getCodigoSKF());
		rcbo.setPais(re.getPais());
		rcbo.setTiempoEstimadoEntrega("3 semanas");
	
		ArrayList<RodamientoCotizadoVO> listrcVO = new ArrayList<RodamientoCotizadoVO>();
		listrcVO.add(rcbo);
		scresp.setRodamientosCotizados(listrcVO);
		
		return scresp;
		//========

//		if (screq.getMarca() == null || screq.getMarca().isEmpty())
//			procesarSinMarca(rBean, screq);
//		else
//			procesarConMarca(rBean, screq);
//
//		return scresp;
	}

	@SuppressWarnings("unchecked")
	public void procesarConMarca(RodamientoEntity r,
			SolicitudCotizacionRequest screq) {
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

		CotizacionVO ctVO = new CotizacionVO();
		ctVO.setIdPedidoCotizacion(screq.getIdPedidoCotizacion());
		OficinaDeVentaVO ofe = new OficinaDeVentaVO();
		ofe.setIdODV(screq.getIdODV());
		ctVO.setOdv(ofe);
		ctVO.setRodamiento(r.getVO());
		ctVO.setFecha(rcVO.getFechaInicio());
		ctVO.setTiempoEntrega(rcVO.getTiempoEstimadoEntrega());
		ctVO.setVencimiento(rcVO.getFechaFin());

		guardarCotizacion(ctVO);
	}

	@SuppressWarnings("unchecked")
	public void procesarSinMarca(RodamientoEntity r,
			SolicitudCotizacionRequest screq) {
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

			guardarCotizacion(ctVO);
		}
	}

	public void guardarCotizacion(CotizacionVO cVO) {
		LOGGER.info("Procesando guardar cotizacion con idPedido"
				+ cVO.getIdPedidoCotizacion() + "y ODV numero"
				+ cVO.getOdv().getIdODV());

		CotizacionEntity cBean = new CotizacionEntity();
		cBean.setVO(cVO);
		CotizacionEntity cGuardada = null;

		try {
			entityManager.persist(cBean);
			cGuardada = (CotizacionEntity) entityManager.find(
					CotizacionEntity.class, cBean);
		} catch (Exception e) {
			LOGGER.error("Hubo un error al guardar la cotizacion");
			e.printStackTrace();
		}
		LOGGER.info("Cotizacion guardada con id: " + cGuardada.getId());
	}

}

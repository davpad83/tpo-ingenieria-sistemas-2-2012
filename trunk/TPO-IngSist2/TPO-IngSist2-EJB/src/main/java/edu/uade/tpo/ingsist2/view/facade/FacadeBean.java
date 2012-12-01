package edu.uade.tpo.ingsist2.view.facade;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import edu.uade.tpo.ingsist2.controllers.RecepcionCotizacionController;
import edu.uade.tpo.ingsist2.controllers.AdministrarOficinaDeVenta;
import edu.uade.tpo.ingsist2.controllers.AdministrarProveedores;
import edu.uade.tpo.ingsist2.controllers.AdministrarRodamientos;
import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;
import edu.uade.tpo.ingsist2.view.vo.OficinaDeVentaVO;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.view.vo.RodamientoVO;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionRequest;
import edu.uade.tpo.ingsist2.view.vo.SolicitudCotizacionResponse;

@Stateless
@WebService(name = "getCotizacionRodamiento", serviceName = "getCotizacionRodamiento")
public class FacadeBean implements Facade {

	@EJB
	private AdministrarProveedores adminProve;
	@EJB
	private AdministrarRodamientos adminRod;
	@EJB
	RecepcionCotizacionController adminCot;
	@EJB
	AdministrarOficinaDeVenta adminOficinaVenta;
	@EJB
	MessagesFacade messagesFacade;

	/* ===========ÊABM PROVEEDORES =========== */

	@Override
	public void guardarProveedor(ProveedorVO p) {
		adminProve.guardarProveedor(p);
	}

	@Override
	public void eliminarProveedor(int id) {
		adminProve.eliminarProveedor(id);
	}

	@Override
	public ProveedorVO getProveedor(int id) {
		return adminProve.getProveedor(id);
	}

	@Override
	public ArrayList<ProveedorVO> getProveedores() {
		return adminProve.getProveedores();
	}

	/* ===========ÊABM RODAMIENTOS =========== */

	@Override
	public void guardarRodamiento(RodamientoVO r) {
		adminRod.guardarRodamiento(r);
	}

	@Override
	public void eliminarRodamiento(int id) {
		adminRod.eliminarRodamiento(id);
	}

	@Override
	public RodamientoVO getRodamiento(int id) {
		return adminRod.getRodamiento(id);
	}

	@Override
	public ArrayList<RodamientoVO> getRodamientos() {
		return adminRod.getRodamientos();
	}

	/* ===========ÊABM DE OFICINA DE VENTA =========== */

	@Override
	public void guardarOficinaDeVenta(OficinaDeVentaVO odv) {
		adminOficinaVenta.guardarOficinaDeVenta(odv);
	}

	/* ===========ÊWEB METHODS =========== */

	@Override
	@WebMethod
	public SolicitudCotizacionResponse recibirSolicitudCotizacion(SolicitudCotizacionRequest scr) {
		return adminCot.procesarSolicitudCotizacion(scr);
	}

	/* =========== GENERAR DATA DE INICIAL DE PRUEBA ========== */

	public void generateInitialData() {
		ArrayList<ProveedorVO> proveedoresACargar = MockDataGenerator
				.getControlledProveedoresList();
		ArrayList<ListaPreciosVO> listaPrecioACargar = MockDataGenerator
				.getControlledListaPrecioList();
		ArrayList<RodamientoVO> rodamientosACargar = MockDataGenerator
				.getControlledRodamientosList();
		ArrayList<OficinaDeVentaVO> odvsACargar = MockDataGenerator
				.getControlledOficinasDeVentaList();

		for (ProveedorVO p : proveedoresACargar) {
			guardarProveedor(p);
		}
		
		for (RodamientoVO r : rodamientosACargar) {
			guardarRodamiento(r);
		}
		
		for (OficinaDeVentaVO odv : odvsACargar) {
			guardarOficinaDeVenta(odv);
		}
		
		for (ListaPreciosVO l : listaPrecioACargar) {
			messagesFacade.agregarListaProveedor(l);
		}
	}
}

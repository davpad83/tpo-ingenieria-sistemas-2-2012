package edu.uade.tpo.ingsist2.utils.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import edu.uade.tpo.ingsist2.view.vo.*;

public class MockDataGenerator {

	public static ArrayList<ProveedorVO> getRandomListaProveedorVO() {
		ArrayList<ProveedorVO> lista = new ArrayList<ProveedorVO>();
		for (int i = 0; i < 10; i++)
			lista.add(getRandomProveedorVO());
		return lista;
	}

	public static ProveedorVO getRandomProveedorVO() {
		ProveedorVO prove = new ProveedorVO();
		prove.setCuit(getRandomCuit());
		prove.setNombre(getRandomNombreProveedor());
		return prove;
	}

	public static int getRandomId() {
		return new Random().nextInt(1000);
	}

	public static String getRandomNombreProveedor() {
		String[] nombresProveedor = { "Rodamientos Locos SRL", "Rodaditos",
				"Patricio Rod y sus Rodamientos de Ricota",
				"Rodamientos Red Ribbon", "Rodamientos por todos lados",
				"Invasion de los Rodamientos" };
		return nombresProveedor[new Random().nextInt(nombresProveedor.length)];
	}

	public static String getRandomNombre() {
		String[] nombres = { "Matias", "Pedro", "Joaco", "Juan", "Carlos",
				"Pepe", "Alejandra", "Silvia", "Nicolas", "Howard" };
		String[] apellidos = { "Fernandes", "Gonzalez", "Favale", "Attanasio",
				"Primo", "Salvatore", "Villagra", "Perez" };

		return nombres[new Random().nextInt(nombres.length - 1)] + " "
				+ apellidos[new Random().nextInt(apellidos.length)];
	}

	public static String getRandomCuit() {
		String cuit = "";
		for (int i = 0; i < 11; i++) {
			cuit += new Random().nextInt(9);
			if (i == 1 || i == 9)
				cuit += "-";
		}
		return cuit;
	}


	public static ListaPreciosVO getRandomListaPreciosVO(int cantItems) {
		ListaPreciosVO lista = new ListaPreciosVO();
		lista.setNombre("Rodamientos Locos SRL");
		lista.setProveedor(getRandomProveedorVO());
		lista.setVigenciaDesde(new Date());
		lista.setVigenciaHasta(getRandomFechaVencimiento());
		lista.setItems(getRandomListaItemListaVO(cantItems));
		return lista;
	}

	public static Date getRandomFechaVencimiento() {
		Calendar cal = Calendar.getInstance();
		Date newDate = new Date();
		cal.add(cal.DATE, 7);
		return cal.getTime();
	}

	public static List<ItemListaVO> getRandomListaItemListaVO(int cantItems) {
		ArrayList<ItemListaVO> lilvo = new ArrayList<ItemListaVO>();
		for (int i = 0; i < cantItems; i++) {
			lilvo.add(getRandomItemListaVO());
		}
		return lilvo;
	}

	public static ItemListaVO getRandomItemListaVO() {
		ItemListaVO ilvo = new ItemListaVO();
		ilvo.setPrecio(getRandomPrecio());
		ilvo.setRodamiento(getRandomRodamientoVO());
		return ilvo;
	}

	public static float getRandomPrecio() {
		return new Random().nextInt(10000);
	}

	public static String getRandomCodigoSKF() {
		String[] codigosSKF = { "BAHB 311424 B", "BAH-0012", "VKDA 35201",
				"LM11749", "LM11719", "10G-88107", "VKC 2123 C", "VKM 12500",
				"VKM 12501", "VKM 22510", "BAH-0055 AAX", "BAF-011",
				"VKM 22173", "VKM 22510", "VKM 12173", "VKM 02000",
				"VKBA 3580", "VKM 32017" };
		return codigosSKF[new Random().nextInt(codigosSKF.length)];
	}

	public static String getRandomPais() {
		String[] paises = { "Argentina", "Brasil", "Estados Unicos", "Chile",
				"Paraguay", "Uruguay", "China", "Japon", "Bolivia", "Peru",
				"Costa Rica", "Canada", "Mexico", "Cuba", "Ecuador" };
		return paises[new Random().nextInt(paises.length)];
	}

	public static RodamientoVO getRandomRodamientoVO() {
		RodamientoVO rod = new RodamientoVO();
		rod.setMarca(getRandomMarca());
		rod.setPais(getRandomPais());
		// rod.setStock(new Random().nextInt(1));
		rod.setCodigoSKF(getRandomCodigoSKF());
		return rod;
	}

	public static String getRandomMarca() {
		String[] marcas = { "Renault", "BMW", "Alfa Romeo", "Fiat", "Ferrari",
				"Volkswagen", "Dodge","Chrysler" };
		return marcas[new Random().nextInt(marcas.length)];
	}

	public static OrdenDeCompraVO getRandomOrdenDeCompraVO() {
		OrdenDeCompraVO ocvo = new OrdenDeCompraVO();
		ocvo.setEstado("Nueva");
		ocvo.setItems(getRandomListaItemRodamientoVO());
		ocvo.setOdv(getRandomOdvVO());		
		return ocvo;
	}

	public static OficinaDeVentaVO getRandomOdvVO() {
		OficinaDeVentaVO odvvo = new OficinaDeVentaVO();
		odvvo.setDireccion(getRandomDireccion());
		odvvo.setNombre(getRandomNombreODV());
		return null;
	}

	public static String getRandomDireccion() {
		String[] direcciones = {
				"Maipu 757", "Belgrano 1415", "9 de Julio 6161","Avenida de Mayo 120", "Lavalle 2131"
		};
		return direcciones[new Random().nextInt(direcciones.length)];
	}

	public static String getRandomNombreODV() {
		String[] nombresODV = {
				"Grupo 1", "Grupo 2","Grupo 3","Grupo 4","Grupo 5"
		};
		return nombresODV[new Random().nextInt(nombresODV.length)];
	}

	public static List<ItemRodamientoVO> getRandomListaItemRodamientoVO() {
		ArrayList<ItemRodamientoVO> lirvo = new ArrayList<ItemRodamientoVO>();
		for(int i=0 ; i < 5 ; i++){
			lirvo.add(getRandomItemRodamientoVO());
		}
		return lirvo;
	}

	public static ItemRodamientoVO getRandomItemRodamientoVO() {
		ItemRodamientoVO irvo = new ItemRodamientoVO();
		irvo.setCantidad(getRandomCantidad());
		irvo.setCotizacion(getRandomCotizacionVO());
		irvo.setRodamiento(getRandomRodamientoVO());
		return irvo;
	}

	public static CotizacionVO getRandomCotizacionVO() {
		CotizacionVO cvo = new CotizacionVO();
		cvo.setVencimiento(getRandomFechaVencimiento());
		cvo.setFecha(new Date());
		cvo.setTiempoEntrega(getRandomCantidad()+" dias");
		cvo.setIdPedidoCotizacion(getRandomId());
		return cvo;
	}

	public static int getRandomCantidad() {
		return new Random().nextInt(100);
	}
}

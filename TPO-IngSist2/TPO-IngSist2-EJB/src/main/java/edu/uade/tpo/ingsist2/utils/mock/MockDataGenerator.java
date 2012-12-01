package edu.uade.tpo.ingsist2.utils.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

	public static RecepcionRodamientosVO getMercaderia(){
		RecepcionRodamientosVO rrvo = new RecepcionRodamientosVO();
		List<RecepcionRodamientosVO.RodamientoListaVO> listRecep = new ArrayList<RecepcionRodamientosVO.RodamientoListaVO>();
		
		RecepcionRodamientosVO.RodamientoListaVO rlvo = rrvo.new RodamientoListaVO(); 
		rlvo.setCantidad(50);
		rlvo.setMarca("SKF");
		rlvo.setPais("Argentina");
		rlvo.setSKF("22310 EKW33");
		listRecep.add(rlvo);
		
		rlvo.setCantidad(20);
		rlvo.setMarca("SNR");
		rlvo.setPais("Francia");
		rlvo.setSKF("NJ 208 EMC3");
		listRecep.add(rlvo);

		rlvo.setCantidad(30);
		rlvo.setMarca("ZKL");
		rlvo.setPais("Alemania");
		rlvo.setSKF("6200");
		
		listRecep.add(rlvo);		
		return rrvo;
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

	public static SolicitudCotizacionRequest getRandomSolicitudCotizacionRequestConMarca() {
		SolicitudCotizacionRequest scr = new SolicitudCotizacionRequest();
		scr.setCantidad(getRandomCantidad());
		scr.setIdPedidoCotizacion(getRandomId());
		scr.setMarca(getRandomMarca());
		scr.setPais(getRandomPais());
		scr.setSKF(getRandomCodigoSKF());
		scr.setIdODV(getRandomId());
		return scr;
	}

	public static SolicitudCotizacionRequest getRandomSolicitudCotizacionRequestSinMarca() {
		SolicitudCotizacionRequest scr = new SolicitudCotizacionRequest();
		scr.setCantidad(getRandomCantidad());
		scr.setIdPedidoCotizacion(getRandomId());
		scr.setPais(getRandomPais());
		scr.setSKF(getRandomCodigoSKF());
		scr.setIdODV(getRandomId());
		return scr;
	}

	public static ArrayList<ProveedorVO> getControlledProveedoresList() {
		ArrayList<ProveedorVO> proveedores = new ArrayList<ProveedorVO>();
		ProveedorVO pvo1 = nuevoProveedorVO("20-34343431-2", "Rodamientos S.A.", "2 semanas");
		ProveedorVO pvo2 = nuevoProveedorVO("20-34343432-2", "Rodaditos Expertos", "3 semanas");
		ProveedorVO pvo3 = nuevoProveedorVO("20-34343433-2", "Los rodados ruedan", "1 semana");
		ProveedorVO pvo4 = nuevoProveedorVO("20-34343434-2", "R con R Rodado", "2 semanas");
		
		proveedores.add(pvo1);
		proveedores.add(pvo2);
		proveedores.add(pvo3);
		proveedores.add(pvo4);
		
		return proveedores;
	}

	private static ProveedorVO nuevoProveedorVO(String cuit, String nombre, String tiempoDeEntrega) {
		ProveedorVO pvo = new ProveedorVO();
		pvo.setCuit(cuit);
		pvo.setNombre(nombre);
		pvo.setTiempoDeEntrega(tiempoDeEntrega);
		return pvo;
	}

	public static ArrayList<ListaPreciosVO> getControlledListaPrecioList() {
		/* ItemListas para la lista 1 */
			/* item lista 1 */
		ItemListaVO ilvo_list1_1 = nuevoItemListaVO(1, 310.7f); 
		ItemListaVO ilvo_list1_2 = nuevoItemListaVO(2, 249f); 
		ItemListaVO ilvo_list1_3 = nuevoItemListaVO(3, 7.1f); 
		ItemListaVO ilvo_list1_4 = nuevoItemListaVO(4, 7.1f); 
		ItemListaVO ilvo_list1_5 = nuevoItemListaVO(5, 13.49f); 
		ItemListaVO ilvo_list1_6 = nuevoItemListaVO(6, 67.01f); 
		ItemListaVO ilvo_list1_7 = nuevoItemListaVO(7, 107f); 
		ItemListaVO ilvo_list1_8 = nuevoItemListaVO(8, 132f); 
		ItemListaVO ilvo_list1_9 = nuevoItemListaVO(9, 10f); 
		ItemListaVO ilvo_list1_10 = nuevoItemListaVO(10, 10f); 
		ItemListaVO ilvo_list1_11 = nuevoItemListaVO(11, 310f); 
		ItemListaVO ilvo_list1_12 = nuevoItemListaVO(12, 249f); 
		ItemListaVO ilvo_list1_13 = nuevoItemListaVO(13, 7.1f); 
		ItemListaVO ilvo_list1_14 = nuevoItemListaVO(14, 7.1f); 
		ItemListaVO ilvo_list1_15 = nuevoItemListaVO(15, 13.9f); 
		ItemListaVO ilvo_list1_16 = nuevoItemListaVO(16, 67f); 
		ItemListaVO ilvo_list1_17 = nuevoItemListaVO(17, 80f); 
		ItemListaVO ilvo_list1_18 = nuevoItemListaVO(18, 132f); 

		/* ItemListas para la lista 2 */
		ItemListaVO ilvo_list2_1 = nuevoItemListaVO(1, 290f); 
		ItemListaVO ilvo_list2_2 = nuevoItemListaVO(2, 230f); 
		ItemListaVO ilvo_list2_3 = nuevoItemListaVO(3, 6f); 
		ItemListaVO ilvo_list2_4 = nuevoItemListaVO(4, 10f); 
		ItemListaVO ilvo_list2_5 = nuevoItemListaVO(5, 20f); 
		ItemListaVO ilvo_list2_6 = nuevoItemListaVO(6, 75f); 
		ItemListaVO ilvo_list2_7 = nuevoItemListaVO(7, 105f); 
		ItemListaVO ilvo_list2_8 = nuevoItemListaVO(8, 154f); 
		ItemListaVO ilvo_list2_9 = nuevoItemListaVO(9, 13f); 
		ItemListaVO ilvo_list2_10 = nuevoItemListaVO(10, 9f); 
		ItemListaVO ilvo_list2_11 = nuevoItemListaVO(11, 309.9f);
		
		/* ItemListas para la lista 3 */
		ItemListaVO ilvo_list3_1 = nuevoItemListaVO(1, 289f); 
		ItemListaVO ilvo_list3_2 = nuevoItemListaVO(2, 240f); 
		ItemListaVO ilvo_list3_3 = nuevoItemListaVO(3, 7f); 
		ItemListaVO ilvo_list3_4 = nuevoItemListaVO(4, 30f); 
		ItemListaVO ilvo_list3_5 = nuevoItemListaVO(10, 16f); 
		ItemListaVO ilvo_list3_6 = nuevoItemListaVO(12, 230f); 
		ItemListaVO ilvo_list3_7 = nuevoItemListaVO(13, 7.2f); 
		ItemListaVO ilvo_list3_8 = nuevoItemListaVO(14, 7.0f); 
		ItemListaVO ilvo_list3_9 = nuevoItemListaVO(15, 14f); 
		ItemListaVO ilvo_list3_10 = nuevoItemListaVO(16, 69f); 
		ItemListaVO ilvo_list3_11 = nuevoItemListaVO(18, 133f);
		
		/* ItemListas para la lista 4 */
		ItemListaVO ilvo_list4_1 = nuevoItemListaVO(5, 12f); 
		ItemListaVO ilvo_list4_2 = nuevoItemListaVO(9, 9f); 
		ItemListaVO ilvo_list4_3 = nuevoItemListaVO(11, 313f); 
		ItemListaVO ilvo_list4_4 = nuevoItemListaVO(13, 7.9f); 
		ItemListaVO ilvo_list4_5 = nuevoItemListaVO(15, 20f); 
		ItemListaVO ilvo_list4_6 = nuevoItemListaVO(17, 83f); 
		
		/* Cargo list item lista para la lista 1 */
		ArrayList<ItemListaVO> listaIL1 = new ArrayList<ItemListaVO>();
		listaIL1.add(ilvo_list1_1);
		listaIL1.add(ilvo_list1_2);
		listaIL1.add(ilvo_list1_3);
		listaIL1.add(ilvo_list1_4);
		listaIL1.add(ilvo_list1_5);
		listaIL1.add(ilvo_list1_6);
		listaIL1.add(ilvo_list1_7);
		listaIL1.add(ilvo_list1_8);
		listaIL1.add(ilvo_list1_9);
		listaIL1.add(ilvo_list1_10);
		listaIL1.add(ilvo_list1_11);
		listaIL1.add(ilvo_list1_12);
		listaIL1.add(ilvo_list1_13);
		listaIL1.add(ilvo_list1_14);
		listaIL1.add(ilvo_list1_15);
		listaIL1.add(ilvo_list1_16);
		listaIL1.add(ilvo_list1_17);
		listaIL1.add(ilvo_list1_18);
		
		/* Cargo list item lista para la lista 2 */
		ArrayList<ItemListaVO> listaIL2 = new ArrayList<ItemListaVO>();
		listaIL2.add(ilvo_list2_1);
		listaIL2.add(ilvo_list2_2);
		listaIL2.add(ilvo_list2_3);
		listaIL2.add(ilvo_list2_4);
		listaIL2.add(ilvo_list2_5);
		listaIL2.add(ilvo_list2_6);
		listaIL2.add(ilvo_list2_7);
		listaIL2.add(ilvo_list2_8);
		listaIL2.add(ilvo_list2_9);
		listaIL2.add(ilvo_list2_10);
		listaIL2.add(ilvo_list2_11);
		
		/* Cargo list item lista para la lista 3 */
		ArrayList<ItemListaVO> listaIL3 = new ArrayList<ItemListaVO>();
		listaIL3.add(ilvo_list3_1);
		listaIL3.add(ilvo_list3_2);
		listaIL3.add(ilvo_list3_3);
		listaIL3.add(ilvo_list3_4);
		listaIL3.add(ilvo_list3_5);
		listaIL3.add(ilvo_list3_6);
		listaIL3.add(ilvo_list3_7);
		listaIL3.add(ilvo_list3_8);
		listaIL3.add(ilvo_list3_9);
		listaIL3.add(ilvo_list3_10);
		listaIL3.add(ilvo_list3_11);
		
		/* Cargo list item lista para la lista 4 */
		ArrayList<ItemListaVO> listaIL4 = new ArrayList<ItemListaVO>();
		listaIL4.add(ilvo_list4_1);
		listaIL4.add(ilvo_list4_2);
		listaIL4.add(ilvo_list4_3);
		listaIL4.add(ilvo_list4_4);
		listaIL4.add(ilvo_list4_5);
		listaIL4.add(ilvo_list4_6);
		
		ListaPreciosVO lpvo1 = nuevaListaPrecios("Lista Rodaditos Expertos", new Date(), getRandomFechaVencimiento(), 1, null,listaIL1);
		ListaPreciosVO lpvo2 = nuevaListaPrecios("Lista Rodamientos S.A.", new Date(), getRandomFechaVencimiento(), 2, null,listaIL2);
	  	ListaPreciosVO lpvo3 = nuevaListaPrecios("Lista Los rodados ruedan", new Date(), getRandomFechaVencimiento(), 3, null,listaIL3);
		ListaPreciosVO lpvo4 = nuevaListaPrecios("Lista R con R Rodado", new Date(), getRandomFechaVencimiento(), 4, null,listaIL4);
		
		ArrayList<ListaPreciosVO> lpvoList = new ArrayList<ListaPreciosVO>();
		lpvoList.add(lpvo1);
		lpvoList.add(lpvo2);
		lpvoList.add(lpvo3);
		lpvoList.add(lpvo4);
		
		return lpvoList;
	}

	private static ItemListaVO nuevoItemListaVO(int idRod, float precio) {
		RodamientoVO rvo = new RodamientoVO();
		rvo.setId(idRod);
		ItemListaVO ilvo = new ItemListaVO();
		ilvo.setPrecio(precio);
		ilvo.setRodamiento(rvo);
		return ilvo;
	}

	private static ListaPreciosVO nuevaListaPrecios(String nombre, Date fechaInicio,
			Date fechaFin, int idProveedor, ArrayList<CondicionVentaVO> condiciones, ArrayList<ItemListaVO> listaItems) {
		ListaPreciosVO lpvo = new ListaPreciosVO();
		lpvo.setNombre(nombre);
		lpvo.setVigenciaDesde(fechaInicio);
		lpvo.setVigenciaHasta(fechaFin);
		ProveedorVO prove = new ProveedorVO();
		prove.setId(idProveedor);
		lpvo.setProveedor(prove);
		lpvo.setCondicionesDeVenta(condiciones);
		lpvo.setItems(listaItems);
		return lpvo;
	}

	public static ArrayList<RodamientoVO> getControlledRodamientosList() {
		RodamientoVO rod1 = nuevoRodamientoVO("22310 CCW33", "ZKL", "Japon", 6);
		RodamientoVO rod2 = nuevoRodamientoVO("22310 EKW33", "SKF", "Argentina", 7);
		RodamientoVO rod3 = nuevoRodamientoVO("6200", "SNR", "Francia", 13);
		RodamientoVO rod4 = nuevoRodamientoVO("6200 2RS", "FAG", "Alemania", 45);
		RodamientoVO rod5 = nuevoRodamientoVO("6200 ZZ", "STEYR", "Reino Unido", 40);
		RodamientoVO rod6 = nuevoRodamientoVO("6204 2RSC3", "SFK", "Brasil", 0);
		RodamientoVO rod7 = nuevoRodamientoVO("K25580/25520", "ZKL", "Japon", 170);
		RodamientoVO rod8 = nuevoRodamientoVO("NJ 208 EMC3", "SNR", "Francia", 56);
		RodamientoVO rod9 = nuevoRodamientoVO("NJ 208 EMC3", "SKF", "Suecia", 55);
		RodamientoVO rod10 = nuevoRodamientoVO("NJ 208 EMC3", "STEYR", "Francia", 12);
		RodamientoVO rod11 = nuevoRodamientoVO("NJ 208 EMC3", "FAG", "Francia", 29);
		RodamientoVO rod12 = nuevoRodamientoVO("NJ 208 EMC3", "ZKL", "Francia", 0);
		RodamientoVO rod13 = nuevoRodamientoVO("6200", "ZKL", "Alemania", 0);
		RodamientoVO rod14 = nuevoRodamientoVO("6200", "FAG", "Alemania", 58);
		RodamientoVO rod15 = nuevoRodamientoVO("6200 ZZ", "ZKL", "Reino Unido", 0);
		RodamientoVO rod16 = nuevoRodamientoVO("6200 ZZ", "ZKL", "Suecia", 89);
		RodamientoVO rod17 = nuevoRodamientoVO("6204 2RSC3", "ZKL", "Brasil", 0);
		RodamientoVO rod18 = nuevoRodamientoVO("K25580/25520", "FAG", "Japon", 11);

		ArrayList<RodamientoVO> rodamientos = new ArrayList<RodamientoVO>();
		rodamientos.add(rod1);
		rodamientos.add(rod2);
		rodamientos.add(rod3);
		rodamientos.add(rod4);
		rodamientos.add(rod5);
		rodamientos.add(rod6);
		rodamientos.add(rod7);
		rodamientos.add(rod8);
		rodamientos.add(rod9);
		rodamientos.add(rod10);
		rodamientos.add(rod11);
		rodamientos.add(rod12);
		rodamientos.add(rod13);
		rodamientos.add(rod14);
		rodamientos.add(rod15);
		rodamientos.add(rod16);
		rodamientos.add(rod17);
		rodamientos.add(rod18);
		
		return rodamientos;
	}

	private static RodamientoVO nuevoRodamientoVO(String skf, String marca, String pais, int stock) {
		RodamientoVO rod = new RodamientoVO();
		rod.setCodigoSKF(skf);
		rod.setMarca(marca);
		rod.setPais(pais);
		rod.setStock(stock);
		return rod;
	}

	public static ArrayList<OficinaDeVentaVO> getControlledOficinasDeVentaList() {
		OficinaDeVentaVO odv1 = nuevaOficinaDeVentaVO("ODV 1", "UADE 1", "127.0.0.1", 1099, "EnviarRemito");
		OficinaDeVentaVO odv2 = nuevaOficinaDeVentaVO("ODV 2", "UADE 2", "127.0.0.1", 1099, "EnviarRemito");
		OficinaDeVentaVO odv3 = nuevaOficinaDeVentaVO("ODV 3", "UADE 3", "127.0.0.1", 1099, "EnviarRemito");
		OficinaDeVentaVO odv4 = nuevaOficinaDeVentaVO("ODV 4", "UADE 4", "127.0.0.1", 1099, "EnviarRemito");
		OficinaDeVentaVO odv5 = nuevaOficinaDeVentaVO("ODV 5", "UADE 5", "127.0.0.1", 1099, "EnviarRemito");
		OficinaDeVentaVO odv6 = nuevaOficinaDeVentaVO("ODV 6", "UADE 6", "127.0.0.1", 1099, "EnviarRemito");
		
		ArrayList<OficinaDeVentaVO> odvvo = new ArrayList<OficinaDeVentaVO>();
		odvvo.add(odv1);
		odvvo.add(odv2);
		odvvo.add(odv3);
		odvvo.add(odv4);
		odvvo.add(odv5);
		odvvo.add(odv6);
		
		return odvvo;
	}

	private static OficinaDeVentaVO nuevaOficinaDeVentaVO(String nombre,
			String direccion, String ip, int puerto, String nombreColaRemito) {
		OficinaDeVentaVO odv = new OficinaDeVentaVO();
		odv.setNombre(nombre);
		odv.setDireccion(direccion);
		odv.setIp(ip);
		odv.setPuerto(puerto);
		odv.setNombreColaRemito(nombreColaRemito);
		return odv;
	}

	public static ArrayList<SolicitudCotizacionRequest> getControlledSolicitudCotizacionRequestList() {
		ArrayList<SolicitudCotizacionRequest> solicitudes = new ArrayList<SolicitudCotizacionRequest>();
		
		SolicitudCotizacionRequest solicitud1 = new SolicitudCotizacionRequest();
		solicitud1.setIdODV(1);
		solicitud1.setIdPedidoCotizacion(1);
		solicitud1.setMarca("ZKL");
		solicitud1.setPais("Japon");
		solicitud1.setSKF("22310 CCW33");
		
		SolicitudCotizacionRequest solicitud2 = new SolicitudCotizacionRequest();
		solicitud2.setIdODV(1);
		solicitud2.setIdPedidoCotizacion(2);
		solicitud2.setMarca("ZKL");
		solicitud2.setPais("Alemania");
		solicitud2.setSKF("6200");
		
		SolicitudCotizacionRequest solicitud3 = new SolicitudCotizacionRequest();
		solicitud3.setIdODV(1);
		solicitud3.setIdPedidoCotizacion(3);
		solicitud3.setPais("Alemania");
		solicitud3.setSKF("6200");

		SolicitudCotizacionRequest solicitud4 = new SolicitudCotizacionRequest();
		solicitud4.setIdODV(1);
		solicitud4.setIdPedidoCotizacion(4);
		solicitud2.setMarca("ZKL");
		solicitud2.setPais("Reino Unido");
		solicitud2.setSKF("6200 ZZ");

		solicitudes.add(solicitud1);
		solicitudes.add(solicitud2);
		solicitudes.add(solicitud3);
		solicitudes.add(solicitud4);
		
		return solicitudes;
	}

	public static SolicitudCompraRequest getControlledSolicitudCompraRequest() {
		SolicitudCompraRequest solicitud = new SolicitudCompraRequest();
		solicitud.setIdODV(1);
		solicitud.setIdOrdenDeCompra(5);
		solicitud.setItems(getControlledItemVOList());
		
		return solicitud;
	}

	private static List<ItemVO> getControlledItemVOList() {
		List<ItemVO> items = new ArrayList<ItemVO>();
		
		ArrayList<SolicitudCotizacionRequest> itemsCotizados = getControlledSolicitudCotizacionRequestList();
		SolicitudCotizacionRequest itemCotizado1 = itemsCotizados.get(0);
		SolicitudCotizacionRequest itemCotizado2 = itemsCotizados.get(1);
		SolicitudCotizacionRequest itemCotizado3 = itemsCotizados.get(3);
		
		ItemVO item1 = new ItemVO();
		item1.setMarca(itemCotizado1.getMarca());
		item1.setPais(itemCotizado1.getPais());
		item1.setSKF(itemCotizado1.getSKF());
		item1.setCantidad(getRandomCantidad());
		item1.setId(1);
		
		ItemVO item2 = new ItemVO();
		item2.setMarca(itemCotizado2.getMarca());
		item2.setPais(itemCotizado2.getPais());
		item2.setSKF(itemCotizado2.getSKF());
		item2.setCantidad(getRandomCantidad());
		item2.setId(2);
		
		ItemVO item3 = new ItemVO();
		item3.setMarca(itemCotizado3.getMarca());
		item3.setPais(itemCotizado3.getPais());
		item3.setSKF(itemCotizado3.getSKF());
		item3.setCantidad(getRandomCantidad());
		item3.setId(3);
		
		items.add(item1);
		items.add(item2);
//		items.add(item3);
		
		return items;
	}
}

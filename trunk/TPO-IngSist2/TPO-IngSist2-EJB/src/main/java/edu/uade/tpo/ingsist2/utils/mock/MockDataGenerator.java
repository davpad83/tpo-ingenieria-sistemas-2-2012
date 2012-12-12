package edu.uade.tpo.ingsist2.utils.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import edu.uade.tpo.ingsist2.view.jms.JMSQueuesNames;
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
		String[] proveedores = { "20-34343431-2", "20-34343432-2", "20-34343433-2","20-34343434-2" };
		return proveedores[new Random().nextInt(proveedores.length)];
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
		lista.setNombre("Nueva Lista Precios Rodamientos Test");
		lista.setProveedor(getRandomProveedorVO());
		lista.setVigenciaDesde(new Date());
		lista.setVigenciaHasta(getRandomFechaVencimiento());
		lista.setItems(getRandomListaItemListaVO(cantItems));
		return lista;
	}
	
	public static RecepcionRodamientosVO getRandomListaRodamientoVO(int cantItems) {
		RecepcionRodamientosVO rrvo = new RecepcionRodamientosVO();		
		RecepcionRodamientosVO.RodamientoListaVO rlvo = rrvo.new RodamientoListaVO();
		RecepcionRodamientosVO.RodamientoListaVO rlvo1 = rrvo.new RodamientoListaVO();
		rlvo.setSKF("6200 ZZ");
		rlvo.setMarca("STEYR");
		rlvo.setPais("Reino Unido");
		rlvo.setCantidad(cantItems);	
		rlvo.setIdPedidoAbastecimiento(1);
		rlvo1.setSKF("6200 2RS");
		rlvo1.setMarca("FAG");
		rlvo1.setPais("Alemania");
		rlvo1.setCantidad(cantItems);	
		rlvo1.setIdPedidoAbastecimiento(2);
		rrvo.getListaRodVO().add(rlvo);
		rrvo.getListaRodVO().add(rlvo1);
		
		return rrvo;
	}

	@SuppressWarnings("static-access")
	public static Date getRandomFechaVencimiento() {
		Calendar cal = Calendar.getInstance();
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
		//ilvo.setRodamiento(getRandomRodamientoVO());
		ilvo.setRodamiento(getControlledRodamientosList().get(new Random().nextInt(17)));
		return ilvo;
	}

	public static float getRandomPrecio() {
		return new Random().nextInt(1000);
	}
	

	public static RodamientoVO getRandomRodamientoVO() {
		RodamientoVO rod = new RodamientoVO();
		rod.setMarca(getRandomMarca());
		rod.setPais(getRandomPais());
		rod.setCodigoSKF(getRandomCodigoSKF());
		return rod;
	}

	public static String getRandomCodigoSKF() {
		String[] codigosSKF = { "22310 CCW33", "22310 EKW33", "6200", "6200 ZZ", "6200 2RS", "K25580/25520", "6204 2RSC3", "NJ 208 EMC3"};
		return codigosSKF[new Random().nextInt(codigosSKF.length)];
	}

	public static String getRandomPais() {
		String[] paises = { "Argentina", "Francia", "Suecia", "Alemania", "Japon", "Brasil", "Reino Unido" };
		return paises[new Random().nextInt(paises.length)];
	}

	public static String getRandomMarca() {
		String[] marcas = { "ZKL", "SKF", "SNR", "FAG", "STEYR","SFK"};
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
		OficinaDeVentaVO odv1 = nuevaOficinaDeVentaVO("ODV 1", "UADE 1", "172.16.171.26", 1099, JMSQueuesNames.ENVIAR_REMITO_QUEUE);
		OficinaDeVentaVO odv2 = nuevaOficinaDeVentaVO("ODV 2", "UADE 2", "172.16.171.36", 1099, JMSQueuesNames.ENVIAR_REMITO_QUEUE);
		OficinaDeVentaVO odv3 = nuevaOficinaDeVentaVO("ODV 3", "UADE 3", "172.16.171.1", 1099, JMSQueuesNames.ENVIAR_REMITO_QUEUE);
		OficinaDeVentaVO odv4 = nuevaOficinaDeVentaVO("ODV 4", "UADE 4", "172.16.171.9", 1099, JMSQueuesNames.ENVIAR_REMITO_QUEUE);
		OficinaDeVentaVO odv5 = nuevaOficinaDeVentaVO("ODV 5", "UADE 5", "172.16.171.1", 1099, JMSQueuesNames.ENVIAR_REMITO_QUEUE);
		OficinaDeVentaVO odv6 = nuevaOficinaDeVentaVO("ODV 6", "UADE 6", "172.16.171.28", 1099, JMSQueuesNames.ENVIAR_REMITO_QUEUE);
		OficinaDeVentaVO odv7 = nuevaOficinaDeVentaVO("ODV 7", "UADE 7", "172.16.171.27", 1099, JMSQueuesNames.ENVIAR_REMITO_QUEUE);
		OficinaDeVentaVO odv8 = nuevaOficinaDeVentaVO("ODV 8", "UADE 8", "172.16.171.33", 1099, JMSQueuesNames.ENVIAR_REMITO_QUEUE);
		OficinaDeVentaVO odv9 = nuevaOficinaDeVentaVO("ODV 9", "UADE 9", "172.16.171.29", 1099, JMSQueuesNames.ENVIAR_REMITO_QUEUE);
		OficinaDeVentaVO odv10 = nuevaOficinaDeVentaVO("ODV 10", "ODV PARA TEST", "127.0.0.1", 1099, JMSQueuesNames.ENVIAR_REMITO_QUEUE);
		
		ArrayList<OficinaDeVentaVO> odvvo = new ArrayList<OficinaDeVentaVO>();
		odvvo.add(odv1);
		odvvo.add(odv2);
		odvvo.add(odv3);
		odvvo.add(odv4);
		odvvo.add(odv5);
		odvvo.add(odv6);
		odvvo.add(odv7);
		odvvo.add(odv8);
		odvvo.add(odv9);
		odvvo.add(odv10);
		
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
		
		//Rodamiento con marca y stock suficiente
		SolicitudCotizacionRequest solicitud1 = new SolicitudCotizacionRequest();
		solicitud1.setIdODV(10);
		solicitud1.setIdPedidoCotizacion(32);
		solicitud1.setMarca("ZKL");
		solicitud1.setPais("Japon");
		solicitud1.setSKF("22310 CCW33");
		solicitud1.setCantidad(6);
		
		//Rodamiento con marca y stock totalmente insuficiente
		SolicitudCotizacionRequest solicitud2 = new SolicitudCotizacionRequest();
		solicitud2.setIdODV(10);
		solicitud2.setIdPedidoCotizacion(41);
		solicitud2.setMarca("SNR");
	    solicitud2.setPais("Francia");
	    solicitud2.setSKF("6200");
		solicitud2.setCantidad(2);
		
		//Rodamiento con marca y stock parcialmente insuficiente
		SolicitudCotizacionRequest solicitud3 = new SolicitudCotizacionRequest();
		solicitud3.setIdODV(10);
		solicitud3.setIdPedidoCotizacion(8);
		solicitud3.setMarca("STEYR");
		solicitud3.setPais("Reino Unido");
		solicitud3.setSKF("6200 ZZ");
		solicitud3.setCantidad(93);

		//Rodamiento sin marca
		SolicitudCotizacionRequest solicitud4 = new SolicitudCotizacionRequest();
		solicitud4.setIdODV(10);
		solicitud4.setIdPedidoCotizacion(235);
		solicitud4.setPais("Alemania");
		solicitud4.setSKF("6200 2RS");
		solicitud4.setCantidad(10);
		
		solicitudes.add(solicitud1);
		solicitudes.add(solicitud2);
		solicitudes.add(solicitud3);
		solicitudes.add(solicitud4);
		
		return solicitudes;
	}

	public static List<SolicitudCompraRequest> getControlledSolicitudCompraRequestList() {
		List<SolicitudCompraRequest> solicitudes = new ArrayList<SolicitudCompraRequest>();
		
		//Solicitud con 4 Items
		SolicitudCompraRequest solicitud1 = new SolicitudCompraRequest();
		solicitud1.setIdODV(10);
		solicitud1.setIdOrdenDeCompra(5);
		solicitud1.setItems(getControlledItemVOList());

		//Solicitud sin items
		SolicitudCompraRequest solicitud2 = new SolicitudCompraRequest();
		solicitud2.setIdODV(10);
		solicitud2.setIdOrdenDeCompra(9);
		solicitud2.setItems(new ArrayList<ItemSolicitudCompraRequest>());

		//Solicitud con items null
		SolicitudCompraRequest solicitud3 = new SolicitudCompraRequest();
		solicitud3.setIdODV(10);
		solicitud3.setIdOrdenDeCompra(120);
		solicitud3.setItems(null);

		//Solicitud con id ODV invalida
		SolicitudCompraRequest solicitud4 = new SolicitudCompraRequest();
		solicitud4.setIdODV(50);
		solicitud4.setIdOrdenDeCompra(44);
		solicitud4.setItems(getControlledItemVOList());

		solicitudes.add(solicitud1);
		solicitudes.add(solicitud2);
		solicitudes.add(solicitud3);
		solicitudes.add(solicitud4);
			
		return solicitudes;
	}

	/**
	 * Esta lista es usada para generar los itemas de la solicitud de compra
	 * 
	 * @return
	 */
	private static List<ItemSolicitudCompraRequest> getControlledItemVOList() {
		List<ItemSolicitudCompraRequest> items = new ArrayList<ItemSolicitudCompraRequest>();
		
		ArrayList<SolicitudCotizacionRequest> itemsCotizados = getControlledSolicitudCotizacionRequestList();
		SolicitudCotizacionRequest itemCotizado1 = itemsCotizados.get(0);
		SolicitudCotizacionRequest itemCotizado2 = itemsCotizados.get(1);
		SolicitudCotizacionRequest itemCotizado3 = itemsCotizados.get(2);
		SolicitudCotizacionRequest itemCotizado4 = itemsCotizados.get(3);
		
		//Rodamiento cotizado con stock suficiente
		
		ItemSolicitudCompraRequest item1 = new ItemSolicitudCompraRequest();
		item1.setMarca(itemCotizado1.getMarca());
		item1.setPais(itemCotizado1.getPais());
		item1.setSKF(itemCotizado1.getSKF());
		item1.setCantidad(6);
		item1.setIdPedidoCotODV(32);

		//Rodamiento cotizado con stock totalmente insuficiente
		
		ItemSolicitudCompraRequest item2 = new ItemSolicitudCompraRequest();
		item2.setMarca(itemCotizado2.getMarca());
		item2.setPais(itemCotizado2.getPais());
		item2.setSKF(itemCotizado2.getSKF());
		item2.setCantidad(2);
		item2.setIdPedidoCotODV(41);

		//Rodamiento cotizado con stock parcialmente insuficiente
		
		ItemSolicitudCompraRequest item3 = new ItemSolicitudCompraRequest();
		item3.setMarca(itemCotizado3.getMarca());
		item3.setPais(itemCotizado3.getPais());
		item3.setSKF(itemCotizado3.getSKF());
		item3.setCantidad(99);
		item3.setIdPedidoCotODV(8);
		
		//INVALIDO - Rodamiento no cotizado
		
		ItemSolicitudCompraRequest item4 = new ItemSolicitudCompraRequest();
		item4.setMarca(getRandomMarca());
		item4.setPais(getRandomPais());
		item4.setSKF(getRandomCodigoSKF());
		item4.setCantidad(getRandomCantidad());
		item4.setIdPedidoCotODV(21);
		
		//Rodamiento con cotizacion sin marca
		
		ItemSolicitudCompraRequest item5 = new ItemSolicitudCompraRequest();
		item5.setMarca("FAG");
		item5.setPais(itemCotizado4.getPais());
		item5.setSKF(itemCotizado4.getSKF());
		item5.setCantidad(57);
		item5.setIdPedidoCotODV(235);

		//INVALIDO - Rodamiento sin Codigo
		
		ItemSolicitudCompraRequest item6 = new ItemSolicitudCompraRequest();
		item6.setMarca("FAG");
		item6.setPais(itemCotizado4.getPais());
		item6.setSKF("");
		item6.setCantidad(57);
		item6.setIdPedidoCotODV(565);
		
		//INVALIDO - Rodamiento sin pais
		
		ItemSolicitudCompraRequest item7 = new ItemSolicitudCompraRequest();
		item7.setMarca("FAG");
		item7.setPais("");
		item7.setSKF(itemCotizado4.getSKF());
		item7.setCantidad(57);
		item7.setIdPedidoCotODV(169);

		//INVALIDO - Rodamiento con cantidad 0
		
		ItemSolicitudCompraRequest item8 = new ItemSolicitudCompraRequest();
		item8.setMarca("FAG");
		item8.setPais("6200");
		item8.setSKF(itemCotizado4.getSKF());
		item8.setCantidad(0);
		item8.setIdPedidoCotODV(169);

		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		items.add(item6);
		items.add(item7);
		items.add(item8);
		
		return items;
	}

	public static ArrayList<ProveedorVO> getProveedorForRealIntegrationTest() {
		ArrayList<ProveedorVO> proves = new ArrayList<ProveedorVO>();
		ProveedorVO prove = new ProveedorVO();
		prove.setCuit("20-33349798-7");
		prove.setNombre("Proveedor S.A.");
		prove.setTiempoDeEntrega("2 dias");
		proves.add(prove);
		return proves;
	}

	public static ArrayList<ListaPreciosVO> getListaPrecioListForRealIntegrationTest() {
		ItemListaVO ilvo_list1_1 = nuevoItemListaVO(1, 310.71f); 
		ItemListaVO ilvo_list1_2 = nuevoItemListaVO(2, 249f); 
		ItemListaVO ilvo_list1_3 = nuevoItemListaVO(3, 7.1f); 
		ItemListaVO ilvo_list1_4 = nuevoItemListaVO(4, 7.9f); 
		ItemListaVO ilvo_list1_5 = nuevoItemListaVO(5, 7.9f); 
		ItemListaVO ilvo_list1_6 = nuevoItemListaVO(6, 13.49f); 
		ItemListaVO ilvo_list1_7 = nuevoItemListaVO(7, 67.09f); 
		ItemListaVO ilvo_list1_8 = nuevoItemListaVO(8, 107.86f); 
		ItemListaVO ilvo_list1_9 = nuevoItemListaVO(9, 132.26f); 

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
		
		ListaPreciosVO lpvo1 = nuevaListaPrecios("Lista Proveedor S.A.", new Date(), getRandomFechaVencimiento(), 1, null,listaIL1);
		
		ArrayList<ListaPreciosVO> lpvoList = new ArrayList<ListaPreciosVO>();
		lpvoList.add(lpvo1);
		
		return lpvoList;
	}

	public static ArrayList<RodamientoVO> getRodamientosListForRealIntegrationTest() {
		RodamientoVO rod1 = nuevoRodamientoVO("22310 CCW33", "ZKL", "Japon", 0);
		RodamientoVO rod2 = nuevoRodamientoVO("22310 EKW33", "SKF", "Argentina", 0);
		RodamientoVO rod3 = nuevoRodamientoVO("6200", "SNR", "Francia", 0);
		RodamientoVO rod4 = nuevoRodamientoVO("6200 2RS", "FAG", "Alemania", 0);
		RodamientoVO rod5 = nuevoRodamientoVO("6200 ZZ", "STEYR", "Reino Unido", 0);
		RodamientoVO rod6 = nuevoRodamientoVO("6204 2RSC3", "SFK", "Brasil", 0);
		RodamientoVO rod7 = nuevoRodamientoVO("K25580/25520", "ZKL", "Japon", 0);
		RodamientoVO rod8 = nuevoRodamientoVO("NJ 208 EMC3", "SNR", "Francia", 0);
		RodamientoVO rod9 = nuevoRodamientoVO("NJ 208 EMC3", "SKF", "Suecia", 0);

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
		
		return rodamientos;
	}
}

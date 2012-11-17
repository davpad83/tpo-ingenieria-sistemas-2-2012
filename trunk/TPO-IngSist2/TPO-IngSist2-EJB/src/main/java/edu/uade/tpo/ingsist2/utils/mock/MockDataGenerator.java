package edu.uade.tpo.ingsist2.utils.mock;

import java.util.ArrayList;
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

	private static ProveedorVO getRandomProveedorVO() {
		ProveedorVO prove = new ProveedorVO();
		prove.setCuit(getRandomCuit());
		prove.setNombre(getRandomNombre());
		return prove;
	}

	private static int getRandomId() {
		return new Random().nextInt(1000);
	}

	private static String getRandomNombre() {
		String[] nombres = { "Matias", "Pedro", "Joaco", "Juan", "Carlos",
				"Pepe", "Alejandra", "Silvia", "Nicolas", "Howard" };
		String[] apellidos = { "Fernandes", "Gonzalez", "Favale", "Attanasio",
				"Primo", "Salvatore", "Villagra", "Perez" };

		return nombres[new Random().nextInt(nombres.length - 1)] + " "
				+ apellidos[new Random().nextInt(apellidos.length)];
	}

	private static String getRandomCuit() {
		String cuit = "";
		for (int i = 0; i < 11; i++) {
			cuit += new Random().nextInt(9);
			if (i == 2 || i == 9)
				cuit += "-";
		}
		return cuit;
	}

	@SuppressWarnings("deprecation")
	public static ListaPreciosVO getRandomListaPreciosVO() {
		ListaPreciosVO lista = new ListaPreciosVO();
		lista.setNombre("Rodamientos Locos SRL");
		lista.setProveedor(getRandomProveedorVO());
		lista.setVigenciaDesde(new Date());
		lista.setVigenciaHasta(new Date(new Date().getYear(), new Date()
				.getMonth(), new Date().getDay() + 7));
		lista.setItems(getRandomListaItemListaVO());
		return lista;
	}

	public static List<ItemListaVO> getRandomListaItemListaVO() {
		ArrayList<ItemListaVO> lilvo = new ArrayList<ItemListaVO>();
		for (int i = 0; i < 10; i++) {
			lilvo.add(getRandomItemListaVO());
		}
		return lilvo;
	}

	public static ItemListaVO getRandomItemListaVO() {
		ItemListaVO ilvo = new ItemListaVO();
		ilvo.setPrecio(getRandomPrecio());
		ilvo.setRodamiento(getRodamientoVO());
		return ilvo;
	}

	private static float getRandomPrecio() {
		return new Random().nextInt(10000);
	}

	private static String getRandomCodigoSKF(){
		String[] codigosSKF = { "BS 3200-A" };
		return codigosSKF[new Random().nextInt(codigosSKF.length)];
	}

	private static String getRandomPais() {
		String[] paises = { "Argentina", "Brasil", "Estados Unicos", "Chile",
				"Paraguay", "Uruguay", "China", "Japon", "Bolivia", "Peru",
				"Costa Rica", "Canada", "Mexico", "Cuba", "Ecuador" };
		return paises[new Random().nextInt(paises.length)];
	}

	public static RodamientoVO getRodamientoVO() {
		RodamientoVO rod = new RodamientoVO();
		rod.setMarca(getRandomMarca());
		rod.setPais(getRandomPais());
//		rod.setStock(new Random().nextInt(1));
		rod.setCodigoSKF(getRandomCodigoSKF());
		return rod;
	}

	private static String getRandomMarca() {
		String[] marcas = { "Rodamientos Locos SRL", "Rodaditos",
				"Patricio Rod y sus Rodamientos de Ricota",
				"Rodamientos Red Ribbon", "Rodamientos por todos lados",
				"Invasion de los Rodamientos" };
		return marcas[new Random().nextInt(marcas.length)];
	}
}

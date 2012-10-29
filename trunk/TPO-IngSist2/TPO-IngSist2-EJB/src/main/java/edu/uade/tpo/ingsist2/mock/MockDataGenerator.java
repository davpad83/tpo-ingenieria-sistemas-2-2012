package edu.uade.tpo.ingsist2.mock;

import java.util.ArrayList;
import java.util.List;

import edu.uade.tpo.ingsist2.entities.vo.*;

public class MockDataGenerator {
	
	public static ArrayList<ProveedorVO> getListaProveedorVOMock(){
		ArrayList<ProveedorVO> lista = new ArrayList<ProveedorVO>();
		for(int i=0; i<10; i++)
			lista.add(getProveedorVOMock());
		return lista;
	}
	
	private static ProveedorVO getProveedorVOMock() {
		ProveedorVO prove = new ProveedorVO();
		prove.getRandomId();
		prove.setCuit(getRandomCuit());
		prove.setNombre(getRandomNombre());	
		return prove;
	}

	private static String getRandomNombre() {
		return "Matias Favale";
	}

	private static String getRandomCuit() {
		return "20-1231233-4";
	}

	public static ArrayList<RodamientoVO> getListaRodamientosVOMock(){
		ArrayList<RodamientoVO> lista = new ArrayList<RodamientoVO>();
		for(int i=0; i<10; i++)
			getRodamientoVOMock();
		return lista;
	}

	public static RodamientoVO getRodamientoVOMock() {
		RodamientoVO rod = new RodamientoVO();
		return rod;
	}

}

package edu.uade.tpo.ingsist2.model.util;

import edu.uade.tpo.ingsist2.model.MapaCaracteristica;
import edu.uade.tpo.ingsist2.model.MapaPais;

public class CodigoSKFHelper {

	private MapaCaracteristica mapaCaracteristica;

	public String generarCodigo(String tipo, int medida, String caracteristica) {

		return getCodTipo(tipo) + getCodMedida(medida)
				+ getCaracteristica(caracteristica);
	}

	public String getTipo(String codTipo){
		return null;
	}
	
	public String getCodTipo(String tipo){
		return tipo.toUpperCase().substring(0, 4);
	}

	public int getMedida(String codMedida){
		return 0;
	}
	
	public String getCodMedida(int medida){
		return String.valueOf(medida);
	}
	
	public String getCodCaracteristica(String caracteristica){
		return caracteristica.toUpperCase().substring(0, 4);
	}
	
	public String getCaracteristica(String codCaracteristica) {
		return null;
	}

}

package edu.uade.tpo.ingsist2.session.util;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

@Local
public interface QueryHelper {
	
	public <T> T getEntidad(Class<T> claseEntidad, Serializable id);
	
	public <T> List<T> getListaEntidades(Class<T> claseEntidad);

}

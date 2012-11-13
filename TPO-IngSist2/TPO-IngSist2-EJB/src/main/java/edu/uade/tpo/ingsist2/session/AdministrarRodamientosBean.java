package edu.uade.tpo.ingsist2.session;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.entities.Proveedor;
import edu.uade.tpo.ingsist2.entities.Rodamiento;
import edu.uade.tpo.ingsist2.entities.vo.RodamientoVO;

@Stateless
public class AdministrarRodamientosBean implements AdministrarRodamientos {

	private static Logger logger = Logger
			.getLogger(AdministrarProveedoresBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	
    public AdministrarRodamientosBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void guardarRodamiento(RodamientoVO r) {
		logger.info("Procesando guardar rodamiento con codigoSKF " + r.getCodigoSKF());
		Rodamiento rBean = new Rodamiento();
		rBean.setVO(r);
		Rodamiento rGuardado = null;
		try {
			rGuardado = (Rodamiento) entityManager.merge(rBean);
		} catch (Exception e) {
			logger.error("Hubo un error al guardar el rodamiento");
			e.printStackTrace();
		}
		logger.info("Rodamiento guardado con id: " + rGuardado.getId());
	}

	@Override
	public void eliminarRodamiento(int id) {
		logger.info("Procesando eliminar rodamiento con id: " + id);
		try {
			Rodamiento r = entityManager.find(Rodamiento.class, id);
			entityManager.remove(r);
		} catch (Exception e) {
			logger.error("Hubo un error intentando eliminar el rodamiento con id "
					+ id);
			e.printStackTrace();
		}
		logger.info("El rodamiento con id " + id + " se ha eliminado con exito.");
	}

	@Override
	public RodamientoVO getRodamiento(int id) {
		logger.info("Buscando Rodamiento con id " + id);
		Rodamiento r = null;
		try {
			r = entityManager.find(Rodamiento.class, id);
		} catch (Exception e) {
			logger.error("Hubo un error al buscar el rodamiento.");
			e.printStackTrace();
			return null;
		} finally {
			if (r != null)
				logger.info("El Rodamiento con id " + id
						+ " se ha encontrado, su codigoSKF es " + r.getCodigoSKF());
			else {
				logger.info("No se ha encontrado el rodamiento con id " + id);
				return null;
			}
		}
		return r.getVO();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<RodamientoVO> getRodamientos() {
		logger.info("Buscando lista de Rodamientos");
		ArrayList<Rodamiento> listaResultado = null;
		try {
			listaResultado = (ArrayList<Rodamiento>) entityManager.createQuery(
					"FROM Rodamiento").getResultList();
		} catch (Exception e) {
			logger.error("Hubo un error al buscar todos los rodamientos.");
			e.printStackTrace();
			return null;
		} finally {
			if (listaResultado == null || listaResultado.isEmpty()) {
				logger.info("No se han encontrado instancias de Rodamientos");
				return null;
			} else
				logger.info("Se han encontrado " + listaResultado.size()
						+ " instancias de Rodamientos");
		}
		return Rodamiento.getVOList(listaResultado);
	}

}
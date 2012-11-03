package edu.uade.tpo.ingsist2.session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.entities.Proveedor;
import edu.uade.tpo.ingsist2.entities.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.facade.AdminFacadeBean;
import edu.uade.tpo.ingsist2.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.session.util.QueryHelper;

@Stateless
public class AdministrarProveedoresBean implements AdministrarProveedores {

	private static Logger logger = Logger.getLogger(AdministrarProveedoresBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	@EJB
	private QueryHelper qHelper;

	public AdministrarProveedoresBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void guardarProveedor(ProveedorVO p) {
		Proveedor pBean = new Proveedor();
		pBean.setVO(p);
		entityManager.merge(pBean);
	}

	@Override
	public void eliminarProveedor(int id) {
		logger.info("Procesando eliminar proveedor con id: " + id);
		Proveedor p = new Proveedor();
		p.setId(id);
		entityManager.remove(p);
	}

	@Override
	public ProveedorVO getProveedor(int id) {
		Proveedor p = qHelper.getEntidad(Proveedor.class, id);
		return p.getVO();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ProveedorVO> getProveedores() {
		// ArrayList<Proveedor> p = (ArrayList<Proveedor>)
		// qHelper.getListaEntidades(Proveedor.class);
		// return MockDataGenerator.getListaProveedorVOMock();

		logger.info("Buscando lista de Proveedores");
		ArrayList<Proveedor> listaResultado = null;
		listaResultado = (ArrayList<Proveedor>) entityManager.createQuery(
				"FROM Proveedor").getResultList();
		if (listaResultado == null) {
			listaResultado = new ArrayList<Proveedor>();
			logger.info("No se han encontrado instancias de Proveedores");
		} else
			logger.info("Se han encontrado " + listaResultado.size()
					+ " instancias de Proveedores");
		return Proveedor.getVOList(listaResultado);

		// return Proveedor.getVOList(p);
	}
}

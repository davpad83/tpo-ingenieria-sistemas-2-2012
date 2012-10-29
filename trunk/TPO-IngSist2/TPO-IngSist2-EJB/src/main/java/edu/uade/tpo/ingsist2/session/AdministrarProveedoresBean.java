package edu.uade.tpo.ingsist2.session;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.entities.Proveedor;
import edu.uade.tpo.ingsist2.entities.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.facade.AdminFacadeBean;
import edu.uade.tpo.ingsist2.model.util.QueryHelper;

@Stateless
public class AdministrarProveedoresBean implements AdministrarProveedores {

	private static Logger logger = Logger.getLogger(AdminFacadeBean.class);

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;
	
	private QueryHelper qHelper = new QueryHelper();
		
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

	@Override
	public ArrayList<ProveedorVO> getProveedores() {
		ArrayList<Proveedor> p = (ArrayList<Proveedor>) qHelper.getListaEntidades(Proveedor.class);
		return Proveedor.getVOList(p);
	}
}

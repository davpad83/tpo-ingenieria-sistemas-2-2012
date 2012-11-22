package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import edu.uade.tpo.ingsist2.model.Proveedor;
import edu.uade.tpo.ingsist2.model.entities.EntitiesTablesNames;
import edu.uade.tpo.ingsist2.model.entities.ProveedorEntity;
import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.facade.FacadeBean;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;

@Stateless
public class AdministrarProveedoresBean implements AdministrarProveedores {

	private static Logger logger = Logger
			.getLogger(AdministrarProveedoresBean.class);

	@EJB
	private Proveedor proveedor;

	public AdministrarProveedoresBean() {
		// empty
	}

	@Override
	public void guardarProveedor(ProveedorVO p) {
		ProveedorEntity pBean = new ProveedorEntity();
		pBean.setVO(p);
		proveedor.guardarProveedor(pBean);
	}

	@Override
	public void eliminarProveedor(int id) {
		proveedor.eliminarProveedor(id);
	}

	@Override
	public ProveedorVO getProveedor(int id) {
		return proveedor.getProveedor(id).getVO();
	}


	@Override
	public ArrayList<ProveedorVO> getProveedores() {
		return ProveedorEntity.getVOList(proveedor.getProveedores());
	}

}

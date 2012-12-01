package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import edu.uade.tpo.ingsist2.model.Proveedor;
import edu.uade.tpo.ingsist2.model.entities.ProveedorEntity;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;

@Stateless
public class AdministrarProveedoresBean implements AdministrarProveedores {



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

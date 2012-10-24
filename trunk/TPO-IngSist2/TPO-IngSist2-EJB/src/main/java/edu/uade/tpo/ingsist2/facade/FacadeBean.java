package edu.uade.tpo.ingsist2.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.uade.tpo.ingsist2.entities.Proveedor;
import edu.uade.tpo.ingsist2.entities.vo.ProveedorVO;

@Stateless
public class FacadeBean implements Facade {

	@PersistenceContext(name = "CPR")
	private EntityManager entityManager;

	public FacadeBean() {
	}

	@Override
	public void guardarProveedor(ProveedorVO p) {
		Proveedor pBean = new Proveedor();
		pBean.setVO(p);
		entityManager.merge(pBean);
	}

	@Override
	public void eliminarProveedor(int id) {
		Proveedor p = new Proveedor();
		p.setId(id);
		entityManager.remove(p);
	}

	@Override
	public ProveedorVO getProveedor(int id) {
		return ((Proveedor) entityManager
				.createQuery("FROM Proveedor where id =:idProve")
				.setParameter("idProve", id).getSingleResult()).getVO();
	}

	@Override
	public ArrayList<ProveedorVO> getProveedores() {
		return Proveedor.getVOList(getListaEntidades(Proveedor.class));
	}

	@Override
	public void solicitarPreciosRodamiento() {
		// TODO Auto-generated method stub

	}

	@Override
	public void recibirOrdenDeCompra() {
		// TODO Auto-generated method stub

	}

	@Override
	public void recibirRodamiento() {
		// TODO Auto-generated method stub

	}

	@Override
	public void recibirListaProveedor() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	private <T> List<T> getListaEntidades(Class<T> claseEntidad) {
		List<T> listaResultado = null;
		listaResultado = (List<T>) entityManager.createQuery(
				"FROM " + claseEntidad.getSimpleName()).getResultList();
		return listaResultado;
	}
}

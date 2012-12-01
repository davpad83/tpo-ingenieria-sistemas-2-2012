package edu.uade.tpo.ingsist2.model;

import java.util.ArrayList;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.model.entities.ProveedorEntity;

@Local
public interface Proveedor {

	public ProveedorEntity getProveedorPorNombre(String nombre);

	public void guardarProveedor(ProveedorEntity p);
	
	public void eliminarProveedor(int id);
	
	public ProveedorEntity getProveedor(int id);

	public ArrayList<ProveedorEntity> getProveedores();

	public ProveedorEntity getProveedorPorCuit(String cuit);

	public String getTiempoDeEntrega(int idItemLista);
}

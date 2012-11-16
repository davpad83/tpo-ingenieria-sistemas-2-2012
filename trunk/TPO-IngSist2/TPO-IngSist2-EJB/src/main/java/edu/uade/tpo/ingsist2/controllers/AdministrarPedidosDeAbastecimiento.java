package edu.uade.tpo.ingsist2.controllers;

import java.util.ArrayList;

import javax.ejb.Local;

import edu.uade.tpo.ingsist2.view.vo.PedidoAbastecimientoVO;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;

@Local
public interface AdministrarPedidosDeAbastecimiento {

	public void guardarPedido(PedidoAbastecimientoVO p);
	
	public void eliminarPedido(int id);
	
	public PedidoAbastecimientoVO getPedido(int id);

	public ArrayList<PedidoAbastecimientoVO> getPedidosActivos();
}

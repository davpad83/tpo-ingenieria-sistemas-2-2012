package edu.uade.tpo.ingsist2.view.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uade.tpo.ingsist2.entities.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.view.bd.BusinessDelegate;

/**
 * Servlet implementation class AdministrarProveedores
 */
public class AdministrarProveedores extends HttpServlet {
private static final long serialVersionUID = 1L;
    
	private static final int ADD = 0;
	private static final int EDIT = 1;
	private static final int DELETE = 2;
	
	private BusinessDelegate bd = new BusinessDelegate();
	
    public AdministrarProveedores() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = (String) request.getParameter("accion");
		int idProveedor = Integer.parseInt((String) request.getParameter("idProve"));
		String cuit = (String) request.getParameter("cuitProve");
		String nombre = (String) request.getParameter("nombreProve");

		switch(Integer.valueOf(accion)){
			case EDIT:
				bd.guardarProveedor(new ProveedorVO(idProveedor, cuit, nombre));
				break;
			case DELETE:
				bd.eliminarProveedor(idProveedor);
				break;
			default:
				break;
		}
		
		request.setAttribute("proveedores", bd.getProveedores());

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/home.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = (String) request.getParameter("accion");
		
		int idProveedor = Integer.parseInt((String) request.getParameter("idProve"));
		String cuit = (String) request.getParameter("cuitProve");
		String nombre = (String) request.getParameter("nombreProve");
		
		
		switch(Integer.valueOf(accion)){
			case EDIT:
				bd.guardarProveedor(new ProveedorVO(idProveedor, cuit, nombre));
				break;
			case ADD:
				bd.guardarProveedor(new ProveedorVO(idProveedor, cuit, nombre));			
				break;
			default:
				break;
		}
		
		request.setAttribute("servicios", bd.getProveedores());

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/home.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}

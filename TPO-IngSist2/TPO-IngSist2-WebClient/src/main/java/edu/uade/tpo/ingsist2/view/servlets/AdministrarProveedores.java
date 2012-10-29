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
	private static final String MENSAJE_ERROR = "SE PRODUJO UN ERROR EN EL SISTEMA, POR FAVOR CONTACTE A SU ADMINISTRADOR.\n\n";

	private BusinessDelegate bd = new BusinessDelegate();

	public AdministrarProveedores() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String accion = (String) request.getParameter("accion");
		String idProveedorString = (String) request
				.getParameter("idProve");
		int idProveedor = Integer.parseInt(idProveedorString);
		String cuit = (String) request.getParameter("cuitProve");
		String nombre = (String) request.getParameter("nombreProve");

		try {
			switch (Integer.valueOf(accion)) {
			case EDIT:
				ProveedorVO pvo = new ProveedorVO();
				pvo.setId(idProveedor);
				pvo.setCuit(cuit);
				pvo.setNombre(nombre);
				bd.guardarProveedor(pvo);
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
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			request.setAttribute("error", MENSAJE_ERROR + e.getMessage());
		} catch (IOException e) {
			request.setAttribute("error", MENSAJE_ERROR + e.getMessage());
		} catch (Exception e) {
			request.setAttribute("error", MENSAJE_ERROR + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String accion = (String) request.getParameter("accion");

		int idProveedor = Integer.parseInt((String) request
				.getParameter("idProve"));
		String cuit = (String) request.getParameter("cuitProve");
		String nombre = (String) request.getParameter("nombreProve");

		ProveedorVO pvo = new ProveedorVO();
		pvo.setId(idProveedor);
		pvo.setCuit(cuit);
		pvo.setNombre(nombre);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/home.jsp");
		try {
			switch (Integer.valueOf(accion)) {
			case EDIT:
				bd.guardarProveedor(pvo);
				break;
			case ADD:
				bd.guardarProveedor(pvo);
				break;
			default:
				break;
			}

			request.setAttribute("servicios", bd.getProveedores());

			dispatcher.forward(request, response);
		} catch (ServletException e) {
			request.setAttribute("error", MENSAJE_ERROR + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			request.setAttribute("error", MENSAJE_ERROR + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			request.setAttribute("error", MENSAJE_ERROR + e.getMessage());
			e.printStackTrace();
		} finally {
			dispatcher.forward(request, response);
		}
	}
}

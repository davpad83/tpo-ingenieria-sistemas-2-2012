package edu.uade.tpo.ingsist2.view.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uade.tpo.ingsist2.entities.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.view.bd.BusinessDelegate;

public class OpcionMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MENSAJE_ERROR = "SE PRODUJO UN ERROR EN EL SISTEMA, POR FAVOR CONTACTE A SU ADMINISTRADOR.\n\n";

	public OpcionMenu() {
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/home.jsp");
		try {
			BusinessDelegate bd = BusinessDelegate.getInstancia();

			String opcion = request.getParameter("opcion");

			HttpSession session = request.getSession(true);

			if (opcion.equals("adminProve")) {
				ArrayList<ProveedorVO> pvoList = bd.getProveedores();
				request.setAttribute("proveedores", pvoList);
				session.setAttribute("opcion", "adminProve");
			} else if (opcion.equals("adminRod")) {
				// session.setAttribute("opcion", "adminRod");
			} else if (opcion.equals("home")) {
				session.setAttribute("opcion", "home");
			}

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
//			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

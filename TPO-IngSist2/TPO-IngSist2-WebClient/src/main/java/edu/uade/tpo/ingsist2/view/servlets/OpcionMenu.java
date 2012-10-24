package edu.uade.tpo.ingsist2.view.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uade.tpo.ingsist2.view.bd.BusinessDelegate;


public class OpcionMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BusinessDelegate bd = new BusinessDelegate();

	public OpcionMenu() {
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String opcion = request.getParameter("opcion");

		HttpSession session = request.getSession(true);

		if (opcion.equals("adminProve")) {
			request.setAttribute("proveedores", bd.getProveedores());
			session.setAttribute("opcion", "adminProve");
		} else if (opcion.equals("adminRod")) {
//			session.setAttribute("opcion", "adminRod");
		} else if (opcion.equals("home")) {
			session.setAttribute("opcion", "home");
		}

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

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

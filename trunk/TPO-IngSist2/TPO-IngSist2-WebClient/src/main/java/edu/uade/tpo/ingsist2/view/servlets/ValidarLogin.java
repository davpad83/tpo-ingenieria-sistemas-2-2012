package edu.uade.tpo.ingsist2.view.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidarLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ValidarLogin() {
//        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = (String) request.getParameter("login");
		String password = (String) request.getParameter("password");
		
		request.getSession(true).setAttribute("login", login);
		
		if(login.toLowerCase().equals("admin") && password.equals("admin")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			request.setAttribute("loginIncorrecto", "true");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				
	}

}

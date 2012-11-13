package edu.uade.tpo.ingsist2.view.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uade.tpo.ingsist2.view.bd.BusinessDelegate;
import edu.uade.tpo.ingsist2.view.vo.ProveedorVO;
import edu.uade.tpo.ingsist2.view.vo.RodamientoVO;

/**
 * Servlet implementation class AdministrarProveedores
 */
public class AdministrarRodamientos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int ADD = 0;
	private static final int EDIT = 1;
	private static final int DELETE = 2;
	private static final String MENSAJE_ERROR = "SE PRODUJO UN ERROR EN EL SISTEMA, POR FAVOR CONTACTE A SU ADMINISTRADOR.\n\n";

	private BusinessDelegate bd = new BusinessDelegate();

	public AdministrarRodamientos() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String accion = (String) request.getParameter("accion");
		String idRodamientoString = (String) request
				.getParameter("idRod");
		int idRodamiento = Integer.parseInt(idRodamientoString);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/home.jsp");

		try {
			switch (Integer.valueOf(accion)) {
			case EDIT:
				request.setAttribute("rodEdit", bd.getRodamiento(idRodamiento));
				break;
			case DELETE:
				bd.eliminarRodamiento(idRodamiento);
				break;
			default:
				break;
			}

			request.setAttribute("rodamientos", bd.getRodamientos());

			dispatcher.forward(request, response);
		} catch (ServletException e) {
			request.setAttribute("error", MENSAJE_ERROR + e.getMessage());
			dispatcher.forward(request, response);
		} catch (IOException e) {
			request.setAttribute("error", MENSAJE_ERROR + e.getMessage());
			dispatcher.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", MENSAJE_ERROR + e.getMessage());
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String accion = (String) request.getParameter("accion");

		String idRodamiento = (String) request
				.getParameter("idRod");
		
		String codigoSKF = (String) request.getParameter("codSKF");
		String marca = (String) request.getParameter("marcaRod");
		String pais = (String) request.getParameter("paisRod");
		String stock = (String) request.getParameter("stockRod");

		RodamientoVO rvo = new RodamientoVO();
		if(idRodamiento!=null && !idRodamiento.isEmpty())
			rvo.setId(Integer.parseInt(idRodamiento));
		rvo.setCodigoSKF(codigoSKF);
		rvo.setMarca(marca);
		rvo.setPais(pais);
		if( stock ==null || stock.isEmpty())
			rvo.setStock(0);
		else
			rvo.setStock(Integer.parseInt(stock));
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/home.jsp");
		try {
			switch (Integer.valueOf(accion)) {
			case EDIT:
				bd.guardarRodamiento(rvo);
				break;
			case ADD:
				bd.guardarRodamiento(rvo);
				break;
			default:
				break;
			}

			request.setAttribute("rodamientos", bd.getRodamientos());

			dispatcher.forward(request, response);
		} catch (ServletException e) {
			request.setAttribute("error", MENSAJE_ERROR + e.getMessage());
			e.printStackTrace();
			dispatcher.forward(request, response);
		} catch (IOException e) {
			request.setAttribute("error", MENSAJE_ERROR + e.getMessage());
			e.printStackTrace();
			dispatcher.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", MENSAJE_ERROR + e.getMessage());
			e.printStackTrace();
			dispatcher.forward(request, response);
		} 
	}
}

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VehicleRegHandler
 */
public class VehicleRegHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String button = (String) req.getAttribute("register_v");
		if(button.equals("register_v")){
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/register_accident.html");
			rd.forward(req, resp);
		}
	}
	
}

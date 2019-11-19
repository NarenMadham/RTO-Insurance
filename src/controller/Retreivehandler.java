package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Retreivehandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String button = (String) req.getAttribute("retreive");
		if(button.equals("retreive")){
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/select_accident_retreive.html");
			rd.forward(req, resp);
		}
	}
	
}

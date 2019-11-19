package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyDetailsHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String button = (String) req.getAttribute("Show_Details");
		if(button.equals("Show_Details")){
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/modify.jsp");
			rd.forward(req, resp);
		}
	}
	
}

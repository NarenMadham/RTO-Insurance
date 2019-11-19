package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String button = (String) req.getAttribute("modify");
		if(button.equals("modify")){
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/select_accident_modify.jsp");
			rd.forward(req, resp);
		}
	}
	
}

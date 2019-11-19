package controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crypto.Crypto;


public class RTOLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String iv = "lsouqteimvnxbzfd";
	final String secretKey = "Entrepreneur has a decent family";
		
	@SuppressWarnings("null")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("username");
		String password = req.getParameter("pwd");
		Crypto crypto = new Crypto();
		String pas = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","geetha");
			PreparedStatement pst = con.prepareStatement("select pwd from rto where username = ?");
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				String abc = rs.getString(1);
				resp.getWriter().println(abc);
				pas = crypto.decrypt(abc, iv, secretKey);
			}
			if(pas.equals(password)) {
				Cookie loginCookie = new Cookie("rto_cookie", name);
				loginCookie.setMaxAge(24*60*60);
				resp.addCookie(loginCookie);
				resp.getWriter().println("Login Successful!!!!  :)");
				RequestDispatcher rd = req.getRequestDispatcher("RTOProfile.html");
				rd.forward(req, resp); 
				con.close();
			}else {
				resp.getWriter().println("Login Failed! :(");
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}

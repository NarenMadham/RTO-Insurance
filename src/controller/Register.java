package controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String vehicle_no = req.getParameter("vehicle_no");
		String place = req.getParameter("place");
		String damage = req.getParameter("damage");
		int id = 0;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","geetha");
			java.sql.Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery("select accidentGenerator.nextval from dual");
			while(result.next()) {
				id = result.getInt(1);
			}
			PreparedStatement pst = con.prepareStatement("insert into accident_info values ?,?,?,?");
			pst.setInt(1, id);
			pst.setString(2, vehicle_no);
			pst.setString(3, place);
			pst.setString(4, damage);
			int i = pst.executeUpdate();
			if(i>0) {
				resp.getWriter().println("Accident Registered Successfully!");
				con.close();
			}else {
				resp.getWriter().println("Failed to register Accident!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

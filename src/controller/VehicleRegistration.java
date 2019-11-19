package controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VehicleRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String vehicle_no = req.getParameter("vehicle_no");
		String vehicle_model = req.getParameter("vehicle_model");
		String vehicle_owner = req.getParameter("vehicle_owner");
		Long owner_phone = Long.parseLong(req.getParameter("owner_phone"));
		Long alt_phone = Long.parseLong(req.getParameter("alt_phone"));
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String branch = req.getParameter("branch");
		String ins_company = req.getParameter("ins_company");
		Double ins_paid = Double.parseDouble(req.getParameter("ins_paid"));
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","geetha");
			java.sql.Statement stmt = con.createStatement();
			PreparedStatement pst = con.prepareStatement("insert into vehicle values ?,?,?,?,?,?,?,?,?,?");
			pst.setString(1, vehicle_no);
			pst.setString(2, vehicle_model);
			pst.setString(3, vehicle_owner);
			pst.setLong(4, owner_phone);
			pst.setLong(5, alt_phone);
			pst.setString(6, address);
			pst.setString(7, email);
			pst.setString(8, branch);
			pst.setString(9, ins_company);
			pst.setDouble(10, ins_paid);
			int i = pst.executeUpdate();
			if(i>0) {
				resp.getWriter().println("Vehicle Registered Successfully!");
				con.close();
			}else {
				resp.getWriter().println("Failed to register Accident!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

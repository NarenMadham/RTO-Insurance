<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RTO and Insurance Integration Services</title>
</head>
<body>
<h1 style="text-align: center;">Accident Details</h1>
<p style="text-align: center;align-content: center;">
<%
Class.forName("oracle.jdbc.OracleDriver");
java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","geetha");
String vehicle_no = (String) session.getAttribute("vehicle");
PreparedStatement pst = con.prepareStatement("select * from accident_info where vehicle_no = ?");
pst.setString(1, vehicle_no);
ResultSet rs = pst.executeQuery();
while(rs.next()){
	%>
	<h2 style="align-content: center;">Vehicle Number : <%=vehicle_no %></h2><br/>
	Vehicle Number : <input type="text" value = "<%=rs.getString(2) %>"/><br/>
	Place : <input type="text" value = "<%=rs.getString(3) %>"/><br/>
	Damage done to the Vehicle : <input type="text" value = "<%=rs.getString(4) %>"/>
	<%
}
con.close();
%>
</p>
</body>
</html>
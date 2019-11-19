<%@page import="java.security.interfaces.RSAKey"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RTO and Insurance Integration Services</title>
</head>
<body>
<h1 style="align-content: center;">RTO and Insurance Integration Services</h1>
<h2 style="align-content: center;">List of accidents insured in your company</h2>
<%
Class.forName("oracle.jdbc.OracleDriver");
java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","geetha");
java.sql.Statement stmt = con.createStatement();
String insurance_name = (String) session.getAttribute("insurance");
ResultSet result = stmt.executeQuery("select * from insured where vehicle_no = (select vehicle_no from connection where insurance_company = '"+insurance_name+"')");
while(result.next()){
	session.setAttribute("vehicle_no", result.getString(2));
	%>
	<hr>
	
	Insured Id : <%=result.getString(1)%><br/>
	Vehicle No : <%=result.getString(2)%><br/>
	Amount Details : <%=result.getString(3)%><br/>
	Start Date : <%=result.getString(4)%><br/>
	Expiry Date : <%=result.getString(5)%><br/><br/>
	<form action="/showDetailsHandler">
	<button type="submit" name = "Show_Details" value = "Show_Details">Show Accident Details</button>
	</form>
	<hr>
	<%
}
%>
</body>
</html>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RTO and Insurance Integration Services</title>
</head>
<body>
<h1>Select the accident that you want to modify</h1>
<%
Class.forName("oracle.jdbc.OracleDriver");
java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","geetha");
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select vehicle_no,place,damage from accident_info");
while(rs.next()){
%>
<hr>
<h2>Vehicle Number : <%=rs.getString(1) %></h2>
Place of Accident : <%=rs.getString(2) %>
Damage to the vehicle : <%=rs.getString(3) %>
<hr>
<%
HttpSession hs = request.getSession();
hs.setAttribute("vehicle",rs.getString(1));
%>
<form action="./showDetailsHandler" method = "get">
<button type="submit" name = "Show_Details" value="Show_Details">Show Details</button>
</form>
<%
}
con.close();
%>
</body>
</html>
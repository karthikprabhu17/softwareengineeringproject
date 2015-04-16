<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@ page import ="java.io.*" %>
<%@ page import ="com.mysql.jdbc.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
java.sql.Connection con=null;
java.sql.Statement s=null;
java.sql.Statement s_sql=null;
java.sql.PreparedStatement p_sql=null;
java.sql.PreparedStatement comm_sql=null;
java.sql.PreparedStatement browse_sql=null;

try
		{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/city","root","root");
		}

catch(ClassNotFoundException ex)
{
	out.println(ex);
	ex.printStackTrace();
}

%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
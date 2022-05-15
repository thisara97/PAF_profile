<%@page import="model.profile" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
//Delete Profile----------------------------------
	if (request.getParameter("uid") != null)
	{
		profile profileObj = new profile();
	String stsMsg = profileObj.deleteProfile(request.getParameter("uid"));
	session.setAttribute("statusMsg", stsMsg);
	}   
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ElectroGrid-Profile</title>
</head>
<body>
<h1>PROFILE DATA</h1>
<% 
 profile profileObj = new profile();
 out.print(profileObj.readProfile());
 %>
</body>
</html>
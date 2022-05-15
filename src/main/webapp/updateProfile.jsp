<%@page import="model.profile" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%    
    //update part
 if (request.getParameter("uid") != null)
 {
 profile profileObj = new profile();
 String stsMsg = profileObj.updateProfile(request.getParameter("uid"),

 request.getParameter("name"),
 request.getParameter("address"),
 request.getParameter("email"),
 request.getParameter("contact"));
 session.setAttribute("statusMsg", stsMsg);
 }
%>
<!DOCTYPE html>
<html>
<style>
input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}


div {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<head>
<meta charset="ISO-8859-1">
<title>ElectroGrid-Profile</title>
</head>
<body>
<h1>Profile Management</h1>
<h3><u> Update Profile </u></h3>

<div>
<form method="post" action="updateProfile.jsp">
 
 UID:<input name="uid" type="text"><br><br> 
 NAME: <input name="name" type="text"><br><br> 
 ADDRESS: <input name="address" type="text"><br><br>
 EMAIL: <input name="email" type="text"><br><br> 
 CONTACT:<input name="contact" type="text"><br><br> 

 
 <input name="btnSubmit" type="submit" value="update"><br><br>
</form>
<a href="viewProfile.jsp" class="button">View Details</a>
</div>
<%
 out.print(session.getAttribute("statusMsg"));
%>


<br>
<%
 profile profileObj = new profile();
 out.print(profileObj.readProfile());
%>
</body>
</html>
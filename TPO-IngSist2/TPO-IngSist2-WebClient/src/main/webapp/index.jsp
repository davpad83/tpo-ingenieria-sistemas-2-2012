<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.*" %>
<%@page import="java.text.DateFormat" %>
<% DateFormat dateFormatter = DateFormat.getDateTimeInstance(); %>
<% String loginInvalido = (String) request.getAttribute("loginInvalido"); %>

<%@page import="java.util.Date"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="javascripts.js"></script>

<link rel=STYLESHEET href="stylesheets/estilos.css" type="text/css">
<title>Administracion Casa Central de Rodamientos</title>
</head>

<body>
<div class="pageHeader">
    	<div id="pageHeaderTopBar"><div class="wrap"><div class="content"><%=dateFormatter.format(new Date())%></div></div></div>
    	
        <div id="loginPageHeaderTitle" class="pageHeaderTitle">Administracion Casa Central de Rodamientos</div>
    </div>
<div id="pageLoginMain">
	<div class="pageLoginForm">
		<h3 style="color: red;">Login Form</h3>
		<p>Ingrese usuario y contraseña para ingresar al
		sistema</p>
		
		<form name="loginForm" action="ValidarLogin" method="post">
			<table class="formTable">
					<tr>
						<td><label>Usuario: </label></td>
						<td><input type="text" value="admin" name="login"></td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><input type="password" value="admin" name="password"></td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td></td>
						<td align="right"><input style="margin-top: 15px; width: 80px;" type="submit" value="Login" name="action"/></td>
					</tr>
					<% if (loginInvalido != null && !loginInvalido.isEmpty() && loginInvalido.equals("true")){ %>
					<tr>
					<td>				
						<p style="color:red">Usuario o contraseña invalidos, intentelo nuevamente.</p>
						</td>
					</tr>
					<% } %>
			</table>
		</form>
	</div>
	<div class="clear"></div>
</div>
</body>
</html>


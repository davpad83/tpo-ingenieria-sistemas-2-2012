<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="edu.uade.tpo.ingsist2.entities.vo.*" %>
<%@page import="java.util.*" %>
<%@page import="java.text.DateFormat" %>
<% String login = (String) session.getAttribute("login"); %>
<% String opcion = (String) session.getAttribute("opcion"); %>
<% DateFormat dateFormatter = DateFormat.getDateTimeInstance(); %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title>Administracion Casa Central de Rodamientos</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>

<script type="text/javascript" src="javascripts.js"></script>
<link rel=STYLESHEET href="estilos.css" type="text/css">

</head>
<body>
<%ArrayList<ProveedorVO> proveedores = (ArrayList<ProveedorVO>) request.getAttribute("proveedores");
//ArrayList<RodamientoVO> rodamientos = (ArrayList<RodamientoVO>) request.getAttribute("rodamientos"); %>

<div class="pageMain">
    <div class="pageHeader">
    	<div id="pageHeaderTopBar"><div class="wrap"><div class="content"><%=dateFormatter.format(new Date())%><br>Logueado como <%=login%></div></div></div>
    	 
        <div class="pageHeaderTitle">Sistema de Requerimientos</div>
    </div>
    <div class="pageBodyLeft">
        <div class="pageBodyMenu">
        	<a href="OpcionMenu?opcion=home">Home</a>
            <a href="OpcionMenu?opcion=adminCate">Administrar Proveedores</a>
        	<a href="OpcionMenu?opcion=adminServ">Administrar Rodamientos</a>
        </div>
    </div>
    <div class="pageBodyRight">
        <div class="pageBodyContent" id="dynamic_content">
    	<% if (opcion == null || opcion.isEmpty() || opcion.equals("home")) {%>
	            <p>Bienvenido <b><%=login%>!</b><br><br>
	            Este sistema te permitira administrar proveedores y rodamientos.</p>
    	<%} else if(opcion.equals("adminProve")){ %>
	    	<h3>Administrar Proveedores</h3>
	    	<div class="formTable">
	    		<% ProveedorVO proveEdit = (ProveedorVO) request.getAttribute("proveEdit"); %>
	    		<% if(proveEdit!=null && !proveEdit.getNombre().isEmpty()) {%>
	    			<form action="AdministrarProveedores?accion=1" method="post">
			    		<table>
				    		<tr>
				    			<td><label>Id:</label></td>
				    			<td><input name="idProve" value="<%=proveEdit.getId()%>" type="text"></input></td>
				  			</tr>
							<tr>
				  				<td><label>CUIT:</label></td>
				    			<td><input name="cuitProve" value="<%=proveEdit.getCuit()%>" type="text"></input></td>
				  			</tr>
				  			<tr>
				  				<td><label>Nombre: </label></td>
				  				<td><input name="nombreProve" value="<%=proveEdit.getNombre()%>" type="text"></input></td>
							</tr>
				  			<tr>
				  				<td></td>
								<td align="right"><input type="submit" value="Guardar cambios"></input></td>
				    		</tr>	
				    	</table>
				    </form>
			    <%} else { %>
			    	<form action="AdministrarProveedores?accion=0" method="post">
				    	<table>
				    		<tr>
				    			<td><label>Id:</label></td>
				    			<td><input name="idProve" type="text"></input></td>
				  			</tr>
							<tr>
				  				<td><label>CUIT:</label></td>
				    			<td><input name="cuitProve" type="text"></input></td>
				  			</tr>
				  			<tr>
				  				<td><label>Nombre: </label></td>
				  				<td><input name="nombreProve" type="text"></input></td>
							</tr>
				  			<tr>
				  				<td></td>
								<td align="right"><input type="submit" value="Agregar"></input></td>
				    		</tr>	
				    	</table>
			    	</form>
		    	<% } %>
	    	</div>	
	    	<br>
	    	<br>
	    	<div class="pageDataTable">
			    	<table>
						<tr>
							<th>Id</th>
							<th>CUIT</th>
							<th>Nombre</th>
							<th>Acciones</th>
						</tr>
						<% if(proveedores != null && !proveedores.isEmpty()){ %>
				    		<% for(ProveedorVO p: proveedores){ %>
				    		<tr>
				    			<td><%=p.getId() %></td>
				    			<td><%=p.getCuit() %></td>
				    			<td><%=p.getNombre()%></td>
				    			<td>
				    				<a href="AdministrarProveedores?idProve=<%=p.getNombre()%>&accion=1"><img class="imgInput" src="imgs/edit.png" alt="Edit"/></a>
				    				<a href="AdministrarProveedores?idProve=<%=p.getNombre()%>&accion=2"><img class="imgInput" src="imgs/delete.png" alt="Delete"/></a>
				    			</td>
				  			</tr>
				  			<%} 
			  			}%>
			    	</table>
	    	</div>
    	<%} else if(opcion.equals("adminRod")){ %>
	    	<h3>Administrar Rodamientos</h3>
	    	
	    	<div class="formTable">
	    		<% RodamientoVO rod = (RodamientoVO) request.getAttribute("adminRod"); %>
	    		<% if(rod!=null && !rod.getNombre().isEmpty()) {%>
	    			<form action="AdministrarServicios?accion=1" method="post">
			    		<table>
				    		<tr>
				    			<td><label>CodigoSKF:</label></td>
				    			<td><input name="codSKF" value="<%=rod.getCodigoSKF()%>" type="text"></input></td>
				  			</tr>
							<tr>
				  				<td><label>Pais:</label></td>
				    			<td><input name="paisRod" value="<%=rod.getPais()%>" type="text"></input></td>
				  			</tr>
				  			<tr>
				  				<td><label>Marca: </label></td>
				    			<td><input name="marcaRod" value="<%=rod.getMarca()%>" type="text"></input></td>
							</tr>
							<tr>
				  				<td><label>Stock: </label></td>
				    			<td><label><%=rod.getStock()%></label></td>
							</tr>
				  			<tr>
				  				<td></td>
								<td align="right"><input type="submit" value="Guardar cambios"></input></td>
				    		</tr>	
				    	</table>
				    </form>
			    <%} else { %>
			    	<form action="AdministrarRodamientos?accion=0" method="post">
				    	<table>
				    		<tr>
				    			<td><label>CodigoSKF:</label></td>
				    			<td><input name="codSKF" type="text"></input></td>
				  			</tr>
							<tr>
				  				<td><label>Pais:</label></td>
				    			<td><input name="paisRod" type="text"></input></td>
				  			</tr>
				  			<tr>
				  				<td><label>Marca: </label></td>
				    			<td><input name="marcaRod" type="text"></input></td>
							</tr>
							<tr>
				  				<td><label>Stock: </label></td>
				    			<td><input name="stockRod" type="text"></input></td>
							</tr>
							<tr>
				  				<td></td>
								<td align="right"><input type="submit" value="Agregar"></input></td>
				    		</tr>
				    	</table>
			    	</form>
		    	<% } %>
	    	</div>	
	    	<br>
	    	<br>
	    	<div class="pageDataTable">
			    	<table>
						<tr>
							<th>CodigoSKF</th>
							<th>Pais</th>
							<th>Marca</th>
							<th>Stock</th>
						</tr>
						<% if(rodamientos != null && !rodamientos.isEmpty()){ %>
				    		<% for(RodamientoVO s: rodamientos){ %>
				    		<tr>
				    			<td><%=s.getNombre() %></td>
				    			<td><%=s.getEstado() %></td>
				    			<td><%=s.getDescripcion()%></td>
				    			<td>
				    				<a href="AdministrarServicios?idServ=<%=s.getNombre()%>&accion=1"><img class="imgInput" src="imgs/edit.png" alt="Edit"/></a>
				    				<a href="AdministrarServicios?idServ=<%=s.getNombre()%>&accion=2"><img class="imgInput" src="imgs/delete.png" alt="Delete"/></a>
				    			</td>
				  			</tr>
				  			<%} 
			  			}%>
			    	</table>
	    	</div>
    	<%} else { %>
	    	<img alt="En construccion" src="imgs/construccion.gif"/>
	    <%}%>
        </div>
    </div>
    <div class="clear"></div>
</div>

<div class="pageFooter">
    <div class="pageFooterContent">
        Created by Matias Favale®
    </div>
</div>

</body>
</html>



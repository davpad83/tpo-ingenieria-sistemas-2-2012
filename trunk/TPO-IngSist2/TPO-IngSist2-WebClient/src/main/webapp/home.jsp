<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="edu.uade.tpo.ingsist2.entities.vo.ProveedorVO" %>
<%@page import="edu.uade.tpo.ingsist2.entities.vo.RodamientoVO" %>
<%@page import="java.util.*" %>
<%@page import="java.text.DateFormat" %>
<% String login = (String) session.getAttribute("login"); %>
<% String opcion = (String) session.getAttribute("opcion"); %>
<% DateFormat dateFormatter = DateFormat.getDateTimeInstance(); %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>

<title>Administracion Casa Central de Rodamientos</title>

<link rel=STYLESHEET href="stylesheets/estilos.css" type="text/css">

</head>
<body>
<%ArrayList<ProveedorVO> proveedores = (ArrayList<ProveedorVO>) request.getAttribute("proveedores");
ArrayList<RodamientoVO> rodamientos = (ArrayList<RodamientoVO>) request.getAttribute("rodamientos"); 
String error = (String) request.getAttribute("error"); 
if (error == null) error = "";%>

<div class="pageMain">
    <div class="pageHeader">
    	<div id="pageHeaderTopBar"><div class="wrap"><div class="content"><%=dateFormatter.format(new Date())%><br>Logueado como <%=login%></div></div></div>
    	 
        <div class="pageHeaderTitle">Administracion Casa Central</div>
    </div>
    <div class="pageBodyLeft">
        <div class="pageBodyMenu">
        	<a href="OpcionMenu?opcion=home">Home</a>
            <a href="OpcionMenu?opcion=adminProve">Administrar Proveedores</a>
        	<a href="OpcionMenu?opcion=adminRod">Administrar Rodamientos</a>
        </div>
    </div>
    <div class="pageBodyRight">
        <div class="pageBodyContent" id="dynamic_content">
        <% if(error != null && error.isEmpty()) {%>
	    	<% if (opcion == null || opcion.isEmpty() || opcion.equals("home")) {%>
		            <p>Bienvenido <b><%=login%>!</b><br><br>
		            Este sistema te permitira administrar proveedores y rodamientos.</p>
	    	<%} else if(opcion.equals("adminProve")){ %>
		    	<h3>Administrar Proveedores</h3>
		    	<div class="formTable">
		    		<% ProveedorVO proveEdit = (ProveedorVO) request.getAttribute("proveEdit"); %>
		    		<% if(proveEdit!=null) {%>
		    			<form class="dataForm" action="AdministrarProveedores?accion=1" method="post">
				    		<table>
					    		<tr>
					    			<td><label>Id:</label></td>
					    			<td><input name="idProve" readonly="readonly" value="<%=proveEdit.getId()%>" ></input></td>
					  			</tr>
								<tr>
					  				<td><label >CUIT:</label></td>
					    			<td><input class="required" name="cuitProve" value="<%=proveEdit.getCuit()%>" type="text"></input></td>
					    			<td><span class="msjRequired"></span></td>
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
				    	<form class="dataForm" action="AdministrarProveedores?accion=0" method="post">
					    	<table>
								<tr>
					  				<td><label>CUIT:</label></td>
					    			<td><input class="required" name="cuitProve" type="text"></input></td>
					    			<td><span class="msjRequired"></span></td>
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
					    		<% for(ProveedorVO p: proveedores) { %>
					    		<tr>
					    			<td><%=p.getId() %></td>
					    			<td><%=p.getCuit() %></td>
					    			<td><%=p.getNombre()%></td>
					    			<td>
					    				<a href="AdministrarProveedores?idProve=<%=p.getId()%>&accion=1"><img class="imgInput" src="imgs/edit.png" alt="Edit"/></a>
					    				<a href="AdministrarProveedores?idProve=<%=p.getId()%>&accion=2"><img class="imgInput" src="imgs/delete.png" alt="Delete"/></a>
					    			</td>
					  			</tr>
					  			<%} 
				  			}%>
				    	</table>
		    	</div>
	    	<%} else if(opcion.equals("adminRod")){ %>
		    	<h3>Administrar Rodamientos</h3>
		    	
		    	<div class="formTable">
		    		<% RodamientoVO rodEdit = (RodamientoVO) request.getAttribute("rodEdit"); %>
		    		<% if(rodEdit!=null) {%>
		    			<form class="dataForm" action="AdministrarRodamientos?accion=1" method="post">
				    		<table>
				    			<tr>
					    			<td><label>Id:</label></td>
					    			<td><input name="idRod" readonly="readonly" value="<%=rodEdit.getId()%>"></input></td>
					    		</tr>
					    		<tr>
					    			<td><label>CodigoSKF:</label></td>
					    			<td><input class="required" name="codSKF" value="<%=rodEdit.getCodigoSKF()%>" type="text"></input></td>
					    			<td><span class="msjRequired"></span></td>
					  			</tr>
					  			<tr>
					  				<td><label>Marca: </label></td>
					    			<td><input class="required" name="marcaRod" value="<%=rodEdit.getMarca()%>" type="text"></input></td>
					    			<td><span class="msjRequired"></span></td>
								</tr>
								<tr>
					  				<td><label>Pais:</label></td>
					    			<td><input class="required" name="paisRod" value="<%=rodEdit.getPais()%>" type="text"></input></td>
					    			<td><span class="msjRequired"></span></td>
					  			</tr>
					  			<tr>
					  				<td><label>Caracteristica:</label></td>
					    			<td><input class="required" name="carRod" value="<%=rodEdit.getCaracteristica()%>" type="text"></input></td>
					    			<td><span class="msjRequired"></span></td>
					  			</tr>
								<tr>
					  				<td><label>Stock: </label></td>
					    			<td><input name="stockRod" type="text" value="<%=rodEdit.getStock()%>"></input></td>
								</tr>
					  			<tr>
					  				<td></td>
									<td align="right"><input type="submit" value="Guardar cambios"></input></td>
					    		</tr>	
					    	</table>
					    </form>
				    <%} else { %>
				    	<form class="dataForm" action="AdministrarRodamientos?accion=0" method="post">
					    	<table>
					    		<tr>
					    			<td><label>CodigoSKF:</label></td>
					    			<td><input class="required" name="codSKF" type="text"></input></td>
					    			<td><span class="msjRequired"></span></td>
					  			</tr>
					  			<tr>
					  				<td><label>Marca: </label></td>
					    			<td><input class="required" name="marcaRod" type="text"></input></td>
					    			<td><span class="msjRequired"></span></td>
								</tr>
								<tr>
					  				<td><label>Pais:</label></td>
					    			<td><input class="required" name="paisRod" type="text"></input></td>
					    			<td><span class="msjRequired"></span></td>
					  			</tr>
					  			<tr>
					  				<td><label>Caracteristica:</label></td>
					    			<td><input class="required" name="carRod" type="text"></input></td>
					    			<td><span class="msjRequired"></span></td>
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
								<th>Id</th>
								<th>CodigoSKF</th>
								<th>Marca</th>
								<th>Pais</th>
								<th>Caracteristica</th>
								<th>Stock</th>
								<th>Acciones</th>
							</tr>
							<% if(rodamientos != null && !rodamientos.isEmpty()){ %>
					    		<% for(RodamientoVO r: rodamientos){ %>
					    		<tr>
					    			<td><%=r.getId() %></td>
					    			<td><%=r.getCodigoSKF() %></td>
					    			<td><%=r.getPais() %></td>
					    			<td><%=r.getCaracteristica() %></td>
					    			<td><%=r.getMarca()%></td>
					    			<td><%=r.getStock()%></td>
					    			<td>
					    				<a href="AdministrarRodamientos?idRod=<%=r.getId()%>&accion=1"><img class="imgInput" src="imgs/edit.png" alt="Edit"/></a>
					    				<a href="AdministrarRodamientos?idRod=<%=r.getId()%>&accion=2"><img class="imgInput" src="imgs/delete.png" alt="Delete"/></a>
					    			</td>
					  			</tr>
					  			<%} 
				  			}%>
				    	</table>
		    	</div>
	    	<%} else { %>
		    	<img alt="En construccion" src="imgs/construccion.gif"/>
		    <%}%>
		<% } else if(error!= null && !error.isEmpty()) {%>
			<p align="left" style="color: Red"><%=error%></p>
		<% } %>
        </div>
    </div>
    <div class="clear"></div>
</div>

<div class="pageFooter">
    <div class="pageFooterContent">
        Created by Matias Favale®
    </div>
</div>

<script type="text/javascript">
	$('.dataForm').submit(function(event){
		var required = $(this).find('.required');
		required.each(function(){ 
			if($(this).val() == ''){
				$('.msjRequired').text("* campo requerido").show().fadeOut(5000);
				event.preventDefault();
			}
		});
	});

	function validateBlank(form){
	/*	v ar required = $(form).find('.required');
		required.each(function(){ 
			if($(this).val() == ''){
				$(form).find('.msjRequired').show();
				form.preventDefault();
			}
		}); */
	} 
	
</script>

</body>
</html>



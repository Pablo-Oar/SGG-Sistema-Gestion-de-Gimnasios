<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="entidades.Usuario"%>
<% Usuario usuario = ((Usuario)session.getAttribute("usuario"));%>
 
<nav class=" navbar navbar-expand-sm bg-dark navbar-dark sticky-top">
<!-- SI ES ADMINISTRADOR MUESTRO: -->
 <%if (usuario.getRol().getId()== 1){ %>
	<!-- Logo -->
  	<a class="navbar-brand " href="${pageContext.request.contextPath}/usuarioAdministrador.jsp" data-toggle="tooltip" title="Home"><i class="fas fa-house-user" ></i></a>
	<!-- Secciones -->
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
      		<li class="nav-item">	
      			<a  class="nav-link" href="${pageContext.request.contextPath}/paginas/admin/cliente/listadoCliente.jsp">
      				<button type="button" class="btn btn-outline-light font-weight-bolder">Clientes</button>
      			</a>
      		</li>
      		<li class="nav-item">
	      		<a  class="nav-link" href="${pageContext.request.contextPath}/ServletControlador?accion=irMembresia">
      				<button type="button" class="btn btn-outline-light font-weight-bolder">Membresias</button>
      			</a>
      		</li>
      		
      		<li class="nav-item">	
      			<a class="nav-link" href="${pageContext.request.contextPath}/ServletProductos?accion=irProducto">
      				<button type="button" class="btn btn-outline-light font-weight-bolder">Productos</button>
      			</a>
      		</li>
      		<li class="nav-item">
      		<a class="nav-link" href="${pageContext.request.contextPath}/ServletProductos?accion=irCategoria">
      				<button type="button" class="btn btn-outline-light font-weight-bolder">Categorias</button>
      			</a>
      		</li>
      		<li class="nav-item">
      			<a  class="nav-link" href="${pageContext.request.contextPath}/ServletProductos?accion=irVentas">
      				<button type="button" class="btn btn-outline-light font-weight-bolder">Registrar Ventas</button>
      			</a>
      		</li>
      		<li class="nav-item">
      			<a  class="nav-link" href="#">
      				<button type="button" class="btn btn-outline-light font-weight-bolder">Profesores</button>
      			</a>
      		</li>
      		<li class="nav-item">
      			<a class="nav-link" href="#">
      				<button type="button" class="btn btn-outline-light font-weight-bolder">Horarios</button>
      			</a>
      		</li>
      		<li class="nav-item">
      			<a class="nav-link" href="#">
      				<button type="button" class="btn btn-outline-light font-weight-bolder">Rutinas</button>
      			</a>
      		</li>
		</ul>
	</div>
	<!-- Usuario y Cerrar Sesion -->
  	<div class="dropdown col-sm-2">
  		<button type="button" class="btn btn-dark dropdown-toggle font-weight-bolder" data-toggle="dropdown">
  		 	 Admin
  		</button>
  		<div class=" bg-dark dropdown-menu text-center" aria-labelledby="dropdownMenuButton">
   			<a class="dropdown-item" href="#"> 
   				<img alt="60" width="60" src="img/icone-utilisateur-vert.png"> 
   			</a>
    		<div class="dropdown-divider"></div><!-- Linea divisoria -->
   			<a  href="${pageContext.request.contextPath}/index.jsp">
      			<button type="button"  class="btn btn-outline font-weight-bolder text-light" data-toggle="tooltip" title="Usuarios" ><i class="fas fa-user-alt" aria-hidden="true"></i> 
      				Usuarios 
      			</button>
      		</a>
   			<a  href="${pageContext.request.contextPath}/index.jsp">
      			<button type="button" class="btn btn-outline font-weight-bolder text-light" data-toggle="tooltip" title="Logout" ><i class="fa fa-sign-out" aria-hidden="true"></i> 
      				Cerrar Sesion 
      			</button>
      		</a>
  		</div>
	</div>
	<%} %>
	<!-- SI ES VENDEDOR MUESTRO: -->
    <%if (usuario.getRol().getId()== 2){ %>
	<!-- Logo -->
  	<a class="navbar-brand " href="${pageContext.request.contextPath}/usuarioVendedor.jsp" data-toggle="tooltip" title="Home"><i class="fas fa-house-user" ></i></a>
    <!-- Secciones -->
    <div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
    		<li class="nav-item">	
      			<a class="nav-link" href="${pageContext.request.contextPath}/ServletProductos?accion=irProducto">
      				<button type="button" class="btn btn-outline-light font-weight-bolder">Productos</button>
      			</a>
     		</li>
     		<li class="nav-item">
      			<a class="nav-link" href="${pageContext.request.contextPath}/ServletProductos?accion=irCategoria">
      				<button type="button" class="btn btn-outline-light font-weight-bolder">Categorias</button>
      			</a>
      		</li>
   			<li class="nav-item">
   				<a  class="nav-link" href="${pageContext.request.contextPath}/ServletProductos?accion=irVentas">
     				<button type="button" class="btn btn-outline-light font-weight-bolder">Registrar Ventas</button>
      			</a>
      		</li>
      	</ul>
	</div>
	<!-- Usuario y Cerrar Sesion -->
    <div class="dropdown col-sm-2">
  		<button type="button" class="btn btn-dark dropdown-toggle font-weight-bolder" data-toggle="dropdown">
  		 	 Vendedor
  		</button>
  		<div class=" bg-dark dropdown-menu text-center" aria-labelledby="dropdownMenuButton">
   			<a class="dropdown-item" href="#"> 
   				<img alt="60" width="60" src="img/icone-utilisateur-vert.png"> 
   			</a>
    		<div class="dropdown-divider"></div><!-- Linea divisoria -->
   				<a  href="${pageContext.request.contextPath}/index.jsp">
      				<button type="button" class="btn btn-outline font-weight-bolder text-light" data-toggle="tooltip" title="Logout" ><i class="fa fa-sign-out" aria-hidden="true"></i> 
      					Cerrar Sesion 
      				</button>
      			</a>
  		</div>
	</div>
<%} %>
</nav>


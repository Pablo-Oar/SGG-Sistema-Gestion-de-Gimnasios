<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="entidades.*" %>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
 <!-- Required meta tags -->
 <jsp:include page="/paginas/comunes/metas.jsp"/>
<title>Sistema de Gestion de Gimnasios</title>
					<!-- Encabezado home -->
<jsp:include page="/paginas/recepcionista/home/encabezadoHome.jsp"/>
</head>

<body class="bg-secondary">

<section id="actions" class="py-4 mb-4 bg-secondary">
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<a href="#" class="btn btn-success font-weight-bolder " data-toggle="modal"
						data-target="#agregarClienteModal"> <i class="fas fa-plus"></i>&nbsp;Agregar Cliente
					</a>
				</div>
			</div>
		</div>
</section>

	<div class="container-fluid">
		<div class="row"> 
			<div class="col-sm-11  mx-auto">
				<div class="card border border-dark">
					<div class="card-header border-bottom border-dark">
						<div class="row">
							<h4 class="col-sm-4">Listado de Clientes</h4>
							<form class="form-inline col-sm-8" action="${pageContext.request.contextPath}/ServletControlador?accion=encontrarClienteApellido" method="post" >
    							<input name="apellido" class="form-control mr-sm-3" type="text" placeholder="Apellido del cliente">
    							<button class="btn btn-outline-dark font-weight-bolder" type="submit">Buscar</button>
  							</form>
  						</div>
  					</div>
  					<div class="card-body">
					<table class="table table-hover list-group-item-dark text-center">
						<thead class="thead-dark">
							<tr>
								<th>#</th> 
								<th>Nombre</th>	
								<th>Email</th>
								<th>Dni</th>
								<th>Telefono</th>
								<th>Direccion</th>
								<th>Membresia</th>
								<th>Estado</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							  <c:forEach var="clientes" items="${clientes}" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>${clientes.nombre} ${clientes.apellido}</td>
									<td>${clientes.email}</td>
									<td>${clientes.dni}</td>
									<td>${clientes.telefono}</td>
									<td>${clientes.direccion}</td>	
									<td>${clientes.membresia.tipoMembresia}</td>	
									<c:if test="${clientes.estado == true}">
                                    	<td><span class="badge badge-pill badge-success active">Cliente Activo</span></td> 
                                    </c:if>
                                    <c:if test="${clientes.estado == false}">
                                    	<td><span class="badge badge-pill badge-danger active">Cliente Inactivo</span></td> 
                                    </c:if>
									<td class="d-flex">
										<c:choose>	
											<c:when test="${clientes.estado == true}">
												<input type="hidden" id="item" value = "${clientes.idCliente}">
												<a id="desactivarCliente" href="${pageContext.request.contextPath}/ServletControlador?cambiarEstadoCliente=desactivar&idCliente=${clientes.idCliente}" class="btn btn-danger ml-auto" data-toggle="tooltip" title="Desactivar" data-original-title="Desactivar">
												<i class="fas fa-eye-slash font-weight-bolder"></i></a>
											</c:when>
											<c:otherwise>
												<input type="hidden" id="item" value = "${clientes.idCliente}">
												<a id="activarCliente" href="${pageContext.request.contextPath}/ServletControlador?cambiarEstadoCliente=activar&idCliente=${clientes.idCliente}" class="btn btn-success ml-auto" data-toggle="tooltip" title="Activar" data-original-title="Activar">
												<i class="fas fa-eye font-weight-bolder"></i><br></a>
											</c:otherwise>
										</c:choose>
										<a href="${pageContext.request.contextPath}/ServletControlador?accion=editarCliente&idCliente=${clientes.idCliente}"
											class="btn btn-primary ml-auto">
											<i class="fas fa-edit "></i><br>
										</a>
									</td>		
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
				</div>
			</div>
		</div>
	</div>

<!-- Agregar un cliente (modal) -->
<jsp:include page="/paginas/recepcionista/agregarCliente.jsp"></jsp:include>

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
<!-- Pie de pagina -->
	<jsp:include page="/paginas/comunes/piePagina.jsp"/>
</html>



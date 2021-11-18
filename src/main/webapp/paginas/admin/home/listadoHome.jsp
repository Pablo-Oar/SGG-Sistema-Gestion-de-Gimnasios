<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="entidades.*" %>
<%@page import="java.util.*"%>

<section id="clientes" >
	<div class="container ">
		<div class="row" >
			<div class="col-sm-12">
				<div class="card border border-dark">
					<div class="card-header border-bottom border-dark">
						<div class="row ">
							<h4 class="col-sm-8">Listado de Clientes</h4>
							<form class="col-sm-4 form-inline " action="${pageContext.request.contextPath}/ServletControlador?accion=encontrarClienteApellido" method="post" >
    							<input name="apellido" class="form-control mr-sm-3" type="text" placeholder="Apellido del cliente">
    							<button class="btn btn-outline-dark font-weight-bolder" type="submit">Buscar</button>
  							</form>
  						</div>
  					</div>
  					<div class="card-body">
					<table class="table table-hover list-group-item-dark">
						<thead class="thead-dark">
							<tr>
								<th>#</th> 
								<th>Nombre</th>
								<th>Apellido</th>
								<th>Email</th>
								<th>Dni</th>
								<th>Telefono</th>
								<th>Direccion</th>
								<th>Membresia</th>
								<th>Estado</th>
							</tr>
						</thead>
						<tbody>
							  <c:forEach var="clientes" items="${clientes}" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>${clientes.nombre}</td>
									<td>${clientes.apellido}</td>
									<td>${clientes.email}</td>
									<td>${clientes.dni}</td>
									<td>${clientes.telefono}</td>
									<td>${clientes.direccion}</td>
									<c:if test="${clientes.estadoMembresia == true}">
										<td class="text-center"><span class="badge badge-pill badge-success active">${clientes.tipoMembresia}</span></td> 
									</c:if>
									<c:if test="${clientes.estadoMembresia == false}">
										<td class="text-center"><span class="badge badge-pill badge-danger active">Membresia Inactiva</span></td>
									</c:if>
									<c:if test="${clientes.estado == true}">
                                    	<td><span class="badge badge-pill badge-success active">Cliente Activo</span></td> 
                                    </c:if>
                                    <c:if test="${clientes.estado == false}">
                                    	<td><span class="badge badge-pill badge-danger active">Cliente Inactivo</span></td> 
                                    </c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>




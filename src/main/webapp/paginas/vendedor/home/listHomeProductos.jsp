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
</head>
<body class="bg-secondary">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<div class="card">
					<div class="card-header">
						<div class="row">
							<h4 class="col-sm-4">Listado de Productos</h4>
							<form class="form-inline col-sm-8" action="${pageContext.request.contextPath}/ServletProductos?accion=encontrarProductoId" method="post" >
    							<input name="id" class="form-control mr-sm-3" type="text" placeholder="Ingrese id del producto">
    							<button class="btn btn-outline-dark font-weight-bolder" type="submit">Buscar</button>
  							</form>
  						</div>
  					</div>
  					<div class="card-body">
					<table class="table table-striped text-center">
						<thead class="thead-dark">
							<tr>
								<th>#</th> 
								<th>Nombre</th>	
								<th>Precio</th>
								<th>Stock</th>
								<th>Estado</th>
								<th>Categoria</th>
								<th>Descripcion</th>
							</tr>
						</thead>
						<tbody>
							  <c:forEach var="productos" items="${productos}" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>${productos.nombre}</td>
									<td>${productos.precio}</td>
									<td>${productos.stock}</td>
									<c:if test="${productos.estado == true}">
                                    	<td><span class="badge badge-pill badge-success active">Producto Activo</span></td> 
                                    </c:if>
                                    <c:if test="${productos.estado == false}">
                                    	<td><span class="badge badge-pill badge-danger active">Producto Inactivo</span></td> 
                                    </c:if>
									<td>${productos.categoria.descripcion}</td>	
									<td>${productos.descripcion}</td>	
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
				</div>
			</div>
		</div>
	</div>

<!-- Agregar un producto (modal) -->
<jsp:include page="/paginas/admin/cliente/agregarCliente.jsp"></jsp:include>

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>


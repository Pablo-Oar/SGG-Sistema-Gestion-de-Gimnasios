<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
 <!-- Required meta tags -->
 <jsp:include page="/paginas/comunes/metas.jsp"/>
<title>Sistema de Gestion de Gimnasios</title>
</head>
<body class="bg-secondary">

	
	
	<section id="actions" class= "py-4 mb-4 bg-secondary">
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<a href="usuarioVendedor.jsp" class="btn btn-outline-light font-weight-bolder ">
						<i class="fas fa-arrow-left"></i>&nbsp;&nbsp;Regresar al inicio
					</a>
				</div>
			</div>
		</div>
	</section>
	
	<section id="productos">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header">
						<h4>Producto encontrado:</h4>
					</div>
					<div class="card-body">
					<table class="table table-striped">
						<thead class="thead-dark">
							<tr>
								<th>#</th> 
								<th>Nombre</th>	
								<th>Precio</th>
								<th>Stock</th>
								<th>Estado</th>
								<th>Categoria</th>
								<th>Descripcion</th>
								<th>Acciones</th>
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
									<td>
										<c:choose>	
											<c:when test="${productos.estado == true}">
												<input type="hidden" id="item" value = "${productos.idProducto}">
												<a id="desactivarProducto" href="${pageContext.request.contextPath}/ServletProducto?cambiarEstadoProducto=desactivar&idProducto=${productos.idProducto}" class="btn btn-danger" data-toggle="tooltip" title="Desactivar" data-original-title="Desactivar">
												<i class="fas fa-eye-slash"></i></a>
											</c:when>
											<c:otherwise>
												<input type="hidden" id="item" value = "${productos.idProducto}">
												<a id="activarProducto" href="${pageContext.request.contextPath}/ServletProducto?cambiarEstadoProducto=activar&idProducto=${productos.idProducto}" class="btn btn-success" data-toggle="tooltip" title="Activar" data-original-title="Activar">
												<i class="fas fa-eye"></i></a>
											</c:otherwise>
										</c:choose>
										<a href="${pageContext.request.contextPath}/ServletProducto?accion=editarProducto&idProducto=${productos.idProducto}"
											class="btn btn-primary">
											<i class="fas fa-edit"></i>Editar
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
</section>

	
	
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
	<!-- Pie de pagina -->
	<jsp:include page="/paginas/comunes/piePagina.jsp"/>
	
</html>
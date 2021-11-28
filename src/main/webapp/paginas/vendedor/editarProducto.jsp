<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import= "entidades.*" %>


<!DOCTYPE html>
<html>
<head>
 <!-- Required meta tags -->
 <jsp:include page="/paginas/comunes/metas.jsp"/>
<title>Editar producto</title>
	<!-- Encabezado home -->
<jsp:include page="/paginas/comunes/encabezado.jsp"/>
<%Producto producto = (Producto) session.getAttribute("producto"); %>
<% Usuario usuario = ((Usuario)session.getAttribute("usuario"));%>
</head>
<body class="bg-secondary">
	
	<form action="${pageContext.request.contextPath}/ServletProductos?accion=modificarProducto&idProducto=${producto.idProducto}"
			method="post" class="was-validated bg-secondary">
	
		<section id="actions" class= "py-4 mb-4 bg-secondary">
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<a href="/TP-JAVA-SGG/paginas/vendedor/listadoProductos.jsp" class="btn btn-outline-light font-weight-bolder ">
						<i class="fas fa-arrow-left"></i>&nbsp;&nbsp;Regresar al inicio
					</a>
				</div>
				<div class="col-md-3">
					<button type="submit" class="btn btn-success btn-block font-weight-bolder">
						<i class="fas fa-check"></i>&nbsp;&nbsp;Guardar Producto
					</button>
				</div>
				<div class="col-md-3">
					<a href="${pageContext.request.contextPath}/ServletProductos?accion=eliminarProducto&idProducto=${producto.idProducto}"
               		    class="btn btn-danger btn-block font-weight-bolder">
						<i class="fas fa-trash"></i>&nbsp;&nbsp;Eliminar Producto
					</a>
				</div>
			</div>
		</div>
		</section>
		
		<section id="details">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="card">
							<div class="card-header bg-success">
								<h4>Editar Producto</h4>
							</div>
							<div class="card-body">
								<div class="d-flex form-group">
									<div class="col-sm-6" >
										<label for="nombre" class="font-weight-bolder">Nombre</label>
										<input type="text" class="form-control" name="nombre" value="<%=producto.getNombre()%>" required>
										<div class="valid-feedback">Valido.</div>
										<div class="invalid-feedback">Por favor completar nombre de producto.</div>
									</div>
									<div class="col-sm-6" >
										<label for="precio" class="font-weight-bolder">Precio</label>
										<input type="text" class="form-control" name="precio"  value="${producto.precio}" required>
										<div class="valid-feedback">Valido.</div>
										<div class="invalid-feedback">Por favor completar precio del producto.</div>
									</div>
								</div>
								<div class="d-flex form-group">
									<div class="col-sm-6" >
										<label for="stock" class="font-weight-bolder">Stock</label>
										<input type="text" class="form-control" name="stock" required value="${producto.stock}">
										<div class="valid-feedback">Valido.</div>
										<div class="invalid-feedback">Por favor completar Strock.</div>
									</div>
									<div class="col-sm-6" >
										<label for="categoria" class="font-weight-bolder">Tipo Categoria:</label>
										<select class="form-control" class="form-select" name="categoria" id="categoria" >
  										<c:forEach var="categorias" items="${categorias}">
  											<option value="${categorias.descripcion}">${categorias.descripcion}</option>
  										</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group d-flex" class="mb-3">
									<div class="col-sm-12" >
										<label for="descripcion" class="font-weight-bolder">Descripcion</label>
										<textarea class="form-control" name="descripcion" rows="3" required >${producto.descripcion}</textarea>	
										<div class="valid-feedback">Valido.</div>
										<div class="invalid-feedback">Por favor completar descripcion.</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</form>			
	
					<!-- Pie de pagina -->
	<jsp:include page="/paginas/comunes/piePagina.jsp"/>
	
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
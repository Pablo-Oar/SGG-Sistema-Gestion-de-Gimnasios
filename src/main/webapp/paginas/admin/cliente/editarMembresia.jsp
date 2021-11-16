<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import= "entidades.*" %>


<!DOCTYPE html>
<html>
<head>
 				<!-- Metas -->
 <jsp:include page="/paginas/comunes/metas.jsp"/>
<title>Editar membresia</title>
	<!-- Encabezado home -->
<jsp:include page="/paginas/admin//home/encabezadoHome.jsp"/>
<%Membresia membresia = (Membresia) session.getAttribute("membresia");%>
</head>
<body class="bg-secondary">
	
	<form action="${pageContext.request.contextPath}/ServletControlador?accion=modificarMembresia&idMembresia=${membresia.idMembresia}"
			method="post" class="was-validated bg-secondary">
	
		<section id="actions" class= "py-4 mb-4 bg-secondary">
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<a href="usuarioAdministrador.jsp" class="btn btn-outline-light font-weight-bolder ">
						<i class="fas fa-arrow-left"></i>&nbsp;&nbsp;Regresar al inicio
					</a>
				</div>
				<div class="col-md-3">
					<button type="submit" class="btn btn-success btn-block font-weight-bolder">
						<i class="fas fa-check"></i>&nbsp;&nbsp;Guardar Membresia
					</button>
				</div>
				<div class="col-md-3">
					<a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminarMembresia&idMembresia=${membresia.idMembresia}"
               		    class="btn btn-danger btn-block font-weight-bolder">
						<i class="fas fa-trash"></i>&nbsp;&nbsp;Eliminar Membresia
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
								<h4>Editar Membresia</h4>
							</div>
							<div class="card-body">
								<div class="form-group">
									<label for="tipoMembresia" class="font-weight-bolder">Tipo Membresia</label>
									<input type="text" class="form-control" name="tipoMembresia" value="${membresia.tipoMembresia }" required>
								</div>
								<div class="form-group">
									<label for="precio" class="font-weight-bolder">Precio</label>
									<input type="text" class="form-control" name="precio" required value="${ membresia.precio}">
								</div>
								<div class="form-group">
									<label for="meses" class="font-weight-bolder">Meses</label>
									<input type="text" class="form-control" name="meses" required value="${membresia.getMeses()}">
								</div>
								<div class="form-group">
									<label for="estado" class="font-weight-bolder">Estado
									<input type="checkbox" class="form-control " checked name="estado"></input></label>
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
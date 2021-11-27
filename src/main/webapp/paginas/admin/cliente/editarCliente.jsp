<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import= "entidades.*" %>


<!DOCTYPE html>
<html>
<head>
 <!-- Required meta tags -->
 <jsp:include page="/paginas/comunes/metas.jsp"/>
<title>Editar cliente</title>
	<!-- Encabezado home -->
<jsp:include page="/paginas/admin//home/encabezadoHome.jsp"/>
<%Cliente cliente = (Cliente) session.getAttribute("cliente"); %>
<%Membresia membresia = (Membresia) session.getAttribute("membresia"); %>
</head>
<body class="bg-secondary">
	
	<form action="${pageContext.request.contextPath}/ServletControlador?accion=modificarCliente&idCliente=${cliente.idCliente}"
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
						<i class="fas fa-check"></i>&nbsp;&nbsp;Guardar Cliente
					</button>
				</div>
				<div class="col-md-3">
					<a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminarCliente&idCliente=${cliente.idCliente}"
               		    class="btn btn-danger btn-block font-weight-bolder">
						<i class="fas fa-trash"></i>&nbsp;&nbsp;Eliminar Cliente
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
								<h4>Editar Cliente</h4>
							</div>
							<div class="card-body">
								<div class="form-group">
									<label for="nombre" class="font-weight-bolder">Nombre</label>
									<input type="text" class="form-control" name="nombre" value="<%=cliente.getNombre()%>" required>
									<div class="valid-feedback">Valido.</div>
									<div class="invalid-feedback">Por favor completar nombre.</div>
								</div>
								<div class="form-group">
									<label for="apellido" class="font-weight-bolder">Apellido</label>
									<input type="text" class="form-control" name="apellido"  value="${cliente.apellido}" required>
									<div class="valid-feedback">Valido.</div>
									<div class="invalid-feedback">Por favor completar apellido.</div>
								</div>
								<div class="form-group">
									<label for="email" class="font-weight-bolder">Email</label>
									<input type="email" class="form-control" name="email" required placeholder="email@email.com " 
											pattern="/([a-zA-Z0-9]+)([\_\.\-{1}])?([a-zA-Z0-9]+)\@([a-zA-Z0-9]+)([\.])([a-zA-Z\.]+)/g" 
											value="${cliente.email}"></input>
									<div class="valid-feedback">Valido.</div>
									<div class="invalid-feedback">Por favor completar email.</div>
								</div>
									<div class="form-group">
									<label for="apellido" class="font-weight-bolder">Dni</label>
									<input type="text" class="form-control" name="dni" required value="${cliente.dni}">
									<div class="valid-feedback">Valido.</div>
									<div class="invalid-feedback">Por favor completar dni.</div>
								</div>
								<div class="form-group">
									<label for="telefono" class="font-weight-bolder">Telefono</label>
									<input type="tel" class="form-control" name="telefono" required value="${cliente.telefono}">
									<div class="valid-feedback">Valido.</div>
									<div class="invalid-feedback">Por favor completar telefono.</div>
								</div>
								<div class="form-group">
									<label for="direccion" class="font-weight-bolder">Direccion</label>
									<input type="text" class="form-control" name="direccion" required value="${cliente.direccion}">
									<div class="valid-feedback">Valido.</div>
									<div class="invalid-feedback">Por favor completar domicilio.</div>
								</div>
								<div class="form-group ">
									<label for="membresia" class="font-weight-bolder">Tipo Membresia:</label>
									<select class="form-control" class="form-select" name="membresia" id="membresia" >
  									<c:forEach var="membresias" items="${membresias}">
  										<option value="${membresias.tipoMembresia}">${membresias.tipoMembresia}</option>
  									</c:forEach>
									</select>
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


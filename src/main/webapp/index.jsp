<%@page import="entidades.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<jsp:include page="/paginas/comunes/metas.jsp" />
<title>Gimnasio - Login</title>
</head>
<br>
<body class="bg-dark">

	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<header id="main-header" class="py-2 bg-success  text-white">
				<div class="container">
				<div class="row">
				<div class="col-md-12">
					<h2>
						<i class="fas fa-user-lock"></i> &nbsp; Login - Sistema Gestion de Gimnasios
					</h2>
				</div>
				</div>
				</div>
			</header>
			<div class="container shadow-lg  p-4 mb-0.5 bg-white ">
				<form class="was-validated "
					action="${pageContext.request.contextPath}/ServletControlador?accion=login"
					method="post">
					<div class="modal-body">
						<div class="form-group ">
							<div data-validate="Usuario requerido">
								<label for="email">Email</label>
								<div class="row">
									<input type="email" class="form-control" name="email" required
										placeholder="ejemplo@gmail.com"></input>
									<div class="valid-feedback">Valido.</div>
									<div class="invalid-feedback">Por favor completar email.</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="validate-input" data-validate="Contraseña Requerida">
								<label for="email">Contraseña</label>
								<div class="row">
									<input type="password" class="form-control" name="password"
										required placeholder="Ingrese su contraseña"></input>
									<div class="valid-feedback">Valido.</div>
									<div class="invalid-feedback">Por favor completar
										contraseña.</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer ">
						<button class="btn btn-success btn-block" type="submit">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

</body>
<!-- Pie de pagina -->
<jsp:include page="/paginas/comunes/piePagina.jsp" />
</html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="modal fade" id="agregarClienteModal">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-success text-white font-weight-bolder">
				<h5 class="modal-title">Agregar Cliente</h5>
				<button class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
			</div>
			<form action="${pageContext.request.contextPath}/ServletControlador?accion=insertarCliente" method="post" class=" bg-light was-validated ">
				<div class="modal-body">
					<div class="d-flex form-group">
						<div class="col-sm-6" >
							<label for="nombre" class="font-weight-bolder">Nombre</label>
							<input type="text" class="form-control" name="nombre" required placeholder="Ingrese su nombre..."></input>
							<div class="valid-feedback">Valido.</div>
							<div class="invalid-feedback">Por favor completar nombre.</div>
						</div>
						<div class="col-sm-6">
							<label for="apellido" class="font-weight-bolder">Apellido</label>
							<input type="text" class="form-control" name="apellido" required placeholder="Ingrese su apellido..."></input>
							<div class="valid-feedback">Valido.</div>
							<div class="invalid-feedback">Por favor completar apellido.</div>
						</div>
					</div>
					<div class="d-flex form-group">
						<div class="col-sm-6" >
							<label for="email" class="font-weight-bolder">Email</label>
							<input type="email" class="form-control" name="email" required placeholder="email@email.com " 
							pattern="/([a-zA-Z0-9]+)([\_\.\-{1}])?([a-zA-Z0-9]+)\@([a-zA-Z0-9]+)([\.])([a-zA-Z\.]+)/g"></input>
							<div class="valid-feedback">Valido.</div>
							<div class="invalid-feedback">Por favor completar email.</div>
						</div>
						<div class="col-sm-6" >
							<label for="apellido" class="font-weight-bolder">Dni</label>
							<input type="text" class="form-control" name="dni" required placeholder="Ingrese su numero de dni..."></input>
							<div class="valid-feedback">Valido.</div>
							<div class="invalid-feedback">Por favor completar dni.</div>
						</div>
					</div>
					<div class="d-flex form-group">
						<div class="col-sm-6" >
							<label for="telefono" class="font-weight-bolder">Telefono:</label>
							<input type="tel" class="form-control" name="telefono" required placeholder="Ingrese su numero de telefono..."></input>
							<div class="valid-feedback">Valido.</div>
							<div class="invalid-feedback">Por favor completar telefono.</div>
						</div>
						<div class="col-sm-6" >
							<label for="direccion" class="font-weight-bolder">Direccion:</label>
							<input type="text" class="form-control" name="direccion" required placeholder="Ingrese su domicilio..."></input>
							<div class="valid-feedback">Valido.</div>
							<div class="invalid-feedback">Por favor completar domicilio.</div>
						</div>
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
				<div class="modal-footer">
					<button class="btn btn-success font-weight-bolder btn-block" type="submit">Guardar</button>
				</div>
			</form>
		</div>
		</div>
</div>

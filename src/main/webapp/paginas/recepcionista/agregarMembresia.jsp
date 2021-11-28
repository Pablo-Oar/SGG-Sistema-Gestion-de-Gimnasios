

<div class="modal fade" id="agregarMembresiaModal">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-success text-white font-weight-bolder">
				<h5 class="modal-title">Agregar Membresia</h5>
				<button class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
			</div>
			
				<form action="${pageContext.request.contextPath}/ServletControlador?accion=insertarMembresia" method="post" class=" bg-light was-validated ">
					<div class="modal-body">
						<div class="form-group">
							<label for="tipoMembresia" class="font-weight-bolder">Tipo Membresia</label>
							<input type="text" class="form-control" name="tipoMembresia" required placeholder="Ingrese el tipo de membresia (Mensual, Anual...)"></input>
							<div class="valid-feedback">Valido.</div>
							<div class="invalid-feedback">Por favor completar tipo.</div>
						</div>
						<div class="form-group">
							<label for="precio" class="font-weight-bolder">Precio</label>
							<input type="text" class="form-control" name="precio" required placeholder="Ingrese precio de membresia..."></input>
							<div class="valid-feedback">Valido.</div>
							<div class="invalid-feedback">Por favor completar precio de membresia.</div>
						</div>
						<div class="form-group">
							<label for="meses" class="font-weight-bolder">meses</label>
							<input type="text" class="form-control" name="meses" required placeholder="meses duracion "></input>
							<div class="valid-feedback">Valido.</div>
							<div class="invalid-feedback">Por favor completar meses de vigencia de la membresia.</div>
						</div>
						<div class="form-group">
							<label for="estado" class="font-weight-bolder ">Estado
							<input type="checkbox" class="form-control" checked name="estado"></input></label>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-success font-weight-bolder btn-block" type="submit">Guardar</button>
					</div>
				</form>
			</div>
			</div>
		</div>

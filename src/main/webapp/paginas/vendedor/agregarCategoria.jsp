

<div class="modal fade" id="agregarCategoriaModal">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-success text-white font-weight-bolder">
				<h5 class="modal-title">Agregar Categoria</h5>
				<button class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
			</div>
			<form action="${pageContext.request.contextPath}/ServletProductos?accion=insertarCategoria" method="post" class=" bg-light was-validated ">
				<div class="modal-body">
					<div class="form-group">
						<label for="descripcion" class="font-weight-bolder">Descripcion Categoria</label>
						<input type="text" class="form-control" name="descripcion" required placeholder="Ingrese el tipo de categoria (Suplementos, Alimentos...)"></input>
						<div class="valid-feedback">Valido.</div>
						<div class="invalid-feedback">Por favor completar descripcion de categoria.</div>
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



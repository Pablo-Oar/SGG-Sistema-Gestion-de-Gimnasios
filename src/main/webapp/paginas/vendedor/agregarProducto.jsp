
<div class="modal fade" id="agregarProductoModal">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-success text-white font-weight-bolder">
				<h5 class="modal-title">Agregar Producto</h5>
				<button type = "button" class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
			</div>
			<form action="${pageContext.request.contextPath}/ServletProductos?accion=insertarProducto" method="post" class=" bg-light was-validated ">
				<div class="modal-body">
					<div class="form-group">
						<label for="nombre" class="font-weight-bolder">Nombre</label>
						<input type="text" class="form-control" name="nombre" required placeholder="Ingrese nombre del producto..."></input>
						<div class="valid-feedback">Valido.</div>
						<div class="invalid-feedback">Por favor completar nombre.</div>
					</div>
					<div class="form-group">
						<label for="precio" class="font-weight-bolder">Precio</label>
						<input type="text" class="form-control" name="precio" required placeholder="Ingrese precio..."></input>
						<div class="valid-feedback">Valido.</div>
						<div class="invalid-feedback">Por favor completar precio.</div>
					</div>
					<div class="form-group">
						<label for="stock" class="font-weight-bolder">Stock</label>
						<input type="text" class="form-control" name="stock" required placeholder="Ingrese stock..."></input>
						<div class="valid-feedback">Valido.</div>
						<div class="invalid-feedback">Por favor completar stock.</div>
					</div>
					<div class="form-group">
						<label for="estado" class="font-weight-bolder ">Estado
						<input type="checkbox" class="form-control" checked name="estado"></input></label>
					</div>
					<div class="form-group ">
						<label for="categoria" class="font-weight-bolder ">Categoria:</label>
						<select class="form-control" class="form-select" name="membresia" id="categoria">
  							<option value="Vestimenta">VESTIMENTA</option>
 							<option value="Suplementos">SUPLEMENTOS</option>
 							<option value="Articulos Entrenamiento">ARTICULOS ENTRENAMIENTO</option>
						</select>
						<div class="valid-feedback">Valido.</div>
						<div class="invalid-feedback">Por favor completar tipo categoría.</div>
					</div>
					<div class="form-group" class="mb-3">
						<label for="descripcion" class="font-weight-bolder">Descripcion</label>
						<textarea class="form-control" name="descripcion" rows="3" required placeholder="Ingrese una descripcion..."></textarea>	
						<div class="valid-feedback">Valido.</div>
						<div class="invalid-feedback">Por favor completar descripcion.</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-success font-weight-bolder btn-block" type="submit">Guardar</button>
				</div>
			</form>
		</div>
	</div>
</div>

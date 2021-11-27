
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
					<div class="d-flex form-group">
						<div class="col-sm-6" >
							<label for="nombre" class="font-weight-bolder">Nombre</label>
							<input type="text" class="form-control" name="nombre" required placeholder="Ingrese nombre del producto..."></input>
							<div class="valid-feedback">Valido.</div>
							<div class="invalid-feedback">Por favor completar nombre.</div>
						</div>
						<div class="col-sm-6" >
							<label for="precio" class="font-weight-bolder">Precio</label>
							<input type="text" class="form-control" name="precio" required placeholder="Ingrese precio..."></input>
							<div class="valid-feedback">Valido.</div>
							<div class="invalid-feedback">Por favor completar precio.</div>
						</div>
					</div>
					<div class="d-flex form-group">
						<div class="col-sm-6" >
							<label for="stock" class="font-weight-bolder">Stock</label>
							<input type="text" class="form-control" name="stock" required placeholder="Ingrese stock..."></input>
							<div class="valid-feedback">Valido.</div>
							<div class="invalid-feedback">Por favor completar stock.</div>
						</div>
						<div class="col-sm-6" >
							<label for="categoria" class="font-weight-bolder ">Categoria:</label>
							<select class="form-control" class="form-select" name="categoria">
  								<option value="Vestimenta">VESTIMENTA</option>
 								<option value="Suplementos">SUPLEMENTOS</option>
 								<option value="Articulos Entrenamiento">ARTICULOS ENTRENAMIENTO</option>
							</select>
							<div class="valid-feedback">Valido.</div>
							<div class="invalid-feedback">Por favor completar tipo categoría.</div>
						</div>
					</div>
					<div class="form-group d-flex" class="mb-3">
						<div class="col-sm-12" >
							<label for="descripcion" class="font-weight-bolder">Descripcion</label>
							<textarea class="form-control" name="descripcion" rows="3" required placeholder="Ingrese una descripcion..."></textarea>	
							<div class="valid-feedback">Valido.</div>
							<div class="invalid-feedback">Por favor completar descripcion.</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-success font-weight-bolder btn-block" type="submit">Guardar</button>
				</div>
			</form>
		</div>
	</div>
</div>

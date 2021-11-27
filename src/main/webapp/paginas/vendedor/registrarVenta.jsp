<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="entidades.*" %>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
 				<!-- Metas -->
 <jsp:include page="/paginas/comunes/metas.jsp"/>
<title>Registrar Ventas</title>
				<!-- Encabezado home -->
<jsp:include page="/paginas/admin//home/encabezadoHome.jsp"/>
	<style type="text/css">
		@media print{
			.parteAgregarProducto, .btn, .acciones, .card-footer, .fa-dollar-sign, .piePagina {
				display: none;
			}
		}
	</style>
</head>
<body class="bg-secondary">
	<br><br>
	<div class="d-flex">
		<div class="col-sm-5 parteAgregarProducto" >
			<div class="card border border-dark" >
				<form action="${pageContext.request.contextPath}/ServletProductos" method="post">
						<div class="card-header border-bottom border-dark">
							<div class="row">
								<h5 class="col-sm-6">Datos Producto</h5>
  							</div>
  						</div>
  						<div class="card-body">
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex" >
								<input type="text" name="idProducto" value="${producto.idProducto }"class="form-control" placeholder="Id Producto">
								<button type="submit" name="accion" value="BuscarProducto" class="btn btn-outline-info">
									Buscar
								</button>
							</div>
							<div class="col-sm-6" >
								<input type="text" name="nombreProducto" value="${producto.nombre}" class="form-control">
							</div>
						</div>
						<div class="form-group d-flex">
							<div class="col-sm-5" >
								<input type="text" name="precio" value="${producto.precio }" class="form-control" placeholder="$ 0.00">
							</div>
							<div class="col-sm-3" >
								<input type="number" min="1" name="cantidad" value="1" class="form-control" placeholder=" ">
							</div>
							<div class="col-sm-4" >
								<input type="hidden" name="estado" value="${producto.estado}" class="form-control">
								<input type="text" name="stock" placeholder="Stock" value="${producto.stock}" class="form-control">
							</div>
						</div>
						<!-- BOTON PARA AGREGAR UN PRODUCTO AL REGISTRO -->
						<div class="form-group col-sm-7 mx-auto">
							<a href="${pageContext.request.contextPath}/ServletProductos">
								<button type="submit" name="accion" value="agregarProducto" class=" btn btn-success fas fa-cart-plus">
									&nbsp;Agregar Producto
								</button>
							</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col-sm-7" >
			<div class="card border border-dark">
			<form action="${pageContext.request.contextPath}/ServletProductos" method="get">
				<div class="card-header border-bottom border-dark">
					<div class=" d-flex col-sm-8 ml-auto">
						<h5 class="col-sm-6">Numero de Serie:</h5>
						<input type="text" name=NroSerie value="${NroSerie}" class="form-control" >
  					</div>
  				</div>
				<div class="card-body border-bottom border-dark">
					<br>
					<table class="table table-hover list-group-item-dark">
						<thead class="thead-dark">
							<tr>
								<th>Nro</th>
								<th>Codigo</th>
								<th>Descripción</th>
								<th>Precio</th>
								<th>Cantidad</th>
								<th>SubTotal</th>
								<th class="acciones">Acciones</th>
							</tr>
						</thead>
						<tbody >
							<c:forEach var="listaProductos" items="${listaProductos}"  varStatus="status">
							<tr> 
								<td>${status.count}</td>						
								<td>${listaProductos.idProducto}</td>
								<td>${listaProductos.descripcion}</td>
								<td>${listaProductos.precio}</td>
								<td>${listaProductos.cantidad}</td>
								<td>${listaProductos.subtotal}</td>
								<td class="form-group d-flex">
									<a>	
										<input type="hidden" name="idProducto" value="${listaProductos.idProducto}" class="form-control">
										<button type="submit" name="accion" value="eliminarProdCarrito" class="btn btn-danger "><i class="fas fa-trash"></i>
											Eliminar
										</button>
									</a>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="card-footer d-flex">
					<div class="col-sm-6">
						<a href="${pageContext.request.contextPath}/ServletProductos?accion=agregarProducto">
							<button type="submit" name="accion" onclick="print()" value="GenerarVenta" class="btn btn-success">
								Generar Venta
							</button>
						</a>
						<a href="${pageContext.request.contextPath}/ServletProductos?accion=cancelarPedido">
							<button type="submit" name="accion" value="CancelarPedido" class="btn btn-danger">
								Cancelar Pedido
							</button>
						</a>
					</div>
				 	<div class="d-flex form-group col-sm-5 ml-auto">
				 		<a href="#" class=" d-flex ">
				 			<button class=" btn btn-success fas fa-dollar-sign">&nbsp;Total:</button>
				 		</a>
						<input type="text" name="total" value="${total}" class="form-control">
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	<br><br><br>
	
	<div class="piePagina">
							<!-- Pie de pagina -->
	<jsp:include page="/paginas/comunes/piePagina.jsp"/>
	</div>
	
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
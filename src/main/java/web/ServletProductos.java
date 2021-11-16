package web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entidades.*;
import logica.*;


@WebServlet({"/ServletProductos"})
public class ServletProductos extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ServletProductos()
    {
    	super();
    }
    List<Venta> listaProductos = new LinkedList<Venta>();
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String accion = request.getParameter("accion"); 
		if(accion!=null) {
			switch(accion) 
			{
				case "irProducto":
	                this.accionDefault(request, response);
	                break;
				case "editarProducto": 
					this.editarProd(request, response); 
					break;
				case "eliminarProducto":
					this.borrarProd(request, response);
					break;
				case "irCategoria":
	                this.listarCategoria(request, response);
	                break;     
				case "editarCategoria":
					this.editarCategoria(request,response);
					break;
				case "modificarCategoria": 
					this.actualizarCategoria(request, response); 
					break;
				case "irVentas":
					this.generarNroSerie(request,response);
					break;     
				case "GenerarVenta": 		
					this.generarVenta(request,response);
					break;
				case "eliminarProdCarrito": 		
					this.eliminarProdCarrito(request,response);
					break;
				case "CancelarPedido": 		
					this.cancelarPedido(request,response);
					break;
				default: this.accionDefault(request, response);
			}
		}else if (request.getParameter("cambiarEstadoProducto") != null) {
            cambiarEstadoProducto(request, response);
        }
		else {
			this.accionDefault(request, response); //Si la accion es nula muestro los mismos productos.
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Recupero la accion a realizar por algun boton.
		String accion = request.getParameter("accion"); 
		if(accion!=null) 
		{
			switch(accion) { 
			case "insertarProducto": 	this.insertarProd(request, response); 
										break;
			case "modificarProducto": 	this.actualizarProd(request, response);
										break;
			case "encontrarPoductoId": 	this.encontrarProdId(request, response); 
										break;					
			case "BuscarProducto": 		this.buscarProducto(request,response);
										break;
			case "agregarProducto": 	this.agregarProducto(request,response);
										break;
			case "cancelarPedido": 		this.cancelarPedido(request,response);
			break;
		}
		}else if (request.getParameter("cambiarEstadoProducto") != null) {
            cambiarEstadoProducto(request, response);
        }else {
			this.accionDefault(request, response); //Si la accion es nula muestro los mismos productos.
		}
	}

	private void generarVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Venta venta = new Venta();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LogicaVenta logicVenta = new LogicaVenta();
		LogicaProducto logicProducto = new LogicaProducto();
		
		//Actualizar stock de productos
		for(int i=0; i< listaProductos.size(); i++ ) {
			Producto producto = new Producto();
			producto.setIdProducto(listaProductos.get(i).getIdProducto()); //El codigo es el id del producto.
			producto = logicProducto.encontrarProductoId(producto);
			int stockActualizado = producto.getStock() - listaProductos.get(i).getCantidad();
			logicProducto.actualizarStock(producto, stockActualizado);
		}
		
		venta.setId_Usuario(1);//Recuperar idUsuario...
		venta.setNroSerie(request.getParameter("NroSerie"));
		venta.setFecha(dtf.format(LocalDateTime.now())); 
		venta.setMonto(Double.parseDouble(request.getParameter("totalAPagar")));
		venta.setEstado("1");
		logicVenta.insertarVenta(venta);
		
		//Tengo que guardar el detalle de la venta
		int idVenta = logicVenta.idVentas();
		for(int i=0; i < listaProductos.size();i++) {
			venta = new Venta();
			venta.setIdVenta(idVenta);
			venta.setIdProducto(listaProductos.get(i).getIdProducto());
			venta.setCantidad(listaProductos.get(i).getCantidad());
			venta.setPrecio(listaProductos.get(i).getPrecio());
			logicVenta.guardarDetalleVentas(venta);
		}
		
		this.cancelarPedido(request, response);
	}

	private void generarNroSerie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogicaVenta logicVenta = new LogicaVenta();
		
		String NroSerie = logicVenta.GenerarNroSerie();
		if(NroSerie == null) {
			NroSerie = "000000001";
		}
		else {
			int incremento = Integer.parseInt(NroSerie);
			NroSerie = logicVenta.NumeroSerie(incremento);
		}
		
		HttpSession sesion = request.getSession();
		sesion.setAttribute("NroSerie", NroSerie);
		request.getRequestDispatcher("/paginas/vendedor/registrarVenta.jsp").forward(request, response);
	}

	private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		List<Producto> productos = new LogicaProducto().listarProductos();
		//Lo muestro por consola para verificar
		for(Producto prod : productos) {
			System.out.println("Productos: " + prod); 
		}
		HttpSession sesion = request.getSession();
		sesion.setAttribute("productos", productos);
		request.getRequestDispatcher("/paginas/vendedor/listadoProductos.jsp").forward(request, response); 
	}
	
	private void agregarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Producto producto = new Producto();
		double total = 0;
		
		//Recupero los datos
		producto.setIdProducto(Integer.parseInt(request.getParameter("idProducto")));
		producto.setNombre(request.getParameter("nombreProducto"));
		producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
		producto.setStock(Integer.parseInt(request.getParameter("stock")));
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		double subtotal = (producto.getPrecio()*cantidad);
		
		//Ahora los seteo a la venta si el stock es mayor a cantidad solicitada
		if(producto.getStock() >= cantidad) {
			
		
		Venta venta = new Venta();
		venta.setIdProducto(producto.getIdProducto());
		venta.setDescripcion(producto.getNombre());
		venta.setPrecio(producto.getPrecio());
		venta.setCantidad(cantidad);
		venta.setSubtotal(subtotal);
		listaProductos.add(venta);
			for(int i=0; i< listaProductos.size() ;i++) {
				total = total + listaProductos.get(i).getSubtotal();
			}
		
		HttpSession sesion = request.getSession();
		sesion.setAttribute("total",total);
		sesion.setAttribute("listaProductos", listaProductos);}
		else {
			System.out.print("No hay stock para la cantidad solicitada");
		}
		request.getRequestDispatcher("/paginas/vendedor/registrarVenta.jsp").forward(request, response);
	}
	
	
	
	private void buscarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Producto producto = new Producto();
		producto.setIdProducto(Integer.parseInt(request.getParameter("idProducto")));
		producto = new LogicaProducto().encontrarProductoId(producto);
		HttpSession sesion = request.getSession();
		sesion.setAttribute("producto", producto);
		request.getRequestDispatcher("/paginas/vendedor/registrarVenta.jsp").forward(request, response);
	}
	
	private void eliminarProdCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idProd = Integer.parseInt(request.getParameter("idProducto"));
		listaProductos.remove(idProd);
		HttpSession sesion = request.getSession();
		sesion.setAttribute("listaProductos", listaProductos);
		request.getRequestDispatcher("/paginas/vendedor/registrarVenta.jsp").forward(request, response);
	}
	
	private void cancelarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listaProductos.removeAll(listaProductos);
		int total=0;
		HttpSession sesion = request.getSession();
		sesion.setAttribute("listaProductos", listaProductos);
		sesion.setAttribute("total", total);
		request.getRequestDispatcher("/paginas/vendedor/registrarVenta.jsp").forward(request, response);
		
	}
	
	private void editarProd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Producto producto = new Producto();
		producto.setIdProducto(Integer.parseInt(request.getParameter("idProducto")));
		producto = new LogicaProducto().encontrarProductoId(producto);
		HttpSession sesion = request.getSession();
		sesion.setAttribute("producto", producto);
		request.getRequestDispatcher("/paginas/vendedor/editarProducto.jsp").forward(request, response);
		
	}
	private void encontrarProdId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Producto producto = new Producto();
		producto.setIdProducto(Integer.parseInt(request.getParameter("idProducto")));
		producto = new LogicaProducto().encontrarProductoId(producto);
		HttpSession sesion = request.getSession();
		sesion.setAttribute("producto", producto);
		request.getRequestDispatcher("/paginas/vendedor/mostrarProducto.jsp").forward(request, response);
	}

	private void actualizarProd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Instancio el nuevo objeto producto y recupero los valores del formulario "editarProducto.jsp"
		Producto producto = new Producto();
		producto.setIdProducto(Integer.parseInt(request.getParameter("idProducto")));
		producto.setNombre(request.getParameter("nombre"));
		producto.setDescripcion(request.getParameter("descripcion"));
		producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
		producto.setStock(Integer.parseInt(request.getParameter("stock")));
		producto.setDescripcionCategoria(request.getParameter("categoria"));
		String estado = request.getParameter("estado");
		if(estado!= null) {
	   		producto.setEstado(true);
	   	}
	   	else {
	   		producto.setEstado(false);
	   	}
		//Modifico el objeto en la base de datos 
		int registrosModificados = new LogicaProducto().modificarProducto(producto);
		//Lo muestro por consola para verificar
		System.out.println("Registros-Modificados = " + registrosModificados); 
		
		//Actualizo la categoria del producto
		LogicaProducto catLogic = new LogicaProducto();
		catLogic.modificarProducto(producto);
		
		//Ahora redirijo a la accion por default asi me lista a todos los clientes
	   	this.accionDefault(request, response);
		
	}

	private void insertarProd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			    
			    //Instancio el nuevo objeto cliente (el id no lo proporciono ya que es autoincrementable)
			    Producto producto = new Producto();
			    producto.setNombre(request.getParameter("nombre"));
			    producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
			    producto.setStock(Integer.parseInt(request.getParameter("stock")));
			    String tipoCategoria = request.getParameter("categoria");
			    producto.setDescripcionCategoria(tipoCategoria);
			    producto.setDescripcion(request.getParameter("descripcion"));
			    String estado = request.getParameter("estado");
			   	if(estado!= null) {producto.setEstado(true);}
			   	else {producto.setEstado(false);}
			   	
			   	//Inserto el nuevo objeto en la base de datos 
				    int registrosModificados = new LogicaProducto().insertarNuevoProducto(producto);
				    System.out.println("Registros-Modificados = " + registrosModificados); //Lo muestro por consola para verificar
				    
				    //Le seteo la categoría correspondiente.
					LogicaCategoria catLogic = new LogicaCategoria();
					catLogic.modificarCategoriaTipo(producto);
					
					//Inserto las FK
					catLogic.insertarIdCategoria(producto);
				    
				    //Ahora redirijo a la accion por default asi me lista a todos los productos
				   	this.accionDefault(request, response);
		
	}

	
	//(BAJA LÓGICA)
	private void cambiarEstadoProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	LogicaProducto logicProducto;
        Producto producto;
        try {
        	logicProducto = new LogicaProducto();
            producto = new Producto();

            if (request.getParameter("cambiarEstadoProducto").equals("activar")) {
                producto.setEstado(true);
            } else {
                producto.setEstado(false);
            }

            if (request.getParameter("idProducto") != null) {
                producto.setIdProducto(Integer.parseInt(request.getParameter("idProducto")));
                logicProducto.cambiarEstadoProducto(producto);
            } else {
                request.setAttribute("mensaje", "No se obtuvo el id del producto");
            }

        } catch (Exception e) {
            request.setAttribute("mensaje", e.getMessage());
        }
        this.accionDefault(request, response);
	}
	
	//(BAJA FÍSICA)
	private void borrarProd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recupero los valores del formulario de editarProducto
		int idProducto = Integer.parseInt(request.getParameter("idProducto"));
     
		//Creo el objeto de producto (entidades)
		Producto producto = new Producto(); 
		producto.setIdProducto(idProducto);

		//Elimino el  objeto en la base de datos
		int registrosModificados = new LogicaProducto().eliminarProducto(producto);
		System.out.println("registrosModificados = " + registrosModificados);

		//Ahora redirijo a la accion por default asi me lista a todos los productos
		this.accionDefault(request, response);
		
	}

	//-----------------------------------------CATEGORIA------------------------------------------------------------------------
	   
	private void listarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CategoriaProducto> categorias = new LogicaCategoria().listarCategorias();
		//Lo muestro por consola para verificar
		for(CategoriaProducto cate : categorias) 
		{
			System.out.println("Categorias: " + cate); 
		}
			HttpSession sesion = request.getSession();
			sesion.setAttribute("categorias", categorias);
			request.getRequestDispatcher("/paginas/vendedor/listadoCategoria.jsp").forward(request, response); 
			//response.sendRedirect("/paginas/vendedor/listadoCategorias.jsp");
		}
			
	private void editarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoriaProducto categoria = new CategoriaProducto();
		categoria.setIdCategoria(Integer.parseInt(request.getParameter("idCategoria")));
		categoria = new LogicaCategoria().encontrarCategoriaId(categoria);
		HttpSession sesion = request.getSession();
		sesion.setAttribute("categoria", categoria);
		request.getRequestDispatcher("/paginas/vendedor/editarCategoria.jsp").forward(request, response);
	}
	
	private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Instancio el nuevo objeto CategoriaProducto y recupero los valores del formulario "editarCategoria.jsp"
		CategoriaProducto categoria = new CategoriaProducto();
		categoria.setIdCategoria(Integer.parseInt(request.getParameter("idCategoria")));
		categoria.setDescripcion(request.getParameter("descripcion"));
		String estado = request.getParameter("estado");
		if(estado!= null) {
	   		categoria.setEstado(true);
	   	}
	   	else {
	   		categoria.setEstado(false);
	   	}
		int registrosModificados = new LogicaCategoria().modificarCategoria(categoria);
		System.out.println("Registros-Modificados = " + registrosModificados); 
	   	this.accionDefault(request, response);
		
	}

}
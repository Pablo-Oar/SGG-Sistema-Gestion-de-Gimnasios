package web;

import java.io.IOException;
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

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String accion = request.getParameter("accion"); 
		if(accion!=null) {
			switch(accion) 
			{
				case "editarProducto": 
					this.editarProd(request, response); 
					break;
				case "eliminarProducto":
					this.borrarProd(request, response);
					break;
				case "irProducto":
	                this.accionDefault(request, response);
	                break;
				case "editarCategoria":
					this.editarCategoria(request,response);
					break;
				case "irCategoria":
	                this.listarCategoria(request, response);
	                break;     
				case "modificarCategoria": 
					this.actualizarCategoria(request, response); 
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
					case "insertarProducto": this.insertarProd(request, response); 
						break;
					case "modificarProducto": this.actualizarProd(request, response);
						break;
					case "encontrarPoductoId": this.encontrarProdId(request, response); 
					break;
					case "eliminarPoducto": this.eliminarProdId(request, response); 
					break;
					default: this.accionDefault(request, response);
					}
				}else if (request.getParameter("cambiarEstadoCliente") != null) {
		            cambiarEstadoProducto(request, response);
		        }else {
					this.accionDefault(request, response); //Si la accion es nula muestro los mismos clientes.
				}
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
			    producto.setDescripcionCategoria(request.getParameter("categoria"));
			    producto.setDescripcion(request.getParameter("descripcion"));
			    String estado = request.getParameter("estado");
			   	if(estado!= null) {producto.setEstado(true);}
			   	else {producto.setEstado(false);}
			   	
			   	//Inserto el nuevo objeto en la base de datos 
				    int registrosModificados = new LogicaProducto().insertarNuevoProducto(producto);
				    System.out.println("Registros-Modificados = " + registrosModificados); //Lo muestro por consola para verificar
				    
				    //Le seteo la categoría correspondiente.
					LogicaCategoria catLogic = new LogicaCategoria();
					catLogic.setearTipoCategoria(producto);
					
					//Inserto las FK
					catLogic.insertarIdCategoria(producto);
				    
				    //Ahora redirijo a la accion por default asi me lista a todos los productos
				   	this.accionDefault(request, response);
		
	}

	
	//(BAJA LÓGICA)
	private void cambiarEstadoProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	LogicaCategoria catDao;
        CategoriaProducto categoria;
        try {
            catDao = new LogicaCategoria();
            categoria = new CategoriaProducto();

            if (request.getParameter("cambiarEstadoCategoria").equals("activar")) {
                categoria.setEstado(true);
            } else {
                categoria.setEstado(false);
            }

            if (request.getParameter("idCategoria") != null) {
                categoria.setIdCategoria(Integer.parseInt(request.getParameter("idCategoria")));
                catDao.cambiarEstadoCategoria(categoria);
            } else {
                request.setAttribute("mensaje", "No se obtuvo el id de categoria");
            }

        } catch (Exception e) {
            request.setAttribute("mensaje", e.getMessage());
        }
       // this.accionDefault(request, response);
        this.listarCategoria(request, response);
		
	}
	
	//(BAJA FÍSICA)
	private void eliminarProdId(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	private void borrarProd(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
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
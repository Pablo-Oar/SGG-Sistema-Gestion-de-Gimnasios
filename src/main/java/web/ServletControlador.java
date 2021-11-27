package web;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.protocol.Message;

import entidades.Membresia;
import entidades.Producto;
import entidades.Usuario;
//import jdk.internal.joptsimple.util.RegexMatcher;
import entidades.Cliente;
import logica.LogicaLogin;
import logica.LogicaCliente;
import logica.LogicaMembresia;

@WebServlet({ "/ServletControlador", "/servletCliente", "/servletcliente", "/Servletcliente" })
public class ServletControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ServletControlador() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recupero la accion a realizar por algun boton.
		String accion = request.getParameter("accion"); 
		if(accion!=null) {
			switch(accion) {
			case "editarCliente": 
				this.editarCli(request, response); 
				break;
			case "eliminarCliente":
                this.borrarCli(request, response);
                break;
			case "editarMembresia":
				this.editarMembresia(request,response);
				break;
			case "eliminarMembresia":
				this.eliminarMembresia(request,response);
				break;
			case "irMembresia":
                this.listarMembresia(request, response);
                break;
			}
		}else if (request.getParameter("cambiarEstadoCliente") != null) {
            cambiarEstadoCliente(request, response);
        }else if (request.getParameter("cambiarEstadoMembresia") != null) {
            cambiarEstadoMembresia(request, response);
        }
		else {
			this.accionDefault(request, response); //Si la accion es nula muestro los mismos clientes.
		}
	}



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recupero la accion a realizar por algun boton.
		String accion = request.getParameter("accion"); 
		if(accion!=null) {
			switch(accion) {
			case "login": this.verificarLogin(request, response); 
			break;
			case "insertarCliente": this.insertarCli(request, response); 
				break;
			case "modificarCliente": this.actualizarCli(request, response);
				break;
			case "encontrarClienteApellido": this.encontrarCliAp(request, response); 
			break;
			case "insertarMembresia": this.insertarMembresia(request, response); 
			break;
			case "modificarMembresia": this.actualizarMembresia(request, response); 
			break;
			default: this.accionDefault(request, response);
			}
		}else if (request.getParameter("cambiarEstadoCliente") != null) {
            cambiarEstadoCliente(request, response);
        }else if (request.getParameter("cambiarEstadoMembresia") != null) {
            cambiarEstadoMembresia(request, response);
        }else {
			this.accionDefault(request, response); //Si la accion es nula muestro los mismos clientes.
		}
	}
	

	private void verificarLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogicaLogin crtlLogin = new LogicaLogin();
		Usuario usuario = new Usuario();
		
		usuario.setEmail(request.getParameter("email"));
		usuario.setPassword(request.getParameter("password"));
		usuario = crtlLogin.validar(usuario);
		if(usuario!= null && usuario.getRol().getDescripcion().equals("administrador")) 
		{
			HttpSession sesion = request.getSession();
			sesion.setAttribute("usuario", usuario);	
			this.accionDefault(request, response);
		}
		else if(usuario!= null && usuario.getRol().getDescripcion().equals("vendedor")) 
		{
		
			HttpSession sesion = request.getSession();
			sesion.setAttribute("usuario", usuario);
			request.getRequestDispatcher("usuarioVendedor.jsp").forward(request, response);
		}
		else if(usuario!= null && usuario.getRol().getDescripcion().equals("profesor")) 
		{
			HttpSession sesion = request.getSession();
			sesion.setAttribute("usuario", usuario);	
			request.getRequestDispatcher("usuarioProfesor.jsp").forward(request, response);
		}
		else 
		{
			HttpSession sesionIncorrecta = request.getSession();
			sesionIncorrecta.setAttribute("usuario", null);
			sesionIncorrecta.invalidate();
			request.getSession().setAttribute("estado", "Usuario o contraseña incorrectos");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			 			
		}
		
	}
	private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cliente> clientes = new LogicaCliente().listarClientes();
		
		//Lo muestro por consola para verificar
		for(Cliente cli : clientes) {
			System.out.println("Clientes:"+cli.toString()); 
		}
		HttpSession sesion = request.getSession();
		sesion.setAttribute("clientes", clientes);
		request.getRequestDispatcher("usuarioAdministrador.jsp").forward(request, response); 
		this.listarMembresia(request, response);
	}
	
	private void insertarCli(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//Recupero los valores del formulario "agregarCliente.jsp"
		String nombre = request.getParameter("nombre");
	    String apellido = request.getParameter("apellido");
	    String email = request.getParameter("email");
	    String dni = request.getParameter("dni");
	    String telefono = request.getParameter("telefono");
	    String direccion = request.getParameter("direccion");
	    String tipoMembresia = request.getParameter("membresia");
	    
	    //Instancio el nuevo objeto cliente (el id no lo proporciono ya que es autoincrementable)
	    Cliente cliente = new Cliente();
	    cliente.setNombre(nombre);
	    cliente.setApellido(apellido);
	    cliente.setEmail(email);
	    cliente.setDni(dni);
	    cliente.setTelefono(telefono);
	    cliente.setDireccion(direccion);
	    cliente.setTipoMembresia(tipoMembresia);
	   	cliente.setEstado(true);//Seteo estado en true por defecto
	   	
	   	//Inserto el nuevo objeto en la base de datos 
		    int registrosModificados = new LogicaCliente().insertarNuevoCliente(cliente);
		    System.out.println("Registros-Modificados = " + registrosModificados); //Lo muestro por consola para verificar
		    
		    //Le seteo la membresia correspondiente.
			LogicaMembresia memLogic = new LogicaMembresia();
			memLogic.modificarMembresiaTipo(cliente);
			
			//Inserto las FK
			memLogic.insertarIdMemebresia(cliente);
		    
		    //Ahora redirijo a la accion por default asi me lista a todos los clientes
		   	this.accionDefault(request, response);
	}
	
	
	private void editarCli(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
		cliente = new LogicaCliente().encontrarClienteId(cliente);
		HttpSession sesion = request.getSession();
		sesion.setAttribute("cliente", cliente);
		request.getRequestDispatcher("/paginas/admin/cliente/editarCliente.jsp").forward(request, response);
	}
	
	
	private void actualizarCli(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//Instancio el nuevo objeto cliente y recupero los valores del formulario "editarCliente.jsp"
		Cliente cliente = new Cliente();
		cliente.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
		cliente.setNombre(request.getParameter("nombre"));
		cliente.setApellido(request.getParameter("apellido"));
		cliente.setEmail(request.getParameter("email"));
		cliente.setDni(request.getParameter("dni"));
		cliente.setTelefono(request.getParameter("telefono"));
		cliente.setDireccion(request.getParameter("direccion"));
		cliente.setTipoMembresia(request.getParameter("membresia"));
		//Modifico el objeto en la base de datos 
		int registrosModificados = new LogicaCliente().modificarCliente(cliente);
		//Lo muestro por consola para verificar
		System.out.println("Registros-Modificados = " + registrosModificados); 
		
		//Actualizo la membresia del cliente
		LogicaMembresia memLogic = new LogicaMembresia();
		memLogic.modificarMembresiaTipo(cliente);
		
		//Ahora redirijo a la accion por default asi me lista a todos los clientes
	   	this.accionDefault(request, response);
	}  
	
	private void borrarCli(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		//recupero los valores del formulario de editarCliente
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
     
		//Creo el objeto de cliente (entidades)
		Cliente cliente = new Cliente(); 
		cliente.setIdCliente(idCliente);

		//Elimino el  objeto en la base de datos
		int registrosModificados = new LogicaCliente().eliminarCliente(cliente);
		System.out.println("registrosModificados = " + registrosModificados);

		//Ahora redirijo a la accion por default asi me lista a todos los clientes
		this.accionDefault(request, response);
    }
	
	private void encontrarCliAp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = new Cliente();
		cliente.setApellido(request.getParameter("apellido"));
		List<Cliente> clientes = new LogicaCliente().encontrarClienteApellido(cliente);
		
		request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("/paginas/admin/cliente/mostrarCliente.jsp").forward(request, response);
	}
	
	//(BAJA LÓGICA)
	private void cambiarEstadoCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogicaCliente clienteDao;
	    Cliente cliente;
	    	try 
	    	{
	    		clienteDao = new LogicaCliente();
	            cliente = new Cliente();

	            if (request.getParameter("cambiarEstadoCliente").equals("activar")) {
	                cliente.setEstado(true);
	            } else {
	                cliente.setEstado(false);
	            }

	            if (request.getParameter("idCliente") != null) {
	                cliente.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
	                clienteDao.cambiarEstadoCliente(cliente);
	            } else {
	                request.setAttribute("mensaje", "No se obtuvo el id del cliente");
	            }

	        } catch (Exception e) {
	            request.setAttribute("mensaje", e.getMessage());
	        }
	        this.accionDefault(request, response);
	    }
	   
	
	
//-----------------------------------------MEMBRESIA------------------------------------------------------------------------
	   
		private void listarMembresia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Membresia> membresias = new LogicaMembresia().listarMembresias();
			//Lo muestro por consola para verificar
			for(Membresia mem : membresias) 
			{
				System.out.println("Membresias: " + mem ); 
			}
			HttpSession sesion = request.getSession();
			sesion.setAttribute("membresias", membresias);
			request.getRequestDispatcher("/paginas/admin/cliente/listadoMembresia.jsp").forward(request, response); 
			//response.sendRedirect("/paginas/admin/cliente/listadoMembresia.jsp");
		}
		
		private void editarMembresia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Membresia membresia = new Membresia();
			membresia.setIdMembresia(Integer.parseInt(request.getParameter("idMembresia")));
			membresia = new LogicaMembresia().encontrarMembresiaId(membresia);
			HttpSession sesion = request.getSession();
			sesion.setAttribute("membresia", membresia);
			request.getRequestDispatcher("/paginas/admin/cliente/editarMembresia.jsp").forward(request, response);
		}
		
		private void actualizarMembresia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			//Instancio el nuevo objeto membresia y recupero los valores del formulario "editarMembresia.jsp"
			Membresia membresia = new Membresia();
			membresia.setIdMembresia(Integer.parseInt(request.getParameter("idMembresia")));
			membresia.setTipoMembresia(request.getParameter("tipoMembresia"));
			membresia.setPrecio(Double.parseDouble(request.getParameter("precio")));
			membresia.setMeses(Integer.parseInt(request.getParameter("meses")));
			String estado = request.getParameter("estado");
			if(estado!= null) {
		   		membresia.setEstado(true);
		   	}
		   	else {
		   		membresia.setEstado(false);
		   	}
			int registrosModificados = new LogicaMembresia().modificarMembresia(membresia);
			System.out.println("Registros-Modificados = " + registrosModificados); 
		   	this.accionDefault(request, response);
			
		}
		
		private void insertarMembresia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		   	//Instancio el nuevo objeto Membresia (el id no lo proporciono ya que es autoincrementable)
		    Membresia membresia = new Membresia();
		    membresia.setTipoMembresia(request.getParameter("tipoMembresia"));
		    membresia.setPrecio(Double.parseDouble(request.getParameter("precio")));
		    membresia.setMeses(Integer.parseInt(request.getParameter("meses")));
		    String estado = request.getParameter("estado");
		   	if(estado!= null) {membresia.setEstado(true);}
		   	else {membresia.setEstado(false);}
		   	
		  	//Inserto el nuevo objeto en la base de datos 
		    int registrosModificados = new LogicaMembresia().insertarNuevaMembresia(membresia);
		    System.out.println("Registros-Modificados = " + registrosModificados); //Lo muestro por consola para verificar

		    //Listar a todos los tipos de membresias
		   	this.listarMembresia(request, response);
		}
	    
		//(BAJA LÓGICA)
		private void cambiarEstadoMembresia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   	LogicaMembresia memDao=null;
	        Membresia membresia=null;
	        try {
	            memDao = new LogicaMembresia();
	            membresia = new Membresia();

	            if (request.getParameter("cambiarEstadoMembresia").equals("activar")) {
	                membresia.setEstado(true);
	            } else {
	                membresia.setEstado(false);
	            }

	            if (request.getParameter("idMembresia") != null) {
	                membresia.setIdMembresia(Integer.parseInt(request.getParameter("idMembresia")));
	                memDao.cambiarEstadoMembresia(membresia);
	            } else {
	                request.setAttribute("mensaje", "No se obtuvo el id de membresia");
	            }

	        } catch (Exception e) {
	            request.setAttribute("mensaje", e.getMessage());
	        }
	        HttpSession sesion = request.getSession();
			sesion.setAttribute("membresia", membresia);
			this.accionDefault(request, response);
	    }
		
		private void eliminarMembresia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	        
			//recupero los valores del formulario de editarMembresia
			int idMembresia = Integer.parseInt(request.getParameter("idMembresia"));
	     
			//Creo el objeto de membresia (entidades)
			Membresia membresia = new Membresia(); 
			membresia.setIdMembresia(idMembresia);

			//Elimino el  objeto en la base de datos
			int registrosModificados = new LogicaMembresia().eliminarMembresia(membresia);
			System.out.println("registrosModificados = " + registrosModificados);

			//Ahora redirijo a la accion por default asi me lista a todos los clientes
			this.accionDefault(request, response);
		}
	
}

package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import logica.LogicaLogin;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet({ "/ServletLogin", "/servletLogin", "/Servletlogin" })
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion"); 
		if(accion!=null && accion.equals("login") ) {
			this.verificarLogin(request, response); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
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
			request.getRequestDispatcher("/ServletControlador").include(request, response);
		}else if(usuario!= null && usuario.getRol().getDescripcion().equals("recepcionista")) 
		{
		
			HttpSession sesion = request.getSession();
			sesion.setAttribute("usuario", usuario);
			request.getRequestDispatcher("/ServletControlador").forward(request, response);
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
	
}

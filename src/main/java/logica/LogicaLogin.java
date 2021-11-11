package logica;

import java.util.LinkedList;

import datos.*;
import entidades.*;


public class LogicaLogin {
	private UsuarioDao usuarioDao;
	
	public LogicaLogin() {
		usuarioDao = new UsuarioDao();
	}
	
	public Usuario validar(Usuario usuario) {
		return usuarioDao.getByUser(usuario);	
	}
 
	public String validarRegistro(Usuario usuario) {
		if (usuarioDao.getByUser(usuario) == null) {
			usuario.setIdUsuario(usuarioDao.newUser(usuario));		
			return ("Usuario Creado: " + usuario.toString());
		}else return ("El usuario ya existe, utilice uno diferente");
	}
	
	public String validaModif(Usuario nuevo, Usuario viejo) {
		//System.out.println("id en ctrl "+nuevo.getLocalidad().getId());
		if (usuarioDao.getOne(nuevo) != null) {			
			return (usuarioDao.updateUsuario(nuevo, viejo));			
		}else return ("Error al modificar usuario");
	}
	
	public String validaBaja(Usuario viejo) {
		//System.out.println("id en ctrl "+nuevo.getLocalidad().getId());
		if (usuarioDao.getOne(viejo) != null) {			
			return (usuarioDao.bajaUsuario(viejo, viejo));			
		}else return ("Error al borrar usuario");
	}
	
	public LinkedList<Usuario> getAll(){
		return (LinkedList<Usuario>) usuarioDao.listarUsuarios();
	}

}

package logica;

import datos.RolDao;
import entidades.*;

public class RolLogica {
	
	private RolDao rolDao;
	//private HashMap<Integer,Rol> roles;
	
	public RolLogica() {
		//this.roles= new HashMap<>();
		rolDao = new RolDao();
	}
	
	public Rol getByDesc(Rol rolToSearch) {
		return rolDao.getByDesc(rolToSearch);
	}
	
	/*public Rol getRol(Rol rol) {
		return rolDao.getByDesc(rol);
	}
	

	public void setRol(CUsuario usuario) {
		 rolDao.setRol(usuario);
	}
	public void agregarRol(Rol agregarRol) {
		this.roles.put(agregarRol.getId(),agregarRol);
	}
	
	public void eliminarRol(Rol elimnarRol) {
		this.roles.remove(elimnarRol.getId());
	}*/
	
	
}

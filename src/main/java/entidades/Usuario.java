package entidades;

import java.util.*;

public class Usuario extends AbsPersona {
	private int idUsuario;
	private String password;
	private String fechaBaja;
	private Rol rol;
	private HashMap<Integer,Rol> roles;
	
	public Usuario() {
		this.roles= new HashMap<>();
	}
	
	public Rol getRol() {
		return this.rol;
	}
	public void agregarRol(Rol rol) {
		this.rol = rol;
	}
	public void addRol(Rol agregarRol) {
		this.roles.put(agregarRol.getId(),agregarRol);
	}
	
	public void eliminarRol(Rol elimnarRol) {
		this.roles.remove(elimnarRol.getId());
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	@Override
	public String toString() {
		return super.toString() + "CUsuario [idUsuario=" + idUsuario + ", password=" + password + ", fechaBaja=" + fechaBaja + ", rol="
				+ roles + "]";
	}

	
	
	
}

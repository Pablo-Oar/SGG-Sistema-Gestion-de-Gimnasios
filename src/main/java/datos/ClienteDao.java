package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import datos.*;
import entidades.*;

public class ClienteDao {
	private static final String SQL_SELECT = "SELECT *  FROM cliente";
	private static final String SQL_INSERT = "INSERT INTO cliente(nombre, apellido, email, dni, telefono, direccion, estado) VALUES(?,?,?,?,?,?,?) ";
	private static final String SQL_UPDATE = "UPDATE cliente SET nombre=?, apellido=?, email=?, dni=?, telefono=?, direccion=? WHERE idCliente = ? ";
	private static final String SQL_DELETE = "DELETE FROM cliente WHERE idCliente = ?";

			
public List<Cliente> listarClientes(){
	MembresiaDao memDao = new MembresiaDao();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	Cliente cliente = null;
	List<Cliente> clientes = new LinkedList<>();
				
	try {
		stmt= Conexion.getInstancia().getConnection().prepareStatement(SQL_SELECT,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		rs = stmt.executeQuery();
		if(rs!=null) {
			while(rs.next()) {
				cliente = new Cliente();
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setEmail( rs.getString("email"));
				cliente.setDni(rs.getString("dni"));
				cliente.setTelefono(rs.getString("telefono"));
				cliente.setDireccion( rs.getString("direccion"));
				cliente.setEstado( rs.getBoolean("estado"));
				memDao.setMembresia(cliente);
				clientes.add(cliente);}	}	} 
	catch (SQLException e) {
		e.printStackTrace(System.out);}
	finally {
		try {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			Conexion.getInstancia().releaseConn();}
		catch (SQLException e) {
			e.printStackTrace(System.out);}}
	return clientes; //Retorna la lista con todos los clientes.
}
			
			
public int insertarNuevoCliente(Cliente cliente){
	
	PreparedStatement stmt = null;
	ResultSet keyResultSet = null;
	int registrosModificados = 0;
	try {
		stmt = Conexion.getInstancia().getConnection().prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setString(1,cliente.getNombre());
		stmt.setString(2,cliente.getApellido());
		stmt.setString(3,cliente.getEmail());
		stmt.setString(4,cliente.getDni());
		stmt.setString(5,cliente.getTelefono());
		stmt.setString(6,cliente.getDireccion());
		stmt.setBoolean(7,cliente.isEstado());
					
		registrosModificados = stmt.executeUpdate();
		keyResultSet=stmt.getGeneratedKeys();
		if(keyResultSet!=null && keyResultSet.next()){
			cliente.setIdCliente(keyResultSet.getInt(1));}
		}
	catch (SQLException e) {
		System.out.println("Ocurrio un error al insertar un nuevo cliente...");
		e.printStackTrace(System.out);}
	finally {
		try {
			if(stmt!=null) {stmt.close();}
			Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			e.printStackTrace(System.out);}}
	
	return registrosModificados;
}
			

public int modificarCliente(Cliente cliente){
	PreparedStatement stmt = null;
	int registrosModificados = 0;
	try {
		stmt=Conexion.getInstancia().getConnection().prepareStatement(SQL_UPDATE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		stmt.setString(1,cliente.getNombre());
		stmt.setString(2,cliente.getApellido());
		stmt.setString(3,cliente.getEmail());
		stmt.setString(4,cliente.getDni());
		stmt.setString(5,cliente.getTelefono());
		stmt.setString(6,cliente.getDireccion());
		stmt.setInt(7,cliente.getIdCliente());
					
		registrosModificados = stmt.executeUpdate();
		} 
		
	catch (SQLException e) {
		e.printStackTrace();}
	finally {
		try {
			if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			e.printStackTrace();}}
	return registrosModificados;
}
			
public int eliminarCliente(Cliente cliente){
	MembresiaDao membresia = new MembresiaDao();
	PreparedStatement stmt = null;
	int registrosModificados = 0;
	try {
		stmt=Conexion.getInstancia().getConnection().prepareStatement(SQL_DELETE);
		stmt.setInt(1,cliente.getIdCliente());
		registrosModificados = stmt.executeUpdate();} 
	catch (SQLException e) {
		e.printStackTrace();}
	finally {
		try {
			if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			e.printStackTrace();}}
	return registrosModificados;
}
			
public List<Cliente> encontrarClienteApellido(Cliente cliente){
	MembresiaDao memDao = new MembresiaDao();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	List<Cliente> clientes = new LinkedList<>();
	try {
		stmt=Conexion.getInstancia().getConnection().prepareStatement("SELECT * FROM cliente WHERE apellido = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		stmt.setString(1,cliente.getApellido());
		rs = stmt.executeQuery();
		if(rs!=null) {
			while(rs.next()) {
				cliente = new Cliente();
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setEmail( rs.getString("email"));
				cliente.setDni(rs.getString("dni"));
				cliente.setTelefono(rs.getString("telefono"));
				cliente.setDireccion( rs.getString("direccion"));
				cliente.setEstado( rs.getBoolean("estado"));
				memDao.setMembresia(cliente);
				clientes.add(cliente);}	}	} 
	catch (SQLException e) {
		e.printStackTrace();}
	finally {
		try {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			System.out.println("Ocurrio un error al encontrar el cliente por su apellido...");
			e.printStackTrace(System.out);}}
	return clientes;
}
					
public List<Cliente> encontrarClienteDni(Cliente cliente){
	MembresiaDao memDao = new MembresiaDao();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	List<Cliente> clientes = new LinkedList<>();
	Cliente cli;
	try {
		stmt=Conexion.getInstancia().getConnection().prepareStatement("SELECT * FROM cliente WHERE dni = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		stmt.setString(1,cliente.getDni());
		rs = stmt.executeQuery();
		if(rs!=null) {
			while(rs.next()) {
				cli = new Cliente();
				cli.setIdCliente(rs.getInt("idCliente"));
				cli.setNombre(rs.getString("nombre"));
				cli.setApellido(rs.getString("apellido"));
				cli.setEmail( rs.getString("email"));
				cli.setDni(rs.getString("dni"));
				cli.setTelefono(rs.getString("telefono"));
				cli.setDireccion( rs.getString("direccion"));
				cli.setEstado( rs.getBoolean("estado"));
				memDao.setMembresia(cli);
				clientes.add(cli);}	}	} 
	catch (SQLException e) {
		e.printStackTrace();}
	finally {
		try {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			System.out.println("Ocurrio un error al encontrar el cliente por su apellido...");
			e.printStackTrace(System.out);}	}
	return clientes;
}
					
public Cliente encontrarClienteId(Cliente cliente){
	MembresiaDao memDao = new MembresiaDao();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	Cliente cli = null;
	try {
		stmt=Conexion.getInstancia().getConnection().prepareStatement("SELECT * FROM cliente WHERE idCliente = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		stmt.setInt(1,cliente.getIdCliente());
		rs = stmt.executeQuery();
		if(rs!=null && rs.next()) {
			cli = new Cliente();
			cli.setIdCliente(rs.getInt("idCliente"));
			cli.setNombre(rs.getString("nombre"));
			cli.setApellido(rs.getString("apellido"));
			cli.setEmail( rs.getString("email"));
			cli.setDni(rs.getString("dni"));
			cli.setTelefono(rs.getString("telefono"));
			cli.setDireccion( rs.getString("direccion"));
			memDao.setMembresia(cli);}
			cli.setEstado( rs.getBoolean("estado"));} 
	
	catch (SQLException e) {
		e.printStackTrace();}
	finally {
		try {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			System.out.println("Ocurrio un error al encontrar el cliente por su apellido...");
			e.printStackTrace(System.out);}}
	return cli;
}
		
			
public void cambiarEstadoCliente(Cliente cliente) throws Exception {
	String sql = "UPDATE cliente SET estado = ? WHERE idCliente = ?";
	PreparedStatement stmt = null;
	try {
		stmt=Conexion.getInstancia().getConnection().prepareStatement(sql);
		stmt.setBoolean(1, cliente.isEstado());
		stmt.setInt(2,cliente.getIdCliente());
		stmt.executeUpdate();} 
	catch (Exception e) {
		e.printStackTrace();}
	finally {
		try{
			if(stmt!=null) {stmt.close();}
			Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			e.printStackTrace();}}
}
			
}

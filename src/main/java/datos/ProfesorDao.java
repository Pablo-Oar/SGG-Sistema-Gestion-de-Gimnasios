package datos;
import entidades.*;
import java.sql.*;
import java.util.*;

public class ProfesorDao {

	private static final String SQL_INSERT = "INSERT INTO profesor"
			+ "(nombre, apellido, email, dni, telefono, direccion, estado,horario_inicio, horario_fin) "
			+ "VALUES(?,?,?,?,?,?,?,?,?) ";
	private static final String SQL_UPDATE = "UPDATE profesor SET nombre=?, apellido=?, email=?, dni=?, telefono=?, direccion=?, estado=?,"
			+ "horario_inicio = ?, horario_fin = ?  WHERE idProfesor = ? ";
	private static final String SQL_DELETE = "DELETE FROM profesor WHERE idprofesor = ?";
	
	public List<Profesor> recuperarProfesores(){
	Profesor profesor;
	List<Profesor> profesores = new ArrayList<Profesor>();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	AreaDao area = new AreaDao();
	try {
		stmt = Conexion.getInstancia().getConnection().prepareStatement("SELECT * FROM profesor");
		rs = stmt.executeQuery();
		//Mapeo los profesores desde la base de datos al abjeto Profesor Java.
		while(rs.next()) {
			profesor = new Profesor();
			profesor.setIdProfesor(rs.getInt("idprofesor"));
			profesor.setNombre(rs.getString("nombre"));
			profesor.setApellido(rs.getString("apellido"));
			profesor.setDni(rs.getString("dni"));
			profesor.setEmail(rs.getString("email"));
			profesor.setHorario_inicio(rs.getString("horario_inicio"));
			profesor.setHorario_fin(rs.getString("horario_fin"));
			profesor.setTelefono(rs.getString("telefono"));
			profesor.setEstado(rs.getBoolean("estado"));
			//Mapeo el area al que pertenece el profesor.
			area.setArea(profesor);
			profesores.add(profesor);
			
		}
	}catch(Exception e) {
		e.printStackTrace(System.out);
	}finally {
		try 
		{
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			Conexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace(System.out);}
	}
	return profesores;
	}
	
	public int insertarNuevoProfesor(Profesor profesor)
	{
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		int registrosModificados = 0;
		try {
			stmt = Conexion.getInstancia().getConnection().prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1,profesor.getNombre());
			stmt.setString(2,profesor.getApellido());
			stmt.setString(3,profesor.getEmail());
			stmt.setString(4,profesor.getDni());
			stmt.setString(5,profesor.getTelefono());
			stmt.setString(6,profesor.getDireccion());
			stmt.setBoolean(7,profesor.isEstado());
			stmt.setString(8, profesor.getHorario_inicio());
			stmt.setString(9, profesor.getHorario_fin());
			
			registrosModificados = stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				profesor.setIdProfesor(keyResultSet.getInt(1));
            }
		} catch (SQLException e) {
			System.out.println("Ocurrio un error al insertar un nuevo profesor...");
			e.printStackTrace(System.out);
		}
		finally 
		{
			try 
			{
				if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();
			} catch (SQLException e) {

				e.printStackTrace(System.out);}
		}
		return registrosModificados;
	}
	
	public int modificarProfesor(Profesor profesor)
	{
		PreparedStatement stmt = null;
		int registrosModificados = 0;
		try {
			stmt=Conexion.getInstancia().getConnection().prepareStatement(SQL_UPDATE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1,profesor.getNombre());
			stmt.setString(2,profesor.getApellido());
			stmt.setString(3,profesor.getEmail());
			stmt.setString(4,profesor.getDni());
			stmt.setString(5,profesor.getTelefono());
			stmt.setString(6,profesor.getDireccion());
			stmt.setBoolean(7,profesor.isEstado());
			stmt.setString(8,profesor.getHorario_inicio());
			stmt.setString(9,profesor.getHorario_fin());
			stmt.setInt(10,profesor.getIdProfesor());
						
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
	
	
	public int eliminarProfesor(Profesor profesor)
	{
		PreparedStatement stmt = null;
		int registrosModificados = 0;
		try {
			stmt=Conexion.getInstancia().getConnection().prepareStatement(SQL_DELETE);
			stmt.setInt(1,profesor.getIdProfesor());
			registrosModificados = stmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();
			} catch (SQLException e) {

				e.printStackTrace();}
		}
		return registrosModificados;
	}
	
}

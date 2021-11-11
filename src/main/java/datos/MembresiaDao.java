package datos;

import java.sql.*;
import java.util.LinkedList;
import entidades.*;

public class MembresiaDao {

	private static final String SQL_SELECT = "SELECT * FROM membresia";
	private static final String SQL_UPDATE = "UPDATE membresia SET tipoMembresia=?, precio=?, meses=?, estado=?  WHERE idMembresia = ? ";
	private static final String SQL_DELETE = "DELETE FROM membresia WHERE idCliente = ?";
	
	public LinkedList<Membresia> listarMembresias(){
		Membresia mem = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Membresia> membresias = new LinkedList<>();
		try 
		{
			stmt= Conexion.getInstancia().getConnection().prepareStatement(SQL_SELECT);
			rs= stmt.executeQuery();
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					mem = new Membresia();
					mem.setIdMembresia(rs.getInt("idMembresia"));
					mem.setTipoMembresia(rs.getString("tipoMembresia"));
					mem.setPrecio(rs.getDouble("precio"));
					mem.setMeses(rs.getInt("meses"));
					membresias.add(mem);
				}	
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return membresias;
}
	
public LinkedList<Membresia> getByType(Membresia membresiaABuscar) {
	Membresia membresia=null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	LinkedList<Membresia> membresias = new LinkedList<>();
	try {
		stmt=Conexion.getInstancia().getConnection().prepareStatement("SELECT * FROM membresia WHERE tipoMembresia=?");
		stmt.setString(1, membresiaABuscar.getTipoMembresia());
		rs=stmt.executeQuery();
		if(rs!=null && rs.next()) {
			membresia = new Membresia();
			membresia.setIdMembresia(rs.getInt("idMembresia"));
			membresia.setTipoMembresia(rs.getString("tipoMembresia"));
			membresia.setPrecio(rs.getDouble("precio"));
			membresia.setMeses(rs.getInt("meses"));
			membresias.add(membresia);}	} 
	catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Ocurrio un error al intentar obtener la membresia...");}
	
	finally {
		try {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			e.printStackTrace();}}
	return membresias;
}	
	
public void setMembresia(Cliente cliente) {
	PreparedStatement stmt = null;
	ResultSet rs = null;
	try {
		stmt = Conexion.getInstancia().getConnection().prepareStatement("SELECT membresia.* FROM membresia "
							+ "INNER JOIN membresia_cliente  ON membresia.idMembresia = membresia_cliente.idMembresia "
							+ "WHERE idMembresiaCliente = ?");
		stmt.setInt(1, cliente.getIdCliente());
		rs = stmt.executeQuery();
		if(rs!=null) {
			while(rs.next()) {
				Membresia mem = new Membresia();
				mem.setIdMembresia(rs.getInt("idMembresia"));
				mem.setTipoMembresia(rs.getString("tipoMembresia"));
				mem.setPrecio(rs.getDouble("precio"));
				mem.setMeses(rs.getInt("meses"));
				cliente.setMembresia(mem);}	}	} 
	catch (SQLException e) {
		System.out.println("Ocurrio un error al setear la membresia...");
		e.printStackTrace(System.out);}
	finally {
		try {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			System.out.println("Ocurrio un error al intentar cerrar conexión...");
			e.printStackTrace(System.out);}	}
}

public int insertarMembresia(Membresia membresia) {
	PreparedStatement stmt= null;
	ResultSet keyResultSet=null;
	int registrosModificados = 0;
	try {
		stmt=Conexion.getInstancia().getConnection().
				prepareStatement("INSERT INTO membresia(tipoMembresia, precio, meses, estado) VALUES(?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setString(1, membresia.getTipoMembresia());
		stmt.setDouble(2, membresia.getPrecio());
		stmt.setInt(3, membresia.getMeses());
		stmt.setBoolean(4, membresia.isEstado());
		registrosModificados = stmt.executeUpdate();
		keyResultSet = stmt.getGeneratedKeys();
        if(keyResultSet!=null && keyResultSet.next()){
            membresia.setIdMembresia(keyResultSet.getInt(1));}	} 
	catch (SQLException e) {
        e.printStackTrace(); 
		System.out.println("Ocurrio un error al insertar una nueva  membresia...");}
	finally {
		try {
			if(keyResultSet!=null)keyResultSet.close();
            if(stmt!=null)stmt.close();
            Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			e.printStackTrace();}	}
	return registrosModificados;

}

public int insertarIdMemebresia(Cliente cliente) {
	int registroModificado = 0;
	PreparedStatement stmt = null;
	try {
		stmt = Conexion.getInstancia().getConnection().prepareStatement("INSERT INTO membresia_cliente(idMembresiaCliente, idMembresia) VALUES(?,?)");
		stmt.setInt(1,cliente.getIdCliente());
		if(cliente.getTipoMembresia().equals("Mensual")) {
			stmt.setInt(2,1);
		}else if(cliente.getTipoMembresia().equals("Bimestral")){
			stmt.setInt(2,2);
		}
		registroModificado = stmt.executeUpdate();
		}
	catch (SQLException e) {
		System.out.println("Ocurrio un error al insertar ids...");
		e.printStackTrace(System.out);}
	finally {
		try {
			if(stmt!=null) {stmt.close();}
			Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			e.printStackTrace(System.out);}}
	return registroModificado;
}

public int modificarMembresia(Membresia membresia){
	PreparedStatement stmt = null;
	int registrosModificados = 0;
	try {
		stmt=Conexion.getInstancia().getConnection().prepareStatement(SQL_UPDATE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		stmt.setString(1,membresia.getTipoMembresia());
		stmt.setDouble(2,membresia.getPrecio());
		stmt.setInt(3,membresia.getMeses());
		stmt.setBoolean(4,membresia.isEstado());
		stmt.setInt(5,membresia.getIdMembresia());
					
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


public int modificarMem(Cliente cliente){
	PreparedStatement stmt = null;
	int registrosModificados = 0;
	Membresia membresia = new Membresia();
	try {	
		stmt=Conexion.getInstancia().getConnection().prepareStatement("select * from membresia m"
				+ "INNER JOIN membresia_cliente mc ON m.idMembresia = mc.idMembresia"
				+ "INNER JOIN cliente c ON mc.idMembresiaCliente = c.idCliente"
				+ "where c.idCliente = ?;",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		stmt.setInt(1, cliente.getIdCliente());
		if(cliente.getTipoMembresia().equals("Mensual")) {
			stmt.setString(1,membresia.getTipoMembresia());
			stmt.setDouble(2,membresia.getPrecio());
			stmt.setInt(3,membresia.getMeses());
			stmt.setBoolean(4,membresia.isEstado());
			stmt.setInt(5,1);
		}
		else if(cliente.getTipoMembresia().equals("Bimestral")) {
			stmt.setString(1,membresia.getTipoMembresia());
			stmt.setDouble(2,membresia.getPrecio());
			stmt.setInt(3,membresia.getMeses());
			stmt.setBoolean(4,membresia.isEstado());
			stmt.setInt(5,2);
		}
					
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

public int modificarMembresiaTipo(Cliente cliente) {
	
	int registrosModificados = 0;
	PreparedStatement stmt=null;
	try {
		
		
		  stmt=Conexion.getInstancia().getConnection().
		  prepareStatement("UPDATE membresia m INNER JOIN membresia_cliente mc ON m.idMembresia = mc.idMembresia INNER JOIN cliente c ON mc.idMembresiaCliente = c.idCliente SET mc.idMembresia = ? WHERE c.idCliente = ?;");
		 
		stmt.setInt(2, cliente.getIdCliente());
		if(cliente.getTipoMembresia().equals("Mensual")) {
			stmt.setInt(1,1);
		}
		else if(cliente.getTipoMembresia().equals("Bimestral")) {
			stmt.setInt(1,2);
		}
					
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


public int setearTipoMembresia(Cliente cliente ) {
	PreparedStatement stmt= null;
	ResultSet keyResultSet=null;
	Membresia membresia = new Membresia();
	int registrosModificados = 0;
	try {
		
		if(cliente.getTipoMembresia().equals("Mensual")) {
			stmt=Conexion.getInstancia().getConnection().prepareStatement("INSERT INTO membresia(tipoMembresia, precio, meses, estado) VALUES(?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cliente.getTipoMembresia());
			stmt.setDouble(2, 2000.0);
			stmt.setInt(3, 1);
			stmt.setBoolean(4,true);
		}
		else if(cliente.getTipoMembresia().equals("Bimestral")) {
			stmt=Conexion.getInstancia().getConnection().prepareStatement("INSERT INTO membresia(tipoMembresia, precio, meses, estado) VALUES(?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cliente.getTipoMembresia());
			stmt.setDouble(2, 3600.0);
			stmt.setInt(3, 2);
			stmt.setBoolean(4,true);
		}
		registrosModificados = stmt.executeUpdate();
		keyResultSet = stmt.getGeneratedKeys();
        if(keyResultSet!=null && keyResultSet.next()){
            membresia.setIdMembresia(keyResultSet.getInt(1));}	} 
	catch (SQLException e) {
        e.printStackTrace(); 
		System.out.println("Ocurrio un error al insertar una nueva  membresia...");}
	finally {
		try {
			if(keyResultSet!=null)keyResultSet.close();
            if(stmt!=null)stmt.close();
            Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			e.printStackTrace();}	}
	return registrosModificados;
}

public int eliminarMembresia(Cliente cliente){
	PreparedStatement stmt = null;
	int registrosModificados = 0;
	try {
		stmt=Conexion.getInstancia().getConnection().prepareStatement(SQL_DELETE);
		stmt.setInt(1,cliente.getIdCliente());
		registrosModificados = stmt.executeUpdate();} 
	catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Ocurrio un error al intentar eliminar la membresia...");}
	finally {
		try {
			if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			e.printStackTrace();}}
	return registrosModificados;
}

public Membresia encontrarMembresiaById(Membresia membresia){
	Membresia mem = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	try {
		stmt=Conexion.getInstancia().getConnection().prepareStatement("SELECT * FROM membresia WHERE idMembresia = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		stmt.setInt(1,membresia.getIdMembresia());
		rs = stmt.executeQuery();
		if(rs!=null && rs.next()) {
			mem = new Membresia();
			mem.setIdMembresia(rs.getInt("idMembresia"));
			mem.setTipoMembresia(rs.getString("tipoMembresia"));
			mem.setPrecio(rs.getDouble("precio"));
			mem.setMeses( rs.getInt("meses"));
			mem.setEstado( rs.getBoolean("estado"));}}
	catch (SQLException e) {
		e.printStackTrace();}
	finally {
		try {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			System.out.println("Ocurrio un error al encontrar la membresia por su id...");
			e.printStackTrace(System.out);}}
	return membresia;
}

public void cambiarEstadoMembresia(Membresia membresia) throws Exception {
	//String SQL_ESTADO_MEMBRESIA = "UPDATE membresia SET estado = " + (membresia.isEstado() == true ? "1" : "0")+ " WHERE idMembresia = ?";
	String SQL_ESTADO_MEMBRESIA="UPDATE membresia SET estado = ? WHERE idMembresia = ?;";
	PreparedStatement stmt = null;
	try {
		stmt=Conexion.getInstancia().getConnection().prepareStatement(SQL_ESTADO_MEMBRESIA);
		stmt.setBoolean(1, membresia.isEstado());
		stmt.setInt(2,membresia.getIdMembresia());
		stmt.executeUpdate();} 
	catch (Exception e) {
		System.out.println("Error al cambiar estado de la membresia...");
		e.printStackTrace();}
	finally {
		try{
			if(stmt!=null) {stmt.close();}
			Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			e.printStackTrace();}}
}
	
	
}//Fin Clase

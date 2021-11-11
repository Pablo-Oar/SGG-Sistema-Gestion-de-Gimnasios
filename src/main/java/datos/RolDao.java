package datos;

import java.sql.*;
import java.util.*;
import entidades.*;

public class RolDao {
	
	public LinkedList<Rol> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Rol> roles= new LinkedList<>();
		
		try {
			stmt= Conexion.getInstancia().getConnection().createStatement();
			rs= stmt.executeQuery("SELECT * FROM rol");
			if(rs!=null) {
				while(rs.next()) {
					Rol r=new Rol();
					r.setId(rs.getInt("id"));
					r.setDescripcion(rs.getString("descripcion"));
					roles.add(r);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return roles;
	}
	
	public Rol getById(Rol rolToSearch) {
		Rol rol=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=Conexion.getInstancia().getConnection().prepareStatement("SELECT * FROM rol WHERE id=?");
			stmt.setInt(1, rolToSearch.getId());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				rol=new Rol();
				rol.setId(rs.getInt("id"));
				rol.setDescripcion(rs.getString("descripcion"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rol;
	}
	
	public Rol getByDesc(Rol rolToSearch) {
		Rol r=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=Conexion.getInstancia().getConnection().prepareStatement("SELECT * FROM rol WHERE descripcion=?");
			stmt.setString(1, rolToSearch.getDescripcion());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				r=new Rol();
				r.setId(rs.getInt("id"));
				r.setDescripcion(rs.getString("descripcion"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return r;
	}
	
	//Agregar un nuevo Rol al usuario.
	public void setRol(Usuario usuario) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
			stmt = Conexion.getInstancia().getConnection().prepareStatement("SELECT rol.* FROM rol "
															+ "INNER JOIN rol_usuario ON rol.id=rol_usuario.id_rol "
															+ "WHERE id_usuario=?");
			stmt.setInt(1, usuario.getIdUsuario());
			rs = stmt.executeQuery();
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					Rol rol = new Rol();
					rol.setId(rs.getInt("id"));
					rol.setDescripcion(rs.getString("descripcion"));
					usuario.agregarRol(rol);;
				}
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Ocurrio un error al setear el rol...");
			e.printStackTrace(System.out);
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
				System.out.println("Ocurrio un error al intentar cerrar conexión...");
				e.printStackTrace(System.out);
			}
		}
	}
	
	public void add(Rol rol) 
	{
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=Conexion.getInstancia().getConnection().
					prepareStatement("INSERT INTO rol(descripcion) VALUES(?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, rol.getDescripcion());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                rol.setId(keyResultSet.getInt(1));
            }

			
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                Conexion.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}

	}
	
	public void update(Rol rol) {
		PreparedStatement stmt= null;
		try {
			stmt= Conexion.getInstancia().getConnection().
					prepareStatement("UPDATE rol SET descripcion=? WHERE id=?");
			stmt.setString(1, rol.getDescripcion());
			stmt.setInt(2, rol.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                Conexion.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
	public void remove(Rol rol) {
		PreparedStatement stmt= null;
		try {
			stmt=Conexion.getInstancia().getConnection().
					prepareStatement("DELETE FROM rol WHERE id=?");
			stmt.setInt(1, rol.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                Conexion.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
}

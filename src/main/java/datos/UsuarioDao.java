package datos;

import java.sql.*;
import java.util.*;
import entidades.*;



//Primera Opcion----------------------------------------------------------------------------------------------------------------
public class UsuarioDao {
	//Defino todas mis Querys
	private static final String SQL_SELECT = "SELECT idUsuario, email FROM usuario";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM usuario WHERE idUsuario = ?";
	private static final String SQL_SELECT_BY_USER = "SELECT idUsuario,email FROM usuario WHERE email=? and password=?";
	private static final String SQL_INSERT = "INSERT INTO usuario(email,password) VALUES(?,?) ";
	private static final String SQL_UPDATE = "UPDATE usuario SET email=?, password=?  WHERE idUsuario = ? ";
	private static final String SQL_DELETE = "DELETE FROM usuario WHERE idUsuario =?";
	/*private static final String SQL_GET_ROL = "SELECT rol.descripcion FROM rol "
															+ "INNER JOIN rol_usuario rus ON rol.id = rus.id_rol"
															+ "INNER JOIN usuario us ON rus.id_usuario = us.idUsuario"
															+ "WHERE rol.descripcion = ?";*/
	
	public List<Usuario> listarUsuarios()
	{
		RolDao dr = new RolDao();
		PreparedStatement stmt = null;
        ResultSet rs = null;
		Usuario usuario = null;
		List<Usuario> usuarios = new LinkedList<>();
		
		try {
			stmt= Conexion.getInstancia().getConnection().prepareStatement(SQL_SELECT,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					usuario = new Usuario();
					usuario.setIdUsuario(rs.getInt("idUsuario"));
					usuario.setEmail( rs.getString("email"));
					
					dr.setRol(usuario);
					usuarios.add(usuario);		
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
		finally 
		{
			try 
			{
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace(System.out);}
		}
		return usuarios; //Retorna la lista con todos los clientes.
	}
	

	public int insertarNuevoUsuario(Usuario usuario)
	{
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		int registrosModificados = 0;
		try {
			stmt = Conexion.getInstancia().getConnection().prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1,usuario.getEmail());
			stmt.setString(2,usuario.getPassword());
			
			registrosModificados = stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				usuario.setIdUsuario(keyResultSet.getInt(1));
            }
		} catch (SQLException e) {
			System.out.println("Ocurrio un error al insertar un nuevo usuario...");
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
	
	public int modificarUsuario(Usuario usuario)
	{
		PreparedStatement stmt = null;
		int registrosModificados = 0;
		try {
			stmt=Conexion.getInstancia().getConnection().prepareStatement(SQL_UPDATE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1,usuario.getEmail());
			stmt.setString(2,usuario.getPassword());
			stmt.setInt(3,usuario.getIdUsuario());
			
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
	
	
	public int eliminarUsuario(Usuario usuario)
	{
		PreparedStatement stmt = null;
		int registrosModificados = 0;
		try {
			stmt=Conexion.getInstancia().getConnection().prepareStatement(SQL_DELETE);
			stmt.setInt(1,usuario.getIdUsuario());
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
	
	public int newUser(Usuario usuario) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=Conexion.getInstancia().getConnection().prepareStatement("INSERT INTO usuario(email,password) values(?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, usuario.getPassword());
			stmt.setString(2, usuario.getEmail());
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                usuario.setIdUsuario(keyResultSet.getInt(1));
            }

			
		}  catch (SQLException e) {
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
		System.out.println("id creada " + usuario.getIdUsuario());
		return usuario.getIdUsuario();
    }
	
	public Usuario getOne(Usuario usuario) {
		Usuario user=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=Conexion.getInstancia().getConnection().prepareStatement(SQL_SELECT_BY_ID);
			stmt.setInt(1, usuario.getIdUsuario());	
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				user=new Usuario();
				user.setIdUsuario(rs.getInt("idUsuario"));
				user.setEmail(rs.getString("email"));
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
		
		return user;
	}


	public Usuario getByUser(Usuario usuario) {
		RolDao dr=new RolDao();
		Usuario user=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt= Conexion.getInstancia().getConnection().prepareStatement(SQL_SELECT_BY_USER,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getPassword());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				user = new Usuario();
				user.setIdUsuario(rs.getInt("idUsuario"));
				user.setEmail(rs.getString("email"));
				//
				dr.setRol(user);
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
		
		return user;
	}
	
	public String updateUsuario(Usuario nuevo, Usuario old) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt= Conexion.getInstancia().getConnection().prepareStatement("UPDATE usuario set email=?,password=? WHERE idUsuario=?",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, nuevo.getEmail());
			stmt.setString(2, nuevo.getPassword());
			stmt.setInt(3,nuevo.getIdUsuario());
			
			System.out.println("id vieja:" + old.getIdUsuario());
			stmt.executeUpdate();			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                nuevo.setIdUsuario(keyResultSet.getInt(1));                
            }			
		}  catch (SQLException e) {
            e.printStackTrace();
            return ("Error: "+ e);
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                Conexion.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            } 
		}
		return("Usuario " + old.getIdUsuario() + " modificado correctamente");
    }
	
	public String bajaUsuario(Usuario nuevo, Usuario viejo) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt= Conexion.getInstancia().getConnection().prepareStatement("UPDATE usuario SET fecha_baja=? WHERE idUsuario=?",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, nuevo.getFechaBaja());
			stmt.setInt(2, viejo.getIdUsuario());			
			System.out.println("seteados" + nuevo.toString());
			System.out.println("id vieja:" + viejo.getIdUsuario());
			stmt.executeUpdate();			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                nuevo.setIdUsuario(keyResultSet.getInt(1));                
            }			
		}  catch (SQLException e) {
            e.printStackTrace();
            return ("Error: "+ e);
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                Conexion.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		return("Usuario " + viejo.getIdUsuario() + " modificado correctamente");
    }
	

}

//Segunda Opcion----------------------------------------------------------------------------------------------------------------

//public class CUsuarioDao {
//
//    private static final String SQL_SELECT = "SELECT idCliente, nombre, apellido, email, telefono, direccion "
//            + " FROM clientes";
//
//    private static final String SQL_SELECT_BY_ID = "SELECT idCliente, nombre, apellido, email, telefono, direccion "
//            + " FROM clientes WHERE idCliente = ?";
//
//   private static final String SQL_INSERT = "INSERT INTO clientes(nombre, apellido, email, telefono, direccion) "
//           + " VALUES(?, ?, ?, ?, ?)";
//
//   private static final String SQL_UPDATE = "UPDATE clientes "
//          + " SET nombre=?, apellido=?, email=?, telefono=?, direccion=? WHERE idCliente=?";
//
//   private static final String SQL_DELETE = "DELETE FROM clientes WHERE idCliente = ?";
//
//  public List<CUsuario> listar() {
//       Connection conn = null;
//        PreparedStatement stmt = null;
//      ResultSet rs = null;
//       CUsuario cliente = null;
//       List<CUsuario> clientes = new ArrayList<>();
//        try {
//            conn = CConexion.getConnection();
//            stmt = conn.prepareStatement(SQL_SELECT);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//               cliente = new CUsuario();
//				 cliente.setIdCliente(rs.getInt("idCliente"));
//				 cliente.setNombre(rs.getString("nombre"));
//				 cliente.setApellido(rs.getString("apellido"));
//				 cliente.setEmail( rs.getString("email"));
//				 cliente.setTelefono(rs.getString("telefono"));
//				 cliente.setDireccion( rs.getString("direccion"));
//
//				 clientes.add(cliente);		
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//            CConexion.close(rs);
//            CConexion.close(stmt);
//            CConexion.close(conn);
//        }
//        return clientes;
//    }
//
//    public CUsuario encontrarCliente(CUsuario cliente) {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//            conn = CConexion.getConnection();
//            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
//            stmt.setInt(1, cliente.getIdCliente());
//            rs = stmt.executeQuery();
//            rs.absolute(1);//nos posicionamos en el primer registro devuelto
//
//            String nombre = rs.getString("nombre");
//            String apellido = rs.getString("apellido");
//            String email = rs.getString("email");
//            String telefono = rs.getString("telefono");
//            String direccion = rs.getString("direccion");
//
//            cliente.setNombre(nombre);
//            cliente.setApellido(apellido);
//            cliente.setEmail(email);
//            cliente.setTelefono(telefono);
//            cliente.setDireccion(direccion);;
//
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//            CConexion.close(rs);
//            CConexion.close(stmt);
//            CConexion.close(conn);
//        }
//        return cliente;
//    }
//
//    public int insertarNuevoCliente(CUsuario cliente) {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        int rows = 0;
//        try {
//            conn = CConexion.getConnection();
//            stmt = conn.prepareStatement(SQL_INSERT);
//            stmt.setString(1, cliente.getNombre());
//            stmt.setString(2, cliente.getApellido());
//            stmt.setString(3, cliente.getEmail());
//            stmt.setString(4, cliente.getTelefono());
//            stmt.setString(5, cliente.getDireccion());
//            
//            rows = stmt.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//            CConexion.close(stmt);
//            CConexion.close(conn);
//        }
//        return rows;
//    }
//
//    public int modificarCliente(CUsuario cliente) {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        int rows = 0;
//        try {
//            conn = CConexion.getConnection();
//            stmt = conn.prepareStatement(SQL_UPDATE);
//            stmt.setString(1, cliente.getNombre());
//            stmt.setString(2, cliente.getApellido());
//            stmt.setString(3, cliente.getEmail());
//            stmt.setString(4, cliente.getTelefono());
//            stmt.setString(5, cliente.getDireccion());
//            stmt.setInt(6, cliente.getIdCliente());
//
//            rows = stmt.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//            CConexion.close(stmt);
//            CConexion.close(conn);
//        }
//        return rows;
//    }
//
//    public int eliminarCliente(CUsuario cliente) {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        int rows = 0;
//        try {
//            conn = CConexion.getConnection();
//            stmt = conn.prepareStatement(SQL_DELETE);
//            stmt.setInt(1, cliente.getIdCliente());
//
//            rows = stmt.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//            CConexion.close(stmt);
//            CConexion.close(conn);
//        }
//        return rows;
//    }
//
//}


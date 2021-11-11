package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import entidades.*;

public class CategoriaDao {
	private static final String SQL_SELECT = "SELECT * FROM categoria";
	private static final String SQL_UPDATE = "UPDATE categoria SET descripcion=?, estado=?  WHERE idCategoria = ? ";
	private static final String SQL_DELETE = "DELETE FROM categoria WHERE idCategoria = ?";
	
public LinkedList<CategoriaProducto> listarCategorias()
{
	CategoriaProducto categoria = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	LinkedList<CategoriaProducto> categorias = new LinkedList<>();
	try 
	{
		stmt= Conexion.getInstancia().getConnection().prepareStatement(SQL_SELECT);
		rs= stmt.executeQuery();
		if(rs!=null) 
		{
			while(rs.next()) 
			{
				categoria = new CategoriaProducto();
				categoria.setIdCategoria(rs.getInt("idCategoria"));
				categoria.setDescripcion(rs.getString("descripcion"));
				categoria.setEstado(rs.getBoolean("estado"));
				categorias.add(categoria);
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
	return categorias;
}
	
public LinkedList<CategoriaProducto> getByType(CategoriaProducto membresiaABuscar) 
{
	CategoriaProducto categoria=null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	LinkedList<CategoriaProducto> categorias = new LinkedList<>();
	try {
		stmt=Conexion.getInstancia().getConnection().prepareStatement("SELECT * FROM membresia WHERE descripcion=?");
		stmt.setString(1, membresiaABuscar.getDescripcion());
		rs=stmt.executeQuery();
		if(rs!=null && rs.next()) {
			categoria = new CategoriaProducto();
			categoria.setIdCategoria(rs.getInt("idCategoria"));
			categoria.setDescripcion(rs.getString("descripcion"));
			categoria.setEstado(rs.getBoolean("estado"));
			categorias.add(categoria);}	} 
	catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Ocurrio un error al intentar obtener la categoria...");}
	
	finally {
		try {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			e.printStackTrace();}}
	return categorias;
}	

public int insertarIdCategoria(Producto producto) {
	int registroModificado = 0;
	PreparedStatement stmt = null;
	try {
		stmt = Conexion.getInstancia().getConnection().prepareStatement("INSERT INTO categoria_producto(idcategoria_producto, idcategoria_categoria) VALUES(?,?)");
		stmt.setInt(1,producto.getIdProducto());
		if(producto.getDescripcionCategoria().equals("Vestimenta")) {
			stmt.setInt(2,1);
		}else if(producto.getDescripcionCategoria().equals("Suplementos")){
			stmt.setInt(2,2);
		}else if(producto.getDescripcionCategoria().equals("Articulos Entrenamiento")){
			stmt.setInt(2,3);
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
	
public void setCategoriaProducto(Producto producto) 
{
	PreparedStatement stmt = null;
	ResultSet rs = null;
	try {
		stmt = Conexion.getInstancia().getConnection().prepareStatement("SELECT categoria.* FROM categoria "
							+ "INNER JOIN categoria_producto  ON categoria.idCategoria = categoria_producto.idCategoria_categoria "
							+ "WHERE idcategoria_producto = ?");
		stmt.setInt(1, producto.getIdProducto());
		rs = stmt.executeQuery();
		if(rs!=null) {
			while(rs.next()) {
				CategoriaProducto categoria1 = new CategoriaProducto();
				categoria1.setIdCategoria(rs.getInt("idCategoria"));
				categoria1.setDescripcion(rs.getString("descripcion"));
				categoria1.setEstado(rs.getBoolean("estado"));
				producto.setCategoria(categoria1);}	}	} 
	catch (SQLException e) {
		System.out.println("Ocurrio un error al setear la categoria al producto...");
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

public int setearTipoCategoria(Producto producto ) {
	PreparedStatement stmt= null;
	ResultSet keyResultSet=null;
	CategoriaProducto categoria = new CategoriaProducto();
	int registrosModificados = 0;
	try {
		
		if(producto.getCategoria().equals("Suplementos")) {
			stmt=Conexion.getInstancia().getConnection().prepareStatement("INSERT INTO categoria(descripcion, estado) VALUES(?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, producto.getDescripcion());
			stmt.setBoolean(2,true);
		}
		else if(producto.getCategoria().equals("Vestimenta")) {
			stmt=Conexion.getInstancia().getConnection().prepareStatement("INSERT INTO categoria(descripcion, estado) VALUES(?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, producto.getDescripcion());
			stmt.setBoolean(2,true);
		}
		else if(producto.getCategoria().equals("Articulos Entrenamiento")) {
			stmt=Conexion.getInstancia().getConnection().prepareStatement("INSERT INTO categoria(descripcion, estado) VALUES(?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, producto.getDescripcion());
			stmt.setBoolean(2,true);
		}
		registrosModificados = stmt.executeUpdate();
		keyResultSet = stmt.getGeneratedKeys();
        if(keyResultSet!=null && keyResultSet.next()){
            categoria.setIdCategoria(keyResultSet.getInt(1));}	} 
	catch (SQLException e) {
        e.printStackTrace(); 
		System.out.println("Ocurrio un error al insertar una nueva categoria...");}
	finally {
		try {
			if(keyResultSet!=null)keyResultSet.close();
            if(stmt!=null)stmt.close();
            Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			e.printStackTrace();}	}
	return registrosModificados;
}

public int insertarCategoria(CategoriaProducto categoria) 
{
	PreparedStatement stmt= null;
	ResultSet keyResultSet=null;
	int registrosModificados = 0;
	try {
		stmt=Conexion.getInstancia().getConnection().
				prepareStatement("INSERT INTO categoria(descripcion, estado) VALUES(?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setString(1, categoria.getDescripcion());
		stmt.setBoolean(2, categoria.isEstado());
		registrosModificados = stmt.executeUpdate();
		keyResultSet = stmt.getGeneratedKeys();
        if(keyResultSet!=null && keyResultSet.next()){
        	categoria.setIdCategoria(keyResultSet.getInt(1));}	} 
	catch (SQLException e) {
        e.printStackTrace(); 
		System.out.println("Ocurrio un error al insertar una nueva categoria...");}
	finally {
		try {
			if(keyResultSet!=null)keyResultSet.close();
            if(stmt!=null)stmt.close();
            Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			e.printStackTrace();}	}
	return registrosModificados;

}

public int modificarCategoria(CategoriaProducto categoria)
{
	PreparedStatement stmt = null;
	int registrosModificados = 0;
	try {
		stmt=Conexion.getInstancia().getConnection().prepareStatement(SQL_UPDATE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		stmt.setString(1,categoria.getDescripcion());
		stmt.setBoolean(2,categoria.isEstado());
		stmt.setInt(3,categoria.getIdCategoria());
					
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

public int eliminarCategoria(Producto producto)
{
	PreparedStatement stmt = null;
	int registrosModificados = 0;
	try {
		stmt=Conexion.getInstancia().getConnection().prepareStatement(SQL_DELETE);
		stmt.setInt(1,producto.getIdProducto());
		registrosModificados = stmt.executeUpdate();} 
	catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Ocurrio un error al intentar eliminar la categoria...");}
	finally {
		try {
			if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			e.printStackTrace();}}
	return registrosModificados;
}

public CategoriaProducto encontrarCategoriaById(CategoriaProducto categoria)
{
	CategoriaProducto categoria1 = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	try {
		stmt=Conexion.getInstancia().getConnection().prepareStatement("SELECT * FROM categoria WHERE idCategoria = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		stmt.setInt(1,categoria.getIdCategoria());
		rs = stmt.executeQuery();
		if(rs!=null && rs.next()) {
			categoria1 = new CategoriaProducto();
			categoria1.setIdCategoria(rs.getInt("idCategoria"));
			categoria1.setDescripcion(rs.getString("descripcion"));
			categoria1.setEstado( rs.getBoolean("estado"));}}
	catch (SQLException e) {
		e.printStackTrace();}
	finally {
		try {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			System.out.println("Ocurrio un error al encontrar la categoria por su id...");
			e.printStackTrace(System.out);}}
	return categoria1;
}

public void cambiarEstadoCategoria(CategoriaProducto categoria) throws Exception 
{
	String SQL_ESTADO_CATEGORIA = "UPDATE categoria SET estado =? WHERE idCategoria = ?";
	PreparedStatement stmt = null;
	try {
		stmt=Conexion.getInstancia().getConnection().prepareStatement(SQL_ESTADO_CATEGORIA);
		stmt.setBoolean(1,categoria.isEstado());
		stmt.setInt(2,categoria.getIdCategoria());
		stmt.executeUpdate();} 
	catch (Exception e) {
		System.out.println("Error al cambiar estado de la categoria...");
		e.printStackTrace();}
	finally {
		try{
			if(stmt!=null) {stmt.close();}
			Conexion.getInstancia().releaseConn();} 
		catch (SQLException e) {
			e.printStackTrace();}}
}
}//Fin Clase

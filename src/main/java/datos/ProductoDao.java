package datos;

	import java.sql.*;
	import java.util.*;
	import entidades.*;

	public class ProductoDao {
		private static final String SQL_SELECT = "SELECT *  FROM producto";
		private static final String SQL_INSERT = "INSERT INTO producto(nombre, precio, stock, estado, descripcion) VALUES(?,?,?,?,?) ";
		private static final String SQL_UPDATE = "UPDATE producto SET nombre=?, precio=?, stock=?, estado=?, descripcion=?  WHERE idproducto = ? ";
		private static final String SQL_DELETE = "DELETE FROM producto WHERE idproducto = ?";

				
	public List<Producto> listarProductos()
	{
		CategoriaDao catDao = new CategoriaDao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Producto producto = null;
		List<Producto> productos = new LinkedList<>();
					
		try {
			stmt= Conexion.getInstancia().getConnection().prepareStatement(SQL_SELECT,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					producto = new Producto();
					producto.setIdProducto(rs.getInt("idproducto"));
					producto.setNombre(rs.getString("nombre"));
					producto.setDescripcion(rs.getString("descripcion"));
					producto.setPrecio( rs.getDouble("precio"));
					producto.setStock(rs.getInt("stock"));
					producto.setEstado( rs.getBoolean("estado"));
					catDao.setCategoriaProducto(producto);
					productos.add(producto);}	}	} 
		catch (SQLException e) {
			e.printStackTrace(System.out);}
		finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();}
			catch (SQLException e) {
				e.printStackTrace(System.out);}}
		return productos; //Retorna la lista con todos los productos.
}
				
				
	public int insertarNuevoProducto(Producto producto)
	{
		
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		int registrosModificados = 0;
		try {
			stmt = Conexion.getInstancia().getConnection().prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1,producto.getNombre());
			stmt.setDouble(2,producto.getPrecio());
			stmt.setInt(3,producto.getStock());
			stmt.setBoolean(4,producto.isEstado());
			stmt.setString(5,producto.getDescripcion());
						
			registrosModificados = stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				producto.setIdProducto(keyResultSet.getInt(1));}}
		catch (SQLException e) {
			System.out.println("Ocurrio un error al insertar un nuevo producto...");
			e.printStackTrace(System.out);}
		finally {
			try {
				if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();} 
			catch (SQLException e) {
				e.printStackTrace(System.out);}}
		return registrosModificados;
	}
				
	public int modificarProducto(Producto producto)
	{
		PreparedStatement stmt = null;
		int registrosModificados = 0;
		try {
			stmt=Conexion.getInstancia().getConnection().prepareStatement(SQL_UPDATE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1,producto.getNombre());
			stmt.setDouble(2,producto.getPrecio());
			stmt.setInt(3,producto.getStock());
			stmt.setBoolean(4,producto.isEstado());
			stmt.setString(5,producto.getDescripcion());
			stmt.setInt(6,producto.getIdProducto());
			
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
				
	public int eliminarProducto(Producto producto)
	{
		CategoriaDao catDao = new CategoriaDao();
		PreparedStatement stmt = null;
		int registrosModificados = 0;
		try {
			stmt=Conexion.getInstancia().getConnection().prepareStatement(SQL_DELETE);
			stmt.setInt(1,producto.getIdProducto());
			catDao.eliminarCategoria(producto);
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
				
	public List<Producto> encontrarProductoNombre(Producto producto)
	{
		CategoriaDao catDao = new CategoriaDao();
		Producto prod=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Producto> productos = new LinkedList<>();
		try {
			stmt=Conexion.getInstancia().getConnection().prepareStatement("SELECT * FROM producto WHERE nombre = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1,producto.getNombre());
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					prod = new Producto();
					prod.setIdProducto(rs.getInt("idProducto"));
					prod.setNombre(rs.getString("nombre"));
					prod.setPrecio(rs.getDouble("precio"));
					prod.setStock( rs.getInt("stock"));
					prod.setDescripcion(rs.getString("descripcion"));
					prod.setEstado( rs.getBoolean("estado"));
					catDao.setCategoriaProducto(prod);
					productos.add(prod);}	}	} 
		catch (SQLException e) {
			e.printStackTrace();}
		finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();} 
			catch (SQLException e) {
				System.out.println("Ocurrio un error al encontrar el producto por su nombre...");
				e.printStackTrace(System.out);}}
		return productos;
	}
						
	public List<Producto> encontrarProductoCategoria(CategoriaProducto categoriaProd)
	{
		CategoriaDao catDao = new CategoriaDao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Producto> productos = new LinkedList<>();
		Producto prod=null;
		try {
			stmt=Conexion.getInstancia().getConnection().prepareStatement("SELECT prod.* FROM producto prod INNER JOIN categoria_producto catProd"
																		+ "ON prod.idproducto = catProd.idcategoria_producto"
																		+ "INNER JOIN categoria cat ON catProd.idcategoria_categoria = cat.idcategoria"
																		+ "WHERE cat.descripcion = ? ",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1,categoriaProd.getDescripcion());
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					prod = new Producto();
					prod.setIdProducto(rs.getInt("idProducto"));
					prod.setNombre(rs.getString("nombre"));
					prod.setPrecio(rs.getDouble("precio"));
					prod.setStock( rs.getInt("stock"));
					prod.setDescripcion(rs.getString("descripcion"));
					prod.setEstado( rs.getBoolean("estado"));
					catDao.setCategoriaProducto(prod);
					productos.add(prod);}	}	} 
		catch (SQLException e) {
			e.printStackTrace();}
		finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();} 
			catch (SQLException e) {
				System.out.println("Ocurrio un error al encontrar el producto por su categoria...");
				e.printStackTrace(System.out);}	}
		return productos;
	}
						
	public Producto encontrarProductoId(Producto producto)
	{
		CategoriaDao catDao = new CategoriaDao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Producto prod=null;
		try {
			stmt=Conexion.getInstancia().getConnection().prepareStatement("SELECT * FROM producto WHERE idProducto = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1,producto.getIdProducto());
			rs = stmt.executeQuery();
			if(rs!=null && rs.next()) {
				prod = new Producto();
				prod.setIdProducto(rs.getInt("idProducto"));
				prod.setNombre(rs.getString("nombre"));
				prod.setPrecio(rs.getDouble("precio"));
				prod.setStock( rs.getInt("stock"));
				prod.setDescripcion(rs.getString("descripcion"));
				prod.setEstado( rs.getBoolean("estado"));
				catDao.setCategoriaProducto(prod);
				}	
			}	 
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
		return prod;
	}
	
	public void cambiarEstadoProducto(Producto producto) throws Exception 
	{
		String sql = "UPDATE producto SET estado = " + (producto.isEstado() == true ? "1" : "0")+ " WHERE idProducto = ?";
		PreparedStatement stmt = null;
		try {
			stmt=Conexion.getInstancia().getConnection().prepareStatement(sql);
			stmt.setInt(1,producto.getIdProducto());
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
	
	public int actualizarStock(Producto producto, int stock) {
		PreparedStatement stmt = null;
		int registrosModificados = 0;
		try {
			stmt=Conexion.getInstancia().getConnection().prepareStatement("UPDATE producto SET stock=? WHERE idProducto = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1,stock);
			stmt.setInt(2,producto.getIdProducto());
			
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
}

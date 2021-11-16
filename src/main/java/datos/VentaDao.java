package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Venta;

public class VentaDao {
	
	public int idVentas() {
		int idVentas=0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = Conexion.getInstancia().getConnection().prepareStatement("SELECT max(idVentas) FROM ventas");
			rs = stmt.executeQuery();
			while(rs.next()) {
				idVentas = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace(System.out);
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();}
			catch (SQLException e) {
				e.printStackTrace(System.out);}}
		return idVentas;
	}
	
	public int insertarVenta(Venta venta) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ResultSet keyResultSet = null;
		int registrosModificados = 0;
		try {
			stmt = Conexion.getInstancia().getConnection().prepareStatement(
			"INSERT INTO ventas(id_Usuario, numeroDeSerie, fechaVenta, monto, estado) VALUES(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, venta.getId_Usuario());
			stmt.setString(2, venta.getNroSerie());
			stmt.setString(3, venta.getFecha());
			stmt.setDouble(4, venta.getMonto());
			stmt.setString(5, venta.getEstado());
			registrosModificados = stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				venta.setIdVenta(keyResultSet.getInt(1));}
		}catch(Exception e){
			System.out.println("Ocurrio un error al insertar una nueva venta...");
			e.printStackTrace(System.out);
			}
		finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();}
			catch (SQLException e) {
				e.printStackTrace(System.out);
				}
		}
		return registrosModificados;
	}
	
	public int guardarDetalleVentas(Venta venta) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ResultSet keyResultSet = null;
		int registrosModificados = 0;
		try {
			stmt = Conexion.getInstancia().getConnection().prepareStatement(
			"INSERT INTO detalle_ventas(id_ventas, id_producto, cantidad, precioVenta) VALUES(?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, venta.getIdVenta());
			stmt.setInt(2, venta.getIdProducto());
			stmt.setInt(3, venta.getCantidad());
			stmt.setDouble(4, venta.getPrecio());
			registrosModificados = stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				venta.setIdDetalleVentas(keyResultSet.getInt(1));}
		}catch(Exception e){
			System.out.println("Ocurrio un error al insertar detalle de venta...");
			e.printStackTrace(System.out);
			}
		finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();}
			catch (SQLException e) {
				e.printStackTrace(System.out);
				}
		}
		return registrosModificados;
	}
	
	public String GenerarNroSerie() {
		String nroSerie="";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = Conexion.getInstancia().getConnection().prepareStatement("SELECT max(numeroDeSerie) FROM ventas");
			rs = stmt.executeQuery();
			while(rs.next()) {
				nroSerie = rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace(System.out);
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				Conexion.getInstancia().releaseConn();}
			catch (SQLException e) {
				e.printStackTrace(System.out);}}
		return nroSerie;
	}
}

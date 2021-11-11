package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.*;

public class AreaDao {

	public void setArea(Profesor profesor) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
			stmt = Conexion.getInstancia().getConnection().prepareStatement("SELECT a.* FROM area  a"
															+ "INNER JOIN area_profesor ap ON a.idarea=ap.idarea_area"
															+ "WHERE idprofesor=?");
			stmt.setInt(1, profesor.getIdProfesor());
			rs = stmt.executeQuery();
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					AreaProfesor area = new AreaProfesor();
					area.setIdArea(rs.getInt("idarea"));
					area.setDescripcion(rs.getString("descripcion"));
					profesor.setArea(area);
				}
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Ocurrio un error al setear el area del profesor...");
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
}

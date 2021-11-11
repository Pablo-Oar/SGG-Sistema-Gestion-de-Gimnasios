package datos;

import java.sql.*;

//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
//import javax.sql.*;

//Primera Opcion----------------------------------------------------------------------------------------------------

public class Conexion {
	//private static final String JDBC_URL = "jdbc:mysql://localhost/gimnasio-java?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/gimnasio-java?user=root&password=admin&serverTimezone=UTC";
	//private static final String JDBC_USER = "root";
	//private static final String JDBC_PASSWORD = "admin";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static Conexion instancia; // PATRON SINGLETON
	private int conectados=0;
	private Connection conn=null;

	private Conexion() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Conexion getInstancia() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}
	
	
	public Connection getConnection() {
		try {
			if(conn==null || conn.isClosed()) {
				conn = DriverManager.getConnection(JDBC_URL);
				conectados =0;
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
		conectados++;
		return conn;
	}
	
	
	 public  void releaseConn() {
			conectados--;
			try {
				if (conectados<=0) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
//Segunda Opcion------------------------------------------------------------------------------------------
//public class CConexion {
//	//private static final String JDBC_URL ="jdbc:mysql://localhost/gimnasio-java?user=root&password=admin&serverTimezone=UTC";
//	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/gimnasio-java?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
//	private static final String JDBC_USER = "root";
//	private static final String JDBC_PASSWORD = "admin";
//	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//
//	private CConexion() {
//		try {
//			Class.forName(DRIVER);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static BasicDataSource dataSource;
//
//	public static DataSource getDataSource() {
//		if (dataSource == null) {
//			dataSource = new BasicDataSource();
//			dataSource.setUrl(JDBC_URL);
//			dataSource.setUsername(JDBC_USER);
//			dataSource.setPassword(JDBC_PASSWORD);
//			dataSource.setInitialSize(50);
//		}
//		return dataSource;
//	}
//
//	public static Connection getConnection() throws SQLException {
//		return getDataSource().getConnection();
//	}
//
//	public static void close(ResultSet rs) {
//		try {
//			if (rs != null) {
//				rs.close();
//			}
//		} catch (SQLException ex) {
//			ex.printStackTrace(System.out);
//		}
//	}
//
//	public static void close(PreparedStatement stmt) {
//		try {
//			if (stmt != null) {
//				stmt.close();
//			}
//		} catch (SQLException ex) {
//			ex.printStackTrace(System.out);
//		}
//	}
//
//	public static void close(Connection conn) {
//		try {
//			if (conn != null) {
//				conn.close();
//			}
//		} catch (SQLException ex) {
//			ex.printStackTrace(System.out);
//		}
//	}
//}
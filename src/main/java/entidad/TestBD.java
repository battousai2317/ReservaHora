package entidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestBD {
	private static String url = "jdbc:mysql://localhost/";
	private static String dbName = "petsmile";
	private static String userName = "root";
	private static String password = "root";
	
	public static Connection conectar() {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url + dbName,userName,password);
		} catch (SQLException e) {
			System.out.println("No se pudo conectar con la base de datos" + e.getMessage());
					}
		return conexion;
	}
}

package co.proyecto.alura.pruebaConexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConexionPool {
	private DataSource conexion;

	public ConexionPool() {
		var conexionPool = new ComboPooledDataSource();
		conexionPool.setJdbcUrl("jdbc:mysql://localhost:3310/proyecto_alura_hotel?useTimeZone=true&serverTimeZone=UTC");
		conexionPool.setUser("root");
		conexionPool.setPassword("");
		conexionPool.setMaxPoolSize(10);
		this.conexion = conexionPool;
		;
	}

	public Connection crearConexion() {
		try {
			return conexion.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

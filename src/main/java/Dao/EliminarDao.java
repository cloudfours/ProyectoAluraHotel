package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.proyecto.alura.pruebaConexion.ConexionPool;

public class EliminarDao {
	final private Connection con;

	public EliminarDao(Connection con) {
		this.con = con;

	}

	public int eliminar(Integer id) {
		final Connection con = new ConexionPool().crearConexion();
		try {
			try (con) {
				final PreparedStatement statement = con.prepareStatement("DELETE  FROM huespedes where id = ?");
				try (statement) {
					statement.setInt(1, id);
					statement.execute();
					return statement.getUpdateCount();// cuantas filas fueron modificadas
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);

		}
	}
	public int eliminarRe(Integer id) {
		final Connection con = new ConexionPool().crearConexion();
		try {
			try (con) {
				final PreparedStatement statement = con.prepareStatement("DELETE  FROM reservas where id = ?");
				try (statement) {
					statement.setInt(1, id);
					statement.execute();
					return statement.getUpdateCount();// cuantas filas fueron modificadas
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);

		}
	}
}

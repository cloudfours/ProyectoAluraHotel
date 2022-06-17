package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.proyecto.alura.pruebaConexion.ConexionPool;

public class EditarDao {
	final private Connection con;

	public EditarDao(Connection con) {
		this.con = con;

	}

	public static int modificar(String nombre, String apellido, String fecha, Integer idPais, Integer telefono, Integer idReserva,
			int id) {
		final Connection con = new ConexionPool().crearConexion();
		try {
			try (con) {
				final PreparedStatement statement = con
						.prepareStatement("UPDATE huespedes set" + "nombre = ?" + ",apellido = ?" + ",fecha_de_nacimiento = ? "
								+ ",id_pais= ? " + ",telefono= ? " + ",id_reserver= ? " + "where id= ? ");
				try (statement) {

					statement.setString(1, nombre);
					statement.setString(2, apellido);
					statement.setString(3, fecha);
					statement.setInt(4, idPais);
					statement.setInt(5, telefono);
					statement.setInt(6, idReserva);
					statement.setInt(7, id);
					statement.execute();
					int cont = statement.getUpdateCount();
					return cont;

				}
			}
		} catch (SQLException e) {
			System.out.println("hay error");
			throw new RuntimeException(e);

		}
	}
	public  int modificarRe( String fechaEntreada,String Fechasalida, Float valor,  Integer idPago,
			int id) {
		final Connection con = new ConexionPool().crearConexion();
		try {
			try (con) {
				final PreparedStatement statement = con
						.prepareStatement("UPDATE reservas set" + "fecha_entrada = ?" + ",fecha_salida = ?" + ",valor = ? "
								+ ",id_pago= ? " + "where id= ? ");
				try (statement) {
					statement.setString(1, fechaEntreada);
					statement.setString(2, Fechasalida);
					statement.setFloat(3, valor);
					statement.setInt(4, idPago);
					statement.setInt(6, id);
					statement.execute();
					int cont = statement.getUpdateCount();
					return cont;

				}
			}
		} catch (SQLException e) {
			System.out.println("hay error");
			throw new RuntimeException(e);

		}
	}


}

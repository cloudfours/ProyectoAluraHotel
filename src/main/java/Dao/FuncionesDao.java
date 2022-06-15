package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Resultset;

import co.proyecto.alura.pruebaConexion.ConexionPool;
import co.proyectoAlura.Modelo.Reservas;
import co.proyectoAlura.Modelo.Usuario;

public class FuncionesDao {
	final private Connection con;

	public FuncionesDao(Connection connectar) {
		this.con = connectar;
	}

	public boolean validarContrasena(String correo, String contrasena) {
		boolean resultado = false;

		ConexionPool conectar = new ConexionPool();
		final Connection con = conectar.crearConexion();
		try (con) {
			final PreparedStatement statement = con.prepareStatement("Select correo,contrasenia from usuarios");
			try (statement) {
				statement.execute();
				final ResultSet resulset = statement.getResultSet();
				try (resulset) {
					while (resulset.next()) {
						if (correo.equals(resulset.getString(1)) && contrasena.equals(resulset.getString(2))) {

							return true;

						} else {

							return false;

						}
					}

				}
				return resultado;

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	public void AgendarReservars(Reservas reserva) {
		try (con) {
			final PreparedStatement statement = con.prepareStatement(
					"insert into reservas" + "(fecha_entrada,fecha_salida,valor,id_pago)" + "values(?,?,?,?)");
			try (statement) {
				ejecutarReserva(reserva, statement);
			} catch (SQLException e) {

				throw new RuntimeException(e);

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	public void ejecutarReserva(Reservas reserva, PreparedStatement statement) throws SQLException {
    statement.setDate(0, Date.valueOf(reserva.getFechaEntrada()));
    statement.setDate(1, Date.valueOf(reserva.getFechaSalida()));
    statement.setFloat(2, reserva.getValor());
    statement.setInt(3, reserva.getMetodoPago());
    
	}
}

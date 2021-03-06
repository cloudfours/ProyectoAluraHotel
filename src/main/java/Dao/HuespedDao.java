package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import co.proyectoAlura.Modelo.Huesped;
import co.proyectoAlura.Modelo.MetodoPago;
import co.proyectoAlura.Modelo.ReservasLogic;

public class HuespedDao {
	final private Connection con;

	public HuespedDao(Connection connectar) {
		this.con = connectar;
	}

	public void AgendarReservars(Huesped huesped) {
		try (con) {
			final PreparedStatement statement = con.prepareStatement("insert into huespedes"
					+ "(nombre,apellido,fecha_de_nacimiento,id_pais,telefono,id_reserver)" + "values(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutarReserva(huesped, statement);
			} catch (SQLException e) {

				throw new RuntimeException(e);

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	public void ejecutarReserva(Huesped huesped, PreparedStatement statement) throws SQLException {

		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setString(3, huesped.getFechaNacimiento());
		statement.setInt(4, huesped.getPais());
		statement.setInt(5, huesped.getTelefono());
		statement.setInt(6, huesped.getReserva());

		final ResultSet result = statement.getGeneratedKeys();
		statement.execute();
		try (result) {// apartir de java 9 se puede con try-catch-resources hacer un codigo mas limpio
			while (result.next()) {

				huesped.setReserva(result.getInt(6));
				System.out.println(String.format("Fue insertado el reserva %s: ", result.toString()));
			}
		}
	}

	public int lista() {
		int resultado = 0;
		try {
			final PreparedStatement statement = con
					.prepareStatement("select id from reservas where id=(select max(id) from reservas)");
			try (statement) {
				statement.execute();
				final ResultSet resulset = statement.getResultSet();
				try (resulset) {
					while (resulset.next()) {
						resultado = resulset.getInt("id");
					}

				}
				System.out.println(resultado);
				return resultado;

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	public List<Huesped>listaHuesped(String apellido){
		List<Huesped>resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("select id,nombre,apellido,fecha_de_nacimiento,id_pais,telefono,id_reserver from huespedes where apellido =?");
			try(statement){
				statement.setString(1, apellido);
				final ResultSet resulset = statement.executeQuery();
				try(resulset){
					while(resulset.next()) {
						
						var  huesped = new Huesped(resulset.getInt("id"), resulset.getString("nombre"),resulset.getString("apellido"),resulset.getString("fecha_de_nacimiento"),resulset.getInt("id_pais"),resulset.getInt("telefono"),resulset.getInt("id_reserver"));
						resultado.add(huesped);
					}
				}
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println(resultado);
		return resultado;	
	}
	public List<Huesped>listaHuesped(){
		List<Huesped>resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT id,nombre,apellido,fecha_de_nacimiento,id_pais,telefono,id_reserver from huespedes ");
			try(statement){
				final ResultSet resulset = statement.executeQuery();
				try(resulset){
					while(resulset.next()) {
						
						var  huesped = new Huesped(resulset.getInt("id"), resulset.getString("nombre"),resulset.getString("apellido"),resulset.getString("fecha_de_nacimiento"),resulset.getInt("id_pais"),resulset.getInt("telefono"),resulset.getInt("id_reserver"));
						resultado.add(huesped);
					}
				}
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println(resultado);
		return resultado;	
	}
	

}

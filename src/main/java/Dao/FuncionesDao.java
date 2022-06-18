package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Resultset;

import co.proyecto.alura.pruebaConexion.ConexionPool;
import co.proyectoAlura.Modelo.Huesped;
import co.proyectoAlura.Modelo.ReservasLogic;
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

	public void AgendarReservars(ReservasLogic reserva) {
		try (con) {
			final PreparedStatement statement = con.prepareStatement(
					"insert into reservas" + "(fecha_entrada,fecha_salida,valor,id_pago)" + "values(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutarReserva(reserva, statement);
			} catch (SQLException e) {

				throw new RuntimeException(e);

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	public void ejecutarReserva(ReservasLogic reserva, PreparedStatement statement) throws SQLException {
	
		statement.setString(1, reserva.getFechaEntrada());
		statement.setString(2, reserva.getFechaSalida());
		statement.setFloat(3, reserva.getValor());
		statement.setInt(4, reserva.getMetodoPago());
		final ResultSet result = statement.getGeneratedKeys();
		statement.execute();
		try (result) {// apartir de java 9 se puede con try-catch-resources hacer un codigo mas limpio
			while (result.next()) {
			
				reserva.setId(result.getInt(1));
				System.out.println(String.format("Fue insertado el reserva %s: ", result.toString()));
			}
		}
	}
	//este busca por id de la tabla reservas
	public List<ReservasLogic>listaReservaConParametro(int id){
		List<ReservasLogic>resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("select id,fecha_entrada,fecha_salida,valor,id_pago from reservas where id =?");
			try(statement){
				statement.setInt(1, id);
				final ResultSet resulset = statement.executeQuery();
				try(resulset){
					while(resulset.next()) {		
						var  huesped = new ReservasLogic(resulset.getInt("id"), resulset.getString("fecha_entrada"),resulset.getString("fecha_salida"),resulset.getFloat("valor"),resulset.getInt("id_pago"));
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
	//este carga la tabla
	public List<ReservasLogic>listaReserva(){
		List<ReservasLogic>resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("select id,fecha_entrada,fecha_salida,valor,id_pago from reservas");
			try(statement){
				final ResultSet resulset = statement.executeQuery();
				try(resulset){
					while(resulset.next()) {
						
						var  huesped = new ReservasLogic(resulset.getInt("id"), resulset.getString("fecha_entrada"),resulset.getString("fecha_salida"),resulset.getFloat("valor"),resulset.getInt("id_pago"));
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

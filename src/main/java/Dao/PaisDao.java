package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.proyectoAlura.Modelo.Huesped;
import co.proyectoAlura.Modelo.MetodoPago;
import co.proyectoAlura.Modelo.Pais;

public class PaisDao {
	private Connection con;

	public PaisDao(Connection con) {
		this.con = con;
	}

	public List<Pais> lista() {
		List<Pais> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("select id,nombre from pais ");
			try (statement) {
				final ResultSet resulset = statement.executeQuery();
				try (resulset) {
					while (resulset.next()) {
						var pais = new Pais(resulset.getInt("id"), resulset.getString("nombre"));
						resultado.add(pais);
					}
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println(resultado);
		return resultado;
	}

}

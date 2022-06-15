package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.proyectoAlura.Modelo.MetodoPago;

public class MetodoPagoDao {
	private Connection con;

	public MetodoPagoDao(Connection con) {
		this.con = con;
	}
	public List<MetodoPago>lista(){
		List<MetodoPago>resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("select id,nombrePago from formaPago ");
			try(statement){
				final ResultSet resulset = statement.executeQuery();
				try(resulset){
					while(resulset.next()) {
						var  metodoDePago = new MetodoPago(resulset.getInt("id"), resulset.getString("nombrePago"));
						resultado.add(metodoDePago);
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

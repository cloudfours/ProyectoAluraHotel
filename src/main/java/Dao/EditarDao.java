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
	public int modificar(int id, String nombre,String apellido,Date fechaNacimiento,int telefono,int idPais,int idReserva) {
		final Connection con = new ConexionPool().crearConexion();
		try {
			try(con){
				final PreparedStatement statement = con.prepareStatement("update huespedes set" + "nombre= ?"
			+ "apellido=?"+"fecha_de_nacimiento=?"+"id_pais=?"+"telefono=?"+"id_reserver=?");
				try(statement){
					statement.setInt(0, id);
					statement.setString(1, nombre);
					statement.setString(2, apellido);
					statement.setDate(3, fechaNacimiento);
					statement.setInt(4, idPais);
					statement.setInt(5, telefono);
					statement.setInt(6, idReserva);
					int cont = statement.getUpdateCount();
					return cont;
					
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}

package co.proyecto.pruebas;

import java.sql.Connection;
import java.sql.SQLException;

import Dao.HuespedDao;
import co.proyecto.alura.pruebaConexion.ConexionPool;

public class MainPruebas {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
	HuespedDao dao = new  HuespedDao(new ConexionPool().crearConexion());
	dao.listaHuesped();
	}

}

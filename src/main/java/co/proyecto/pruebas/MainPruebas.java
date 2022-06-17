package co.proyecto.pruebas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import Dao.EditarDao;
import Dao.HuespedDao;
import co.proyecto.alura.pruebaConexion.ConexionPool;
import co.proyecto.controller.reservaController;

public class MainPruebas {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
	HuespedDao dao = new  HuespedDao(new ConexionPool().crearConexion());
	reservaController editar = new reservaController();
	
	System.out.println(editar.modificar("angel", "cesar", "2020-05-02", 1, 1, 1, 9));
	}

}

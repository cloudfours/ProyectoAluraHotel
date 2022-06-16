package co.proyecto.pruebas;

import Dao.HuespedDao;
import co.proyecto.alura.pruebaConexion.ConexionPool;

public class PruebasFecha {

	public static void main(String[] args) {
	HuespedDao dao = new HuespedDao(new ConexionPool().crearConexion());
	dao.lista();
    
	}

}

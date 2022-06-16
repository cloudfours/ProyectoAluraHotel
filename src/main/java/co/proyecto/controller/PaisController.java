package co.proyecto.controller;

import java.util.List;

import Dao.MetodoPagoDao;
import Dao.PaisDao;
import co.proyecto.alura.pruebaConexion.ConexionPool;
import co.proyectoAlura.Modelo.Pais;

public class PaisController {
	private PaisDao pago = new PaisDao(new ConexionPool().crearConexion());

	public List<Pais> listaPais() {
		return this.pago.lista();
	}
}

package co.proyecto.controller;

import java.util.List;

import Dao.FuncionesDao;
import Dao.MetodoPagoDao;
import co.proyecto.alura.pruebaConexion.ConexionPool;
import co.proyectoAlura.Modelo.MetodoPago;

public class metodoPagosController {
	 MetodoPagoDao dao;
public metodoPagosController() {
	ConexionPool con = new ConexionPool();
	this.dao = new MetodoPagoDao(con.crearConexion());
}
	public List<MetodoPago> lista() {
		return this.dao.lista();
	}
}

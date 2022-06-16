package co.proyecto.controller;

import Dao.FuncionesDao;
import co.proyecto.alura.pruebaConexion.ConexionPool;
import co.proyectoAlura.Modelo.Usuario;

public class UsuarioController {
private FuncionesDao dao = new FuncionesDao(new ConexionPool().crearConexion()) ;

public boolean  validarContrasena(String correo,String contrasena) {
	
	return this.dao.validarContrasena(correo,contrasena);
}
}

package co.proyecto.controller;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;

import Dao.FuncionesDao;
import co.proyecto.alura.pruebaConexion.ConexionPool;
import co.proyectoAlura.Modelo.ReservasLogic;

public class reservaController {
	private FuncionesDao dao = new FuncionesDao(new ConexionPool().crearConexion());
	

	public void guardarReserva(ReservasLogic reserva, int metodoPago) {
		reserva.setMetodoPago(metodoPago);
		dao.AgendarReservars(reserva);

	}
	

}

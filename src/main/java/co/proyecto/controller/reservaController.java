package co.proyecto.controller;

import Dao.FuncionesDao;
import co.proyectoAlura.Modelo.Reservas;

public class reservaController {
	private FuncionesDao dao ;

public void guardarReserva(Reservas reserva) {
	dao.AgendarReservars(reserva);
}
}

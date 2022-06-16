package co.proyecto.controller;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.JOptionPane;

import Dao.FuncionesDao;
import Dao.HuespedDao;
import co.proyecto.alura.pruebaConexion.ConexionPool;
import co.proyectoAlura.Modelo.Huesped;
import co.proyectoAlura.Modelo.ReservasLogic;

public class reservaController {
	private FuncionesDao dao = new FuncionesDao(new ConexionPool().crearConexion());
	private HuespedDao daoRe = new HuespedDao(new ConexionPool().crearConexion());

	public void guardarReserva(ReservasLogic reserva, int metodoPago) {
		reserva.setMetodoPago(metodoPago);
		dao.AgendarReservars(reserva);

	}
	public int mostrarReserva() {
		return daoRe.lista();
	}
	
	public void AngendarReserva(Huesped huesped,int pais) {
		huesped.setPais(pais);
		this.daoRe.AgendarReservars(huesped);
	}
	
	public List<Huesped>list(String apellido){
	return daoRe.listaHuesped(apellido);	
	}
	public List<Huesped>listcompleta(){
		return daoRe.listaHuesped();	
		}
	

}

package co.proyecto.controller;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.JOptionPane;

import Dao.EditarDao;
import Dao.EliminarDao;
import Dao.FuncionesDao;
import Dao.HuespedDao;
import co.proyecto.alura.pruebaConexion.ConexionPool;
import co.proyectoAlura.Modelo.Huesped;
import co.proyectoAlura.Modelo.ReservasLogic;

public class reservaController {
	private FuncionesDao dao;
	private HuespedDao daoRe;
	private EditarDao daoEdit;
	private EliminarDao daoEl;

	public reservaController() {
		this.dao = new FuncionesDao(new ConexionPool().crearConexion());
		this.daoRe = new HuespedDao(new ConexionPool().crearConexion());
		this.daoEdit = new EditarDao(new ConexionPool().crearConexion());
		this.daoEl = new EliminarDao(new ConexionPool().crearConexion());
	}

	public void guardarReserva(ReservasLogic reserva, int metodoPago) {
		reserva.setMetodoPago(metodoPago);
		dao.AgendarReservars(reserva);

	}

	public int mostrarReserva() {
		return daoRe.lista();
	}

	public void AngendarReserva(Huesped huesped, int pais) {
		huesped.setPais(pais);
		this.daoRe.AgendarReservars(huesped);
	}

	public List<Huesped> list(String apellido) {
		return daoRe.listaHuesped(apellido);
	}

	public List<Huesped> listcompleta() {
		return daoRe.listaHuesped();
	}

	public List<ReservasLogic> listaReserva() {
		return dao.listaReserva();
	}

	public List<ReservasLogic> listaReserva(int id) {
		return dao.listaReservaConParametro(id);
	}

	public int modificar(String nombre, String apellido, String fechaNacimiento, Integer idPais, Integer telefono,
			Integer idReserva, Integer id) {
		return EditarDao.modificar(nombre, apellido, fechaNacimiento, idPais, telefono, idReserva, id);

	}

	public int eliminar(Integer id) {
		return this.daoEl.eliminar(id);
	}

	public int modificarRe(String fechaEntreada, String Fechasalida, Float valor, Integer idPago, int id) {
		return daoEdit.modificarRe(fechaEntreada, Fechasalida, valor, idPago, id);
	}

	public int eliminarRe(Integer id) {
		return this.daoEl.eliminarRe(id);
	}
}

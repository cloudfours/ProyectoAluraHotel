package co.proyectoAlura.Modelo;

import java.sql.Date;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;

public class ReservasLogic {
	private int id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private float valor;
	private int metodoPago;

	public ReservasLogic(int id) {
		this.id = id;
	}

	public ReservasLogic() {
		super();
	}

	public ReservasLogic(Date fechaEntrada, Date fechaSalida, float valor) {

		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;

	}
	public ReservasLogic(int id,Date fechaEntrada, Date fechaSalida, float valor, int idPago) {

		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.id = id;
		this.metodoPago = idPago;

	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(int metodoPago) {
		this.metodoPago = metodoPago;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		String info;
		return String.valueOf(this.id = id);
	}

}

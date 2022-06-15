package co.proyectoAlura.Modelo;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Reservas {
	private LocalDate fechaEntrada;
	private LocalDate  fechaSalida;
	private float valor;
	private int metodoPago;
	
	public Reservas(LocalDate fechaEntrada, LocalDate fechaSalida, float valor, int metodoPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.metodoPago = metodoPago;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}
	
	public LocalDate getFechaSalida() {
		return fechaSalida;
	}
	
	public float getValor() {
		return 25000*(ChronoUnit.DAYS.between(fechaEntrada, fechaSalida));
	}
	
	public void setValor(float valor) {
		this.valor =  25000*(ChronoUnit.DAYS.between(fechaEntrada, fechaSalida));
	}

	public int getMetodoPago() {
		return metodoPago;
	}
	

	
}

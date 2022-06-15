package co.proyectoAlura.Modelo;

import java.util.ArrayList;
import java.util.List;

public class MetodoPago {
	private int id;
	private String nombre;
	private List<ReservasLogic> reservas;

	public List<ReservasLogic> getReservas() {
		return reservas;
	}

	public MetodoPago(int id, String nombre) {

		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nombre;
	}

	public void AgregarReserva(ReservasLogic reserva) {
		if (this.reservas == null) {
			this.reservas = new ArrayList<>();
		}
		this.reservas.add(reserva);

	}
}

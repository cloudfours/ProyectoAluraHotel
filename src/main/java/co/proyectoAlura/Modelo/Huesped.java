package co.proyectoAlura.Modelo;

import java.sql.Date;

public class Huesped {

	private String nombre;
	private String apellido;
	private String fechaNacimiento;
	private Integer pais;
	private Integer telefono;
	private Integer reserva;
	private Integer id;

	public Huesped(String nombre, String apellido, String fechaNacimiento, Integer telefono, Integer reserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.reserva = reserva;
	}

	public Huesped(int id,String nombre, String apellido, String fechaNacimiento, Integer idPais, Integer telefono, Integer reserva
			) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.reserva = reserva;
		this.id = id;
		this.pais = idPais;
	}

	public Huesped() {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public int getPais() {
		return pais;
	}

	public void setPais(int pais) {
		this.pais = pais;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public Integer getReserva() {
		return reserva;
	}

	public void setReserva(int reserva) {
		this.reserva = reserva;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Huesped [nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento
				+ ", pais=" + pais + ", telefono=" + telefono + ", reserva=" + reserva + ", id=" + id + "]";
	}
}

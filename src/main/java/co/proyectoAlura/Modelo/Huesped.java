package co.proyectoAlura.Modelo;

import java.sql.Date;

public class Huesped {
private  String nombre;
private String apellido;
private Date fechaNacimiento;
private int pais;
private int telefono;
private int reserva;

public Huesped(String nombre, String apellido, Date fechaNacimiento, int telefono, int reserva) {
	this.nombre = nombre;
	this.apellido = apellido;
	this.fechaNacimiento = fechaNacimiento;
	this.telefono = telefono;
	this.reserva = reserva;
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

public Date getFechaNacimiento() {
	return fechaNacimiento;
}

public int getPais() {
	return pais;
}
public void setPais(int pais) {
	this.pais = pais;
}
public int getTelefono() {
	return telefono;
}

public int getReserva() {
	return reserva;
}
public void setReserva(int reserva) {
	this.reserva = reserva;
}


}

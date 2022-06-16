package co.proyectoAlura.Modelo;

import java.util.List;

public class Pais {
	private int id;
	private String nombre;
	private List<Huesped>huesped;

	public Pais(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public List<Huesped> getHuesped() {
		return huesped;
	}

	public void setHuesped(List<Huesped> huesped) {
		this.huesped = huesped;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nombre;
	}
}

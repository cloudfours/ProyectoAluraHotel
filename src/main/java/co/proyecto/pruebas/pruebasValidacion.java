package co.proyecto.pruebas;

import co.proyecto.controller.UsuarioController;

public class pruebasValidacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
UsuarioController con = new UsuarioController();
con.validarContrasena("angelxd0714@gmail.com", "1007648218");
System.out.println(con.validarContrasena("angelxd0714@gmail.com", "1007648218"));

	}

}

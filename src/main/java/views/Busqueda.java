package views;

import co.proyecto.controller.reservaController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas = new JTable();
	private DefaultTableModel modelo1;
	private DefaultTableModel modelo2;
	private reservaController reservar = new reservaController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1010, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(647, 85, 158, 31);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(9);

		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tbHuespedes.isVisible()) {
					buscar();
					limpiarTablaHuespedes();
				} else if (tbReservas.isVisible()) {
					buscarRe();
					limpiarTablaReserva();
				}

			}

			private void buscar() {

				String buscar = txtBuscar.getText();
				var huespedes = reservar.list(buscar);
				huespedes.forEach(huesped -> modelo1.addRow(new Object[] { huesped.getId(), huesped.getNombre(),
						huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getPais(), huesped.getTelefono(),
						huesped.getReserva() }));

			}

			private void buscarRe() {
				int id = Integer.valueOf(txtBuscar.getText());
				var huespedes = reservar.listaReserva(id);
				huespedes.forEach(huesped -> modelo2.addRow(new Object[] { huesped.getId(), huesped.getFechaEntrada(),
						huesped.getFechaSalida(), huesped.getValor(), huesped.getMetodoPago() }));

			}
		});
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/lupa2.png")));
		btnBuscar.setBounds(815, 75, 54, 41);
		contentPane.add(btnBuscar);

		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/editar-texto.png")));
		btnEditar.setBackground(SystemColor.menu);
		btnEditar.setBounds(587, 416, 54, 41);
		contentPane.add(btnEditar);

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ((tbReservas.getSelectedRowCount() == tbReservas.getSelectedRowCount())
						|| tbHuespedes.getSelectedRowCount() == tbHuespedes.getSelectedRowCount()) {
					editarReser();
					editarHuesped();
				} else {

				}
			}

		});

		JLabel lblNewLabel_4 = new JLabel("Sistema de Búsqueda");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_4.setBounds(155, 42, 258, 42);
		contentPane.add(lblNewLabel_4);

		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

		});

		btnSalir.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(815, 416, 54, 41);
		contentPane.add(btnSalir);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBounds(10, 127, 774, 265);
		contentPane.add(panel);

		tbHuespedes = new JTable();
		tbHuespedes.setFont(new Font("Arial", Font.PLAIN, 14));

		modelo1 = (DefaultTableModel) tbHuespedes.getModel();
		modelo1.addColumn("identificacion");
		modelo1.addColumn("Nombre");
		modelo1.addColumn("Apellido");
		modelo1.addColumn("fecha de nacimiento");
		modelo1.addColumn("pais");
		modelo1.addColumn("telefono");
		modelo1.addColumn("Reserva");
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/persona.png")), tbHuespedes,
				null);

		cargarTabla();
		tbReservas.setFont(new Font("Arial", Font.PLAIN, 14));
		modelo2 = (DefaultTableModel) tbReservas.getModel();
		modelo2.addColumn("id");
		modelo2.addColumn("fecha entrada");
		modelo2.addColumn("fecha de salida");
		modelo2.addColumn("valor");
		modelo2.addColumn("id_pago");
		panel.addTab("ReservasLogic", new ImageIcon(Busqueda.class.getResource("/imagenes/calendario.png")), tbReservas,
				null);
		cargarTablaReserva();
		JButton btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/deletar.png")));
		btnEliminar.setBackground(SystemColor.menu);
		btnEliminar.setBounds(651, 416, 54, 41);
		contentPane.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ((tbReservas.getSelectedRowCount() == tbReservas.getSelectedRowCount())
						|| (tbHuespedes.getSelectedRowCount() == tbHuespedes.getSelectedRowCount())) {

					eliminarHuesped();
					eliminarReserva();
				} else {

				}
			}

		});

		JButton btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.setBackground(SystemColor.menu);
		btnCancelar.setBounds(713, 416, 54, 41);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}

		});
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(25, 10, 104, 107);
		contentPane.add(lblNewLabel_2);
		setResizable(false);
	}

	private void limpiarTablaHuespedes() {
		modelo1.getDataVector().clear();
		;

	}

	private void limpiarTablaReserva() {
		modelo2.getDataVector().clear();
		;

	}

	// me carga la tabla que me instancia a travez de la clase reservacrontoller por
	// medio de un for each ;
	private void cargarTabla() {

		var huespedes = this.reservar.listcompleta();
		huespedes.forEach(huesped -> modelo1.addRow(new Object[] { huesped.getId(), huesped.getNombre(),
				huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getPais(), huesped.getTelefono(),
				huesped.getReserva() }));
	}

	private void cargarTablaReserva() {
		var huespedes = this.reservar.listaReserva();
		huespedes.forEach(huesped -> modelo2.addRow(new Object[] { huesped.getId(), huesped.getFechaEntrada(),
				huesped.getFechaSalida(), huesped.getValor(), huesped.getMetodoPago() }));
	}

	private boolean seleccionCasilla() {
		return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedRowCount() == 0;
	}

	// cada vez que seleccione una casilla en la columna me lee toda la fila me la
	// edita;
	private void editarHuesped() {
		if (seleccionCasilla()) {
			JOptionPane.showMessageDialog(null, "Por favor, elije un item");
			return;
		}
		Optional.ofNullable(modelo1.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					try {
						Integer id = Integer.valueOf(modelo1.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
						String nombre = (String) modelo1.getValueAt(tbHuespedes.getSelectedRow(), 1);
						String apellido = (String) modelo1.getValueAt(tbHuespedes.getSelectedRow(), 2);
						String fechaNacimiento = modelo1.getValueAt(tbHuespedes.getSelectedRow(), 3).toString();
						Integer idPais = Integer
								.valueOf(modelo1.getValueAt(tbHuespedes.getSelectedRow(), 4).toString());
						Integer telefono = Integer
								.valueOf(modelo1.getValueAt(tbHuespedes.getSelectedRow(), 5).toString());
						Integer idReserva = Integer
								.valueOf(modelo1.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());

						reservar.modificar(nombre, apellido, fechaNacimiento, idPais, telefono, idReserva, id);

					} catch (NumberFormatException e) {
						System.out.println("error" + e);
					}
				}, () -> JOptionPane.showMessageDialog(null, "por favor elija item"));

	}

	private boolean seleccionCasillas() {
		return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedRowCount() == 0;
	}

	// cada vez que seleccione una casilla en la columna me lee toda la fila me la
	// edita;
	private void editarReser() {
		if (seleccionCasillas()) {
			JOptionPane.showMessageDialog(null, "Por favor, elije un item");
			return;
		}
		Optional.ofNullable(modelo2.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					try {
						Integer id = Integer.valueOf(modelo2.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						String fechaEn = (String) modelo2.getValueAt(tbReservas.getSelectedRow(), 1).toString();
						String fechaSa = (String) modelo2.getValueAt(tbReservas.getSelectedRow(), 2).toString();
						float valor = Float.valueOf(modelo2.getValueAt(tbReservas.getSelectedRow(), 3).toString());
						Integer idPago = Integer.valueOf(modelo2.getValueAt(tbReservas.getSelectedRow(), 4).toString());

						reservar.modificarRe(fechaEn, fechaSa, valor, idPago, id);

					} catch (NumberFormatException e) {
						System.out.println("error" + e);
					}
				}, () -> JOptionPane.showMessageDialog(null, "por favor elija item"));

	}

	// cada vez que seleccione una casilla en la columna me lee toda la fila me la
	// elimina;
	private void eliminarHuesped() {
		if (seleccionCasilla()) {
			JOptionPane.showMessageDialog(null, "fila huesped");
			return;
		}
		Optional.ofNullable(modelo1.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = Integer.valueOf(modelo1.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
					int cantidadEliminada;
					cantidadEliminada = this.reservar.eliminar(id);
					modelo1.removeRow(tbHuespedes.getSelectedRow());
					JOptionPane.showMessageDialog(this, cantidadEliminada + " Item eliminado con éxito!");
				}, () -> JOptionPane.showMessageDialog(this, "ha seleccionado de la tabla huesped, elije un item"));
	}

//cada vez que seleccione una casilla en la columna me lee toda la fila me la elimina;
	private void eliminarReserva() {
		if (seleccionCasillas()) {
			JOptionPane.showMessageDialog(null, "fila reserva");
			return;
		}
		Optional.ofNullable(modelo2.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = Integer.valueOf(modelo2.getValueAt(tbReservas.getSelectedRow(), 0).toString());
					int cantidadEliminada;
					cantidadEliminada = this.reservar.eliminarRe(id);
					modelo2.removeRow(tbReservas.getSelectedRow());
					JOptionPane.showMessageDialog(this, cantidadEliminada + " Item eliminado con éxito!");
				}, () -> JOptionPane.showMessageDialog(this, "ha seleccionado tabla reserva, elije un item"));
	}

}

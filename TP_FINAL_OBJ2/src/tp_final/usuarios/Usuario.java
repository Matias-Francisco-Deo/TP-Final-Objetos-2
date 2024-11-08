package tp_final.usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tp_final.reserva.Reserva;

public class Usuario {
	private String nombre;
	private String email;
	private String telefono;
	private List<Reserva> reservas = new ArrayList<Reserva>();

	public Usuario(String nombre, String email, String telefono) {
		this.setNombre(nombre);
		this.setEmail(email);
		this.setTelefono(telefono);
	}

	// ------------------------------
	// GETTERS & SETTERS
	// ------------------------------

	public String getNombre() {
		return this.nombre;
	}

	public String getEmail() {
		return this.email;
	}

	public String getTelefono() {
		return this.telefono;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	private void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	// ------------------------------
	// RESERVAS
	// ------------------------------

	public List<Reserva> getReservas() {
		return this.reservas;
	}

	public void realizarReserva(Reserva reserva) {
		this.getReservas().add(reserva);
	}

	public void cancelarReserva(Reserva reserva) {
		this.getReservas().remove(reserva);
	}

	public List<Reserva> getReservasFuturas() {
		return this.getReservas().stream().filter(reserva -> reserva.getFechaCheckIn().isAfter(LocalDate.now()))
				.toList();
	}

	public List<Reserva> getReservasEnCiudad(String ciudad) {
		return this.getReservas().stream().filter(reserva -> reserva.getCiudad().equals(ciudad)).toList();
	}

	public List<String> getCiudadesConReserva() {
		return this.getReservas().stream().map(reserva -> reserva.getCiudad()).toList();
	}
}

package tp_final.usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tp_final.reserva.Reserva;
import tp_final.reseña.Reseña;
import tp_final.reseña.Reseñable;

public class Usuario implements Reseñable {
	private String nombre;
	private String email;
	private String telefono;
	private List<Reserva> reservas = new ArrayList<Reserva>();
	private List<Reseña> reseñas = new ArrayList<Reseña>();

	public Usuario(String nombre, String email, String telefono) {
		this.setNombre(nombre);
		this.setEmail(email);
		this.setTelefono(telefono);
	}

	// ------------------------------------------------------------
	// NOMBRE, EMAIL Y TELEFONO
	// ------------------------------------------------------------

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

	// ------------------------------------------------------------
	// ACCIONES DE RESERVA
	// ------------------------------------------------------------

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

	// ------------------------------------------------------------
	// RESEÑAS DE INQUILINO
	// ------------------------------------------------------------

	@Override
	public List<Reseña> getReseñas() {
		return this.reseñas;
	}

	public void recibirReseña(Reseña reseña) {
		this.getReseñas().add(reseña);
	}

	@Override
	public List<Double> getPuntajes() {
		return this.getReseñas().stream().map(reseña -> reseña.getPuntaje()).toList();
	}

	@Override
	public double getPuntajePromedio() {
		return this.getPuntajes().stream().mapToDouble(puntaje -> puntaje).average().orElse(0);
	}

	@Override
	public List<String> getComentarios() {
		return this.getReseñas().stream().map(reseña -> reseña.getComentario()).toList();
	}
}

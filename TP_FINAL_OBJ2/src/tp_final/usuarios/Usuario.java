package tp_final.usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tp_final.alquiler.Alquiler;
import tp_final.inmueble.Inmueble;
import tp_final.ranking.GestorDeRanking;
import tp_final.reserva.Reserva;

public class Usuario {
	private String nombre;
	private String email;
	private String telefono;
	private GestorDeRanking gestorDeRanking;

	// RESERVAS REALIZADAS COMO INQUILINO
	private List<Reserva> reservas = new ArrayList<Reserva>();

	// ALQUILERES REGISTRADOS COMO PROPIETARIO
	private List<Alquiler> alquileresRegistrados = new ArrayList<Alquiler>();

	// INMUEBLES REGISTRADOS COMO PROPIETARIO
	private List<Inmueble> inmueblesRegistrados = new ArrayList<Inmueble>();

	// ------------------------------------------------------------

	public Usuario(String nombre, String email, String telefono, GestorDeRanking gestorDeRanking) {
		this.setNombre(nombre);
		this.setEmail(email);
		this.setTelefono(telefono);
		this.setGestorDeRanking(gestorDeRanking);
	}

	// ------------------------------------------------------------
	// NOMBRE, EMAIL, TELEFONO Y GESTOR DE RANKING
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

	public GestorDeRanking getGestorDeRanking() {
		return this.gestorDeRanking;
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

	private void setGestorDeRanking(GestorDeRanking gestorDeRanking) {
		this.gestorDeRanking = gestorDeRanking;
	}

	// ------------------------------------------------------------
	// RESERVAS REALIZADAS COMO INQUILINO
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
		return this.getReservas().stream().map(reserva -> reserva.getCiudad()).distinct().toList();
	}

	public int getCantidadDeReservas() {
		return this.getReservas().size();
	}

	// ------------------------------------------------------------
	// ALQUILERES E INMUEBLES REGISTRADOS COMO PROPIETARIO
	// ------------------------------------------------------------

	public void registrarAlquiler(Alquiler alquiler) {
		this.alquileresRegistrados.add(alquiler);
	}

	public void registrarInmueble(Inmueble inmueble) {
		this.inmueblesRegistrados.add(inmueble);
	}

	public List<Alquiler> getAlquileres() {
		return this.alquileresRegistrados;
	}

	public List<Inmueble> getInmuebles() {
		return this.inmueblesRegistrados;
	}

	// ------------------------------------------------------------
}

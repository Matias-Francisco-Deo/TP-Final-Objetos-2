package tp_final.alquiler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tp_final.estado_de_alquiler.Alquilado;
import tp_final.estado_de_alquiler.EstadoDeAlquiler;
import tp_final.estado_de_alquiler.Libre;
import tp_final.inmueble.Inmueble;
import tp_final.politica_cancelacion.PoliticaDeCancelacion;
import tp_final.usuarios.Usuario;
import tp_final_extra.Reserva;

public class Alquiler {

	private Inmueble inmueble;

	private LocalDate fechaEntrada;

	private LocalDate fechaSalida;

	private LocalTime checkIn;

	private LocalTime checkOut;

	private MedioDePago medioPago;

	private Map<String, Double> precioTemporadas;

	private double precioBase;

	private PoliticaDeCancelacion politicaDeCancelacion;

	private EstadoDeAlquiler estado;

	private List<Reserva> colaDeEspera;

	private List<Usuario> subscriptores;

	Alquiler(Inmueble inmueble, LocalTime checkIn, LocalTime checkOut, MedioDePago medioDePago, double precioBase,
			PoliticaDeCancelacion politica) {
		this.inmueble = inmueble;
		this.setCheckIn(checkIn);
		this.setCheckOut(checkOut);
		this.setMedioDePago(medioDePago);
		this.precioTemporadas = new HashMap<>();
		this.colaDeEspera = new ArrayList<>();
		this.setPrecioBase(precioBase);
		this.setPoliticaDeCancelacion(politica);
		this.estado = new Libre();
		this.colaDeEspera = new ArrayList<>();
		this.subscriptores = new ArrayList<>();
	}

	public Inmueble getInmueble() {
		return inmueble;
	}

	public Usuario getPropietario() {
		return (inmueble.getPropietario());
	}

	public LocalDate getFechaCheckIn() {
		return fechaEntrada;
	}

	public LocalDate getFechaDeCheckOut() {
		return fechaSalida;
	}

	private LocalTime getCheckIn() {
		return checkIn;
	}

	private LocalTime getCheckOut() {
		return checkOut;
	}

	public MedioDePago getMedioDePago() {
		return medioPago;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public String getCiudad() {
		return this.getInmueble().getCiudad();
	}

	public EstadoDeAlquiler getEstadoDeAlquiler() {
		return estado;
	}

	public PoliticaDeCancelacion getPoliticaDeCancelacion() {
		return this.politicaDeCancelacion;
	}

	public List<Reserva> getcolaDeEspera() {
		return this.colaDeEspera;
	}

	public List<Usuario> getSubscriptores() {
		return subscriptores;
	}

	public Map<String, Double> getPreciosTemporadas() {
		return this.precioTemporadas; // revisar metodo
	}

	public void setFechaDeEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public void setFechaDeSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	private void setCheckIn(LocalTime checkIn) {
		this.checkIn = checkIn;
	}

	private void setCheckOut(LocalTime checkOut) {
		this.checkOut = checkOut;
	}

	public void setMedioDePago(MedioDePago medioPago) {
		this.medioPago = medioPago;
	}

	public void setPrecioBase(double precioNuevo) {
		if (precioBase < precioNuevo) {

			// this.notificarSubs(this.mesajeDescuento());
		}

		this.precioBase = precioNuevo;
	}

	public void setPoliticaDeCancelacion(PoliticaDeCancelacion politica) {
		this.politicaDeCancelacion = politica;
	}

	public void setEstadoDeAlquiler(EstadoDeAlquiler estado) {
		this.estado = estado;
	}

	public void addSubscriptor(Usuario sub) {
		subscriptores.add(sub);
	}

	public void addPrecioTemporada(String temporada, Double precio) {
		precioTemporadas.put(temporada, precio);
	}

	public void addReserva(Reserva reserva) {
		this.colaDeEspera.add(reserva);
	}

	public void deleteSubscriptor(Usuario sub) {
		this.subscriptores.remove(sub);
	}

	public boolean esLibre() {
		return this.estado instanceof Libre;
	}

	public boolean esAlquilado() {
		return this.estado instanceof Alquilado;
	}

	public void reservar(Reserva reserva) {
		estado.alquilar(reserva, this);
	}

	public void confirmarReserva(Reserva reserva) {

		this.setEstadoDeAlquiler(new Alquilado());

		this.setFechaDeEntrada(reserva.getfechaEntrada());
		this.setFechaDeSalida(reserva.getfechaSalida());

		this.getInmueble().aumentarCantDeVecesAlquilado();

	}

	public void cancelarReserva(Reserva reserva) {

		this.estado.cancelar(reserva, this);
		this.politicaDeCancelacion.aplicarPolitica(reserva, this.getPrecioBase());
	}

	public void notificarSubs(String mensaje) {

		for (Usuario sub : subscriptores) {
			sub.mandarMensaje(mensaje);// implementar en usuario
		}
	}

	private String mesajeDescuento() {
		return ("“No te pierdas\r\n esta oferta: Un inmueble " + this.getTipoInmueble() + "a tan sólo "
				+ this.getPrecioBase() + "pesos”.");
	}

	private String mesajeCancelacion() {
		return ("“No te pierdas\r\n esta oferta: Un inmueble " + this.getTipoInmueble() + "a tan sólo "
				+ this.getPrecioBase() + "pesos”.");
	}

	private String getTipoInmueble() {
		return this.inmueble.getTipoInmueble();
	}

	public void doCancelarAlquilado(Reserva reserva) {

		List<Reserva> cola = this.getcolaDeEspera();
		Reserva reservaActual = cola.get(0);
		// reserva.cancelar()
		this.getcolaDeEspera().remove(reserva);

		if (cola.isEmpty()) {
			this.setEstadoDeAlquiler(new Libre());
			// this.notificarSubs(this.mesajeCancelacion());
		} else if (!cola.get(0).equals(reservaActual)) {
			this.setEstadoDeAlquiler(new Libre());
			// this.notificarSubs(this.mesajeCancelacion());
			cola.get(0).desencolar();
		}

	}

	public void doCancelarLibre(Reserva reserva) {
		List<Reserva> cola = this.getcolaDeEspera();

		// reserva.cancelar()

		if (cola.size() > 1 && cola.get(0).equals(reserva)) {

			this.getcolaDeEspera().remove(0);

			cola.get(0).desencolar();
		} else {
			this.getcolaDeEspera().remove(reserva);
		}

	}

}

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
import tp_final.reserva.Reserva;
import tp_final.suscriptores.Suscriptor;
import tp_final.usuarios.Usuario;

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

	private List<Suscriptor> subscriptores;

	Alquiler(Inmueble inmueble, LocalTime checkIn, LocalTime checkOut, MedioDePago medioDePago, double precioBase,
			PoliticaDeCancelacion politica) {
		this.inmueble = inmueble;
		this.setCheckIn(checkIn);
		this.setCheckOut(checkOut);
		this.setMedioDePago(medioDePago);
		this.precioTemporadas = new HashMap<>();
		this.colaDeEspera = new ArrayList<>();
		this.precioBase = precioBase;
		this.setPoliticaDeCancelacion(politica);
		this.estado = new Libre();
		this.colaDeEspera = new ArrayList<>();
		this.subscriptores = new ArrayList<>();
	}

	public Inmueble getInmueble() {
		return inmueble;
	}

	public Usuario getPropietario() {
		return inmueble.getPropietario();
	}

	public LocalDate getFechaCheckIn() {
		return fechaEntrada;
	}

	public LocalDate getFechaCheckOut() {
		return fechaSalida;
	}

	public LocalTime getCheckIn() {
		return checkIn;
	}

	public LocalTime getCheckOut() {
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

	public int getCantidadHuespedes() {
		return this.inmueble.getCapacidad();
	}

	public PoliticaDeCancelacion getPoliticaDeCancelacion() {
		return this.politicaDeCancelacion;
	}

	public List<Reserva> getColaDeEspera() {
		return this.colaDeEspera;
	}

	public List<Suscriptor> getSubscriptores() {
		return subscriptores;
	}

	public Map<String, Double> getPreciosTemporadas() {
		return this.precioTemporadas;
	}

	public double getPrecioEnTemporada(String temporada) {
		return this.getPreciosTemporadas().get(temporada);
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
		double precioantiguo = precioBase;

		this.precioBase = precioNuevo;

		comprobarBajaDePrecio(precioantiguo);

	}

	private void comprobarBajaDePrecio(double precioantiguo) {
		if (precioBase < precioantiguo) {

			this.notificarSubs(this.mensajeDescuento());
		}
	}

	public void setPoliticaDeCancelacion(PoliticaDeCancelacion politica) {
		this.politicaDeCancelacion = politica;
	}

	public void setEstadoDeAlquiler(EstadoDeAlquiler estado) {
		this.estado = estado;
	}

	public void addSubscriptor(Suscriptor sub) {
		subscriptores.add(sub);
	}

	public void addPrecioTemporada(String temporada, Double precio) {
		precioTemporadas.put(temporada, precio);
	}

	public void addReserva(Reserva reserva) {
		this.colaDeEspera.add(reserva);
	}

	public void deleteSubscriptor(Suscriptor sub) {
		this.subscriptores.remove(sub);
	}

	public boolean esLibre() {
		return this.estado.esLibre();
	}

	public void reservar(Reserva reserva) {
		estado.alquilar(reserva, this);
	}

	public void confirmarReserva(Reserva reserva) {

		this.setEstadoDeAlquiler(new Alquilado());

		this.setFechaDeEntrada(reserva.getFechaCheckIn());
		this.setFechaDeSalida(reserva.getFechaCheckOut());

		this.getInmueble().aumentarCantDeVecesAlquilado();
	}

	public void cancelarReserva(Reserva reserva) {

		this.estado.cancelar(reserva, this);
		this.politicaDeCancelacion.aplicarPolitica(reserva, this.getPrecioBase());
	}

	public void notificarSubs(String mensaje) {

		for (Suscriptor sub : subscriptores) {
			sub.mandarMensaje(mensaje);
		}
	}

	private String mensajeDescuento() {
		return ("No te pierdas\r\n esta oferta: Un inmueble " + this.getTipoInmueble() + " a tan sólo "
				+ this.getPrecioBase() + " pesos.");
	}

	private String mensajeCancelacion() {
		return ("El/la " + this.getTipoInmueble() + " que te interesa se ha liberado! Corre a reservarlo");
	}

	private String getTipoInmueble() {
		return this.inmueble.getTipoInmueble();
	}

	public void doCancelarAlquilado(Reserva reservaACancelar) {

		List<Reserva> cola = this.getColaDeEspera();
		Reserva primeraReserva = cola.get(0);
		this.getColaDeEspera().remove(reservaACancelar);

		boolean laReservaQueSeCancelaEraLaPrimera = primeraReserva.equals(reservaACancelar);

		// si no queda nadie más
		if (cola.isEmpty()) {
			this.setEstadoDeAlquiler(new Libre());
			this.notificarSubs(this.mensajeCancelacion());

		} else if (laReservaQueSeCancelaEraLaPrimera) {
			// en caso que sea la primera, significa que hay reservas delante y desencolo la
			// siguiente
			Reserva reservaSiguiente = cola.get(0);
			this.setEstadoDeAlquiler(new Libre());
			reservaSiguiente.desencolar();
			this.notificarSubs(this.mensajeCancelacion());
		}
		// si era una reserva de cualquier otra posición (la segunda, tercera, cuarta)
		// no hace falta desencolar y además no hace falta notificar cancelación

	}

	public void doCancelarLibre(Reserva reservaACancelar) {
		List<Reserva> cola = this.getColaDeEspera();
		Reserva primeraReserva = cola.get(0);
		boolean laReservaQueSeCancelaEraLaPrimera = primeraReserva.equals(reservaACancelar);

		if (cola.size() > 1 && laReservaQueSeCancelaEraLaPrimera) {

			this.getColaDeEspera().remove(0);

			// desencola siguiente
			cola.get(0).desencolar();

		} else if (cola.size() >= 1) {
			// si no era el primero, simplemente quita al actual
			this.getColaDeEspera().remove(reservaACancelar);
		}
		// si size es 0, entonces no hace nada

	}

	public EstadoDeAlquiler getEstadoDeAlquiler() {
		return this.estado;
	}

	public void doAlquilarLibre(Reserva reserva) {
		if (this.getColaDeEspera().isEmpty()) {

			this.addReserva(reserva);
		} else {
			this.addReserva(reserva);
			reserva.encolar();

		}
	}

	public void doAlquilarAlquilado(Reserva reserva) {
		this.addReserva(reserva);
		reserva.encolar();

	}
}

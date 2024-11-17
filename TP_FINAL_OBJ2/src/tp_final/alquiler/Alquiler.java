package tp_final.alquiler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private List<Suscriptor> subscriptores;

	Alquiler(Inmueble inmueble, LocalTime checkIn, LocalTime checkOut, LocalDate fechaEntrada, LocalDate fechaSalida,
			MedioDePago medioDePago, double precioBase, PoliticaDeCancelacion politica) {
		this.inmueble = inmueble;
		this.setCheckIn(checkIn);
		this.setCheckOut(checkOut);
		this.setFechaDeEntrada(fechaEntrada);
		this.setFechaDeSalida(fechaSalida);
		this.setMedioDePago(medioDePago);
		this.precioTemporadas = new HashMap<>();
		this.precioBase = precioBase;
		this.setPoliticaDeCancelacion(politica);
		this.subscriptores = new ArrayList<>();
		new ManagerDeAlquiler(this);
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

	public void addSubscriptor(Suscriptor sub) {
		subscriptores.add(sub);
	}

	public void addPrecioTemporada(String temporada, Double precio) {
		precioTemporadas.put(temporada, precio);
	}

	public void deleteSubscriptor(Suscriptor sub) {
		this.subscriptores.remove(sub);
	}

	public void aplicarPoliticaDeCancelacion(Reserva reserva) {
		this.politicaDeCancelacion.aplicarPolitica(reserva, this.getPrecioBase());
	}// se queda aca o pasa a manager?

	private void notificarSubs(String mensaje) {

		for (Suscriptor sub : subscriptores) {
			sub.mandarMensaje(mensaje);
		}
	}

	public void notificarCancelacion() {
		this.notificarSubs(this.mensajeCancelacion());
	}

	private String mensajeDescuento() {
		return ("No te pierdas\r\n esta oferta: Un inmueble " + this.getTipoInmueble() + " a tan sólo "
				+ this.getPrecioBase() + " pesos.");
	}

	public String mensajeCancelacion() {
		return ("El/la " + this.getTipoInmueble() + " que te interesa se ha liberado! Corre a reservarlo");
	}

	private String getTipoInmueble() {
		return this.inmueble.getTipoInmueble();
	}

}

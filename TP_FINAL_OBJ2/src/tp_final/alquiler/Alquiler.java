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

	private double precioBase = 0;

	private PoliticaDeCancelacion politicaDeCancelacion;

	private List<Suscriptor> subscriptores;

	private ManagerDeAlquiler manager;

	Alquiler(Inmueble inmueble, LocalTime checkIn, LocalTime checkOut, LocalDate fechaEntrada, LocalDate fechaSalida,
			MedioDePago medioDePago, double precioBase, PoliticaDeCancelacion politica) {
		this.inmueble = inmueble;
		this.setCheckIn(checkIn);
		this.setCheckOut(checkOut);
		this.setFechaDeEntrada(fechaEntrada);
		this.setFechaDeSalida(fechaSalida);
		this.setMedioDePago(medioDePago);
		this.precioTemporadas = new HashMap<>();
		this.cambiarPrecioBase(precioBase);
		this.setPoliticaDeCancelacion(politica);
		this.subscriptores = new ArrayList<>();
		this.manager = new ManagerDeAlquiler(this);
	}

	private ManagerDeAlquiler getManager() {
		return this.manager;
	}

	public Inmueble getInmueble() {
		return this.inmueble;
	}

	public Usuario getPropietario() {
		return this.inmueble.getPropietario();
	}

	public LocalDate getFechaCheckIn() {
		return this.fechaEntrada;
	}

	public LocalDate getFechaCheckOut() {
		return this.fechaSalida;
	}

	public LocalTime getCheckIn() {
		return this.checkIn;
	}

	public LocalTime getCheckOut() {
		return this.checkOut;
	}

	public MedioDePago getMedioDePago() {
		return this.medioPago;
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

	private void setPrecioBase(double precioNuevo) {
		this.precioBase = precioNuevo;
	}

	public void cambiarPrecioBase(double precioNuevo) {
		double precioantiguo = precioBase;

		this.setPrecioBase(precioNuevo);

		this.comprobarBajaDePrecio(precioantiguo);

	}

	private void comprobarBajaDePrecio(double precioantiguo) {
		if (this.getPrecioBase() < precioantiguo) {

			this.notificarSubs(this.mensajeDescuento());
		}
	}

	public void setPoliticaDeCancelacion(PoliticaDeCancelacion politica) {
		this.politicaDeCancelacion = politica;
	}

	public void addSubscriptor(Suscriptor sub) {
		this.getSubscriptores().add(sub);
	}

	public void addPrecioTemporada(String temporada, Double precio) {
		this.getPreciosTemporadas().put(temporada, precio);
	}

	public void deleteSubscriptor(Suscriptor sub) {
		this.getSubscriptores().remove(sub);
	}

	public void aplicarPoliticaDeCancelacion(Reserva reserva) {
		this.getPoliticaDeCancelacion().aplicarPolitica(reserva, this.getPrecioBase());
	}

	private void notificarSubs(String mensaje) {

		this.getSubscriptores().forEach(sub -> sub.mandarMensaje(mensaje));
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
		return this.getInmueble().getTipoInmueble();
	}

	public boolean estaLibreEnRango(LocalDate fechaCheckIn, LocalDate fechaCheckOut) {
		return this.getManager().elRangoEstaOcupadoPorAlgunaReserva(fechaCheckIn, fechaCheckOut);
	}

	public boolean esLibre() {

		return this.getManager().getColaReservados().isEmpty();
	}

}

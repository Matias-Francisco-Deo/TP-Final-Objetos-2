package tp_final.alquiler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tp_final.reserva.Reserva;

public class ManagerDeAlquiler {

	private Alquiler alquiler;

	private List<Reserva> colaDeEspera;

	private List<Reserva> colaDeVigentes;

	public ManagerDeAlquiler(Alquiler alquiler) {

		this.colaDeEspera = new ArrayList<>();
		this.colaDeVigentes = new ArrayList<>();
		this.setAlquiler(alquiler);

	}

	public List<Reserva> getColaDeEspera() {
		return this.colaDeEspera;
	}

	public List<Reserva> getColaReservados() {
		return this.colaDeVigentes;
	}

	private void setAlquiler(Alquiler alquiler) {
		this.alquiler = alquiler;
	}

	public void addReservaEnCola(Reserva reserva) {
		this.getColaDeEspera().add(reserva);
	}

	public void addReservaEnVigencia(Reserva reserva) {
		this.getColaReservados().add(reserva);
	}

	public void reservar(Reserva reserva) {
		LocalDate checkIn = reserva.getFechaCheckIn();
		LocalDate checkOut = reserva.getFechaCheckOut();

		LocalDate fechaInicioAlquilado = alquiler.getFechaCheckIn();
		LocalDate fechaFinAlquilado = alquiler.getFechaCheckOut();

		if (this.verificarSiAmbasFechasEstanDentroDeUnRango(fechaInicioAlquilado, fechaFinAlquilado, checkIn,
				checkOut)) {
			this.realizarReserva(reserva);
		}
	}

	private void realizarReserva(Reserva reserva) {
		LocalDate checkIn = reserva.getFechaCheckIn();
		LocalDate checkOut = reserva.getFechaCheckOut();

		boolean ocupado = elRangoEstaOcupadoPorAlgunaReserva(checkIn, checkOut);
		if (ocupado) {
			this.addReservaEnCola(reserva);

			reserva.encolar();

		} else {
			reserva.desencolar();
			this.addReservaEnVigencia(reserva);
		}
	}

	public boolean elRangoEstaOcupadoPorAlgunaReserva(LocalDate checkIn, LocalDate checkOut) {
		return this.getColaReservados().stream()
				.anyMatch(r -> verificarSiReservaOcupaRangoPedido(r, checkIn, checkOut));
	}

	private boolean verificarSiReservaOcupaRangoPedido(Reserva reserva, LocalDate checkIn, LocalDate checkOut) {
		LocalDate entrada = reserva.getFechaCheckIn();
		LocalDate salida = reserva.getFechaCheckOut();

		return (verificarSiRangoOcupadoEstaDentroDeRangoDeReserva(entrada, salida, checkIn, checkOut)
				|| verificarSiFechaDentroDelRango(entrada, salida, checkIn)
				|| verificarSiFechaDentroDelRango(entrada, salida, checkOut));

	}

	private boolean verificarSiRangoOcupadoEstaDentroDeRangoDeReserva(LocalDate entrada, LocalDate salida,
			LocalDate checkIn, LocalDate checkOut) {

		return checkIn.isBefore(entrada.plusDays(1)) && checkOut.isAfter(salida.minusDays(1));
	}

	private boolean verificarSiAmbasFechasEstanDentroDeUnRango(LocalDate entrada, LocalDate salida, LocalDate checkIn,
			LocalDate checkOut) {
		return (verificarSiFechaDentroDelRango(entrada, salida, checkIn)
				&& verificarSiFechaDentroDelRango(entrada, salida, checkOut));
	}

	private boolean verificarSiFechaDentroDelRango(LocalDate entrada, LocalDate salida, LocalDate fecha) {

		return fecha.isBefore(salida.plusDays(1)) && fecha.isAfter(entrada.minusDays(1));
	}

	private void comprobarYMoverReservasEncoladas() {

		for (int i = 0; i < this.getColaDeEspera().size(); i++) {
			Reserva reservaEnEspera = this.getColaDeEspera().get(i);
			LocalDate checkIn = reservaEnEspera.getFechaCheckIn();
			LocalDate checkOut = reservaEnEspera.getFechaCheckOut();

			boolean ocupado = elRangoEstaOcupadoPorAlgunaReserva(checkIn, checkOut);

			if (!ocupado) {

				addReservaEnVigencia(reservaEnEspera);

				reservaEnEspera.desencolar();

				deleteReservaDe(this.getColaDeEspera(), reservaEnEspera);

				i--;
			}
		}
	}

	public void cancelarReserva(Reserva reservaACancelar) {

		if (this.getColaDeEspera().contains(reservaACancelar)) {
			deleteReservaDe(this.getColaDeEspera(), reservaACancelar);
		} else {
			deleteReservaDe(this.getColaReservados(), reservaACancelar);
			this.comprobarYMoverReservasEncoladas();
			alquiler.notificarCancelacion();
		}

		alquiler.aplicarPoliticaDeCancelacion(reservaACancelar);

	}

	private void deleteReservaDe(List<Reserva> lista, Reserva reserva) {
		lista.remove(reserva);
	}

	public boolean estaLibreElAlquiler() {
		return this.getColaReservados().isEmpty();
	}

	public void finalizarReserva(Reserva reservaFinalizada) {

		deleteReservaDe(this.getColaReservados(), reservaFinalizada);
		this.comprobarYMoverReservasEncoladas();

	}

}

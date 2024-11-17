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

	private Alquiler getAlquiler() {
		return this.alquiler;
	}

	public List<Reserva> getColaDeEspera() {
		return this.colaDeEspera;
	}

	public List<Reserva> getColaReservados() {
		return this.colaDeVigentes;
	}

	private Alquiler setAlquiler(Alquiler alquiler) {
		return this.alquiler = alquiler;
	}

	public void addReservaEnCola(Reserva reserva) {
		this.colaDeEspera.add(reserva);
	}

	public void addReservaEnVigencia(Reserva reserva) {
		this.colaDeVigentes.add(reserva);
	}

	public void reservar(Reserva reserva) {
		LocalDate checkIn = reserva.getFechaCheckIn();
		LocalDate checkOut = reserva.getFechaCheckOut();

		boolean ocupado = verificarSiElRangoEstaOcupadoPorAlgunaReserva(this.colaDeVigentes, checkIn, checkOut);
		if (ocupado) {
			this.addReservaEnCola(reserva);
		} else {
			reserva.desencolar();
			this.addReservaEnVigencia(reserva);
		}
	}

	private boolean verificarSiElRangoEstaOcupadoPorAlgunaReserva(List<Reserva> lista, LocalDate checkIn,
			LocalDate checkOut) {
		return lista.stream().anyMatch(r -> verificarSiReservaOcupaRangoPedido(r, checkIn, checkOut));
	}

	private boolean verificarSiReservaOcupaRangoPedido(Reserva reserva, LocalDate checkIn, LocalDate checkOut) {
		LocalDate entrada = reserva.getFechaCheckIn();
		LocalDate salida = reserva.getFechaCheckOut();

		return (verificarSiRangoOcupadoEstaDentroDeRangoDeReserva(entrada, salida, checkIn, checkOut)
				|| verificarSiFechaDentroDelRangoOcupado(entrada, salida, checkIn)
				|| verificarSiFechaDentroDelRangoOcupado(entrada, salida, checkOut));

	}

	private boolean verificarSiRangoOcupadoEstaDentroDeRangoDeReserva(LocalDate entrada, LocalDate salida,
			LocalDate checkIn, LocalDate checkOut) {

		return checkIn.isBefore(entrada.plusDays(1)) && checkOut.isAfter(salida.minusDays(1));
	}

	private boolean verificarSiFechaDentroDelRangoOcupado(LocalDate entrada, LocalDate salida, LocalDate fecha) {

		return fecha.isBefore(salida.plusDays(1)) && fecha.isAfter(entrada.minusDays(1));
	}

	private void moverReservasAhoraValidas() {

		for (int i = 0; i < colaDeEspera.size(); i++) {
			Reserva reservaEnEspera = colaDeEspera.get(i);
			LocalDate checkIn = reservaEnEspera.getFechaCheckIn();
			LocalDate checkOut = reservaEnEspera.getFechaCheckOut();

			boolean ocupado = verificarSiElRangoEstaOcupadoPorAlgunaReserva(colaDeVigentes, checkIn, checkOut);

			if (!ocupado) {

				addReservaEnVigencia(reservaEnEspera);

				reservaEnEspera.desencolar();

				deleteReservaDe(this.getColaDeEspera(), reservaEnEspera);

				i--;
			}
		}
	}

	// necesario el metodo confirmar??
	// this.getInmueble().aumentarCantDeVecesAlquilado();// se agrega o pasa al
	// check out de reserva??

	public void cancelarReserva(Reserva reservaACancelar) {// testear

		alquiler.aplicarPoliticaDeCancelacion(reservaACancelar);// mover mas abajo?

		if (this.getColaDeEspera().contains(reservaACancelar)) {
			deleteReservaDe(this.getColaDeEspera(), reservaACancelar);
		} else {
			deleteReservaDe(this.getColaReservados(), reservaACancelar);
			this.moverReservasAhoraValidas();
			alquiler.notificarCancelacion();
		}

	}

	private void deleteReservaDe(List<Reserva> lista, Reserva reserva) {
		lista.remove(reserva);
	}

}

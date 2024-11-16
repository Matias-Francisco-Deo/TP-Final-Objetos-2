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

		boolean ocupado = verificarSiElRangoEstaOcupado(this.colaDeVigentes, checkIn, checkOut);
		if (ocupado) {
			this.addReservaEnCola(reserva);
		} else {
			this.addReservaEnVigencia(reserva);
		}
	}

	private boolean verificarSiElRangoEstaOcupado(List<Reserva> lista, LocalDate checkIn, LocalDate checkOut) {
		return lista.stream().anyMatch(r -> verificarSiReservaOcupadaRango(r, checkIn, checkOut));
	}

	private boolean verificarSiReservaOcupadaRango(Reserva reserva, LocalDate checkIn, LocalDate checkOut) {
		LocalDate entrada = reserva.getFechaCheckIn();
		LocalDate salida = reserva.getFechaCheckOut();

		return (verificarRangoEstaOcupado(entrada, salida, checkIn)
				|| verificarRangoEstaOcupado(entrada, salida, checkOut));

	}

	private boolean verificarRangoEstaOcupado(LocalDate checkIn, LocalDate checkOut, LocalDate fecha) {

		return fecha.isBefore(checkOut.plusDays(1)) && fecha.isAfter(checkIn.minusDays(1));
	}

	private void moverReservasAhoraValidas() {

		for (int i = 0; i < colaDeEspera.size(); i++) {
			Reserva reservaEnEspera = colaDeEspera.get(i);
			LocalDate checkIn = reservaEnEspera.getFechaCheckIn();
			LocalDate checkOut = reservaEnEspera.getFechaCheckOut();

			boolean ocupado = verificarSiElRangoEstaOcupado(colaDeVigentes, checkIn, checkOut);

			if (!ocupado) {

				addReservaEnVigencia(reservaEnEspera);

				colaDeEspera.remove(i);

				i--;
			}
		}
	}

	// necesario el metodo confirmar??

	public void cancelarReserva(Reserva reservaACancelar) {// testear

		if (this.getColaDeEspera().contains(reservaACancelar)) {
			deleteReservaDe(this.getColaDeEspera(), reservaACancelar);
		} else {
			deleteReservaDe(this.getColaReservados(), reservaACancelar);
			this.moverReservasAhoraValidas();
		}

	}

	private void deleteReservaDe(List<Reserva> lista, Reserva reserva) {
		lista.remove(reserva);
	}

}

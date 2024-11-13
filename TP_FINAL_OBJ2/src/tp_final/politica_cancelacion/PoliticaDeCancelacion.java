package tp_final.politica_cancelacion;

import java.time.LocalDate;

import tp_final.reserva.Reserva;

public abstract class PoliticaDeCancelacion {

	public void aplicarPolitica(Reserva reserva, double precio) {

		LocalDate checkIn = reserva.getFechaCheckIn();
		LocalDate checkOut = reserva.getFechaCheckOut();

		double monto = this.calcularCosto(checkIn, checkOut, precio);
		String texto = this.generarMail(monto);
		this.enviarMail(texto);
	}

	public abstract double calcularCosto(LocalDate checkIn, LocalDate checkOut, double precio);

	public String generarMail(double monto) {
		return ("Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de "
				+ monto + " $");
	}

	public void enviarMail(String texto) {

	}
}

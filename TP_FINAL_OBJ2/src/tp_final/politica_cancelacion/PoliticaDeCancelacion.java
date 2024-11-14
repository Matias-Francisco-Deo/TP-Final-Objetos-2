package tp_final.politica_cancelacion;

import java.time.LocalDate;

import tp_final.reserva.Reserva;
import tp_final.varios.ServidorDeCorreo;

public abstract class PoliticaDeCancelacion {

	private ServidorDeCorreo servidorDeCorreo;

	public PoliticaDeCancelacion(ServidorDeCorreo sistemaDeCorreo) {
		this.servidorDeCorreo = sistemaDeCorreo;
	}

	final public void aplicarPolitica(Reserva reserva, double precio) {

		LocalDate checkIn = reserva.getFechaCheckIn();
		LocalDate checkOut = reserva.getFechaCheckOut();

		double monto = this.calcularCosto(checkIn, checkOut, precio);
		String texto = this.generarMail(monto);
		this.enviarMail(texto, reserva);
	}

	public abstract double calcularCosto(LocalDate checkIn, LocalDate checkOut, double precio);

	final public String generarMail(double monto) {
		return "Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de "
				+ monto + " $";
	}

	final public void enviarMail(String texto, Reserva reserva) {
		String destinatario = reserva.getEmailInquilino();
		this.servidorDeCorreo.enviar(destinatario, "Factura por la cancelacion de su reserva", texto);
	}
}

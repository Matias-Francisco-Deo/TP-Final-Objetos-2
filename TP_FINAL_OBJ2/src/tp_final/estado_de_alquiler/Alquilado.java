package tp_final.estado_de_alquiler;

import tp_final.alquiler.Alquiler;
import tp_final.reserva.Reserva;

public class Alquilado implements EstadoDeAlquiler {

	@Override
	public void alquilar(Reserva reserva, Alquiler alquiler) {
		alquiler.addReserva(reserva);
		reserva.encolar();
	}

	@Override
	public void cancelar(Reserva reserva, Alquiler alquiler) {

		alquiler.doCancelarAlquilado(reserva);

	}

	@Override
	public boolean esLibre() {
		return false;
	}

}

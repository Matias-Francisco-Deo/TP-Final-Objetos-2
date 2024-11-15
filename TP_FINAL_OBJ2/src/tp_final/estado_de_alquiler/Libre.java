package tp_final.estado_de_alquiler;

import tp_final.alquiler.Alquiler;
import tp_final.reserva.Reserva;

public class Libre implements EstadoDeAlquiler {

	@Override
	public void alquilar(Reserva reserva, Alquiler alquiler) {
		alquiler.doAlquilarLibre(reserva);

	}

	@Override
	public void cancelar(Reserva reserva, Alquiler alquiler) {
		alquiler.doCancelarLibre(reserva);

	}

	@Override
	public boolean esLibre() {
		return true;
	}
}

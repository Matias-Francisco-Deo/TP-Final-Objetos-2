package tp_final.estado_de_alquiler;

import tp_final.alquiler.Alquiler;
import tp_final.reserva.Reserva;

public interface EstadoDeAlquiler {
	public void alquilar(Reserva reserva, Alquiler alquiler);

	public void cancelar(Reserva reserva, Alquiler alquiler);
}

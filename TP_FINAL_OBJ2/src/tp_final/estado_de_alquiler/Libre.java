package tp_final.estado_de_alquiler;

import tp_final.alquiler.Alquiler;
import tp_final_extra.Reserva;

public class Libre implements EstadoDeAlquiler {

	@Override
	public void alquilar(Reserva reserva, Alquiler alquiler) {
		if (alquiler.getcolaDeEspera().isEmpty()) {

			alquiler.addReserva(reserva);
		} else {
			alquiler.addReserva(reserva);
			// reserva.encolar
		}

	}

	@Override
	public void cancelar(Reserva reserva, Alquiler alquiler) {

		alquiler.doCancelarLibre(reserva);

	}
	/*
	 * @Override public void cancelar(Reserva reserva, Alquiler alquiler) {
	 * List<Reserva> cola = alquiler.getcolaDeEspera();
	 * 
	 * // reserva.cancelar()
	 * 
	 * if (cola.size() > 1 && cola.get(0).equals(reserva)) {
	 * 
	 * alquiler.getcolaDeEspera().remove(0);
	 * 
	 * this.prepararSiguiente(cola); } else {
	 * alquiler.getcolaDeEspera().remove(reserva); }
	 * 
	 * }
	 * 
	 * private void prepararSiguiente(List<Reserva> cola) {
	 * cola.get(0).desencolar(); // cambiar por el metodo de la reserva original }
	 */
}

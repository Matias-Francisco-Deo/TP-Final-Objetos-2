package tp_final.estado_de_alquiler;

import tp_final.alquiler.Alquiler;
import tp_final_extra.Reserva;//reemplazar por el reserva real

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

}

/*
 * @Override public void cancelar(Reserva reserva, Alquiler alquiler) {
 *
 * List<Reserva> cola = alquiler.getcolaDeEspera();// mover codigo a alquiler
 * Reserva reservaActual = cola.get(0); // reserva.cancelar()
 * alquiler.getcolaDeEspera().remove(reserva);
 *
 * if (cola.isEmpty()) { alquiler.setEstadoDeAlquiler(new Libre());
 * alquiler.notificarSubs(); } else if (!cola.get(0).equals(reservaActual)) {
 * alquiler.setEstadoDeAlquiler(new Libre()); alquiler.notificarSubs();
 * this.prepararSiguiente(cola); }
 *
 * }
 *
 * private void prepararSiguiente(List<Reserva> cola) {
 * cola.get(0).desencolar(); }
 */

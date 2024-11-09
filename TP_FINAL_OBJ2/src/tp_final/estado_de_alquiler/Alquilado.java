package tp_final.estado_de_alquiler;

import java.util.List;

import tp_final.alquiler.Alquiler;
import tp_final_extra.Reserva;

public class Alquilado implements EstadoDeAlquiler{
	
	public void alquilar(Reserva reserva,Alquiler alquiler) {
		alquiler.addReserva(reserva);
	}
	
	public void cancelar(Reserva reserva,Alquiler alquiler) {
		
		List<Reserva> cola = alquiler.getcolaDeEspera();
		Reserva reservaActual = cola.get(0);
		//reserva.cancelar()
		alquiler.getcolaDeEspera().remove(reserva);
		
		if(cola.isEmpty()) {
			alquiler.setEstadoDeAlquiler(new Libre());
			alquiler.notificarSubs();
		}else if(!cola.get(0).equals(reservaActual)) {
			alquiler.setEstadoDeAlquiler(new Libre());
			alquiler.notificarSubs();
			//cola.get(0).encolar()
		}
		 
	}
}

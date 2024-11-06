package tp_final;

import java.util.List;

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
		}else if(!cola.get(0).equals(reservaActual)) {
			alquiler.setEstadoDeAlquiler(new Libre());
			//cola.get(0).encolar()
		}
		
	}
}

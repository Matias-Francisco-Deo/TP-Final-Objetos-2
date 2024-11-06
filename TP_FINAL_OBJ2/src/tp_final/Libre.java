package tp_final;

import java.util.List;

public class Libre implements EstadoDeAlquiler{
	
	public void alquilar(Reserva reserva,Alquiler alquiler) {
		if (alquiler.getcolaDeEspera().isEmpty()) {
			//reserva.encolar
			alquiler.addReserva(reserva);
		}else {
			alquiler.addReserva(reserva);
		}
		
	} 
	
	public void cancelar(Reserva reserva,Alquiler alquiler) {
		List<Reserva> cola = alquiler.getcolaDeEspera();
		if (cola.size() > 1 && cola.get(0).equals(reserva)) {
			//reserva.cancelar()
			alquiler.getcolaDeEspera().remove(0);
			
			//cola.get(0).encolar()
		}else {
			alquiler.getcolaDeEspera().remove(reserva);
		}
		//reserva.cancelar()
	}
}

package tp_final.reserva;

import tp_final.reseña.CategoriaDeReseñaDeInmueble;
import tp_final.reseña.Reseña;

public class EnCola implements EstadoDeReserva {
	@Override
	public void aprobar(Reserva reserva) {
		// NOTHING
	}

	@Override
	public void cancelar(Reserva reserva) {
		reserva.setEstado(new Cancelado());
		reserva.doCancelar();
	}

	@Override
	public void finalizar(Reserva reserva) {
		// NOTHING
	}

	@Override
	public void encolar(Reserva reserva) {
		// NOTHING
	}

	@Override
	public void desencolar(Reserva reserva) {
		reserva.setEstado(new PendienteDeAprobacion());
	}

	@Override
	public void rankearInmueble(Reserva reserva, CategoriaDeReseñaDeInmueble categoria, Reseña reseña) {
		// NOTHING
	}

	@Override
	public void rankearInquilino(Reserva reserva, Reseña reseña) {
		// NOTHING
	}

	@Override
	public void rankearPropietario(Reserva reserva, Reseña reseña) {
		// NOTHING
	}
}

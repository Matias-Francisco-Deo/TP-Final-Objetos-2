package tp_final.reserva;

import tp_final.reseña.CategoriaDeReseñaDeInmueble;
import tp_final.reseña.Reseña;

public class Finalizado implements EstadoDeReserva {
	@Override
	public void aprobar(Reserva reserva) {
		// NOTHING
	}

	@Override
	public void cancelar(Reserva reserva) {
		// NOTHING
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
		// NOTHING
	}

	@Override
	public void rankearInmueble(Reserva reserva, CategoriaDeReseñaDeInmueble categoria, Reseña reseña) {
		reserva.doRankearInmueble(categoria, reseña);
	}

	@Override
	public void rankearInquilino(Reserva reserva, Reseña reseña) {
		reserva.doRankearInquilino(reseña);
	}

	@Override
	public void rankearPropietario(Reserva reserva, Reseña reseña) {
		reserva.rankearPropietario(reseña);
	}
}

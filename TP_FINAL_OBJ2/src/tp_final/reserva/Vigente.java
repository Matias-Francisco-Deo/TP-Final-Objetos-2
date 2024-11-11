package tp_final.reserva;

import tp_final.reseña.CategoriaDeReseñaDeInmueble;
import tp_final.reseña.Reseña;

public class Vigente implements EstadoDeReserva {
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
		reserva.setEstado(new Finalizado());
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
		reserva.doRankearPropietario(reseña);
	}
}

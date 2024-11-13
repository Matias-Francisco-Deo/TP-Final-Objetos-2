package tp_final.reserva;

import tp_final.ranking.Ranking;

public class PendienteDeAprobacion implements EstadoDeReserva {
	@Override
	public void aprobar(Reserva reserva) {
		reserva.setEstado(new Vigente());
		reserva.doAprobar();
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
		reserva.setEstado(new EnCola());
	}

	@Override
	public void desencolar(Reserva reserva) {
		// NOTHING
	}

	@Override
	public void rankearInmueble(Reserva reserva, Ranking ranking) {
		// NOTHING
	}

	@Override
	public void rankearInquilino(Reserva reserva, Ranking ranking) {
		// NOTHING
	}

	@Override
	public void rankearPropietario(Reserva reserva, Ranking ranking) {
		// NOTHING
	}
}

package tp_final.reserva;

import tp_final.ranking.Ranking;

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
	public void rankearInmueble(Reserva reserva, Ranking ranking) {
		reserva.doRankearInmueble(ranking);
	}

	@Override
	public void rankearInquilino(Reserva reserva, Ranking ranking) {
		reserva.doRankearInquilino(ranking);
	}

	@Override
	public void rankearPropietario(Reserva reserva, Ranking ranking) {
		reserva.doRankearPropietario(ranking);
	}

	@Override
	public void rechazar(Reserva reserva) {
		// NOTHING

	}
}

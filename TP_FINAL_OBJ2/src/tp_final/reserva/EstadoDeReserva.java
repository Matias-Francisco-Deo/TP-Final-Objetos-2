package tp_final.reserva;

import tp_final.ranking.Ranking;

public interface EstadoDeReserva {
	public void aprobar(Reserva reserva);

	public void cancelar(Reserva reserva);

	public void finalizar(Reserva reserva);

	public void encolar(Reserva reserva);

	public void desencolar(Reserva reserva);

	public void rankearInmueble(Reserva reserva, Ranking ranking);

	public void rankearInquilino(Reserva reserva, Ranking ranking);

	public void rankearPropietario(Reserva reserva, Ranking ranking);
}

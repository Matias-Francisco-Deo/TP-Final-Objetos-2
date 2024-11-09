package tp_final.reserva;

public class Cancelado implements EstadoDeReserva {
	@Override
	public void aprobar(Reserva reserva) {
		// TODO: NOTHING
	}

	@Override
	public void cancelar(Reserva reserva) {
		// TODO: NOTHING
	}

	@Override
	public void finalizar(Reserva reserva) {
		reserva.setEstado(new PendienteDeAprobacion());
	}
}
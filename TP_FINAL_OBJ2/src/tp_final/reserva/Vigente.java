package tp_final.reserva;

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
}

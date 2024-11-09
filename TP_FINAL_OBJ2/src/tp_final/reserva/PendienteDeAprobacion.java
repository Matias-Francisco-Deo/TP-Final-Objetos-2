package tp_final.reserva;

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
		// TODO: NOTHING
	}
}

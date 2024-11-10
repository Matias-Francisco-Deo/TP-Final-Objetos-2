package tp_final.reserva;

public interface EstadoDeReserva {
	public void aprobar(Reserva reserva);

	public void cancelar(Reserva reserva);

	public void finalizar(Reserva reserva);

	public void encolar(Reserva reserva);

	public void desencolar(Reserva reserva);
}

package tp_final.reserva;

import tp_final.reseña.CategoriaDeReseñaDeInmueble;
import tp_final.reseña.Reseña;

public interface EstadoDeReserva {
	public void aprobar(Reserva reserva);

	public void cancelar(Reserva reserva);

	public void finalizar(Reserva reserva);

	public void encolar(Reserva reserva);

	public void desencolar(Reserva reserva);

	public void rankearInmueble(Reserva reserva, CategoriaDeReseñaDeInmueble categoria, Reseña reseña);

	public void rankearInquilino(Reserva reserva, Reseña reseña);

	public void rankearPropietario(Reserva reserva, Reseña reseña);
}

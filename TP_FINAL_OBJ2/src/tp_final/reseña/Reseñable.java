package tp_final.reseña;

import java.util.List;

public interface Reseñable {
	public List<Reseña> getReseñas();

	public List<Double> getPuntajes();

	public double getPuntajePromedio();

	public List<String> getComentarios();
}

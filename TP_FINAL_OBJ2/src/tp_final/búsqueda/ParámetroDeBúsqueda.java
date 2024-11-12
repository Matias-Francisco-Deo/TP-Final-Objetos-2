package tp_final.búsqueda;

import java.util.List;

import tp_final.alquiler.Alquiler;

public interface ParámetroDeBúsqueda extends Filtro {
	@Override
	public List<Alquiler> filtrar(List<Alquiler> alquileres);
}

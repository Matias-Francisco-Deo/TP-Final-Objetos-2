package tp_final.búsqueda;

import java.util.List;

import tp_final.alquiler.Alquiler;

public class FiltroPrecioMínimo implements ParámetroDeBúsqueda {

	private double precioMínimo;

	public FiltroPrecioMínimo(double precioMínimo) {
		this.setPrecioMínimo(precioMínimo);
	}

	@Override
	public List<Alquiler> filtrar(List<Alquiler> alquileres) {
		return alquileres.stream().filter(alquiler -> alquiler.getPrecio() >= this.getPrecioMínimo()).toList();
	}

	private double getPrecioMínimo() {
		return this.precioMínimo;
	}

	private void setPrecioMínimo(double precioMínimo) {
		this.precioMínimo = precioMínimo;
	}

}

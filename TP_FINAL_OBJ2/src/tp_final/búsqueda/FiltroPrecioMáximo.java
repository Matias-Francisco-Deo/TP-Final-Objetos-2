package tp_final.búsqueda;

import java.util.List;

import tp_final.alquiler.Alquiler;

public class FiltroPrecioMáximo implements ParámetroDeBúsqueda {

	private double precioMáximo;

	public FiltroPrecioMáximo(double precioMáximo) {
		this.setPrecioMáximo(precioMáximo);
	}

	@Override
	public List<Alquiler> filtrar(List<Alquiler> alquileres) {
		return alquileres.stream().filter(alquiler -> alquiler.getPrecio() <= this.getPrecioMáximo()).toList();
	}

	private double getPrecioMáximo() {
		return this.precioMáximo;
	}

	private void setPrecioMáximo(double precioMáximo) {
		this.precioMáximo = precioMáximo;
	}

}

package tp_final.búsqueda;

import tp_final.alquiler.Alquiler;

public class FiltroPrecioMáximoYMinimo implements ParámetroDeBúsqueda {

	private double precioMáximo;
	private double precioMínimo;

	public FiltroPrecioMáximoYMinimo(double precioMáximo, double precioMínimo) {
		this.setPrecioMáximo(precioMáximo);
		this.setPrecioMínimo(precioMínimo);
	}

	private double getPrecioMáximo() {
		return this.precioMáximo;
	}

	private void setPrecioMáximo(double precioMáximo) {
		this.precioMáximo = precioMáximo;
	}

	private double getPrecioMínimo() {
		return this.precioMínimo;
	}

	private void setPrecioMínimo(double precioMínimo) {
		this.precioMínimo = precioMínimo;
	}

	@Override
	public boolean esVálido(Alquiler alquiler) {

		return alquiler.getPrecioBase() >= this.getPrecioMínimo() && alquiler.getPrecioBase() <= this.getPrecioMáximo();
	}

}

package tp_final.búsqueda;

import tp_final.alquiler.Alquiler;

public class FiltroPrecioMínimo implements ParámetroDeBúsqueda {

	private double precioMínimo;

	public FiltroPrecioMínimo(double precioMínimo) {
		this.setPrecioMínimo(precioMínimo);
	}

	private double getPrecioMínimo() {
		return this.precioMínimo;
	}

	private void setPrecioMínimo(double precioMínimo) {
		this.precioMínimo = precioMínimo;
	}

	@Override
	public boolean esVálido(Alquiler alquiler) {
		// TODO Auto-generated method stub
		return alquiler.getPrecio() >= this.getPrecioMínimo();
	}

}

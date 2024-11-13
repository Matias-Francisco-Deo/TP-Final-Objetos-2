package tp_final.búsqueda;

import tp_final.alquiler.Alquiler;

public class FiltroPrecioMáximo implements ParámetroDeBúsqueda {

	private double precioMáximo;

	public FiltroPrecioMáximo(double precioMáximo) {
		this.setPrecioMáximo(precioMáximo);
	}

	private double getPrecioMáximo() {
		return this.precioMáximo;
	}

	private void setPrecioMáximo(double precioMáximo) {
		this.precioMáximo = precioMáximo;
	}

	@Override
	public boolean esVálido(Alquiler alquiler) {
		// TODO Auto-generated method stub
		return alquiler.getPrecioBase() <= this.getPrecioMáximo();
	}

}

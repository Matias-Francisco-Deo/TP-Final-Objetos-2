package tp_final.búsqueda;

import tp_final.alquiler.Alquiler;

public class FiltroCantidadHuéspedes implements ParámetroDeBúsqueda {

	private int cantidadMínimaHuéspedes;

	public FiltroCantidadHuéspedes(int i) {
		this.setCantidadMínimaHuéspedes(i);
	}

	private int getCantidadMínimaHuéspedes() {
		return cantidadMínimaHuéspedes;
	}

	private void setCantidadMínimaHuéspedes(int cantidadMínima) {
		this.cantidadMínimaHuéspedes = cantidadMínima;
	}

	@Override
	public boolean esVálido(Alquiler alquiler) {

		return alquiler.getcantidadHuespedes() >= this.getCantidadMínimaHuéspedes();
	}

}

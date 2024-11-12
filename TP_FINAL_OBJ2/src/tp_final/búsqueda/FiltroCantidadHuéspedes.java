package tp_final.búsqueda;

import java.util.List;

import tp_final.alquiler.Alquiler;

public class FiltroCantidadHuéspedes implements ParámetroDeBúsqueda {

	private int cantidadMínimaHuéspedes;

	public FiltroCantidadHuéspedes(int i) {
		this.setCantidadMínimaHuéspedes(i);
	}

	@Override
	public List<Alquiler> filtrar(List<Alquiler> alquileres) {
		return alquileres.stream()
				.filter(alquiler -> alquiler.getCantidadHuéspedes() >= this.getCantidadMínimaHuéspedes()).toList();
	}

	private int getCantidadMínimaHuéspedes() {
		return cantidadMínimaHuéspedes;
	}

	private void setCantidadMínimaHuéspedes(int cantidadMínima) {
		this.cantidadMínimaHuéspedes = cantidadMínima;
	}

}

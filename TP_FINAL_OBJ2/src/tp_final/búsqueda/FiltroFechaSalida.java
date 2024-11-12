package tp_final.búsqueda;

import java.time.LocalDate;
import java.util.List;

import tp_final.alquiler.Alquiler;

public class FiltroFechaSalida implements ParámetroDeBúsqueda {

	private LocalDate fechaSalida;

	public FiltroFechaSalida(LocalDate fechaSalida) {
		this.setFechaSalida(fechaSalida);
	}

	@Override
	public List<Alquiler> filtrar(List<Alquiler> alquileres) {
		return alquileres.stream().filter(alquiler -> alquiler.getFechaSalida().equals(this.getFechaSalida())).toList();
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

}

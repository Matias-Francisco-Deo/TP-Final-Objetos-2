package tp_final.búsqueda;

import java.time.LocalDate;
import java.util.List;

import tp_final.alquiler.Alquiler;

public class FiltroFechaEntrada implements ParámetroDeBúsqueda {

	private LocalDate fechaEntrada;

	public FiltroFechaEntrada(LocalDate fechaEntrada) {
		this.setFechaEntrada(fechaEntrada);
	}

	@Override
	public List<Alquiler> filtrar(List<Alquiler> alquileres) {
		return alquileres.stream().filter(alquiler -> alquiler.getFechaEntrada().equals(this.getFechaEntrada()))
				.toList();
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

}

package tp_final.búsqueda;

import java.time.LocalDate;

import tp_final.alquiler.Alquiler;

public class FiltroFechaEntrada implements ParámetroDeBúsqueda {

	private LocalDate fechaEntrada;

	public FiltroFechaEntrada(LocalDate fechaEntrada) {
		this.setFechaEntrada(fechaEntrada);
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	@Override
	public boolean esVálido(Alquiler alquiler) {
		// TODO Auto-generated method stub
		return alquiler.getFechaCheckIn().equals(this.getFechaEntrada());
	}

}

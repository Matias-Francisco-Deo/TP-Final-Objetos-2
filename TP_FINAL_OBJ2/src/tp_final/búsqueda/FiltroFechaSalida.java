package tp_final.búsqueda;

import java.time.LocalDate;

import tp_final.alquiler.Alquiler;

public class FiltroFechaSalida implements ParámetroDeBúsqueda {

	private LocalDate fechaSalida;

	public FiltroFechaSalida(LocalDate fechaSalida) {
		this.setFechaSalida(fechaSalida);
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	@Override
	public boolean esVálido(Alquiler alquiler) {
		// TODO Auto-generated method stub
		return alquiler.getFechaCheckOut().equals(this.getFechaSalida());
	}

}

package tp_final.búsqueda;

import java.time.LocalDate;

import tp_final.alquiler.Alquiler;

public class FiltroFechaEntradaYSalida implements ParámetroDeBúsqueda {

	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;

	public FiltroFechaEntradaYSalida(LocalDate fechaEntrada, LocalDate fechaSalida) {
		this.setFechaEntrada(fechaEntrada);
		this.setFechaSalida(fechaSalida);
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	@Override
	public boolean esVálido(Alquiler alquiler) {

		return alquiler.getManager().elRangoEstaOcupadoPorAlgunaReserva(fechaEntrada, fechaSalida);
	}

}

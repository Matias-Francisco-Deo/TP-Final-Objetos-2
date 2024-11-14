package tp_final.politica_cancelacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import tp_final.varios.ServidorDeCorreo;

public class CancelacionIntermedia extends PoliticaDeCancelacion {

	public CancelacionIntermedia(ServidorDeCorreo sistemaDeCorreo) {
		super(sistemaDeCorreo);
	}

	@Override
	public double calcularCosto(LocalDate checkIn, LocalDate checkOut, double precio) {

		LocalDate fechaActual = LocalDate.now();

		long diasAlquilados = ChronoUnit.DAYS.between(checkIn, checkOut);

		long diasFaltantes = ChronoUnit.DAYS.between(fechaActual, checkIn);

		if (diasFaltantes >= 20) {
			return 0;

		} else if (diasFaltantes > 10 && diasFaltantes < 20) {
			return (precio * diasAlquilados) / 2;

		} else {
			return precio * diasAlquilados;
		}
	}

}

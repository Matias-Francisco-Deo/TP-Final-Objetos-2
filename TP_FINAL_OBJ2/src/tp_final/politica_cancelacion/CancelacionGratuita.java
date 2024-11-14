package tp_final.politica_cancelacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import tp_final.varios.ServidorDeCorreo;

public class CancelacionGratuita extends PoliticaDeCancelacion {
	public CancelacionGratuita(ServidorDeCorreo sistemaDeCorreo) {
		super(sistemaDeCorreo);
	}

	@Override
	public double calcularCosto(LocalDate checkIn, LocalDate checkOut, double precio) {

		LocalDate fechaActual = LocalDate.now();

		long diasFaltantes = ChronoUnit.DAYS.between(fechaActual, checkIn);

		if (diasFaltantes >= 10) {
			return 0; //
		} else {
			return precio * 2;
		}

	}

}

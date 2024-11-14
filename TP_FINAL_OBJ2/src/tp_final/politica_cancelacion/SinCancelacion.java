package tp_final.politica_cancelacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import tp_final.varios.ServidorDeCorreo;

public class SinCancelacion extends PoliticaDeCancelacion {

	public SinCancelacion(ServidorDeCorreo sistemaDeCorreo) {
		super(sistemaDeCorreo);
	}

	@Override
	public double calcularCosto(LocalDate checkIn, LocalDate checkOut, double precio) {

		long diasDeAlquilados = ChronoUnit.DAYS.between(checkIn, checkOut);

		return precio * diasDeAlquilados;

	}

}

package tp_final.politica_cancelacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SinCancelacion extends PoliticaDeCancelacion {

	@Override
	public double calcularCosto(LocalDate checkIn, LocalDate checkOut, double precio) {

		long diasDeAlquilados = ChronoUnit.DAYS.between(checkIn, checkOut);

		return precio * diasDeAlquilados;

	}

}

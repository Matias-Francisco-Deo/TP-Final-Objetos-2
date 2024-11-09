package tp_final.politica_cancelacion;
import java.time.LocalDate;

import tp_final_extra.Reserva;

public interface PoliticaDeCancelacion {
	public void aplicarPolitica(Reserva reserva,double precio);
	public double calcularCosto(LocalDate checkIn,LocalDate checkOut,double precio);//cambiar fecha por la reserva
	public String generarMail(double monto);
}

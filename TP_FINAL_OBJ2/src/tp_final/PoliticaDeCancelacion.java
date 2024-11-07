package tp_final;
import java.time.LocalDate;

public interface PoliticaDeCancelacion {
	public void aplicarPolitica(Reserva reserva,double precio);
	public double calcularCosto(LocalDate checkIn,LocalDate checkOut,double precio);//cambiar fecha por la reserva
	public String generarMail(double monto);
}

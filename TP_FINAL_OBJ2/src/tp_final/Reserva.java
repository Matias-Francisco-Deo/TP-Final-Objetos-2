package tp_final;
import java.time.LocalDate;

public class Reserva {
	private LocalDate checkin;
	private LocalDate checkout;
	
	public LocalDate getFechaCheckIn() {
		return checkin;
	}
	public LocalDate getFechaCheckOut() {
		return checkout;
	}
}

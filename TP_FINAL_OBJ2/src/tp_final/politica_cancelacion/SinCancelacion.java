package tp_final;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SinCancelacion implements PoliticaDeCancelacion{
	
	public void aplicarPolitica(Reserva reserva,double precio){
		LocalDate checkIn = reserva.getfechaEntrada();
		LocalDate checkOut = reserva.getfechaSalida();
		
		double monto = this.calcularCosto(checkIn,checkOut,precio);
		String texto = this.generarMail(monto);
		this.enviarMail(texto);
	}
	
	public double calcularCosto(LocalDate checkIn,LocalDate checkOut,double precio) {
		
		long diasDeAlquilados = ChronoUnit.DAYS.between(checkIn, checkOut);
		
		 return precio*diasDeAlquilados;
		
	}
	
	public String generarMail(double monto) {
		return ("Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de "+ monto +" $");
	}
	
	public void enviarMail(String texto) {
		 
	}
}

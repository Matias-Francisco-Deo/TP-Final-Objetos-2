package tp_final;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SinCancelacion {
	
	public void aplicarPolitica(Reserva reserva,double precio){
		double monto = this.calcularCosto(reserva,precio);
		String texto = this.generarMail(monto);
		this.enviarMail(texto);
	}
	
	public double calcularCosto(Reserva reserva,double precio) {
		LocalDate fechaCheckOut = reserva.getFechaCheckOut();
		LocalDate fechaCheckIn = reserva.getFechaCheckIn();
		
		long diasDeAlquilados = ChronoUnit.DAYS.between(fechaCheckIn, fechaCheckOut);
		
		 return precio*diasDeAlquilados;
		
	}
	
	public String generarMail(double monto) {
		return ("Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de "+ monto +" $");
	}
	
	public void enviarMail(String texto) {
		 System.out.println(texto);//se envio el email
	}
}

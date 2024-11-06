package tp_final;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CancelacionGratuita implements PoliticaDeCancelacion{
	
	public void aplicarPolitica(LocalDate checkIn,LocalDate checkOut,double precio){
		double monto = this.calcularCosto(checkIn,checkOut,precio);
		String texto = this.generarMail(monto);
		this.enviarMail(texto);
	}
	
	public double calcularCosto(LocalDate checkIn,LocalDate checkOut,double precio) {
		
		LocalDate fechaActual = LocalDate.now();
		
		long diasFaltantes = ChronoUnit.DAYS.between(fechaActual, checkIn);
		
		 if (diasFaltantes >= 10) {
	            return 0; //
	        } else {
	            return precio * 2;
	        }
		
	}
	
	public String generarMail(double monto) {
		return ("Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de "+ monto +" $");
	}
	
	public void enviarMail(String texto) {
		 System.out.println(texto);//se envio el email
	}
}

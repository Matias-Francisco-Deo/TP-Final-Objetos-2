package tp_final;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Intermedia {
	
	public void aplicarPolitica(Reserva reserva,double precio){
		double monto = this.calcularCosto(reserva,precio);
		String texto = this.generarMail(monto);
		this.enviarMail(texto);
	}
	
	public double calcularCosto(Reserva reserva,double precio) {
		
		LocalDate fechaActual = LocalDate.now();
		
		LocalDate fechaCheckIn = reserva.getFechaCheckIn();
		
		LocalDate fechaCheckOut = reserva.getFechaCheckOut();
		
		long diasAlquilados = ChronoUnit.DAYS.between(fechaCheckIn, fechaCheckOut);
		
		long diasFaltantes = ChronoUnit.DAYS.between(fechaActual, fechaCheckIn);
		
		if (diasFaltantes >= 20) {
            return 0;
            
        } else if (diasFaltantes >= 10 && diasFaltantes <= 19) {
            return (precio * diasAlquilados) / 2;
            
        } else {
            return precio * diasAlquilados;
        }
	}
	
	public String generarMail(double monto) {
		return ("Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de "+ monto +" $");
	}
	
	public void enviarMail(String texto) {
		 System.out.println(texto);//se envio el email
	}
}

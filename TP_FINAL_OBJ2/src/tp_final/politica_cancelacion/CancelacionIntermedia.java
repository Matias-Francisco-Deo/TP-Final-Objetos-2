package tp_final.politica_cancelacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import tp_final_extra.Reserva;

public class CancelacionIntermedia implements PoliticaDeCancelacion{
	
	public void aplicarPolitica(Reserva reserva,double precio){
		
		LocalDate checkIn = reserva.getfechaEntrada();
		LocalDate checkOut = reserva.getfechaSalida();
		
		double monto = this.calcularCosto(checkIn,checkOut,precio);
		String texto = this.generarMail(monto);
		this.enviarMail(texto);
	}
	
	public double calcularCosto(LocalDate checkIn,LocalDate checkOut,double precio) {
		
		LocalDate fechaActual = LocalDate.now();
		
		long diasAlquilados = ChronoUnit.DAYS.between(checkIn, checkOut);
		
		long diasFaltantes = ChronoUnit.DAYS.between(fechaActual, checkIn);
		
		if (diasFaltantes >= 20) {
            return 0;
            
        } else if (diasFaltantes > 10 && diasFaltantes < 20) {
            return (precio * diasAlquilados) / 2;
            
        } else {
            return precio * diasAlquilados;
        }
	}
	
	public String generarMail(double monto) {
		return ("Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de "+ monto +" $");
	}
	
	public void enviarMail(String texto) {
		 
	}
}

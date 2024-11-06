package tp_final;

public interface PoliticaDeCancelacion {
	public void aplicarPolitica(Reserva reserva,double precio);
	public double calcularCosto(Reserva reserva,double precio);//cambiar fecha por la reserva
	public String generarMail(double monto);
}

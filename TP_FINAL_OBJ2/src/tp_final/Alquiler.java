package tp_final;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

public class Alquiler {
	private LocalDate fechaEntrada;
	
	private LocalDate fechaSalida;
	
	private LocalTime checkIn;
	
	private LocalTime checkOut;
	
	private String medioPago;//cambiar String por objeto medio de pago
	
	private Map<String,Double> precioTemporadas;
	
	private double precioBase;
	
	private PoliticaDeCancelacion politicaDeCancelacion;
	
	private EstadoDeAlquiler estado;
	
	private List<String> colaDeEspera;//cambiar String de la lista por objeto reserva

	Alquiler(LocalTime checkIn, LocalTime checkOut, String medioDePago, double precioBase,PoliticaDeCancelacion politica) {//agregar atributos necesarios
		this.precioTemporadas = new HashMap<>();
		this.colaDeEspera = new ArrayList<>();
		this.setPrecioBase(precioBase);
		this.setPoliticaDeCancelacion(politica);
		this.estado = new Libre();
	}
	
	public LocalDate getFechaDeEntrada() {
		return fechaEntrada;
	}

	public void setFechaDeEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	
	public LocalDate getFechaDeSalida() {
		return fechaSalida;
	}

	public void setFechaDeSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	public LocalTime getCheckIn(){
		return checkIn;
	}
	
	public void setCheckIn(LocalTime checkIn){
		this.checkIn = checkIn;
	}
	
	public LocalTime getCheckOut(){
		return checkOut;
	}
	
	public void setCheckOut(LocalTime checkOut){
		this.checkOut = checkOut;
	}
	
	public String getMedioDePago(){
		return medioPago;
	}
	
	public void setMedioDePago(String medioPago){
		this.medioPago = medioPago;
	}
	
	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}
	
	public void addPrecioTemporada(String temporada, Double precio) {
	    if (temporada != null && precio != null) {
	    	precioTemporadas.put(temporada, precio);
	    } else {
	        throw new IllegalArgumentException("indique temporada y precio");
	    }
	}
	
	public void addReserva(String reserva) {//cambiar string por objeto reserva
		this.colaDeEspera.add(reserva);
	}
	
	public void setPoliticaDeCancelacion(PoliticaDeCancelacion politica) {
		this.politicaDeCancelacion = politica;
	}
}

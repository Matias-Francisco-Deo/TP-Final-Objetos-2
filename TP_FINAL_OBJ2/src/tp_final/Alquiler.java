package tp_final;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

public class Alquiler {
	
	private Inmueble inmueble;
	
	private LocalDate fechaEntrada;
	
	private LocalDate fechaSalida;
	
	private LocalTime checkIn;
	
	private LocalTime checkOut;
	
	private MedioDePago medioPago;//cambiar String por objeto medio de pago
	
	private Map<String,Double> precioTemporadas;
	
	private double precioBase;
	
	private PoliticaDeCancelacion politicaDeCancelacion;
	
	private EstadoDeAlquiler estado;
	
//	private Reserva reservaActual;
	
	private List<Reserva> colaDeEspera;//cambiar String de la lista por objeto reserva

	Alquiler(Inmueble inmueble, LocalTime checkIn, LocalTime checkOut, MedioDePago medioDePago, double precioBase,PoliticaDeCancelacion politica) {//agregar atributos necesarios
		this.inmueble = inmueble;
		this.setCheckIn(checkIn);
		this.setCheckOut(checkOut);
		this.setMedioDePago(medioDePago);
		this.precioTemporadas = new HashMap<>();
		this.colaDeEspera = new ArrayList<>();
		this.setPrecioBase(precioBase);
		this.setPoliticaDeCancelacion(politica);
		this.estado = new Libre();
		this.colaDeEspera = new ArrayList<>();
	}
	
	public Inmueble getInmueble() {
		return inmueble;
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
	
	public MedioDePago getMedioDePago(){
		return medioPago;
	}
	
	public void setMedioDePago(MedioDePago medioPago){
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
	
	public void addReserva(Reserva reserva) {//cambiar string por objeto reserva
		this.colaDeEspera.add(reserva);
	} 
	
	public List<Reserva> getcolaDeEspera() {
		return this.colaDeEspera;
	}
	
	public void setPoliticaDeCancelacion(PoliticaDeCancelacion politica) {
		this.politicaDeCancelacion = politica;
	}
	
	public EstadoDeAlquiler getEstadoDeAlquiler() {
		return estado;
	}

	public void setEstadoDeAlquiler(EstadoDeAlquiler estado) {
		this.estado = estado;
	}
	
	public void reservar(Reserva reserva) {
		estado.alquilar(reserva,this);
	}
	
	public void cancelarReserva(Reserva reserva) {
		//reserva.cancelar()?
		this.estado.cancelar(reserva,this);
		this.politicaDeCancelacion.aplicarPolitica(reserva,this.getFechaDeEntrada(),this.getFechaDeSalida(),this.getPrecioBase());
	}
	
	public void confirmarReserva(Reserva reserva) {//agregar reserva como parametro?
		this.setEstadoDeAlquiler(new Alquilado());
		
		this.setFechaDeEntrada(reserva.getfechaEntrada());
		this.setFechaDeSalida(reserva.getfechaSalida());
		
		this.getInmueble().sumarCantAlquilado();
		//reserva.confirmar?
	}
}

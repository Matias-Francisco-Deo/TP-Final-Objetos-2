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
	
	private MedioDePago medioPago;
	
	private Map<String,Double> precioTemporadas;
	
	private double precioBase;
	
	private PoliticaDeCancelacion politicaDeCancelacion;
	
	private EstadoDeAlquiler estado;
	
	private List<Reserva> colaDeEspera;
	
	private List<Usuario> subscriptores;

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
		this.subscriptores = new ArrayList<>();
	}
	
	public Inmueble getInmueble() {
		return inmueble;
	}
	
	public LocalDate getFechaDeCheckIn() {
		return fechaEntrada;
	}
	
	public LocalDate getFechaDeCheckOut() {
		return fechaSalida;
	}
	
	public LocalTime getCheckIn(){
		return checkIn;
	}
	
	public LocalTime getCheckOut(){
		return checkOut;
	}
	
	public MedioDePago getMedioDePago(){
		return medioPago;
	}
	
	public double getPrecioBase() {
		return precioBase;
	} 
	
	public EstadoDeAlquiler getEstadoDeAlquiler() {
		return estado;
	}
	
	public List<Reserva> getcolaDeEspera() {
		return this.colaDeEspera;
	}
	
	public List<Usuario> getSubscriptores(){
		return subscriptores;
	}
	
	public Map<String, Double> getPreciosTemporadas() {
		return this.precioTemporadas;                                                    //revisar metodo
	}

	public void setFechaDeEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public void setFechaDeSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	public void setCheckIn(LocalTime checkIn){
		this.checkIn = checkIn;
	}
	
	public void setCheckOut(LocalTime checkOut){
		this.checkOut = checkOut;
	}
	
	public void setMedioDePago(MedioDePago medioPago){
		this.medioPago = medioPago;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}
	
	public void setPoliticaDeCancelacion(PoliticaDeCancelacion politica) {
		this.politicaDeCancelacion = politica;
	}

	public void setEstadoDeAlquiler(EstadoDeAlquiler estado) {
		this.estado = estado;
	}
	
	public void addSubscriptor(Usuario sub) {
		subscriptores.add(sub);
	}
	
	public void addPrecioTemporada(String temporada, Double precio) {
	    	precioTemporadas.put(temporada, precio);
	}
	
	public void addReserva(Reserva reserva) {
		this.colaDeEspera.add(reserva);
	}
	
	public void reservar(Reserva reserva) {
		estado.alquilar(reserva,this);
	}
	
	public void cancelarReserva(Reserva reserva) {
		//reserva.cancelar()?
		this.estado.cancelar(reserva,this);
		this.politicaDeCancelacion.aplicarPolitica(reserva,this.getPrecioBase());
	}
	
	public void confirmarReserva(Reserva reserva) {
		
		this.setEstadoDeAlquiler(new Alquilado());
		
		this.setFechaDeEntrada(reserva.getfechaEntrada());
		this.setFechaDeSalida(reserva.getfechaSalida());
		
		this.getInmueble().sumarCantAlquilado();
		
	}
	
	public void notificarSubs() {
		
		for(Usuario sub : subscriptores) {
			sub.notificar();
		}
	}
}

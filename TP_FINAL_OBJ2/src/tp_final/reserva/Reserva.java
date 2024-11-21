package tp_final.reserva;

import java.time.LocalDate;

import tp_final.alquiler.Alquiler;
import tp_final.inmueble.Inmueble;
import tp_final.ranking.Ranking;
import tp_final.usuarios.Usuario;
import tp_final.varios.MedioDePago;
import tp_final.varios.ServidorDeCorreo;

public class Reserva {
	private Alquiler alquiler;
	private Usuario inquilino;
	private MedioDePago medioDePago;
	private EstadoDeReserva estado;
	private ServidorDeCorreo servidorDeCorreo;
	private LocalDate fechaCheckIn;
	private LocalDate fechaCheckOut;

	// ------------------------------------------------------------

	public Reserva(Alquiler alquiler, Usuario inquilino, LocalDate fechaCheckIn, LocalDate fechaCheckOut,
			MedioDePago medioDePago, ServidorDeCorreo servidorDeCorreo) {
		this.setAlquiler(alquiler);
		this.setInquilino(inquilino);
		this.setFechaCheckIn(fechaCheckIn);
		this.setFechaCheckOut(fechaCheckOut);
		this.setMedioDePago(medioDePago);
		this.setServidorDeCorreo(servidorDeCorreo);
		this.setEstado(new PendienteDeAprobacion()); // ESTADO INICIAL
	}

	// ------------------------------------------------------------
	// ALQUILER, INQUILINO, MEDIO DE PAGO & SISTEMA DE CORREO
	// ------------------------------------------------------------

	public Alquiler getAlquiler() {
		return this.alquiler;
	}

	public Usuario getInquilino() {
		return this.inquilino;
	}

	public MedioDePago getMedioDePago() {
		return this.medioDePago;
	}

	private ServidorDeCorreo getServidorDeCorreo() {
		return this.servidorDeCorreo;
	}

	private void setAlquiler(Alquiler alquiler) {
		this.alquiler = alquiler;
	}

	private void setInquilino(Usuario inquilino) {
		this.inquilino = inquilino;
	}

	private void setMedioDePago(MedioDePago medioDePago) {
		this.medioDePago = medioDePago;
	}

	private void setServidorDeCorreo(ServidorDeCorreo servidorDeCorreo) {
		this.servidorDeCorreo = servidorDeCorreo;
	}

	// ------------------------------------------------------------
	// CHECK-IN, CHECK-OUT, CIUDAD, PROPIETARIO E INMUEBLE
	// ------------------------------------------------------------

	public LocalDate getFechaCheckIn() {
		return this.fechaCheckIn;
	}

	public LocalDate getFechaCheckOut() {
		return this.fechaCheckOut;
	}

	private void setFechaCheckIn(LocalDate fechaCheckIn) {
		this.fechaCheckIn = fechaCheckIn;
	}

	private void setFechaCheckOut(LocalDate fechaCheckOut) {
		this.fechaCheckOut = fechaCheckOut;
	}

	public String getCiudad() {
		return this.getAlquiler().getCiudad();
	}

	public Usuario getPropietario() {
		return this.getAlquiler().getPropietario();
	}

	public Inmueble getInmueble() {
		return this.getAlquiler().getInmueble();
	}

	// ------------------------------------------------------------
	// ESTADOS DE RESERVA
	// ------------------------------------------------------------

	public EstadoDeReserva getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoDeReserva estado) {
		this.estado = estado;
	}

	public void aprobar() {
		this.getEstado().aprobar(this);
	}

	public void doAprobar() {
		this.enviarCorreoDeAprobacion();
	}

	public void cancelar() {
		this.getEstado().cancelar(this);
	}

	public void doCancelar() {
		this.getAlquiler().getManager().cancelarReserva(this);
		this.enviarCorreoDeCancelacion();
	}

	public void doFinalizar() {
		this.getAlquiler().getManager().finalizarReserva(this);
	}

	public void realizarCheckOut() {
		this.getEstado().finalizar(this);
	}

	public void encolar() {
		this.getEstado().encolar(this);
	}

	public void desencolar() {
		this.getEstado().desencolar(this);
	}

	// ------------------------------------------------------------
	// ENVIO DE CORREOS
	// ------------------------------------------------------------

	private void enviarCorreo(String destinatario, String asunto, Object cuerpo) {
		this.getServidorDeCorreo().enviar(destinatario, asunto, cuerpo);
	}

	private void enviarCorreoDeAprobacion() {
		String destinatario = this.getEmailInquilino();
		this.enviarCorreo(destinatario, "Reserva aprobada", this);
	}

	private void enviarCorreoDeCancelacion() {
		String destinatario = this.getEmailPropietario();
		this.enviarCorreo(destinatario, "Reserva cancelada", this);
	}

	public String getEmailInquilino() {
		return this.getInquilino().getEmail();
	}

	private String getEmailPropietario() {
		return this.getPropietario().getEmail();
	}

	// ------------------------------------------------------------
	// RANKING DE INMUEBLE, INQUILINO Y PROPIETARIO
	// ------------------------------------------------------------

	// INMUEBLE

	public void rankearInmueble(Ranking ranking) {
		this.getEstado().rankearInmueble(this, ranking);
	}

	public void doRankearInmueble(Ranking ranking) {
		this.getInmueble().getGestorDeRanking().recibirRankeo(ranking);
	}

	// INQUILINO

	public void rankearInquilino(Ranking ranking) {
		this.getEstado().rankearInquilino(this, ranking);
	}

	public void doRankearInquilino(Ranking ranking) {
		this.getInquilino().getGestorDeRanking().recibirRankeo(ranking);
	}

	// PROPIETARIO

	public void rankearPropietario(Ranking ranking) {
		this.getEstado().rankearPropietario(this, ranking);
	}

	public void doRankearPropietario(Ranking ranking) {
		this.getPropietario().getGestorDeRanking().recibirRankeo(ranking);
	}

	// ------------------------------------------------------------
}

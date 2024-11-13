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

	// ------------------------------------------------------------

	public Reserva(Alquiler alquiler, Usuario inquilino, MedioDePago medioDePago, ServidorDeCorreo servidorDeCorreo) {
		this.setAlquiler(alquiler);
		this.setInquilino(inquilino);
		this.setMedioDePago(medioDePago);
		this.setServidorDeCorreo(servidorDeCorreo);
		this.setEstado(new PendienteDeAprobacion());
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

	public ServidorDeCorreo getServidorDeCorreo() {
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
	// FECHA DE CHECK-IN, CIUDAD, PROPIETARIO E INMUEBLE
	// ------------------------------------------------------------

	public LocalDate getFechaCheckIn() {
		return this.getAlquiler().getFechaCheckIn();
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
		this.enviarCorreoDeCancelacion();
	}

	public void realizarCheckOut() {
		this.getEstado().finalizar(this);
	}

	// ------------------------------------------------------------
	// ENVIO DE CORREOS
	// ------------------------------------------------------------

	private void enviarCorreoDeAprobacion() {
		String destinatario = this.getInquilino().getEmail();
		String asunto = "Reserva aprobada";
		this.getServidorDeCorreo().enviar(destinatario, asunto, this);
	}

	private void enviarCorreoDeCancelacion() {
		String destinatario = this.getPropietario().getEmail();
		String asunto = "Reserva cancelada";
		this.getServidorDeCorreo().enviar(destinatario, asunto, this);
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

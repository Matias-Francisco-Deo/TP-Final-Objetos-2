package tp_final.reserva;

import java.time.LocalDate;

import tp_final.alquiler.Alquiler;
import tp_final.reseña.CategoriaDeReseñaDeInmueble;
import tp_final.reseña.Reseña;
import tp_final.usuarios.Usuario;
import tp_final.varios.MedioDePago;
import tp_final.varios.ServidorDeCorreo;

public class Reserva {
	private Alquiler alquiler;
	private Usuario inquilino;
	private MedioDePago medioDePago;
	private EstadoDeReserva estado;
	private ServidorDeCorreo servidorDeCorreo;

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
	// FECHA DE CHECK-IN, CIUDAD Y PROPIETARIO
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

	// ------------------------------------------------------------
	// ESTADO DE RESERVA
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
	// RANKEO DE INMUEBLE, INQUILINO Y PROPIETARIO
	// ------------------------------------------------------------

	// INMUEBLE

	public void rankearInmueble(CategoriaDeReseñaDeInmueble categoria, Reseña reseña) {
		this.getEstado().rankearInmueble(this, categoria, reseña);
	}

	public void doRankearInmueble(CategoriaDeReseñaDeInmueble categoria, Reseña reseña) {
		this.getAlquiler().getInmueble().recibirReseña(categoria, reseña);
	}

	// INQUILINO

	public void rankearInquilino(Reseña reseña) {
		this.getEstado().rankearInquilino(this, reseña);
	}

	public void doRankearInquilino(Reseña reseña) {
		this.getInquilino().recibirReseña(reseña);
	}

	// PROPIETARIO

	public void rankearPropietario(Reseña reseña) {
		this.getEstado().rankearPropietario(this, reseña);
	}

	public void doRankearPropietario(Reseña reseña) {
		this.getPropietario().recibirReseña(reseña);
	}
}

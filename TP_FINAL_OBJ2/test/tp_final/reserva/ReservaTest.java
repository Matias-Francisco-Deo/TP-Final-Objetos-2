package tp_final.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.Alquiler;
import tp_final.usuarios.Usuario;
import tp_final.varios.MedioDePago;
import tp_final.varios.ServidorDeCorreo;

class ReservaTest {
	private Reserva reserva;
	private Alquiler alquiler;
	private Usuario inquilino;
	private MedioDePago medioDePago;
	private ServidorDeCorreo servidorDeCorreo;

	@BeforeEach
	void setUp() throws Exception {
		// MOCK
		alquiler = mock(Alquiler.class);
		inquilino = mock(Usuario.class);
		medioDePago = mock(MedioDePago.class);
		servidorDeCorreo = mock(ServidorDeCorreo.class);
		Usuario propietario = mock(Usuario.class);

		// STUB
		when(alquiler.getPropietario()).thenReturn(propietario);
		when(inquilino.getEmail()).thenReturn("inquilino@gmail.com");
		when(propietario.getEmail()).thenReturn("propietario@gmail.com");

		// SUT
		reserva = new Reserva(alquiler, inquilino, medioDePago, servidorDeCorreo);
	}

	// ------------------------------------------------------------
	// ALQUILER, INQUILINO, MEDIO DE PAGO & SISTEMA DE CORREO
	// ------------------------------------------------------------

	@Test
	void getAlquilerTest() {
		assertEquals(reserva.getAlquiler(), alquiler);
	}

	@Test
	void getInquilinoTest() {
		assertEquals(reserva.getInquilino(), inquilino);
	}

	@Test
	void getMedioDePagoTest() {
		assertEquals(reserva.getMedioDePago(), medioDePago);
	}

	@Test
	void getServidorDeCorreoTest() {
		assertEquals(reserva.getServidorDeCorreo(), servidorDeCorreo);
	}

	// ------------------------------------------------------------
	// FECHA DE CHECK-IN, CIUDAD Y PROPIETARIO
	// ------------------------------------------------------------

	@Test
	void getFechaCheckInTest() {
		assertEquals(reserva.getFechaCheckIn(), alquiler.getFechaCheckIn());
	}

	@Test
	void getCiudadTest() {
		assertEquals(reserva.getCiudad(), alquiler.getCiudad());
	}

	@Test
	void getPropietarioTest() {
		assertEquals(reserva.getPropietario(), alquiler.getPropietario());
	}

	// ------------------------------------------------------------
	// ESTADO DE RESERVA
	// ------------------------------------------------------------

	@Test
	void getEstadoTest() {
		assertEquals(reserva.getEstado().getClass(), PendienteDeAprobacion.class);
	}

	@Test
	void setEstadoTest() {
		EstadoDeReserva estado = mock(EstadoDeReserva.class);
		reserva.setEstado(estado);
		assertEquals(reserva.getEstado(), estado);
	}

	@Test
	void unaReservaInicialmenteTieneUnEstadoPendienteDeAprobacionTest() {
		assertEquals(reserva.getEstado().getClass(), PendienteDeAprobacion.class);
	}

	// ESTADO PENDIENTE DE APROBACION

	@Test
	void unaReservaPendienteDeAprobacionEsAprobadaYCambiaSuEstadoAVigenteTest() {
		reserva.aprobar();
		assertEquals(reserva.getEstado().getClass(), VigenteTest.class);
	}

	@Test
	void unaReservaPendienteDeAprobacionEsCanceladaYCambiaSuEstadoACanceladoTest() {
		reserva.cancelar();
		assertEquals(reserva.getEstado().getClass(), Cancelado.class);
	}

	@Test
	void unaReservaEnviaUnCorreoDeAprobacion() {
		reserva.doAprobar();
		verify(servidorDeCorreo).enviar(anyString(), anyString(), anyString());
	}

	// ESTADO VIGENTE

	@Test
	void unaReservaVigenteEsCanceladaYCambiaSuEstadoACanceladoTest() {
		reserva.aprobar();
		reserva.cancelar();
		assertEquals(reserva.getEstado().getClass(), Cancelado.class);
	}

	@Test
	void unaReservaEnviaUnCorreoDeCancelacion() {
		reserva.doCancelar();
		verify(servidorDeCorreo).enviar(anyString(), anyString(), anyString());
	}
}

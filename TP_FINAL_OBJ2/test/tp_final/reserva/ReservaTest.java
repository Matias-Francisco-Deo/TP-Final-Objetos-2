package tp_final.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;
import tp_final.alquiler.ManagerDeAlquiler;
import tp_final.inmueble.Inmueble;
import tp_final.ranking.GestorDeRanking;
import tp_final.ranking.Ranking;
import tp_final.usuarios.Usuario;
import tp_final.varios.MedioDePago;
import tp_final.varios.ServidorDeCorreo;

class ReservaTest {
	private Reserva reserva;
	private Alquiler alquiler;
	private Usuario inquilino;
	private MedioDePago medioDePago;
	private ServidorDeCorreo servidorDeCorreo;
	private Inmueble inmueble;
	private Ranking ranking;
	private Usuario propietario;
	private GestorDeRanking gestorDeRanking;
	LocalDate fechaEntrada;
	LocalDate fechaSalida;
	private ManagerDeAlquiler manager;

	@BeforeEach
	void setUp() throws Exception {
		// MOCK
		alquiler = mock(Alquiler.class);
		inquilino = mock(Usuario.class);
		medioDePago = mock(MedioDePago.class);
		servidorDeCorreo = mock(ServidorDeCorreo.class);
		manager = mock(ManagerDeAlquiler.class);

		inmueble = mock(Inmueble.class);
		propietario = mock(Usuario.class);
		gestorDeRanking = mock(GestorDeRanking.class);
		ranking = mock(Ranking.class);
		fechaEntrada = LocalDate.of(2024, 11, 1);
		fechaSalida = LocalDate.of(2024, 12, 1);

		// STUB
		when(alquiler.getInmueble()).thenReturn(inmueble);
		when(alquiler.getPropietario()).thenReturn(propietario);

		when(inmueble.getGestorDeRanking()).thenReturn(gestorDeRanking);

		when(inquilino.getEmail()).thenReturn("inquilino@gmail.com");
		when(inquilino.getGestorDeRanking()).thenReturn(gestorDeRanking);

		when(propietario.getEmail()).thenReturn("propietario@gmail.com");
		when(propietario.getGestorDeRanking()).thenReturn(gestorDeRanking);

		when(ranking.getComentario()).thenReturn("Excelente");
		when(ranking.getPuntaje()).thenReturn(5);

		// SUT
		reserva = new Reserva(alquiler, inquilino, fechaEntrada, fechaSalida, medioDePago, servidorDeCorreo);
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

	// ------------------------------------------------------------
	// CHECK-IN, CHECK-OUT, CIUDAD, PROPIETARIO E INMUEBLE
	// ------------------------------------------------------------

	@Test
	void getFechaCheckInTest() {
		assertEquals(reserva.getFechaCheckIn(), fechaEntrada);
	}

	@Test
	void getFechaCheckOutTest() {
		assertEquals(reserva.getFechaCheckOut(), fechaSalida);
	}

	@Test
	void getCiudadTest() {
		assertEquals(reserva.getCiudad(), alquiler.getCiudad());
	}

	@Test
	void getPropietarioTest() {
		assertEquals(reserva.getPropietario(), alquiler.getPropietario());
	}

	@Test
	void getInmuebleTest() {
		assertEquals(reserva.getInmueble(), alquiler.getInmueble());
	}

	// ------------------------------------------------------------
	// ESTADOS DE RESERVA
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

	// ESTADO INICIAL

	@Test
	void unaReservaInicialmenteTieneUnEstadoPendienteDeAprobacion() {
		assertEquals(reserva.getEstado().getClass(), PendienteDeAprobacion.class);
	}

	// ESTADO PENDIENTE DE APROBACION

	@Test
	void unaReservaPendienteDeAprobacionEsAprobadaYCambiaSuEstadoAVigente() {
		reserva.aprobar();
		assertEquals(reserva.getEstado().getClass(), Vigente.class);
	}

	@Test
	void unaReservaPendienteDeAprobacionEsCanceladaYCambiaSuEstadoACancelado() {
		when(alquiler.getManager()).thenReturn(manager);

		reserva.cancelar();
		assertEquals(reserva.getEstado().getClass(), Cancelado.class);
	}

	@Test
	void unaReservaEnviaUnCorreoDeAprobacion() {
		reserva.doAprobar();
		verify(servidorDeCorreo).enviar(anyString(), anyString(), any());
	}

	// ESTADO VIGENTE

	@Test
	void unaReservaVigenteEsCanceladaYCambiaSuEstadoACancelado() {
		when(alquiler.getManager()).thenReturn(manager);

		reserva.aprobar();
		reserva.cancelar();
		assertEquals(reserva.getEstado().getClass(), Cancelado.class);
	}

	@Test
	void unaReservaEnviaUnCorreoDeCancelacion() {
		when(alquiler.getManager()).thenReturn(manager);

		reserva.doCancelar();
		verify(servidorDeCorreo).enviar(anyString(), anyString(), any());
	}

	@Test
	void unaReservaVigenteRealizaElCheckOutYCambiaSuEstadoAFinalizado() {
		when(alquiler.getManager()).thenReturn(manager);

		reserva.aprobar();
		reserva.realizarCheckOut();
		assertEquals(reserva.getEstado().getClass(), Finalizado.class);
	}

	// ENCOLAR Y DESENCOLAR

	@Test
	void unaReservaPendienteDeAprobacionEsEncoladaYCambiaSuEstadoAEnCola() {
		reserva.encolar();
		assertEquals(reserva.getEstado().getClass(), EnCola.class);
	}

	@Test
	void unaReservaEnColaEsDesencoladaYCambiaSuEstadoAPendienteDeAprobacion() {
		reserva.encolar();
		reserva.desencolar();
		assertEquals(reserva.getEstado().getClass(), PendienteDeAprobacion.class);
	}

	// ------------------------------------------------------------
	// RANKING DE INMUEBLE, INQUILINO Y PROPIETARIO
	// ------------------------------------------------------------

	// INMUEBLE

	@Test
	void rankearUnInmuebleSoloEsPosibleEnEstadoFinalizado() {
		when(alquiler.getManager()).thenReturn(manager);

		reserva.aprobar();
		reserva.realizarCheckOut();
		reserva.rankearInmueble(ranking);
		verify(gestorDeRanking).recibirRankeo(ranking);
	}

	@Test
	void rankearUnInmuebleEnCualquierOtroEstadoNoRealizaNingunaAccion() {
		reserva.rankearInmueble(ranking);
		verify(gestorDeRanking, never()).recibirRankeo(ranking);
	}

	// INQUILINO

	@Test
	void rankearUnInquilinoSoloEsPosibleEnEstadoFinalizado() {
		when(alquiler.getManager()).thenReturn(manager);

		reserva.aprobar();
		reserva.realizarCheckOut();
		reserva.rankearInquilino(ranking);
		verify(gestorDeRanking).recibirRankeo(ranking);
	}

	@Test
	void rankearUnInquilinoEnCualquierOtroEstadoNoRealizaNingunaAccion() {
		reserva.rankearInquilino(ranking);
		verify(gestorDeRanking, never()).recibirRankeo(ranking);
	}

	// PROPIETARIO

	@Test
	void rankearUnPropietarioSoloEsPosibleEnEstadoFinalizado() {
		when(alquiler.getManager()).thenReturn(manager);

		reserva.aprobar();
		reserva.realizarCheckOut();
		reserva.rankearPropietario(ranking);
		verify(gestorDeRanking).recibirRankeo(ranking);
	}

	@Test
	void rankearUnPropietarioEnCualquierOtroEstadoNoRealizaNingunaAccion() {
		reserva.rankearPropietario(ranking);
		verify(gestorDeRanking, never()).recibirRankeo(ranking);
	}

	// ------------------------------------------------------------
}

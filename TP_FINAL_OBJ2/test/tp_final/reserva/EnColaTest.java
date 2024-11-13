package tp_final.reserva;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.ranking.Ranking;

class EnColaTest {
	private EstadoDeReserva estado;
	private Reserva reserva;
	private Ranking ranking;

	// ------------------------------------------------------------

	@BeforeEach
	void setUp() {
		// MOCK
		reserva = mock(Reserva.class);
		ranking = mock(Ranking.class);

		// SUT
		estado = new EnCola();
	}

	// ------------------------------------------------------------
	// TRANSICION DE ESTADOS
	// ------------------------------------------------------------

	@Test
	void UnEstadoEnColaEsAprobadoYLaReservaNoCambiaDeEstado() {
		estado.aprobar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	@Test
	void UnEstadoEnColaEsCanceladoYLaReservaCambiaACancelado() {
		estado.cancelar(reserva);
		verify(reserva).setEstado(any(Cancelado.class));
	}

	@Test
	void UnEstadoEnColaEsFinalizadoYLaReservaNoCambiaDeEstado() {
		estado.finalizar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	@Test
	void UnEstadoEnColaEsEncoladoYLaReservaNoCambiaDeEstado() {
		estado.encolar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	@Test
	void UnEstadoEnColaEsDesencoladoYLaReservaCambiaAPendienteDeAprobacion() {
		estado.desencolar(reserva);
		verify(reserva).setEstado(any(PendienteDeAprobacion.class));
	}

	// ------------------------------------------------------------
	// RANKING DE INMUEBLES, INQUILINOS Y PROPIETARIOS
	// ------------------------------------------------------------

	@Test
	void UnEstadoEnColaNoPuedeRankearInmueble() {
		estado.rankearInmueble(reserva, ranking);
		verify(reserva, never()).doRankearInmueble(ranking);
	}

	@Test
	void UnEstadoEnColaNoPuedeRankearInquilino() {
		estado.rankearInquilino(reserva, ranking);
		verify(reserva, never()).doRankearInquilino(ranking);
	}

	@Test
	void UnEstadoEnColaNoPuedeRankearPropietario() {
		estado.rankearPropietario(reserva, ranking);
		verify(reserva, never()).doRankearPropietario(ranking);
	}

	// ------------------------------------------------------------
}

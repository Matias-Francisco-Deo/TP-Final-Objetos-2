package tp_final.reserva;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.ranking.Ranking;

class PendienteDeAprobacionTest {
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
		estado = new PendienteDeAprobacion();
	}

	// ------------------------------------------------------------
	// TRANSICIONES DE ESTADOS
	// ------------------------------------------------------------

	@Test
	void UnEstadoPendienteDeAprobacionEsAprobadoYLaReservaCambiaAVigente() {
		estado.aprobar(reserva);
		verify(reserva).setEstado(isA(Vigente.class));
	}

	@Test
	void UnEstadoPendienteDeAprobacionEsRechazadoYLaReservaCambiaACancelado() {
		estado.rechazar(reserva);
		verify(reserva).setEstado(isA(Cancelado.class));
	}

	@Test
	void UnEstadoPendienteDeAprobacionEsCanceladoYLaReservaCambiaACancelado() {
		estado.cancelar(reserva);
		verify(reserva).setEstado(isA(Cancelado.class));
	}

	@Test
	void UnEstadoPendienteDeAprobacionEsFinalizadoYLaReservaNoCambiaDeEstado() {
		estado.finalizar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	@Test
	void UnEstadoPendienteDeAprobacionEsEncoladoYLaReservaCambiaAEnCola() {
		estado.encolar(reserva);
		verify(reserva).setEstado(isA(EnCola.class));
	}

	@Test
	void UnEstadoPendienteDeAprobacionEsDesencoladoYLaReservaNoCambiaDeEstado() {
		estado.desencolar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	// ------------------------------------------------------------
	// RANKING DE INMUEBLE, INQUILINO Y PROPIETARIO
	// ------------------------------------------------------------

	@Test
	void UnEstadoPendienteDeAprobacionNoPuedeRankearInmueble() {
		estado.rankearInmueble(reserva, ranking);
		verify(reserva, never()).doRankearInmueble(ranking);
	}

	@Test
	void UnEstadoPendienteDeAprobacionNoPuedeRankearInquilino() {
		estado.rankearInquilino(reserva, ranking);
		verify(reserva, never()).doRankearInquilino(ranking);
	}

	@Test
	void UnEstadoPendienteDeAprobacionNoPuedeRankearPropietario() {
		estado.rankearPropietario(reserva, ranking);
		verify(reserva, never()).doRankearPropietario(ranking);
	}

	// ------------------------------------------------------------
}

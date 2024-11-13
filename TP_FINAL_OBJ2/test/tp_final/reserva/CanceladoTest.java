package tp_final.reserva;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.ranking.Ranking;

class CanceladoTest {
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
		estado = new Cancelado();
	}

	// ------------------------------------------------------------
	// TRANSICION DE ESTADOS
	// ------------------------------------------------------------

	@Test
	void UnEstadoCanceladoEsAprobadoYLaReservaNoCambiaDeEstado() {
		estado.aprobar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	@Test
	void UnEstadoCanceladoEsCanceladoYLaReservaNoCambiaDeEstado() {
		estado.cancelar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	@Test
	void UnEstadoCanceladoEsFinalizadoYLaReservaNoCambiaDeEstado() {
		estado.finalizar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	@Test
	void UnEstadoCanceladoEsEncoladoYLaReservaNoCambiaDeEstado() {
		estado.encolar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	@Test
	void UnEstadoCanceladoEsDesencoladoYLaReservaNoCambiaDeEstado() {
		estado.desencolar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	// ------------------------------------------------------------
	// RANKING DE INMUEBLE, INQUILINO Y PROPIETARIO
	// ------------------------------------------------------------

	@Test
	void UnEstadoCanceladoNoPuedeRankearInmueble() {
		estado.rankearInmueble(reserva, ranking);
		verify(reserva, never()).doRankearInmueble(ranking);
	}

	@Test
	void UnEstadoCanceladoNoPuedeRankearInquilino() {
		estado.rankearInquilino(reserva, ranking);
		verify(reserva, never()).doRankearInquilino(ranking);
	}

	@Test
	void UnEstadoCanceladoNoPuedeRankearPropietario() {
		estado.rankearPropietario(reserva, ranking);
		verify(reserva, never()).doRankearPropietario(ranking);
	}

	// ------------------------------------------------------------
}

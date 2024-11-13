package tp_final.reserva;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.ranking.Ranking;

class VigenteTest {
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
		estado = new Vigente();
	}

	// ------------------------------------------------------------
	// TRANSICION DE ESTADOS
	// ------------------------------------------------------------

	@Test
	void UnEstadoVigenteEsAprobadoYLaReservaNoCambiaDeEstado() {
		estado.aprobar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	@Test
	void UnEstadoVigenteEsCanceladoYLaReservaCambiaACancelado() {
		estado.cancelar(reserva);
		verify(reserva).setEstado(isA(Cancelado.class));
	}

	@Test
	void UnEstadoVigenteEsFinalizadoYLaReservaCambiaAFinalizado() {
		estado.finalizar(reserva);
		verify(reserva).setEstado(isA(Finalizado.class));
	}

	@Test
	void UnEstadoVigenteEsEncoladoYLaReservaNoCambiaDeEstado() {
		estado.encolar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	@Test
	void UnEstadoVigenteEsDesencoladoYLaReservaNoCambiaDeEstado() {
		estado.desencolar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	// ------------------------------------------------------------
	// RANKING DE INMUEBLES, INQUILINOS Y PROPIETARIOS
	// ------------------------------------------------------------

	@Test
	void UnEstadoVigenteNoPuedeRankearInmueble() {
		estado.rankearInmueble(reserva, ranking);
		verify(reserva, never()).doRankearInmueble(ranking);
	}

	@Test
	void UnEstadoVigenteNoPuedeRankearInquilino() {
		estado.rankearInquilino(reserva, ranking);
		verify(reserva, never()).doRankearInquilino(ranking);
	}

	@Test
	void UnEstadoVigenteNoPuedeRankearPropietario() {
		estado.rankearPropietario(reserva, ranking);
		verify(reserva, never()).doRankearPropietario(ranking);
	}

	// ------------------------------------------------------------
}

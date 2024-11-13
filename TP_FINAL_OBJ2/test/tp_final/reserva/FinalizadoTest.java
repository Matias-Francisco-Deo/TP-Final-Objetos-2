package tp_final.reserva;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.ranking.Ranking;
import tp_final.usuarios.Usuario;

class FinalizadoTest {
	private EstadoDeReserva estado;
	private Reserva reserva;
	private Ranking ranking;

	// ------------------------------------------------------------

	@BeforeEach
	void setUp() {
		// MOCK
		reserva = mock(Reserva.class);
		ranking = mock(Ranking.class);
		Usuario propietario = mock(Usuario.class);

		// STUB
		when(reserva.getPropietario()).thenReturn(propietario);

		// SUT
		estado = new Finalizado();
	}

	// ------------------------------------------------------------
	// TRANSICION DE ESTADOS
	// ------------------------------------------------------------

	@Test
	void UnEstadoFinalizadoEsAprobadoYLaReservaNoCambiaDeEstado() {
		estado.aprobar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	@Test
	void UnEstadoFinalizadoEsCanceladoYLaReservaNoCambiaDeEstado() {
		estado.cancelar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	@Test
	void UnEstadoFinalizadoEsFinalizadoYLaReservaNoCambiaDeEstado() {
		estado.finalizar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	@Test
	void UnEstadoFinalizadoEsEncoladoYLaReservaNoCambiaDeEstado() {
		estado.encolar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	@Test
	void UnEstadoFinalizadoEsDesencoladoYLaReservaNoCambiaDeEstado() {
		estado.desencolar(reserva);
		verify(reserva, never()).setEstado(any());
	}

	// ------------------------------------------------------------
	// RANKING DE INMUEBLES, INQUILINOS Y PROPIETARIOS
	// ------------------------------------------------------------

	@Test
	void UnEstadoFinalizadoPuedeRankearInmueble() {
		estado.rankearInmueble(reserva, ranking);
		verify(reserva).doRankearInmueble(ranking);
	}

	@Test
	void UnEstadoFinalizadoPuedeRankearInquilino() {
		estado.rankearInquilino(reserva, ranking);
		verify(reserva).doRankearInquilino(ranking);
	}

	@Test
	void UnEstadoFinalizadoPuedeRankearPropietario() {
		estado.rankearPropietario(reserva, ranking);
		verify(reserva).doRankearPropietario(ranking);
	}

	// ------------------------------------------------------------
}

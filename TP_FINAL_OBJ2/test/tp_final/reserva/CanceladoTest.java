package tp_final.reserva;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CanceladoTest {
	private EstadoDeReserva estado;
	private Reserva reserva;

	@BeforeEach
	void setUp() {
		// MOCK
		reserva = mock(Reserva.class);

		// SUT
		estado = new Cancelado();
	}

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
}

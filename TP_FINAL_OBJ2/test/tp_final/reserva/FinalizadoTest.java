package tp_final.reserva;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FinalizadoTest {
	private EstadoDeReserva estado;
	private Reserva reserva;

	@BeforeEach
	void setUp() {
		// MOCK
		reserva = mock(Reserva.class);

		// SUT
		estado = new Finalizado();
	}

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
}

package tp_final.reserva;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PendienteDeAprobacionTest {
	private EstadoDeReserva estado;
	private Reserva reserva;

	@BeforeEach
	void setUp() {
		// MOCK
		reserva = mock(Reserva.class);

		// SUT
		estado = new PendienteDeAprobacion();
	}

	@Test
	void UnEstadoPendienteDeAprobacionEsAprobadoYLaReservaCambiaAVigente() {
		estado.aprobar(reserva);
		verify(reserva).setEstado(isA(Vigente.class));
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
}

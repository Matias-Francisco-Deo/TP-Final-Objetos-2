package tp_final.reserva;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnColaTest {
	private EstadoDeReserva estado;
	private Reserva reserva;

	@BeforeEach
	void setUp() {
		// MOCK
		reserva = mock(Reserva.class);

		// SUT
		estado = new EnCola();
	}

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
}

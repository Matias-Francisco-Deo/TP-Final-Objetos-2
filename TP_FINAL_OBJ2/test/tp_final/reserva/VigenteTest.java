package tp_final.reserva;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VigenteTest {
	private EstadoDeReserva estado;
	private Reserva reserva;

	@BeforeEach
	void setUp() {
		// MOCK
		reserva = mock(Reserva.class);

		// SUT
		estado = new Vigente();
	}

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
}

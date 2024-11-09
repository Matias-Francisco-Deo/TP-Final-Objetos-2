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
	private Reserva spy;

	@BeforeEach
	void setUp() {
		// MOCK
		spy = mock(Reserva.class);

		// SUT
		estado = new Vigente();
	}

	@Test
	void UnEstadoVigenteEsAprobadoYLaReservaNoCambiaDeEstado() {
		estado.aprobar(spy);
		verify(spy, never()).setEstado(any());
	}

	@Test
	void UnEstadoVigenteEsCanceladoYLaReservaCambiaACancelado() {
		estado.cancelar(spy);
		verify(spy).setEstado(isA(Cancelado.class));
	}

	@Test
	void UnEstadoVigenteEsFinalizadoYLaReservaCambiaAFinalizado() {
		estado.finalizar(spy);
		verify(spy).setEstado(isA(Finalizado.class));
	}
}

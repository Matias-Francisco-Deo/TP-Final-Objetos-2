package tp_final.reserva;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.Alquiler;
import tp_final.usuarios.Usuario;
import tp_final.varios.MedioDePago;
import tp_final.varios.ServidorDeCorreo;

class VigenteTest {
	private EstadoDeReserva estado;
	private Reserva spy;

	@BeforeEach
	void setUp() {
		// MOCK
		Alquiler alquiler = mock(Alquiler.class);
		Usuario inquilino = mock(Usuario.class);
		MedioDePago medioDePago = mock(MedioDePago.class);
		ServidorDeCorreo servidorDeCorreo = mock(ServidorDeCorreo.class);
		Usuario propietario = mock(Usuario.class);

		// STUB
		when(alquiler.getPropietario()).thenReturn(propietario);

		// SPY
		spy = spy(new Reserva(alquiler, inquilino, medioDePago, servidorDeCorreo));

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

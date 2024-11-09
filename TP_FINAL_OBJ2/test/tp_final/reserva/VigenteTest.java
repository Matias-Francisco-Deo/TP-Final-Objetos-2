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
	private Reserva reserva;

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
		when(propietario.getEmail()).thenReturn("propietario@gmail.com");

		// SPY
		reserva = spy(new Reserva(alquiler, inquilino, medioDePago, servidorDeCorreo));

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
	void UnEstadoVigenteEsFinalizadoYLaReservaNoCambiaDeEstado() {
		estado.finalizar(reserva);
		verify(reserva, never()).setEstado(any());
	}
}

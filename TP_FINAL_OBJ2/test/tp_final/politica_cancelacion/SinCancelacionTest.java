package tp_final.politica_cancelacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.reserva.Reserva;
import tp_final.varios.ServidorDeCorreo;

public class SinCancelacionTest {

	private Reserva reserva1;

	private SinCancelacion sinCancelacion;

	@BeforeEach
	void setUp() throws Exception {

		reserva1 = mock(Reserva.class);
		ServidorDeCorreo servidorDeCorreo = mock(ServidorDeCorreo.class);

		sinCancelacion = new SinCancelacion(servidorDeCorreo);

	}

	@Test
	void sinCancelacioPagaLoReservadoTest() {

		String textoEsperado = "Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de 50000.0 $";

		LocalDate fechaEntrada = LocalDate.now().plusDays(15);
		LocalDate fechaSalida = fechaEntrada.plusDays(5);

		SinCancelacion spy = spy(sinCancelacion);

		when(reserva1.getFechaCheckIn()).thenReturn(fechaEntrada);
		when(reserva1.getFechaCheckOut()).thenReturn(fechaSalida);

		spy.aplicarPolitica(reserva1, 10000);

		// dentro del metodo ocurre lo siguiente y se testea

		double costo = sinCancelacion.calcularCosto(fechaEntrada, fechaSalida, 10000);

		String textoGenerado = sinCancelacion.generarMail(costo);

		assertEquals(50000, costo);
		assertEquals(textoEsperado, textoGenerado);
		verify(spy).enviarMail(textoGenerado, reserva1);
	}
}

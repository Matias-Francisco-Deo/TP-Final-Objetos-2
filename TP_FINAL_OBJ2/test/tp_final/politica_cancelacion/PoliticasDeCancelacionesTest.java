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

public class PoliticasDeCancelacionesTest {

	private Reserva reserva1;

	private CancelacionGratuita cancelacionGratuita;

	private SinCancelacion sinCancelacion;

	private CancelacionIntermedia cancelacionIntermedia;

	@BeforeEach
	void setUp() throws Exception {

		reserva1 = mock(Reserva.class);
		ServidorDeCorreo servidorDeCorreo = mock(ServidorDeCorreo.class);

		cancelacionGratuita = new CancelacionGratuita(servidorDeCorreo);
		sinCancelacion = new SinCancelacion(servidorDeCorreo);
		cancelacionIntermedia = new CancelacionIntermedia(servidorDeCorreo);

	}

	@Test
	void cancelacionGratuitaConMasDe10DiasTest() {

		String textoEsperado = "Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de 0.0 $";

		LocalDate fechaEntrada = LocalDate.now().plusDays(15);
		LocalDate fechaSalida = fechaEntrada.plusDays(5);

		CancelacionGratuita spy = spy(cancelacionGratuita);

		when(reserva1.getFechaCheckIn()).thenReturn(fechaEntrada);
		when(reserva1.getFechaCheckOut()).thenReturn(fechaSalida);

		spy.aplicarPolitica(reserva1, 10000);

		// dentro del metodo ocurre lo siguiente y se testea

		double costo = cancelacionGratuita.calcularCosto(fechaEntrada, fechaSalida, 10000);

		String textoGenerado = cancelacionGratuita.generarMail(costo);

		assertEquals(0, costo);
		assertEquals(textoEsperado, textoGenerado);
		verify(spy).enviarMail(textoGenerado, reserva1);
	}

	@Test
	void sinCancelacionGratuitaConMenosDe10DiasTest() {

		String textoEsperado = "Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de 20000.0 $";

		LocalDate fechaEntrada = LocalDate.now().plusDays(5);
		LocalDate fechaSalida = fechaEntrada.plusDays(5);

		CancelacionGratuita spy = spy(cancelacionGratuita);

		when(reserva1.getFechaCheckIn()).thenReturn(fechaEntrada);
		when(reserva1.getFechaCheckOut()).thenReturn(fechaSalida);

		spy.aplicarPolitica(reserva1, 10000);

		// dentro del metodo ocurre lo siguiente y se testea

		double costo = cancelacionGratuita.calcularCosto(fechaEntrada, fechaSalida, 10000);

		String textoGenerado = cancelacionGratuita.generarMail(costo);

		assertEquals(20000, costo);
		assertEquals(textoEsperado, textoGenerado);
		verify(spy).enviarMail(textoGenerado, reserva1);
	}

	@Test
	void sinCancelacionTest() {

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

	@Test
	void cancelacionIntermediaConMasDe20DiasTest() {

		String textoEsperado = "Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de 0.0 $";

		LocalDate fechaEntrada = LocalDate.now().plusDays(25);
		LocalDate fechaSalida = fechaEntrada.plusDays(5);

		CancelacionIntermedia spy = spy(cancelacionIntermedia);

		when(reserva1.getFechaCheckIn()).thenReturn(fechaEntrada);
		when(reserva1.getFechaCheckOut()).thenReturn(fechaSalida);

		spy.aplicarPolitica(reserva1, 10000);

		// dentro del metodo ocurre lo siguiente y se testea

		double costo = cancelacionIntermedia.calcularCosto(fechaEntrada, fechaSalida, 10000);

		String textoGenerado = cancelacionIntermedia.generarMail(costo);

		assertEquals(0, costo);
		assertEquals(textoEsperado, textoGenerado);
		verify(spy).enviarMail(textoGenerado, reserva1);
	}

	@Test
	void CancelacionIntermediaConMenosDe20YMasDe10DiasTest() {

		String textoEsperado = "Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de 25000.0 $";

		LocalDate fechaEntrada = LocalDate.now().plusDays(15);
		LocalDate fechaSalida = fechaEntrada.plusDays(5);

		CancelacionIntermedia spy = spy(cancelacionIntermedia);

		when(reserva1.getFechaCheckIn()).thenReturn(fechaEntrada);
		when(reserva1.getFechaCheckOut()).thenReturn(fechaSalida);

		spy.aplicarPolitica(reserva1, 10000);

		// dentro del metodo ocurre lo siguiente y se testea

		double costo = cancelacionIntermedia.calcularCosto(fechaEntrada, fechaSalida, 10000);

		String textoGenerado = cancelacionIntermedia.generarMail(costo);

		assertEquals(25000, costo);
		assertEquals(textoEsperado, textoGenerado);
		verify(spy).enviarMail(textoGenerado, reserva1);
	}

	@Test
	void CancelacionIntermediaConMenos10DiasTest() {

		String textoEsperado = "Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de 50000.0 $";

		LocalDate fechaEntrada = LocalDate.now().plusDays(7);
		LocalDate fechaSalida = fechaEntrada.plusDays(5);

		CancelacionIntermedia spy = spy(cancelacionIntermedia);

		when(reserva1.getFechaCheckIn()).thenReturn(fechaEntrada);
		when(reserva1.getFechaCheckOut()).thenReturn(fechaSalida);

		spy.aplicarPolitica(reserva1, 10000);

		// dentro del metodo ocurre lo siguiente y se testea

		double costo = cancelacionIntermedia.calcularCosto(fechaEntrada, fechaSalida, 10000);

		String textoGenerado = cancelacionIntermedia.generarMail(costo);

		assertEquals(50000, costo);
		assertEquals(textoEsperado, textoGenerado);
		verify(spy).enviarMail(textoGenerado, reserva1);
	}

}

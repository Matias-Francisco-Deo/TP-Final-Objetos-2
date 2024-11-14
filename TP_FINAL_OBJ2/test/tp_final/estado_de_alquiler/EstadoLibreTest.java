package tp_final.estado_de_alquiler;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;
import tp_final.reserva.Reserva;

public class EstadoLibreTest {

	Alquiler alquiler;

	private Reserva reserva1;
	private Reserva reserva2;

	private EstadoDeAlquiler libre;

	@BeforeEach
	void setUp() throws Exception {

		alquiler = mock(Alquiler.class);

		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);

		libre = new Libre();

	}

	@Test
	void libreAlquilarTest() {

		libre.alquilar(reserva1, alquiler);

		verify(alquiler).doAlquilarLibre(reserva1);
	}

	@Test
	void cancelarTest() {

		libre.cancelar(reserva1, alquiler);

		verify(alquiler, times(1)).doCancelarLibre(reserva1);

	}

	@Test
	void esLibreAlquiladoTest() {

		libre.cancelar(reserva1, alquiler);

		assertTrue(libre.esLibre());

	}
}

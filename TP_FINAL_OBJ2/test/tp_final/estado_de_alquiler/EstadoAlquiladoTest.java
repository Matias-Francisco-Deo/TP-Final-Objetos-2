package tp_final.estado_de_alquiler;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;
import tp_final.reserva.Reserva;

public class EstadoAlquiladoTest {

	Alquiler alquiler;

	private Reserva reserva1;
	private Reserva reserva2;

	private EstadoDeAlquiler alquilado;

	List<Reserva> colaReservas;

	@BeforeEach
	void setUp() throws Exception {

		alquiler = mock(Alquiler.class);

		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);

		colaReservas = new ArrayList<>();

		alquilado = new Alquilado();

		alquiler.setEstadoDeAlquiler(alquilado);

	}

	@Test
	void alquiladoAlquilarTest() {

		alquilado.alquilar(reserva1, alquiler);

		verify(alquiler).doAlquilarAlquilado(reserva1);

	}

	@Test
	void cancelarTest() {

		alquilado.cancelar(reserva1, alquiler);

		verify(alquiler, times(1)).doCancelarAlquilado(reserva1);

	}

	@Test
	void esLibreAlquiladoTest() {

		alquilado.cancelar(reserva1, alquiler);

		assertFalse(alquilado.esLibre());

	}

}

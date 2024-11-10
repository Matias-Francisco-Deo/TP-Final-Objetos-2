package tp_final.estado_de_alquiler;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;
import tp_final_extra.Reserva;

public class estadoAlquiladoTest {

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

	}

	@Test
	void alquiladoAlquilarTest() {
		when(alquiler.getcolaDeEspera()).thenReturn(new java.util.ArrayList<>());

		alquilado.alquilar(reserva1, alquiler);

		verify(alquiler).addReserva(reserva1);

		// verify(reserva1).encolarReserva();

	}

	@Test
	void alquiladoCancelarConUnaSolaReservaTest() {

		colaReservas.add(reserva1);

		when(alquiler.getcolaDeEspera()).thenReturn(colaReservas);

		alquilado.cancelar(reserva1, alquiler);

		assertTrue(colaReservas.isEmpty());

		verify(alquiler).getcolaDeEspera();
	}
}
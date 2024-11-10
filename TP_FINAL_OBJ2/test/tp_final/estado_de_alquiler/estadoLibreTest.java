package tp_final.estado_de_alquiler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;
import tp_final_extra.Reserva;

public class estadoLibreTest {

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
		List<Reserva> colaReservas = new ArrayList<>();

		when(alquiler.getcolaDeEspera()).thenReturn(new java.util.ArrayList<>());

		libre.alquilar(reserva1, alquiler);

		verify(alquiler).addReserva(reserva1);

		// verify(reserva1).encolarReserva();

		reset(alquiler);

		colaReservas.add(reserva1);

		when(alquiler.getcolaDeEspera()).thenReturn(colaReservas);

		libre.alquilar(reserva2, alquiler);

		verify(alquiler).addReserva(reserva2);
	}

	@Test
	void libreCancelarPimeraConMasDeUnaReservaTest() {

		List<Reserva> colaReservas = new ArrayList<>();

		colaReservas.add(reserva1);
		colaReservas.add(reserva2);

		when(alquiler.getcolaDeEspera()).thenReturn(colaReservas);

		libre.cancelar(reserva1, alquiler);

		verify(reserva2).desencolar();

	}

	@Test
	void libreCancelarConMasDeUnaReservaYNoLaPrimeraTest() {

		List<Reserva> colaReservas = new ArrayList<>();

		when(alquiler.getcolaDeEspera()).thenReturn(colaReservas);

		colaReservas.add(reserva1);
		colaReservas.add(reserva2);

		libre.cancelar(reserva2, alquiler);

	}

	@Test
	void libreCancelarConUnaSolaReservaTest() {

		List<Reserva> colaReservas = new ArrayList<>();

		when(alquiler.getcolaDeEspera()).thenReturn(colaReservas);

		colaReservas.add(reserva1);

		libre.cancelar(reserva1, alquiler);

	}

}

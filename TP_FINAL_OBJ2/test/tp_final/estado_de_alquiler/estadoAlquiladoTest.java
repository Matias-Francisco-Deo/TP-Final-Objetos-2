package tp_final.estado_de_alquiler;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;
import tp_final.reserva.Reserva;

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

		alquiler.setEstadoDeAlquiler(alquilado);

	}

	@Test
	void alquiladoAlquilarTest() {
		when(alquiler.getcolaDeEspera()).thenReturn(new java.util.ArrayList<>());

		alquilado.alquilar(reserva1, alquiler);

		verify(alquiler).addReserva(reserva1);

		// verify(reserva1).encolarReserva();

	}

	@Test
	void cancelarTest() {

		colaReservas.add(reserva1);

		doNothing().when(alquiler).doCancelarAlquilado(reserva1);

		alquilado.cancelar(reserva1, alquiler);// error en alquilado con notificarsubs

		verify(alquiler, times(1)).doCancelarAlquilado(reserva1);

	}
	/*
	 * @Test void AlquiladoCancelarConMasDeUnaReservaYNoLaPrimeraTest() {
	 *
	 * List<Reserva> colaReservas = new ArrayList<>();
	 *
	 * when(alquiler.getcolaDeEspera()).thenReturn(colaReservas);
	 *
	 * colaReservas.add(reserva1); colaReservas.add(reserva2);
	 *
	 * alquilado.cancelar(reserva2, alquiler);// error en alquilado con
	 * notificarsubs verify(reserva2, never()).desencolar();
	 *
	 * }
	 *
	 * @Test void alquiladoCancelarConUnaSolaReservaTest() {
	 *
	 * verify(alquiler).setEstadoDeAlquiler(any(Alquilado.class));
	 *
	 * colaReservas.add(reserva1);
	 *
	 * when(alquiler.getcolaDeEspera()).thenReturn(colaReservas);
	 *
	 * alquilado.cancelar(reserva1, alquiler);
	 *
	 * assertTrue(colaReservas.isEmpty());
	 *
	 * verify(alquiler).notificarSubs();
	 *
	 * verify(alquiler).setEstadoDeAlquiler(any(Libre.class));
	 *
	 * }
	 */
}

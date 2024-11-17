package tp_final.alquiler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.reserva.Reserva;

public class ManagerAlquilerTest {

	ManagerDeAlquiler manager;

	Alquiler alquiler;

	private Reserva reserva1;
	private Reserva reserva2;
	private Reserva reserva3;
	private Reserva reserva4;

	@BeforeEach
	void setUp() throws Exception {

		alquiler = mock(Alquiler.class);

		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reserva3 = mock(Reserva.class);
		reserva4 = mock(Reserva.class);

		manager = new ManagerDeAlquiler(alquiler);
	}

	@Test
	void addReservaFechaOcupadaTest() {
		when(reserva1.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 5));

		when(reserva2.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 10, 1));
		when(reserva2.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 3));

		when(reserva3.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 2));
		when(reserva3.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 12, 3));

		manager.reservar(reserva1);

		assertEquals(manager.getColaReservados().size(), 1);

		manager.reservar(reserva2);
		manager.reservar(reserva3);

		assertEquals(manager.getColaDeEspera().size(), 2);
		assertEquals(manager.getColaReservados().size(), 1);

		verify(reserva2).encolar();
	}

	@Test
	void addReservaFechalibreTest() {
		when(reserva1.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 5));

		when(reserva2.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 10, 1));
		when(reserva2.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 10, 3));

		when(reserva3.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 6));
		when(reserva3.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 12, 3));

		manager.reservar(reserva1);

		assertEquals(manager.getColaReservados().size(), 1);

		manager.reservar(reserva2);
		manager.reservar(reserva3);

		assertEquals(manager.getColaDeEspera().size(), 0);
		assertEquals(manager.getColaReservados().size(), 3);
	}

	@Test
	void cancelarReservaVigenteHabilitandoRangoDeOtraReservaTest() {
		when(reserva1.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 10, 1));
		when(reserva1.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 10, 5));

		when(reserva2.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 10, 6));
		when(reserva2.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 10, 10));

		when(reserva3.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 10, 2));
		when(reserva3.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 12, 7));

		when(reserva4.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 10, 2));
		when(reserva4.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 10, 5));

		manager.reservar(reserva1);
		manager.reservar(reserva2);
		manager.reservar(reserva3);
		manager.reservar(reserva4);

		assertEquals(manager.getColaDeEspera().size(), 2);
		assertEquals(manager.getColaReservados().size(), 2);

		manager.cancelarReserva(reserva1);

		verify(alquiler).aplicarPoliticaDeCancelacion(reserva1);

		verify(alquiler).notificarCancelacion();

		assertEquals(manager.getColaDeEspera().size(), 1);// reserva3 no se deberia mover porque no tiene rango
															// disponible
		assertEquals(manager.getColaReservados().size(), 2);// por lo que se mueve la 4 que si tiene lugar

		verify(reserva4).desencolar();

	}

	@Test
	void cancelarReservaVigenteHabilitandoRangoDeDosReservaConRangosSimilaresTest() {
		when(reserva1.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 5));

		when(reserva2.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 6));
		when(reserva2.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 10));

		when(reserva3.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 2));
		when(reserva3.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 5));

		when(reserva4.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 2));
		when(reserva4.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 5));

		manager.reservar(reserva1);
		manager.reservar(reserva2);
		manager.reservar(reserva3);
		manager.reservar(reserva4);

		assertEquals(manager.getColaDeEspera().size(), 2);
		assertEquals(manager.getColaReservados().size(), 2);

		manager.cancelarReserva(reserva1);

		verify(alquiler).aplicarPoliticaDeCancelacion(reserva1);

		verify(alquiler).notificarCancelacion();

		assertEquals(manager.getColaDeEspera().size(), 1);// reserva4 no se muebe ya que la reserva 3 se movio primero,
															// ocupando el lugar libre
		assertEquals(manager.getColaReservados().size(), 2);

		assertEquals(manager.getColaDeEspera().get(0), reserva4);

		verify(reserva3).desencolar();

	}

	@Test
	void cancelarReservaVigenteSinHabilitarRangoDeOtraReservaTest() {
		when(reserva1.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 5));

		when(reserva2.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 6));
		when(reserva2.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 10));

		when(reserva3.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 9));
		when(reserva3.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 12, 1));

		when(reserva4.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 2));
		when(reserva4.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 6));

		manager.reservar(reserva1);
		manager.reservar(reserva2);
		manager.reservar(reserva3);
		manager.reservar(reserva4);

		assertEquals(manager.getColaDeEspera().size(), 2);
		assertEquals(manager.getColaReservados().size(), 2);

		manager.cancelarReserva(reserva1);

		verify(alquiler).notificarCancelacion();

		assertEquals(manager.getColaDeEspera().size(), 2);
		assertEquals(manager.getColaReservados().size(), 1);

	}

	@Test
	void cancelarReservaDegetColaDeEsperaTest() {
		when(reserva1.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 5));

		when(reserva2.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 6));
		when(reserva2.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 10));

		when(reserva3.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 2));
		when(reserva3.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 12, 1));

		when(reserva4.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 2));
		when(reserva4.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 5));

		manager.reservar(reserva1);
		manager.reservar(reserva2);
		manager.reservar(reserva3);
		manager.reservar(reserva4);

		assertEquals(manager.getColaDeEspera().size(), 2);
		assertEquals(manager.getColaReservados().size(), 2);

		manager.cancelarReserva(reserva3);

		assertEquals(manager.getColaDeEspera().size(), 1);
		assertEquals(manager.getColaReservados().size(), 2);

	}

}

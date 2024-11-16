package tp_final.alquiler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
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

	@BeforeEach
	void setUp() throws Exception {

		alquiler = mock(Alquiler.class);

		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reserva3 = mock(Reserva.class);

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

}

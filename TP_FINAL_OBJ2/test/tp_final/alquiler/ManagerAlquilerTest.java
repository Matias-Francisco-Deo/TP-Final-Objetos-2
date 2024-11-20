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
	void agregaUnaReservaEnUnaFechaOcupadaTest() {
		when(reserva1.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 5));

		when(reserva2.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 10, 1));
		when(reserva2.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 3));

		when(reserva3.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 2));
		when(reserva3.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 12, 3));

		manager.reservar(reserva1); // colaReservados tiene un elemento

		manager.reservar(reserva2);
		manager.reservar(reserva3);

		assertEquals(manager.getColaDeEspera().size(), 2); // colaDeEspera ahora tiene 2 reservas esperando
		assertEquals(manager.getColaReservados().size(), 1); // la colaReservados se mantiene igual

		verify(reserva2).encolar(); // se verifica que se encolo la reserva

	}

	@Test
	void agregaUnaReservaEnUnaFechaLibreTest() {
		when(reserva1.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 5));

		when(reserva2.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 10, 1));
		when(reserva2.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 10, 3));

		when(reserva3.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 6));
		when(reserva3.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 12, 3));

		manager.reservar(reserva1);

		assertEquals(manager.getColaReservados().size(), 1); // se verifica que se agrego la reserva a la colaReservados

		manager.reservar(reserva2);
		manager.reservar(reserva3);

		assertEquals(manager.getColaDeEspera().size(), 0); // colaDeEspera se mantiene vacia
		assertEquals(manager.getColaReservados().size(), 3);// se verifica que se agregaron las reservas a la
															// colaReservados que ya tenia una reserva
	}

	@Test
	void notificaCancelacionAlCancelarUnaReserva() {

		when(reserva1.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 5));

		manager.reservar(reserva1);

		manager.cancelarReserva(reserva1);

		verify(alquiler).notificarCancelacion();

	}

	@Test
	void llamaALaPoliticaDeCancelacionAlCancelarUnaReserva() {

		when(reserva1.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 5));

		manager.reservar(reserva1);

		manager.cancelarReserva(reserva1);

		verify(alquiler).aplicarPoliticaDeCancelacion(reserva1);

	}

	@Test
	void desencolaReservaEnColaAlCancelarOtraQueOcupabaSusFechas() {

		when(reserva1.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 5));

		when(reserva4.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 10, 2));
		when(reserva4.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 10, 5));

		manager.reservar(reserva1);
		manager.reservar(reserva4);

		manager.cancelarReserva(reserva1);

		verify(reserva4).desencolar();

	}

	@Test
	void seCancelaReservaVigenteHabilitandoRangoDeReservaEnColaPeroQueNoEsLaPrimeraPorqueNoTieneRangoDeFechasTest() {
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

		assertEquals(manager.getColaReservados().size(), 2);// las primeras 2 resdervas van a la colaReservados

		assertEquals(manager.getColaDeEspera().size(), 2);// las 2 ultimas reservas van a la colaDeESpera porque las 2
															// primeras ocupan el rango de las ultimas

		manager.cancelarReserva(reserva1);// cancelamos la reserva que ocupa las fechas una de las reservas

		assertEquals(manager.getColaDeEspera().size(), 1);// reserva3 no se mueve porque no tiene rango
															// disponible
		assertEquals(manager.getColaReservados().size(), 2);// se mueve la reserva4 que ahora si tiene las fechas
															// disponibles

	}

	@Test
	void seCancelaReservaVigenteHabilitandoRangoDeReservaEnColaPasandoLaPrimeraReservaQueSeLeHabilitaElRangoTest() {
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

		manager.cancelarReserva(reserva1);

		assertEquals(manager.getColaDeEspera().get(0), reserva4);// reserva4 no se muebe ya que la reserva 3 se movio
																	// primero, cupando el lugar libre

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

		manager.cancelarReserva(reserva1);

		assertEquals(manager.getColaDeEspera().size(), 2);
		assertEquals(manager.getColaReservados().size(), 1);

	}

	@Test
	void cancelandoReservaDegetColaDeEsperaTest() {
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

		manager.cancelarReserva(reserva3);

		assertEquals(manager.getColaDeEspera().size(), 1);

	}

}

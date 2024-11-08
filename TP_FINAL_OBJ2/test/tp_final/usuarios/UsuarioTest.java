package tp_final.usuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.reserva.Reserva;

class UsuarioTest {
	private Usuario usuario;
	private Reserva reserva;

	@BeforeEach
	void setUp() throws Exception {
		usuario = new Usuario("Juan Perez", "juan.perez@gmail.com", "555-555-555");

		// MOCKS
		reserva = mock(Reserva.class);
	}

	// ------------------------------
	// GETTERS
	// ------------------------------

	@Test
	void getNombreTest() {
		assertEquals(usuario.getNombre(), "Juan Perez");
	}

	@Test
	void getEmailTest() {
		assertEquals(usuario.getEmail(), "juan.perez@gmail.com");
	}

	@Test
	void getTelefonoTest() {
		assertEquals(usuario.getTelefono(), "555-555-555");
	}

	// ------------------------------
	// RESERVAS
	// ------------------------------

	@Test
	void getReservasTest() {
		assertEquals(usuario.getReservas().size(), 0);
	}

	@Test
	void realizarReservaTest() {
		usuario.realizarReserva(reserva);
		assertEquals(usuario.getReservas().size(), 1);
	}

	@Test
	void cancelarReservaTest() {
		usuario.realizarReserva(reserva);
		usuario.cancelarReserva(reserva);
		assertEquals(usuario.getReservas().size(), 0);
	}

	@Test
	void getReservasFuturasTest() {
		when(reserva.getFechaCheckIn()).thenReturn(LocalDate.of(2021, 12, 1));

		usuario.realizarReserva(reserva);
		assertEquals(usuario.getReservasFuturas().size(), 0);
	}

	@Test
	void getReservasEnCiudadTest() {
		when(reserva.getCiudad()).thenReturn("Buenos Aires");

		usuario.realizarReserva(reserva);
		assertEquals(usuario.getReservasEnCiudad("Buenos Aires").size(), 1);
		assertEquals(usuario.getReservasEnCiudad("Rosario").size(), 0);
	}

	@Test
	void getCiudadesConReservaTest() {
		when(reserva.getCiudad()).thenReturn("Buenos Aires");

		usuario.realizarReserva(reserva);
		assertEquals(usuario.getCiudadesConReserva().size(), 1);
		assertEquals(usuario.getCiudadesConReserva().get(0), "Buenos Aires");
	}
}

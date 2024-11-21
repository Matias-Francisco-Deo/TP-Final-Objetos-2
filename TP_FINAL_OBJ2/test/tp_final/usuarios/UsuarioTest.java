package tp_final.usuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;
import tp_final.inmueble.Inmueble;
import tp_final.ranking.GestorDeRanking;
import tp_final.reserva.Reserva;

class UsuarioTest {
	private Usuario usuario;
	private Reserva reserva;

	private GestorDeRanking gestorDeRanking;

	// ------------------------------------------------------------

	@BeforeEach
	void setUp() throws Exception {
		// MOCK
		reserva = mock(Reserva.class);
		gestorDeRanking = mock(GestorDeRanking.class);
		Alquiler alquiler = mock(Alquiler.class);
		Inmueble inmueble = mock(Inmueble.class);

		// STUB
		when(alquiler.getInmueble()).thenReturn(inmueble);

		// SUT
		usuario = new Usuario("Juan Perez", "juan.perez@gmail.com", "555-555-555", gestorDeRanking);
	}

	// ------------------------------------------------------------
	// NOMBRE, EMAIL, TELEFONO Y GESTOR DE RANKING
	// ------------------------------------------------------------

	@Test
	void getNombreTest() {
		// VERIFY
		assertEquals(usuario.getNombre(), "Juan Perez");
	}

	@Test
	void getEmailTest() {
		// VERIFY
		assertEquals(usuario.getEmail(), "juan.perez@gmail.com");
	}

	@Test
	void getTelefonoTest() {
		// VERIFY
		assertEquals(usuario.getTelefono(), "555-555-555");
	}

	@Test
	void getGestorDeRankingTest() {
		// VERIFY
		assertEquals(usuario.getGestorDeRanking(), gestorDeRanking);
	}

	// ------------------------------------------------------------
	// RESERVAS REALIZADAS COMO INQUILINO
	// ------------------------------------------------------------

	@Test
	void getReservasTest() {
		// VERIFY
		assertEquals(usuario.getReservas().size(), 0);
	}

	@Test
	void realizarReservaTest() {
		// EXERCISE
		usuario.realizarReserva(reserva);

		// VERIFY
		assertEquals(usuario.getReservas().size(), 1);
	}

	@Test
	void cancelarReservaTest() {
		// SETUP
		usuario.realizarReserva(reserva);

		// EXERCISE
		usuario.cancelarReserva(reserva);

		// VERIFY
		assertEquals(usuario.getReservas().size(), 0);
	}

	@Test
	void getReservasFuturasTest() {
		// SETUP
		when(reserva.getFechaCheckIn()).thenReturn(LocalDate.of(2021, 12, 1));

		// EXERCISE
		usuario.realizarReserva(reserva);

		// VERIFY
		assertEquals(usuario.getReservasFuturas().size(), 0);
	}

	@Test
	void getReservasEnCiudadTest() {
		// SETUP
		when(reserva.getCiudad()).thenReturn("Buenos Aires");

		// EXERCISE
		usuario.realizarReserva(reserva);

		// VERIFY
		assertEquals(usuario.getReservasEnCiudad("Buenos Aires").size(), 1);
	}

	@Test
	void getCiudadesConReservaTest() {
		// SETUP
		when(reserva.getCiudad()).thenReturn("Buenos Aires");

		// EXERCISE
		usuario.realizarReserva(reserva);

		// VERIFY
		assertEquals(usuario.getCiudadesConReserva().size(), 1);
	}

	@Test
	void getCantidadDeReservasTest() {
		// SETUP
		usuario.realizarReserva(reserva);

		// VERIFY
		assertEquals(usuario.getCantidadDeReservas(), 1);
	}

	// ------------------------------------------------------------
	// ALQUILERES E INMUEBLES REGISTRADOS COMO PROPIETARIO
	// ------------------------------------------------------------

	@Test
	public void registrarYObtenerAlquilerTest() {
		// SETUP / MOCK
		Alquiler alquiler = mock(Alquiler.class);

		// // EXERCISE
		usuario.registrarAlquiler(alquiler);

		// VERIFY
		assertEquals(usuario.getAlquileres().size(), 1);
	}

	@Test
	public void registrarYObtenerInmuebleTest() {
		// SETUP / MOCK
		Inmueble inmueble = mock(Inmueble.class);

		// EXERCISE
		usuario.registrarInmueble(inmueble);

		// VERIFY
		assertEquals(usuario.getInmuebles().size(), 1);
	}

	// ------------------------------------------------------------
}

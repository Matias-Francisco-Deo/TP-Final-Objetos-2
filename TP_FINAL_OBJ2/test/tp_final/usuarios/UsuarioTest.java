package tp_final.usuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.reserva.Reserva;
import tp_final.reseña.Reseña;

class UsuarioTest {
	private Usuario usuario;
	private Reserva reserva;
	private Reseña reseña;
	private Reseña otraReseña;

	@BeforeEach
	void setUp() throws Exception {
		// MOCK
		reserva = mock(Reserva.class);
		reseña = mock(Reseña.class);
		otraReseña = mock(Reseña.class);

		// SUT
		usuario = new Usuario("Juan Perez", "juan.perez@gmail.com", "555-555-555");

	}

	// ------------------------------------------------------------
	// NOMBRE, EMAIL Y TELEFONO
	// ------------------------------------------------------------

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

	// ------------------------------------------------------------------------------------------
	// ACCIONES DE RESERVA
	// ------------------------------------------------------------------------------------------

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

	// ------------------------------------------------------------
	// RESEÑA COMO INQUILINO Y PROPIETARIO
	// ------------------------------------------------------------

	@Test
	void getReseñasTest() {
		assertEquals(usuario.getReseñas().size(), 0);
	}

	@Test
	void recibirReseñaTest() {
		usuario.recibirReseña(reseña);
		assertEquals(usuario.getReseñas().size(), 1);
	}

	@Test
	void getPuntajesTest() {
		when(reseña.getPuntaje()).thenReturn(5d);

		usuario.recibirReseña(reseña);
		assertEquals(usuario.getPuntajes().size(), 1);
		assertEquals(usuario.getPuntajes().get(0), 5d);
	}

	@Test
	void getPuntajePromedioTest() {
		when(reseña.getPuntaje()).thenReturn(6d);
		when(otraReseña.getPuntaje()).thenReturn(4d);

		usuario.recibirReseña(reseña);
		usuario.recibirReseña(otraReseña);
		assertEquals(usuario.getPuntajePromedio(), 5d);
	}

	@Test
	void getPuntajePromedioSinReseñasTest() {
		assertEquals(usuario.getPuntajePromedio(), 0d);
	}

	@Test
	void getComentariosTest() {
		when(reseña.getComentario()).thenReturn("Excelente");

		usuario.recibirReseña(reseña);
		assertEquals(usuario.getComentarios().size(), 1);
		assertEquals(usuario.getComentarios().get(0), "Excelente");
	}
}

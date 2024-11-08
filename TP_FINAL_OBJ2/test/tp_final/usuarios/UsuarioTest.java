package tp_final.usuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioTest {
	private Usuario usuario;

	@BeforeEach
	void setUp() throws Exception {
		usuario = new Usuario("Juan Perez", "juan.perez@gmail.com", "555-555-555");
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
}

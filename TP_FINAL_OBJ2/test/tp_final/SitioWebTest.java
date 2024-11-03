package tp_final;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SitioWebTest {

	private SitioWeb sitioWeb;
	private SistemaDeBúsqueda sistemaDeBúsqueda;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		sistemaDeBúsqueda = mock(SistemaDeBúsqueda.class);
		sitioWeb = new SitioWeb(sistemaDeBúsqueda);
	}

	@Test
	void testAñadirCategorías() {
		// exercise
		sitioWeb.añadirCategoría("Limpieza");

		// verify
		assertEquals(sitioWeb.getCategorías(), Arrays.asList("Limpieza"));
	}

	@Test
	void testGetCategorías() { // puedo quitar este test?

		// setup
		sitioWeb.añadirCategoría("Limpieza");
		sitioWeb.añadirCategoría("Cuidado");

		// verify
		assertEquals(2, sitioWeb.getCategorías().stream().count());
	}

	@Test
	void testQuitarCategorías() {

		// setup
		sitioWeb.añadirCategoría("Limpieza");

		// exercise
		sitioWeb.eliminarCategoría("Limpieza");

		// verify
		assertTrue(sitioWeb.getCategorías().isEmpty());
	}

	@Test
	void testAñadirServicios() {
		// exercise
		sitioWeb.añadirServicio("Agua");

		// verify
		assertEquals(sitioWeb.getServicios(), Arrays.asList("Agua"));
	}

	@Test
	void testGetServicios() {
		// setup
		sitioWeb.añadirServicio("Agua");
		sitioWeb.añadirServicio("Gas");

		// verify
		assertEquals(2, sitioWeb.getServicios().stream().count());
	}

	@Test
	void testQuitarServicios() {
		// setup
		sitioWeb.añadirServicio("Agua");

		// exercise
		sitioWeb.eliminarServicio("Agua");

		// verify
		assertTrue(sitioWeb.getServicios().isEmpty());
	}

	@Test
	void testAñadirTipoInmueble() {
		// exercise
		sitioWeb.añadirTipoInmueble("Departamento");

		// verify
		assertEquals(sitioWeb.getTiposInmueble(), Arrays.asList("Departamento"));
	}

	@Test
	void testGetTiposInmueble() {
		// setup
		sitioWeb.añadirTipoInmueble("Quincho");
		sitioWeb.añadirTipoInmueble("Departamento");

		// verify
		assertEquals(2, sitioWeb.getTiposInmueble().stream().count());
	}

	@Test
	void testQuitarTiposInmuebles() {
		// setup
		sitioWeb.añadirTipoInmueble("Quincho");

		// exercise
		sitioWeb.eliminarTipoInmueble("Quincho");

		// verify
		assertTrue(sitioWeb.getTiposInmueble().isEmpty());
	}

	@Test
	void testRegistrarUsuario() {
		// setup
		UsuarioPlaceholder usuario = mock(UsuarioPlaceholder.class);

		// exercise
		sitioWeb.registrar(usuario);

		// verify

		assertFalse(sitioWeb.getUsuarios().isEmpty());
	}

	@Test
	void testGetUsuarios() {
		// setup
		UsuarioPlaceholder usuario = mock(UsuarioPlaceholder.class);
		sitioWeb.registrar(usuario);

		// verify
		assertEquals(1, sitioWeb.getUsuarios().stream().count());

	}

	@Test
	void testLoguearUsuario() {
		// setup
		UsuarioPlaceholder usuario = mock(UsuarioPlaceholder.class);
		when(usuario.getNombre()).thenReturn("pepe");
		sitioWeb.registrar(usuario);

		// exercise
		UsuarioPlaceholder usuarioLogueado = sitioWeb.loguear("pepe");

		// verify
		assertEquals(usuario, usuarioLogueado);
	}

	@Test
	void testNoSePuedeLoguearUsuarioQueNoExista() {
		// setup
		UsuarioPlaceholder usuario = mock(UsuarioPlaceholder.class);
		sitioWeb.registrar(usuario);

		// verify
		assertThrows(NoSuchElementException.class, () -> sitioWeb.loguear("pepe"));
	}

	@Test
	void testObtenerInmuebles() {
		// setup
		UsuarioPlaceholder usuario = mock(UsuarioPlaceholder.class);
		sitioWeb.registrar(usuario);

		// verify

		// TO DO

	}

	@Test
	void testBuscar() {
//		List<ParámetroDeBúsqueda> parámetrosDeBúsqueda = mock(ArrayList.class);
//		when(sistemaDeBúsqueda.buscar(parámetrosDeBúsqueda)).thenReturn();
		// resolver cosas más sencillas primero
	}

	@Test
	void testObtenerAlquileres() {
		// setup
		UsuarioPlaceholder usuario = mock(UsuarioPlaceholder.class);
		sitioWeb.registrar(usuario);

		// verify

		// TO DO
	}

}

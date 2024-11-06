package tp_final.SitioWeb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.SitioWeb.ManagerDeOpciones;

class ManagerDeOpcionesTest {

	private ManagerDeOpciones sitioWeb;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		sitioWeb = new ManagerDeOpciones();
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

}

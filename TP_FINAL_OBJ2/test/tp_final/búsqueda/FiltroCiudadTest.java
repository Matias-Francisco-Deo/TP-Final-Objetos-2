package tp_final.búsqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;

class FiltroCiudadTest {

	private FiltroCiudad filtro;
	private Alquiler alquiler1;
	private Alquiler alquiler2;
	private Alquiler alquiler3;
	private List<Alquiler> alquileres;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		String ciudad = "Lanús";
		filtro = new FiltroCiudad(ciudad);
		alquiler1 = mock(Alquiler.class);

	}

	@Test
	void testFiltroCiudadFiltraPorAlquileresEnLaMismaCiudad() {
		// setup
		when(alquiler1.getCiudad()).thenReturn("Lanús");

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertTrue(filtrado);

	}

	@Test
	void testFiltroCiudadNoDejaPasarSiLaCiudadDelAlquilerEsDistinta() {
		// setup
		when(alquiler1.getCiudad()).thenReturn("Quilmes");

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertFalse(filtrado);

	}

}

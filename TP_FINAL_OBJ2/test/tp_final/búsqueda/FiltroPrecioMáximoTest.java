package tp_final.búsqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;

class FiltroPrecioMáximoTest {

	private FiltroPrecioMáximo filtro;
	private Alquiler alquiler1;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		double precioMáximo = 5000d;
		filtro = new FiltroPrecioMáximo(precioMáximo);
		alquiler1 = mock(Alquiler.class);

	}

	@Test
	void testFiltroPrecioMáximoFiltraPorPrecioDejandoLosAlquileresQueTenganPrecioMenorQueSuPrecioMáximo() {
		// setup
		when(alquiler1.getPrecio()).thenReturn(2000d);

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertTrue(filtrado);

	}

	@Test
	void testFiltroPrecioMáximoFiltraPorPrecioDejandoLosAlquileresQueIgualenSuPrecioMáximo() {
		// setup
		when(alquiler1.getPrecio()).thenReturn(5000d);

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertTrue(filtrado);

	}

	@Test
	void testFiltroNoDejaPasarSiEsMayorElPrecioQueSuPrecioMáximo() {
		// setup
		when(alquiler1.getPrecio()).thenReturn(20000d);

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertFalse(filtrado);

	}

}

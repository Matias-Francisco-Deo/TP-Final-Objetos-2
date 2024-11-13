package tp_final.búsqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;

class FiltroPrecioMínimoTest {

	private FiltroPrecioMínimo filtro;
	private Alquiler alquiler1;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		double precioMínimo = 1500d;
		filtro = new FiltroPrecioMínimo(precioMínimo);
		alquiler1 = mock(Alquiler.class);

	}

	@Test
	void testFiltroPrecioMínimoFiltraPorPrecioDejandoLosAlquileresQueIgualenSuPrecioMínimo() {
		// setup
		when(alquiler1.getPrecio()).thenReturn(1500d);

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertTrue(filtrado);

	}

	@Test
	void testFiltroPrecioMínimoFiltraPorPrecioDejandoLosAlquileresQueSuperenSuPrecioMínimo() {
		// setup
		when(alquiler1.getPrecio()).thenReturn(15000d);

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertTrue(filtrado);

	}

	@Test
	void testFiltroNoDejaPasarALosQueTenganUnPrecioInferiorASuPrecioMínimo() {
		// setup
		when(alquiler1.getPrecio()).thenReturn(100d);

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertFalse(filtrado);

	}

}

package tp_final.búsqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;

class FiltroPrecioMáximoYMinimoTest {

	private FiltroPrecioMáximoYMinimo filtro;
	private Alquiler alquiler1;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		double precioMáximo = 5000d;
		double precioMinimo = 2000d;
		filtro = new FiltroPrecioMáximoYMinimo(precioMáximo, precioMinimo);
		alquiler1 = mock(Alquiler.class);

	}

	@Test
	void testFiltroPrecioMáximoYMinimoFiltraPorPrecioDejandoLosAlquileresQueTenganPrecioDentroDelRangoDado() {
		// setup
		when(alquiler1.getPrecioBase()).thenReturn(3000d);

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertTrue(filtrado);

	}

	@Test
	void testFiltroPrecioMáximoYMinimoFiltraPorPrecioDejandoLosAlquileresQueIgualenSuPrecioMáximo() {
		// setup
		when(alquiler1.getPrecioBase()).thenReturn(5000d);

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertTrue(filtrado);

	}

	@Test
	void testFiltroPrecioMáximoYMinimoFiltraPorPrecioDejandoLosAlquileresQueIgualenSuPrecioMinimo() {
		// setup
		when(alquiler1.getPrecioBase()).thenReturn(2000d);

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertTrue(filtrado);

	}

	@Test
	void testFiltroNoDejaPasarSiEsMayorElPrecioQueSuPrecioMáximo() {
		// setup
		when(alquiler1.getPrecioBase()).thenReturn(20000d);

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertFalse(filtrado);

	}

	@Test
	void testFiltroNoDejaPasarSiEsMenorElPrecioQueSuPrecioMinimo() {
		// setup
		when(alquiler1.getPrecioBase()).thenReturn(1000d);

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertFalse(filtrado);

	}

}

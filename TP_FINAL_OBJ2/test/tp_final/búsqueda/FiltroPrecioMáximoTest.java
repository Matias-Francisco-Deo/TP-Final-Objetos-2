package tp_final.búsqueda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;

class FiltroPrecioMáximoTest {

	private FiltroPrecioMáximo filtro;
	private Alquiler alquiler1;
	private Alquiler alquiler2;
	private Alquiler alquiler3;
	private List<Alquiler> alquileres;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		double precioMáximo = 5000d;
		filtro = new FiltroPrecioMáximo(precioMáximo);
		alquiler1 = mock(Alquiler.class);
		alquiler2 = mock(Alquiler.class);
		alquiler3 = mock(Alquiler.class);
		alquileres = Arrays.asList(alquiler1, alquiler2, alquiler3);

	}

	@Test
	void testFiltroPrecioMáximoFiltraPorPrecioDejandoLosAlquileresQueSuperenOIgualenSuPrecioMáximo() {
		// setup
		when(alquiler1.getPrecio()).thenReturn(20000d);
		when(alquiler2.getPrecio()).thenReturn(1500d);
		when(alquiler3.getPrecio()).thenReturn(5000d);

		// exercise
		List<Alquiler> filtrado = filtro.filtrar(alquileres);

		// verify
		assertEquals(Arrays.asList(alquiler2, alquiler3), filtrado);

	}

	@Test
	void testFiltroNoDevuelveNadaSiNoCumpleNingúnAlquiler() {
		// setup
		when(alquiler1.getPrecio()).thenReturn(20000d);
		when(alquiler2.getPrecio()).thenReturn(13000d);
		when(alquiler3.getPrecio()).thenReturn(5001d);

		// exercise
		List<Alquiler> filtrado = filtro.filtrar(alquileres);

		// verify
		assertEquals(Arrays.asList(), filtrado);

	}

}

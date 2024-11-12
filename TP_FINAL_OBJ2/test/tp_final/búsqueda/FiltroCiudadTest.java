package tp_final.búsqueda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
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
		alquiler2 = mock(Alquiler.class);
		alquiler3 = mock(Alquiler.class);
		alquileres = Arrays.asList(alquiler1, alquiler2, alquiler3);

	}

	@Test
	void testFiltroCiudadFiltraPorAlquileresEnLaMismaCiudad() {
		// setup
		when(alquiler1.getCiudad()).thenReturn("Lanús");
		when(alquiler2.getCiudad()).thenReturn("Lanús");
		when(alquiler3.getCiudad()).thenReturn("Quilmes");

		// exercise
		List<Alquiler> filtrado = filtro.filtrar(alquileres);

		// verify
		assertEquals(Arrays.asList(alquiler1, alquiler2), filtrado);

	}

	@Test
	void testFiltroNoDevuelveNadaSiNoCumpleNingúnAlquiler() {
		// setup
		when(alquiler1.getCiudad()).thenReturn("Avellaneda");
		when(alquiler2.getCiudad()).thenReturn("El Pato");
		when(alquiler3.getCiudad()).thenReturn("Quilmes");

		// exercise
		List<Alquiler> filtrado = filtro.filtrar(alquileres);

		// verify
		assertEquals(Arrays.asList(), filtrado);

	}

}

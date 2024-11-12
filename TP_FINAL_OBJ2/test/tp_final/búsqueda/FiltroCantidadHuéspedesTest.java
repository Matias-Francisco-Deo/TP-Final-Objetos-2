package tp_final.búsqueda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;

class FiltroCantidadHuéspedesTest {

	private FiltroCantidadHuéspedes filtro;
	private Alquiler alquiler1;
	private Alquiler alquiler2;
	private Alquiler alquiler3;
	private List<Alquiler> alquileres;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		int cantidadMínimaHuéspedes = 5;
		filtro = new FiltroCantidadHuéspedes(cantidadMínimaHuéspedes);
		alquiler1 = mock(Alquiler.class);
		alquiler2 = mock(Alquiler.class);
		alquiler3 = mock(Alquiler.class);
		alquileres = Arrays.asList(alquiler1, alquiler2, alquiler3);

	}

	@Test
	void testFiltroDaTodosLosAlquileresQueCumplanConAlMenosLaCantidadDeHuéspedesMínima() {
		// setup
		when(alquiler1.getCantidadHuéspedes()).thenReturn(20);
		when(alquiler2.getCantidadHuéspedes()).thenReturn(5);
		when(alquiler3.getCantidadHuéspedes()).thenReturn(2);

		// exercise
		List<Alquiler> filtrado = filtro.filtrar(alquileres);

		// verify
		assertEquals(Arrays.asList(alquiler1, alquiler2), filtrado);

	}

	@Test
	void testFiltroNoDevuelveNadaSiNoCumpleNingúnAlquiler() {
		// setup
		when(alquiler1.getCantidadHuéspedes()).thenReturn(2);
		when(alquiler2.getCantidadHuéspedes()).thenReturn(2);
		when(alquiler3.getCantidadHuéspedes()).thenReturn(2);

		// exercise
		List<Alquiler> filtrado = filtro.filtrar(alquileres);

		// verify
		assertEquals(Arrays.asList(), filtrado);

	}

}

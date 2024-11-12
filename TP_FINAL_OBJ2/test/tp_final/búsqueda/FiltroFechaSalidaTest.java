package tp_final.búsqueda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;

class FiltroFechaSalidaTest {

	private FiltroFechaSalida filtro;
	private Alquiler alquiler1;
	private Alquiler alquiler2;
	private Alquiler alquiler3;
	private List<Alquiler> alquileres;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		LocalDate fechaSalida = LocalDate.of(2025, 1, 10);
		filtro = new FiltroFechaSalida(fechaSalida);
		alquiler1 = mock(Alquiler.class);
		alquiler2 = mock(Alquiler.class);
		alquiler3 = mock(Alquiler.class);
		alquileres = Arrays.asList(alquiler1, alquiler2, alquiler3);

	}

	@Test
	void testFiltroFechaSalidaFiltraPorAlquileresConLaMismaFechaDeSalida() {
		// setup
		when(alquiler1.getFechaSalida()).thenReturn(LocalDate.of(2025, 1, 10));
		when(alquiler2.getFechaSalida()).thenReturn(LocalDate.of(2025, 2, 20));
		when(alquiler3.getFechaSalida()).thenReturn(LocalDate.of(2025, 1, 13));

		// exercise
		List<Alquiler> filtrado = filtro.filtrar(alquileres);

		// verify
		assertEquals(Arrays.asList(alquiler1), filtrado);

	}

	@Test
	void testFiltroNoDevuelveNadaSiNoCumpleNingúnAlquiler() {
		// setup
		when(alquiler1.getFechaSalida()).thenReturn(LocalDate.of(2024, 12, 20));
		when(alquiler2.getFechaSalida()).thenReturn(LocalDate.of(2025, 1, 20));
		when(alquiler3.getFechaSalida()).thenReturn(LocalDate.of(2024, 11, 9));

		// exercise
		List<Alquiler> filtrado = filtro.filtrar(alquileres);

		// verify
		assertEquals(Arrays.asList(), filtrado);

	}

}

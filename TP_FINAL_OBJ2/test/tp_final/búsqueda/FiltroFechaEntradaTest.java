package tp_final.búsqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;

class FiltroFechaEntradaTest {

	private FiltroFechaEntrada filtro;
	private Alquiler alquiler1;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		LocalDate fechaEntrada = LocalDate.of(2024, 12, 15);
		filtro = new FiltroFechaEntrada(fechaEntrada);
		alquiler1 = mock(Alquiler.class);

	}

	@Test
	void testFiltroFechaEntradaFiltraPorAlquileresConLaMismaFechaDeEntrada() {
		// setup
		when(alquiler1.getFechaEntrada()).thenReturn(LocalDate.of(2024, 12, 15));

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertTrue(filtrado);

	}

	@Test
	void testFiltroNoDejaPasarSiLaFechaEsDiferente() {
		// setup
		when(alquiler1.getFechaEntrada()).thenReturn(LocalDate.of(2024, 11, 30));

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertFalse(filtrado);

	}

}

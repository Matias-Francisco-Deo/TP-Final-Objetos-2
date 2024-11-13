package tp_final.búsqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;

class FiltroFechaSalidaTest {

	private FiltroFechaSalida filtro;
	private Alquiler alquiler1;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		LocalDate fechaSalida = LocalDate.of(2024, 12, 15);
		filtro = new FiltroFechaSalida(fechaSalida);
		alquiler1 = mock(Alquiler.class);

	}

	@Test
	void testFiltroFechaSalidaFiltraPorAlquileresConLaMismaFechaDeSalida() {
		// setup
		when(alquiler1.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 12, 15));

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertTrue(filtrado);

	}

	@Test
	void testFiltroNoDejaPasarSiLaFechaEsDiferente() {
		// setup
		when(alquiler1.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 30));

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertFalse(filtrado);

	}

}

package tp_final.búsqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;
import tp_final.alquiler.ManagerDeAlquiler;

class FiltroFechaEntradaYSalidaTest {

	private FiltroFechaEntradaYSalida filtro;
	private Alquiler alquiler1;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		LocalDate fechaEntrada = LocalDate.of(2024, 12, 15);
		LocalDate fechaSalida = LocalDate.of(2024, 12, 15);

		filtro = new FiltroFechaEntradaYSalida(fechaEntrada, fechaSalida);
		alquiler1 = mock(Alquiler.class);

	}

	@Test
	void testFiltroFechaEntradaFiltraPorAlquileresConLaMismaFechaDeEntrada() {
		// setup
		LocalDate fechaEntrada = filtro.getFechaEntrada();
		LocalDate fechaSalida = filtro.getFechaSalida();

		ManagerDeAlquiler manager = mock(ManagerDeAlquiler.class);
		when(alquiler1.getManager()).thenReturn(manager);
		when(manager.elRangoEstaOcupadoPorAlgunaReserva(fechaEntrada, fechaSalida)).thenReturn(true);

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertTrue(filtrado);

	}

	@Test
	void testFiltroNoDejaPasarSiLaFechaEsDiferente() {
		// setup
		LocalDate fechaEntrada = filtro.getFechaEntrada();
		LocalDate fechaSalida = filtro.getFechaSalida();

		ManagerDeAlquiler manager = mock(ManagerDeAlquiler.class);
		when(alquiler1.getManager()).thenReturn(manager);
		when(manager.elRangoEstaOcupadoPorAlgunaReserva(fechaEntrada, fechaSalida)).thenReturn(false);

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertFalse(filtrado);

	}

}

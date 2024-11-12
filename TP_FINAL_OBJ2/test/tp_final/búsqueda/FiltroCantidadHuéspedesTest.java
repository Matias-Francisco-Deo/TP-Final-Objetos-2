package tp_final.búsqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;

class FiltroCantidadHuéspedesTest {

	private FiltroCantidadHuéspedes filtro;
	private Alquiler alquiler1;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		int cantidadMínimaHuéspedes = 5;
		filtro = new FiltroCantidadHuéspedes(cantidadMínimaHuéspedes);
		alquiler1 = mock(Alquiler.class);

	}

	@Test
	void testFiltroSólamentePasaComoVálidoLosAlquileresQueTenganComoMínimoLaCantidadEstablecidaDeHuéspedesEnÉl() {
		// setup
		when(alquiler1.getCantidadHuéspedes()).thenReturn(20);

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertTrue(filtrado);

	}

	@Test
	void testFiltroSólamentePasaComoVálidoLosAlquileresQueTenganLaMismaCantidadDeHuéspedesQueÉl() {
		// setup
		when(alquiler1.getCantidadHuéspedes()).thenReturn(5);

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertTrue(filtrado);

	}

	@Test
	void testFiltroNoDejaPasarSiNoEsMayorOIgualQueSuCantidadDeHuéspedes() {
		// setup
		when(alquiler1.getCantidadHuéspedes()).thenReturn(4);

		// exercise
		boolean filtrado = filtro.esVálido(alquiler1);

		// verify
		assertFalse(filtrado);

	}

}

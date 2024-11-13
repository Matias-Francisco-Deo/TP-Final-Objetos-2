package tp_final.búsqueda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;

class FiltroDefaultTest {

	private FiltroDefault filtroDefault;
	private FiltroCiudad ciudad;
	private FiltroFechaEntrada fechaEntrada;
	private FiltroFechaSalida fechaSalida;
	private List<Alquiler> alquileres;
	private Alquiler alquiler1;
	private Alquiler alquiler2;
	private Alquiler alquiler3;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		ciudad = mock(FiltroCiudad.class);
		fechaEntrada = mock(FiltroFechaEntrada.class);
		fechaSalida = mock(FiltroFechaSalida.class);
		alquiler1 = mock(Alquiler.class);
		alquiler2 = mock(Alquiler.class);
		alquiler3 = mock(Alquiler.class);
		alquileres = Arrays.asList(alquiler1, alquiler2, alquiler3);
		filtroDefault = new FiltroDefault(ciudad, fechaEntrada, fechaSalida);
	}

	@Test
	void testFiltroDefaultPermiteAplicarVariosFiltrosDondeCadaUnoEsUnaIntersecciónEntreLosResultadosDelOtro() {
		// setup

		when(ciudad.esVálido(alquiler1)).thenReturn(false);
		when(fechaEntrada.esVálido(alquiler1)).thenReturn(true);
		when(fechaSalida.esVálido(alquiler1)).thenReturn(true);

		// dejan pasar alquiler2
		when(ciudad.esVálido(alquiler2)).thenReturn(true);
		when(fechaEntrada.esVálido(alquiler2)).thenReturn(true);
		when(fechaSalida.esVálido(alquiler2)).thenReturn(true);

		when(ciudad.esVálido(alquiler3)).thenReturn(true);
		when(fechaEntrada.esVálido(alquiler3)).thenReturn(false);
		when(fechaSalida.esVálido(alquiler3)).thenReturn(true);

		List<Alquiler> alquileresEsperados = Arrays.asList(alquiler2);

		// exercise
		List<Alquiler> alquileresFiltrados = filtroDefault.filtrar(alquileres);

		// verify
		assertEquals(alquileresEsperados, alquileresFiltrados);
	}

	@Test
	void testFiltroDefaultPermiteAplicarVariosFiltrosDondeSiNoHayUnAlquilerEnComúnDevuelveVacío() {
		// setup
		when(ciudad.esVálido(alquiler1)).thenReturn(false);
		when(fechaEntrada.esVálido(alquiler1)).thenReturn(false);
		when(fechaSalida.esVálido(alquiler1)).thenReturn(true);

		when(ciudad.esVálido(alquiler2)).thenReturn(true);
		when(fechaEntrada.esVálido(alquiler2)).thenReturn(false);
		when(fechaSalida.esVálido(alquiler2)).thenReturn(true);

		when(ciudad.esVálido(alquiler3)).thenReturn(true);
		when(fechaEntrada.esVálido(alquiler3)).thenReturn(true);
		when(fechaSalida.esVálido(alquiler3)).thenReturn(false);

		List<Alquiler> alquileresEsperados = Arrays.asList();

		// exercise
		List<Alquiler> alquileresFiltrados = filtroDefault.filtrar(alquileres);

		// verify
		assertEquals(alquileresEsperados, alquileresFiltrados);
	}

	@Test
	void testFiltroDefaultPermiteAñadirFiltrosAdemásDeLosPorDefecto() {
		// setup

		when(ciudad.esVálido(alquiler1)).thenReturn(true);
		when(fechaEntrada.esVálido(alquiler1)).thenReturn(true);
		when(fechaSalida.esVálido(alquiler1)).thenReturn(true);

		when(ciudad.esVálido(alquiler2)).thenReturn(true);
		when(fechaEntrada.esVálido(alquiler2)).thenReturn(true);
		when(fechaSalida.esVálido(alquiler2)).thenReturn(true);

		when(ciudad.esVálido(alquiler3)).thenReturn(true);
		when(fechaEntrada.esVálido(alquiler3)).thenReturn(true);
		when(fechaSalida.esVálido(alquiler3)).thenReturn(true);

		FiltroCantidadHuéspedes filtroCantidadHuéspedes = mock(FiltroCantidadHuéspedes.class);
		when(filtroCantidadHuéspedes.esVálido(alquiler1)).thenReturn(true);
		when(filtroCantidadHuéspedes.esVálido(alquiler2)).thenReturn(false);
		when(filtroCantidadHuéspedes.esVálido(alquiler3)).thenReturn(false);

		List<Alquiler> alquileresEsperados = Arrays.asList(alquiler1);

		// exercise
		filtroDefault.añadirFiltro(filtroCantidadHuéspedes);
		List<Alquiler> alquileresFiltrados = filtroDefault.filtrar(alquileres);

		// verify
		assertEquals(alquileresEsperados, alquileresFiltrados);
	}

}

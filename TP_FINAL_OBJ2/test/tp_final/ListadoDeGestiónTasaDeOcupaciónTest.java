package tp_final;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.SitioWeb.ManagerDeOpciones;

class ListadoDeGestiónTasaDeOcupaciónTest {

	private ManagerDeOpciones sitioWeb;
	private TasaDeOcupación listado;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		sitioWeb = mock(ManagerDeOpciones.class);
		AlquilerPlaceholder alquiler = mock(AlquilerPlaceholder.class);
		InmueblePlaceholder inmueble = mock(InmueblePlaceholder.class);
		when(alquiler.esLibre()).thenReturn(true).thenReturn(false).thenReturn(false);
		when(sitioWeb.getAlquileres()).thenReturn(Arrays.asList(alquiler, alquiler, alquiler)); // 2 alquileres ocupados
		when(sitioWeb.getInmuebles()).thenReturn(Arrays.asList(inmueble, inmueble, inmueble, inmueble)); // 4 inmuebles

		listado = new TasaDeOcupación();
	}

	@Test
	void testRealizarListadoDeAlquileresLibresDevuelveSólamenteAquellosAlquileresLibres() {

		// exercise
		String informe = listado.realizar(sitioWeb);

		// verify
		assertEquals("Tasa de ocupación: 0.5", informe);
	}

}

package tp_final;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.SitioWeb.ManagerDeOpciones;

class ListadoDeGestiónInmueblesLibresTest {

	private InmueblesLibres listado;
	private ManagerDeOpciones sitioWeb;
	private InquilinoPlaceholder inquilino;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		sitioWeb = mock(ManagerDeOpciones.class);
		AlquilerPlaceholder alquiler = mock(AlquilerPlaceholder.class);
		when(alquiler.esLibre()).thenReturn(true).thenReturn(true).thenReturn(false);
		when(sitioWeb.getAlquileres()).thenReturn(Arrays.asList(alquiler, alquiler, alquiler)); // 3 alquileres, 2
																								// libres, 1 no

		listado = new InmueblesLibres();
	}

	@Test
	void testRealizarListadoDeAlquileresLibresDevuelveSólamenteAquellosAlquileresLibres() {

		// exercise
		Object informe = listado.realizar(sitioWeb);

		// verify
		assertEquals(2, ((List<AlquilerPlaceholder>) informe).size());
	}

}

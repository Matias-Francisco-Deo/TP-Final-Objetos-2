package tp_final.sitioweb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.AlquilerPlaceholder;
import tp_final.InmueblePlaceholder;
import tp_final.InquilinoPlaceholder;
import tp_final.PropietarioPlaceholder;
import tp_final.sitioweb.SitioWeb;

class SitioWebTest {

	private SitioWeb sitioWeb;

	@BeforeEach
	void setUp() throws Exception {
		sitioWeb = new SitioWeb();
	}

	@Test
	void testRegistarInquilinos() {
		// setup
		InquilinoPlaceholder inquilino = mock(InquilinoPlaceholder.class);
		// exercise
		sitioWeb.registrarInquilino(inquilino);
		// verify
		assertEquals(1, sitioWeb.getInquilinos().size());
	}

	@Test
	void testGetInquilinos() {
		// setup
		InquilinoPlaceholder inquilino = mock(InquilinoPlaceholder.class);
		// exercise
		sitioWeb.registrarInquilino(inquilino);
		// verify
		List<InquilinoPlaceholder> inquilinosEsperados = Arrays.asList(inquilino);
		assertEquals(inquilinosEsperados, sitioWeb.getInquilinos());
	}

	@Test
	void testRegistarPropietarios() {
		// setup
		PropietarioPlaceholder propietario = mock(PropietarioPlaceholder.class);
		// exercise
		sitioWeb.registrarPropietario(propietario);
		// verify
		assertEquals(1, sitioWeb.getPropietarios().size());
	}

	@Test
	void testGetPropietarios() {
		// setup
		PropietarioPlaceholder propietario = mock(PropietarioPlaceholder.class);
		// exercise
		sitioWeb.registrarPropietario(propietario);
		// verify
		List<PropietarioPlaceholder> propietariosEsperados = Arrays.asList(propietario);
		assertEquals(propietariosEsperados, sitioWeb.getPropietarios());
	}

	@Test
	void testGetAlquileres() {
		// setup
		PropietarioPlaceholder propietario = mock(PropietarioPlaceholder.class);
		sitioWeb.registrarPropietario(propietario);
		AlquilerPlaceholder alquiler = mock(AlquilerPlaceholder.class);
		when(propietario.getAlquileres()).thenReturn(Arrays.asList(alquiler));

		// verify
		List<AlquilerPlaceholder> alquileresEsperados = Arrays.asList(alquiler);
		assertEquals(alquileresEsperados, sitioWeb.getAlquileres());
	}

	@Test
	void testGetInmuebles() {
		// setup
		PropietarioPlaceholder propietario = mock(PropietarioPlaceholder.class);
		sitioWeb.registrarPropietario(propietario);
		InmueblePlaceholder inmueble = mock(InmueblePlaceholder.class);
		when(propietario.getInmuebles()).thenReturn(Arrays.asList(inmueble));

		// verify
		List<InmueblePlaceholder> inmueblesEsperados = Arrays.asList(inmueble);
		assertEquals(inmueblesEsperados, sitioWeb.getInmuebles());

	}

}

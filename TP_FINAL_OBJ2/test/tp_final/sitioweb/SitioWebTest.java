package tp_final.sitioweb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;
import tp_final.búsqueda.FiltroDefault;
import tp_final.inmueble.Inmueble;
import tp_final.usuarios.Usuario;

class SitioWebTest {

	private SitioWeb sitioWeb;

	@BeforeEach
	void setUp() throws Exception {
		sitioWeb = new SitioWeb();
	}

	@Test
	void testRegistarUsuarios() {
		// setup
		Usuario inquilino = mock(Usuario.class);
		// exercise
		sitioWeb.registrarUsuario(inquilino);
		// verify
		assertEquals(1, sitioWeb.getUsuarios().size());
	}

	@Test
	void testGetUsuarios() {
		// setup
		Usuario inquilino = mock(Usuario.class);
		// exercise
		sitioWeb.registrarUsuario(inquilino);
		// verify
		List<Usuario> inquilinosEsperados = Arrays.asList(inquilino);
		assertEquals(inquilinosEsperados, sitioWeb.getUsuarios());
	}

	@Test
	void testGetAlquileres() {
		// setup
		Usuario propietario = mock(Usuario.class);
		sitioWeb.registrarUsuario(propietario);
		Alquiler alquiler = mock(Alquiler.class);
		when(propietario.getAlquileres()).thenReturn(Arrays.asList(alquiler));

		// verify
		List<Alquiler> alquileresEsperados = Arrays.asList(alquiler);
		assertEquals(alquileresEsperados, sitioWeb.getAlquileres());
	}

	@Test
	void testGetInmuebles() {
		// setup
		Usuario propietario = mock(Usuario.class);
		sitioWeb.registrarUsuario(propietario);
		Inmueble inmueble = mock(Inmueble.class);
		when(propietario.getInmuebles()).thenReturn(Arrays.asList(inmueble));

		// verify
		List<Inmueble> inmueblesEsperados = Arrays.asList(inmueble);
		assertEquals(inmueblesEsperados, sitioWeb.getInmuebles());

	}

	@Test
	void testBuscarPorFiltros() {
		// setup

		// alquileres
		Alquiler alquiler1 = mock(Alquiler.class);
		Alquiler alquiler2 = mock(Alquiler.class);

		// usuario que contiene alquileres, del cual se obtienen sus alquileres para la
		// búsqueda luego
		Usuario propietario = mock(Usuario.class);
		when(propietario.getAlquileres()).thenReturn(Arrays.asList(alquiler1, alquiler2));
		sitioWeb.registrarUsuario(propietario);

		// filtro de búsqueda
		FiltroDefault filtro = mock(FiltroDefault.class);
		when(filtro.filtrar(Arrays.asList(alquiler1, alquiler2))).thenReturn(Arrays.asList(alquiler1));
		List<Alquiler> alquileresEsperados = Arrays.asList(alquiler1);

		// exercise
		List<Alquiler> búsqueda = sitioWeb.buscar(filtro);

		// verify
		assertEquals(alquileresEsperados, búsqueda);

	}

}

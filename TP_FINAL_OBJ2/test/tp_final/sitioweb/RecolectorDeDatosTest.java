package tp_final.sitioweb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.alquiler.Alquiler;
import tp_final.alquiler.ManagerDeAlquiler;
import tp_final.inmueble.Inmueble;
import tp_final.usuarios.Usuario;

class RecolectorDeDatosTest {

	private SitioWeb sitioWeb;
	private RecolectorDeDatos recolectorDeDatos;
	private Alquiler alquiler;

	@BeforeEach
	void setUp() throws Exception {
		sitioWeb = mock(SitioWeb.class);
		recolectorDeDatos = new RecolectorDeDatos(sitioWeb);
		alquiler = mock(Alquiler.class);
	}

	@Test
	void testRealizarTopTenDevuelveLos10InquilinosQueMásHanAlquilado() {
		// setup
		Usuario inquilino1 = mock(Usuario.class);
		Usuario inquilino2 = mock(Usuario.class);
		Usuario inquilino3 = mock(Usuario.class);
		Usuario inquilino4 = mock(Usuario.class);
		Usuario inquilino5 = mock(Usuario.class);
		Usuario inquilino6 = mock(Usuario.class);
		Usuario inquilino7 = mock(Usuario.class);
		Usuario inquilino8 = mock(Usuario.class);
		Usuario inquilino9 = mock(Usuario.class);
		Usuario inquilino10 = mock(Usuario.class);
		Usuario inquilino11 = mock(Usuario.class);

		when(inquilino1.getCantidadDeReservas()).thenReturn(1);
		when(inquilino2.getCantidadDeReservas()).thenReturn(2);
		when(inquilino3.getCantidadDeReservas()).thenReturn(3);
		when(inquilino4.getCantidadDeReservas()).thenReturn(4);
		when(inquilino5.getCantidadDeReservas()).thenReturn(5);
		when(inquilino6.getCantidadDeReservas()).thenReturn(6);
		when(inquilino7.getCantidadDeReservas()).thenReturn(7);
		when(inquilino8.getCantidadDeReservas()).thenReturn(8);
		when(inquilino9.getCantidadDeReservas()).thenReturn(9);
		when(inquilino10.getCantidadDeReservas()).thenReturn(10);
		when(inquilino11.getCantidadDeReservas()).thenReturn(11);

		when(sitioWeb.getUsuarios()).thenReturn(Arrays.asList(inquilino1, inquilino2, inquilino3, inquilino4,
				inquilino5, inquilino6, inquilino7, inquilino8, inquilino9, inquilino10, inquilino11));

		// exercise
		List<Usuario> informe = recolectorDeDatos.getTopTenInquilinos();

		// verify
		List<Usuario> inquilinosEsperados = Arrays.asList(inquilino11, inquilino10, inquilino9, inquilino8, inquilino7,
				inquilino6, inquilino5, inquilino4, inquilino3, inquilino2);

		assertEquals(inquilinosEsperados, informe); // todos menos inquilino1
	}

	@Test
	void testRealizarTopTenDevuelveLosÚnicosQueHayCuandoNoSeLlegaA10EnOrdenAscendente() {
		// setup
		Usuario inquilino1 = mock(Usuario.class);
		Usuario inquilino2 = mock(Usuario.class);
		Usuario inquilino3 = mock(Usuario.class);
		Usuario inquilino4 = mock(Usuario.class);

		when(inquilino1.getCantidadDeReservas()).thenReturn(1);
		when(inquilino2.getCantidadDeReservas()).thenReturn(2);
		when(inquilino3.getCantidadDeReservas()).thenReturn(3);
		when(inquilino4.getCantidadDeReservas()).thenReturn(4);

		when(sitioWeb.getUsuarios()).thenReturn(Arrays.asList(inquilino1, inquilino2, inquilino3, inquilino4));

		// exercise
		List<Usuario> informe = recolectorDeDatos.getTopTenInquilinos();

		// verify
		List<Usuario> inquilinosEsperados = Arrays.asList(inquilino4, inquilino3, inquilino2, inquilino1);

		assertEquals(inquilinosEsperados, informe); // todos menos inquilino1
	}

	@Test
	void testRealizarTopTenDevuelveVacíoSiNoHayInquilinos() {
		// setup
		when(sitioWeb.getUsuarios()).thenReturn(Arrays.asList());

		// exercise
		List<Usuario> informe = recolectorDeDatos.getTopTenInquilinos();

		// verify
		assertTrue(informe.isEmpty());
	}

	@Test
	void testRealizarListadoDeInmueblesLibresDevuelveSólamenteAquellosInmueblesCuyoAlquilerEsLibre() {
		// setup
		ManagerDeAlquiler manager = mock(ManagerDeAlquiler.class);
		when(alquiler.getManager()).thenReturn(manager);
		when(manager.estaLibreElAlquiler()).thenReturn(true).thenReturn(true).thenReturn(false);
		when(sitioWeb.getAlquileres()).thenReturn(Arrays.asList(alquiler, alquiler, alquiler)); // 3 alquileres, 2
																								// libres, 1 no

		// exercise
		List<Inmueble> informe = recolectorDeDatos.getInmueblesLibres();

		// verify
		assertEquals(2, informe.size());
	}

	@Test
	void testRealizarListadoDeInmueblesLibresSiNoTieneAlquileresNoDevuelveNada() {
		// setup
		when(sitioWeb.getAlquileres()).thenReturn(Arrays.asList()); // 3 alquileres, 2
																	// libres, 1 no

		// exercise
		List<Inmueble> informe = recolectorDeDatos.getInmueblesLibres();

		// verify
		assertTrue(informe.isEmpty());
	}

	@Test
	void testRealizarListadoDeInmueblesLibresNoDevuelveNadaSiNoHayAlquileresLibres() {
		// setup
		ManagerDeAlquiler manager = mock(ManagerDeAlquiler.class);
		when(alquiler.getManager()).thenReturn(manager);
		when(manager.estaLibreElAlquiler()).thenReturn(false).thenReturn(false).thenReturn(false);
		when(sitioWeb.getAlquileres()).thenReturn(Arrays.asList(alquiler, alquiler, alquiler)); // 3 alquileres, 2
																								// libres, 1 no

		// exercise
		List<Inmueble> informe = recolectorDeDatos.getInmueblesLibres();

		// verify
		assertTrue(informe.isEmpty());
	}

	@Test
	void testRealizarListadoDeInmueblesLibresDevuelveTodosSiTodosSonLibres() {
		// setup
		ManagerDeAlquiler manager = mock(ManagerDeAlquiler.class);
		when(alquiler.getManager()).thenReturn(manager);
		when(manager.estaLibreElAlquiler()).thenReturn(true).thenReturn(true).thenReturn(true);
		when(sitioWeb.getAlquileres()).thenReturn(Arrays.asList(alquiler, alquiler, alquiler)); // 3 alquileres, 2
																								// libres, 1 no

		// exercise
		List<Inmueble> informe = recolectorDeDatos.getInmueblesLibres();

		// verify
		assertEquals(3, informe.size());
	}

	@Test
	void testRealizarListadoDeTasaDeOcupaciónEsInmueblesAlquiladosSobreTotalDeInmuebles() {
		// setup
		Inmueble inmueble = mock(Inmueble.class);

		ManagerDeAlquiler manager = mock(ManagerDeAlquiler.class);
		when(alquiler.getManager()).thenReturn(manager);
		when(manager.estaLibreElAlquiler()).thenReturn(true).thenReturn(false).thenReturn(false);
		when(sitioWeb.getAlquileres()).thenReturn(Arrays.asList(alquiler, alquiler, alquiler)); // 2
																								// alquileres
																								// ocupados
		when(sitioWeb.getInmuebles()).thenReturn(Arrays.asList(inmueble, inmueble, inmueble, inmueble)); // 4 inmuebles

		// exercise
		double informe = recolectorDeDatos.getTasaDeOcupación();

		// verify
		assertEquals(0.5d, informe);
	}

	@Test
	void testRealizarListadoDeTasaDeOcupaciónEs0SiNingúnInmuebleFueAlquilado() {
		// setup
		Inmueble inmueble = mock(Inmueble.class);
		ManagerDeAlquiler manager = mock(ManagerDeAlquiler.class);
		when(alquiler.getManager()).thenReturn(manager);
		when(manager.estaLibreElAlquiler()).thenReturn(true).thenReturn(true).thenReturn(true);
		when(sitioWeb.getAlquileres()).thenReturn(Arrays.asList(alquiler, alquiler, alquiler)); // 3 alquileres ocupados
		when(sitioWeb.getInmuebles()).thenReturn(Arrays.asList(inmueble, inmueble, inmueble, inmueble)); // 4 inmuebles

		// exercise
		double informe = recolectorDeDatos.getTasaDeOcupación();

		// verify
		assertEquals(0, informe);
	}

}

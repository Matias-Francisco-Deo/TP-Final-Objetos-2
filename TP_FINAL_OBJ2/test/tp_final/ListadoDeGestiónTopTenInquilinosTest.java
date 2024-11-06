package tp_final;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.SitioWeb.ManagerDeOpciones;

class ListadoDeGestiónTopTenInquilinosTest {

	private TopTenInquilinos listado;
	private ManagerDeOpciones sitioWeb;
	private InquilinoPlaceholder inquilino;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		sitioWeb = mock(ManagerDeOpciones.class);
		inquilino = mock(InquilinoPlaceholder.class);

		listado = new TopTenInquilinos();
	}

	@Test
	void testRealizarTopTenDevuelveLos10InquilinosQueMásHanAlquilado() {
		// setup
		InquilinoPlaceholder inquilino1 = mock(InquilinoPlaceholder.class);
		InquilinoPlaceholder inquilino2 = mock(InquilinoPlaceholder.class);
		InquilinoPlaceholder inquilino3 = mock(InquilinoPlaceholder.class);
		InquilinoPlaceholder inquilino4 = mock(InquilinoPlaceholder.class);
		InquilinoPlaceholder inquilino5 = mock(InquilinoPlaceholder.class);
		InquilinoPlaceholder inquilino6 = mock(InquilinoPlaceholder.class);
		InquilinoPlaceholder inquilino7 = mock(InquilinoPlaceholder.class);
		InquilinoPlaceholder inquilino8 = mock(InquilinoPlaceholder.class);
		InquilinoPlaceholder inquilino9 = mock(InquilinoPlaceholder.class);
		InquilinoPlaceholder inquilino10 = mock(InquilinoPlaceholder.class);
		InquilinoPlaceholder inquilino11 = mock(InquilinoPlaceholder.class);

		when(inquilino1.contarInmueblesAlquilados()).thenReturn(1);
		when(inquilino2.contarInmueblesAlquilados()).thenReturn(2);
		when(inquilino3.contarInmueblesAlquilados()).thenReturn(3);
		when(inquilino4.contarInmueblesAlquilados()).thenReturn(4);
		when(inquilino5.contarInmueblesAlquilados()).thenReturn(5);
		when(inquilino6.contarInmueblesAlquilados()).thenReturn(6);
		when(inquilino7.contarInmueblesAlquilados()).thenReturn(7);
		when(inquilino8.contarInmueblesAlquilados()).thenReturn(8);
		when(inquilino9.contarInmueblesAlquilados()).thenReturn(9);
		when(inquilino10.contarInmueblesAlquilados()).thenReturn(10);
		when(inquilino11.contarInmueblesAlquilados()).thenReturn(11);

		when(sitioWeb.getInquilinos()).thenReturn(Arrays.asList(inquilino1, inquilino2, inquilino3, inquilino4,
				inquilino5, inquilino6, inquilino7, inquilino8, inquilino9, inquilino10, inquilino11));

		// exercise
		Object informe = listado.realizar(sitioWeb);

		// verify
		List<InquilinoPlaceholder> inquilinosEsperados = Arrays.asList(inquilino11, inquilino10, inquilino9, inquilino8,
				inquilino7, inquilino6, inquilino5, inquilino4, inquilino3, inquilino2);

		assertEquals(inquilinosEsperados, informe); // todos menos inquilino1
	}

	@Test
	void testRealizarTopTenDevuelveLosÚnicosQueHayCuandoNoSeLlegaA10EnOrdenAscendente() {
		// setup
		InquilinoPlaceholder inquilino1 = mock(InquilinoPlaceholder.class);
		InquilinoPlaceholder inquilino2 = mock(InquilinoPlaceholder.class);
		InquilinoPlaceholder inquilino3 = mock(InquilinoPlaceholder.class);
		InquilinoPlaceholder inquilino4 = mock(InquilinoPlaceholder.class);

		when(inquilino1.contarInmueblesAlquilados()).thenReturn(1);
		when(inquilino2.contarInmueblesAlquilados()).thenReturn(2);
		when(inquilino3.contarInmueblesAlquilados()).thenReturn(3);
		when(inquilino4.contarInmueblesAlquilados()).thenReturn(4);

		when(sitioWeb.getInquilinos()).thenReturn(Arrays.asList(inquilino1, inquilino2, inquilino3, inquilino4));

		// exercise
		Object informe = listado.realizar(sitioWeb);

		// verify
		List<InquilinoPlaceholder> inquilinosEsperados = Arrays.asList(inquilino4, inquilino3, inquilino2, inquilino1);

		assertEquals(inquilinosEsperados, informe); // todos menos inquilino1
	}

	@Test
	void testRealizarTopTenDevuelveVacíoSiNoHayInquilinos() {
		// setup
		when(sitioWeb.getInquilinos()).thenReturn(Arrays.asList()); // 4
																	// inquilino
		// exercise
		Object informe = listado.realizar(sitioWeb);

		// verify
		assertTrue(((List<InquilinoPlaceholder>) informe).isEmpty());
	}

}

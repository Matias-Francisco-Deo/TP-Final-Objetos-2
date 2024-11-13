package tp_final.ranking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GestorDeRankingTest {
	private GestorDeRanking gestorDeRanking;

	private Ranking ranking;
	private Ranking otroRanking;

	private String categoria = "CATEGORIA";
	private String otraCategoria = "OTRA CATEGORIA";

	// ------------------------------------------------------------

	@BeforeEach
	void setUp() throws Exception {
		// MOCK
		ranking = mock(Ranking.class);
		otroRanking = mock(Ranking.class);

		// STUB
		when(ranking.getCategoria()).thenReturn(categoria);
		when(ranking.getPuntaje()).thenReturn(3);
		when(ranking.getComentario()).thenReturn("Muy bueno");

		when(otroRanking.getCategoria()).thenReturn(otraCategoria);
		when(otroRanking.getPuntaje()).thenReturn(5);
		when(otroRanking.getComentario()).thenReturn("Excelente");

		// SUT
		gestorDeRanking = new GestorDeRanking();

		// EXERCISE
		gestorDeRanking.recibirRankeo(ranking);
		gestorDeRanking.recibirRankeo(otroRanking);
	}

	// ------------------------------------------------------------
	// RECIBIR RANKEO, PUNTAJES Y COMENTARIOS
	// ------------------------------------------------------------

	@Test
	void getPuntajesDeCategoriaTest() {
		assertEquals(gestorDeRanking.getPuntajesDeCategoria(categoria).size(), 1);
		assertEquals(gestorDeRanking.getPuntajesDeCategoria(categoria).get(0), 3);

		assertEquals(gestorDeRanking.getPuntajesDeCategoria(otraCategoria).size(), 1);
		assertEquals(gestorDeRanking.getPuntajesDeCategoria(otraCategoria).get(0), 5);
	}

	@Test
	void getPuntajePromedioDeCategoriaTest() {
		assertEquals(gestorDeRanking.getPuntajePromedioDeCategoria(categoria), 3d);
		assertEquals(gestorDeRanking.getPuntajePromedioDeCategoria(otraCategoria), 5d);
	}

	@Test
	void getPuntajePromedioTotalTest() {
		assertEquals(gestorDeRanking.getPuntajePromedioTotal(), 4d);
	}

	@Test
	void getComentariosDeCategoriaTest() {
		assertEquals(gestorDeRanking.getComentariosDeCategoria(categoria).size(), 1);
		assertTrue(gestorDeRanking.getComentariosDeCategoria(categoria).contains("Muy bueno"));

		assertEquals(gestorDeRanking.getComentariosDeCategoria(otraCategoria).size(), 1);
		assertTrue(gestorDeRanking.getComentariosDeCategoria(otraCategoria).contains("Excelente"));
	}

	@Test
	void getTodosLosComentariosTest() {
		assertEquals(gestorDeRanking.getTodosLosComentarios().size(), 2);
		assertTrue(gestorDeRanking.getTodosLosComentarios().contains("Muy bueno"));
		assertTrue(gestorDeRanking.getTodosLosComentarios().contains("Excelente"));
	}

	// ------------------------------------------------------------
}

package tp_final.ranking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.usuarios.Usuario;

class RankingTest {
	private Ranking ranking;
	private Usuario autor;
	private String categoria = "CATEGORIA";
	private int puntaje = 5;
	private String comentario = "COMENTARIO";

	// ------------------------------------------------------------

	@BeforeEach
	void setUp() throws Exception {
		// MOCK
		autor = mock(Usuario.class);

		// SUT
		ranking = new Ranking(autor, categoria, puntaje, comentario);
	}

	// ------------------------------------------------------------
	// AUTOR, AUTOR, CATEGORIA, PUNTAJE Y COMENTARIO
	// ------------------------------------------------------------

	@Test
	void getAutorTest() {
		assertEquals(ranking.getAutor(), autor);
	}

	@Test
	void getCategoriaTest() {
		assertEquals(ranking.getCategoria(), categoria);
	}

	@Test
	void getPuntajeTest() {
		assertEquals(ranking.getPuntaje(), puntaje);
	}

	@Test
	void getComentarioTest() {
		assertEquals(ranking.getComentario(), comentario);
	}

	@Test
	void crearCategoriaConPuntajeMayorA5DebeSetearPuntaje5() {
		// SUT
		ranking = new Ranking(autor, categoria, 6, comentario);

		assertEquals(ranking.getPuntaje(), 5);
	}

	@Test
	void crearCategoriaConPuntajeMenorA1DebeSetearPuntaje1() {
		// SUT
		ranking = new Ranking(autor, categoria, 0, comentario);

		assertEquals(ranking.getPuntaje(), 1);
	}

	// ------------------------------------------------------------
}

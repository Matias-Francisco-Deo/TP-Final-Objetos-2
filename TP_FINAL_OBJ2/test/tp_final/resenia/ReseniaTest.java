package tp_final.resenia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReseniaTest {
	private Resenia resenia;

	@BeforeEach
	void setUp() throws Exception {
		resenia = new Resenia();
	}

	@Test
	void getPuntajeTest() {
		resenia.setPuntaje(5d);
		assertEquals(resenia.getPuntaje(), 5d);
	}

	@Test
	void getComentarioTest() {
		resenia.setComentario("Excelente");
		assertEquals(resenia.getComentario(), "Excelente");
	}
}

package tp_final.reseña;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReseñaTest {
	private Reseña reseña;

	@BeforeEach
	void setUp() throws Exception {
		// SUT
		reseña = new Reseña();
	}

	@Test
	void getPuntajeTest() {
		reseña.setPuntaje(5d);
		assertEquals(reseña.getPuntaje(), 5d);
	}

	@Test
	void getComentarioTest() {
		reseña.setComentario("Excelente");
		assertEquals(reseña.getComentario(), "Excelente");
	}
}

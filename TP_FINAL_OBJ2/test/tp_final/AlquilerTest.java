package tp_final;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlquilerTest {
	Alquiler alquiler;

	@BeforeEach
	void setUp() throws Exception {
		alquiler = new Alquiler(200d);
	}

	@Test
	void getterTest() {
		assertEquals(alquiler.getPrecioBase(), 200d);
	}

	@Test
	void setterTest() {
		alquiler.setPrecioBase(500);
		assertEquals(alquiler.getPrecioBase(), 500d);
	}
}

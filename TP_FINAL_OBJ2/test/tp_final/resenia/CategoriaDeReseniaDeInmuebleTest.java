package tp_final.resenia;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoriaDeReseniaDeInmuebleTest {

	@BeforeEach
	void setUp() throws Exception {
		// NOTHING
	}

	@Test
	void valoresDeCategoriaDeReseniaDeInmuebleTest() {
		assertEquals(CategoriaDeReseniaDeInmueble.UBICACION, CategoriaDeReseniaDeInmueble.valueOf("UBICACION"));
		assertEquals(CategoriaDeReseniaDeInmueble.PRECIO, CategoriaDeReseniaDeInmueble.valueOf("PRECIO"));
		assertEquals(CategoriaDeReseniaDeInmueble.CONFORT, CategoriaDeReseniaDeInmueble.valueOf("CONFORT"));
		assertEquals(CategoriaDeReseniaDeInmueble.SERVICIOS, CategoriaDeReseniaDeInmueble.valueOf("SERVICIOS"));
		assertEquals(CategoriaDeReseniaDeInmueble.LIMPIEZA, CategoriaDeReseniaDeInmueble.valueOf("LIMPIEZA"));
	}

	@Test
	void CategoriaDeReseniaDeInmuebleNoEsNuloTest() {
		for (CategoriaDeReseniaDeInmueble categoria : CategoriaDeReseniaDeInmueble.values()) {
			assertNotNull(categoria);
		}
	}
}

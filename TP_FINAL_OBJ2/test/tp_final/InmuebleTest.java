package tp_final;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InmuebleTest {
	
	private Inmueble inmueble;
	
	@BeforeEach
	void setUp() throws Exception {
		ArrayList<String> misServicios= new ArrayList<>(Arrays.asList("Agua", "Luz", "Gas"));
		ArrayList<Foto> misFotos= new ArrayList<>(Arrays.asList(new FotoEjemplo()));
		
		inmueble = new Inmueble("Casa", 20, "Argentina", "Quilmes", "mi casa", misServicios, 4, misFotos);
	}
	
	@Test
	void getterYSetterTipoInmuebleTest() {
		assertEquals(inmueble.getTipoInmueble(),"Casa");
		inmueble.setTipoInmueble("Departamento");
		assertEquals(inmueble.getTipoInmueble(),"Departamento");
	}
}

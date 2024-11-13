package tp_final.inmueble;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.usuarios.Usuario;
import tp_final_extra.Ranking;

public class InmuebleTest {

	private ArrayList<Foto> misFotos;
	private ArrayList<String> misServicios;
	private Ranking ranking;
	private Inmueble inmueble;
	private String casa;
	private Usuario propietario;
	private Foto foto;

	@BeforeEach
	void setUp() throws Exception {
		ranking = mock(Ranking.class);

		propietario = mock(Usuario.class);

		foto = mock(Foto.class);

		misServicios = new ArrayList<>(Arrays.asList("Agua", "Luz"));
		misFotos = new ArrayList<>(Arrays.asList());

		casa = new String();

		inmueble = new Inmueble(casa, propietario, 20, "Argentina", "Quilmes", "mi direccion", misServicios, 4,
				misFotos);
	}

	@Test
	void getterYSetterTipoInmuebleTest() {
		String departamento = new String();
		assertEquals(inmueble.getTipoInmueble(), casa);
		inmueble.setTipoInmueble(departamento);
		assertEquals(inmueble.getTipoInmueble(), departamento);
	}

	@Test
	void getterPropietarioTest() {

		assertEquals(inmueble.getPropietario(), propietario);
	}

	@Test
	void getterYSetterSuperficieTest() {
		assertEquals(inmueble.getSuperficie(), 20);
		inmueble.setSuperficie(25);
		assertEquals(inmueble.getSuperficie(), 25);
	}

	@Test
	void getterYSetterPaisTest() {
		assertEquals(inmueble.getPais(), "Argentina");
		inmueble.setPais("Brasil");
		assertEquals(inmueble.getPais(), "Brasil");
	}

	@Test
	void getterYSetterCiudadTest() {
		assertEquals(inmueble.getCiudad(), "Quilmes");
		inmueble.setCiudad("Bernal");
		assertEquals(inmueble.getCiudad(), "Bernal");
	}

	@Test
	void getterYSetterDireccionTest() {
		assertEquals(inmueble.getDireccion(), "mi direccion");
		inmueble.setDireccion("Tu direccion");
		assertEquals(inmueble.getDireccion(), "Tu direccion");
	}

	@Test
	void getterYAgregarServicioTest() {
		assertEquals(inmueble.getServicios(), misServicios);
		inmueble.addServicio("Gas");
		misServicios.add("Gas");
		assertEquals(inmueble.getServicios(), misServicios);
	}

	@Test
	void getterYSetterCapacidad() {
		assertEquals(inmueble.getCapacidad(), 4);
		inmueble.setCapacidad(5);
		assertEquals(inmueble.getCapacidad(), 5);
	}

	@Test
	void getterYAgregarFotoTest() {
		assertEquals(inmueble.getFotos(), misFotos);

		assertTrue(inmueble.getFotos().isEmpty());

		inmueble.addFoto(foto);

		assertEquals(inmueble.getFotos().size(), 1);
	}

	@Test
	void sumaYGetterDeCantAlquiladoTest() {
		assertEquals(inmueble.getCantVecesEnAlquiler(), 0);
		inmueble.aumentarCantDeVecesAlquilado();
		assertEquals(inmueble.getCantVecesEnAlquiler(), 1);
	}

	@Test
	void addYGetterPuntajesPorCategoria() {

		inmueble.addPuntajePorCategoria("Limpieza", 8.3);

		Map<String, Double> precios = inmueble.getPuntajesPorCategoria();

		assertEquals(1, precios.size());

		inmueble.addPuntajePorCategoria("Ubicacion", 6.1);

		precios = inmueble.getPuntajesPorCategoria();

		assertEquals(2, precios.size());

		assertEquals(8.3, precios.get("Limpieza"));
	}
}

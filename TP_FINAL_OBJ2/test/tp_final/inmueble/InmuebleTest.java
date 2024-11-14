package tp_final.inmueble;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.ranking.GestorDeRanking;
import tp_final.usuarios.Usuario;

public class InmuebleTest {

	private ArrayList<Foto> misFotos;
	private ArrayList<String> misServicios;
	private Inmueble inmueble;
	private String casa;
	private Usuario propietario;
	private Foto foto;
	private GestorDeRanking gestor;

	@BeforeEach
	void setUp() throws Exception {

		propietario = mock(Usuario.class);

		foto = mock(Foto.class);

		gestor = mock(GestorDeRanking.class);

		misServicios = new ArrayList<>(Arrays.asList("Agua", "Luz"));
		misFotos = new ArrayList<>(Arrays.asList());

		casa = new String();

		inmueble = new Inmueble(casa, propietario, 20, "Argentina", "Quilmes", "mi direccion", misServicios, 4,
				misFotos, gestor);
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
	void gettergestorTest() {

		assertEquals(inmueble.getGestorDeRanking(), gestor);
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

		assertTrue(inmueble.getServicios().size() == 3);
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

		// era vacía, ahora agrego
		inmueble.addFoto(foto);

		assertEquals(inmueble.getFotos().size(), 1);
	}

	@Test
	void sumaYGetterDeCantAlquiladoTest() {
		// era 0
		inmueble.aumentarCantDeVecesAlquilado();
		// pasa a 1
		assertEquals(inmueble.getCantVecesEnAlquiler(), 1);
	}

}

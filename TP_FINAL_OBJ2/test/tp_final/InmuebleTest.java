package tp_final;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InmuebleTest {
	
	private ArrayList<Foto> misFotos;
	private ArrayList<String> misServicios;
	private Inmueble inmueble;
	
	@BeforeEach
	void setUp() throws Exception {
		misServicios= new ArrayList<>(Arrays.asList("Agua", "Luz"));
		misFotos= new ArrayList<>(Arrays.asList(new FotoEjemplo()));
		
		inmueble = new Inmueble("Casa", 20, "Argentina", "Quilmes", "mi casa", misServicios, 4, misFotos);
	}
	
	@Test
	void getterYSetterTipoInmuebleTest() {
		assertEquals(inmueble.getTipoInmueble(),"Casa");
		inmueble.setTipoInmueble("Departamento");
		assertEquals(inmueble.getTipoInmueble(),"Departamento");
	}
	
	@Test
	void getterYSetterSuperficieTest() {
		assertEquals(inmueble.getSuperficie(),20);
		inmueble.setSuperficie(25);
		assertEquals(inmueble.getSuperficie(),25);
	}
	
	@Test
	void getterYSetterPaisTest() {
		assertEquals(inmueble.getPais(),"Argentina");
		inmueble.setPais("Brasil");
		assertEquals(inmueble.getPais(),"Brasil");
	}
	
	@Test
	void getterYSetterCiudadTest() {
		assertEquals(inmueble.getCiudad(),"Quilmes");
		inmueble.setCiudad("Bernal");
		assertEquals(inmueble.getCiudad(),"Bernal");
	}
	
	@Test
	void getterYSetterDireccionTest() {
		assertEquals(inmueble.getDireccion(),"mi casa");
		inmueble.setDireccion("Tu casa");
		assertEquals(inmueble.getDireccion(),"Tu casa");
	}
	
	@Test
	void getterYAgregarServicioTest() {
		assertEquals(inmueble.getServicios(),misServicios);
		inmueble.addServicio("Gas");
		misServicios.add("Gas");
		assertEquals(inmueble.getServicios(),misServicios);
	}
	
	@Test
	void getterYSetterCapacidad() {
		assertEquals(inmueble.getCapacidad(),4);
		inmueble.setCapacidad(5);
		assertEquals(inmueble.getCapacidad(),5);
	}
	
	@Test
	void getterYAgregarFotoTest() {
		assertEquals(inmueble.getFotos(),misFotos);
		inmueble.addFoto(new FotoEjemplo());
		misFotos.add(new FotoEjemplo());
		assertEquals(inmueble.getFotos(),misFotos);
	}
}

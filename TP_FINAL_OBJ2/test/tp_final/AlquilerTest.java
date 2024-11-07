package tp_final;

import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class AlquilerTest {
	Alquiler alquiler;
	private Inmueble inmueble;
	private PoliticaDeCancelacion politica;

	@BeforeEach
	void setUp() throws Exception {
		inmueble = mock(Inmueble.class);
		politica = new SinCancelacion();
		alquiler = new Alquiler(inmueble,LocalTime.of(10, 0), LocalTime.of(14, 30), "Tarjeta Credito",200d,politica);
	}

	@Test
	void getterPrecioBaseTest() {
		assertEquals(alquiler.getPrecioBase(), 200d);
	}

	@Test
	void setterPrecioBaseTest() {
		alquiler.setPrecioBase(500);
		assertEquals(alquiler.getPrecioBase(), 500d);
	}
	
	@Test
	void getterYSetterFechaEntrada() {
		LocalDate entrada = LocalDate.of(2024, 11, 3);
        alquiler.setFechaDeEntrada(entrada);
		
		assertEquals(entrada, alquiler.getFechaDeEntrada());
	}
	
	@Test
	void getterYSetterFechaSalida() {
        LocalDate salida = LocalDate.of(2024, 11, 4);
        alquiler.setFechaDeSalida(salida);
		
		assertEquals(salida, alquiler.getFechaDeSalida());
	}
	
	@Test
	void getterYSetterCheckIn() {
		LocalTime checkIn = LocalTime.of(8, 0);
        alquiler.setCheckIn(checkIn);
		
		assertEquals(checkIn, alquiler.getCheckIn());
	}
	
	@Test
	void getterYSetterCheckOut() {
		LocalTime checkOut = LocalTime.of(16, 0);
        alquiler.setCheckOut(checkOut);
		
		assertEquals(checkOut, alquiler.getCheckOut());
	}
	
	@Test
	void getterYSetterMedioPago() {
		String medioDePago = "Mercado Pago";
        alquiler.setMedioDePago(medioDePago);
		
		assertEquals(medioDePago, alquiler.getMedioDePago());
	}
}

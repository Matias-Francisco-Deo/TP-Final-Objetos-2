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
	private Reserva reserva1;
	private PoliticaDeCancelacion politica;

	@BeforeEach
	void setUp() throws Exception {
		inmueble = mock(Inmueble.class);
		reserva1 = mock(Reserva.class);
		politica = new SinCancelacion();
		alquiler = new Alquiler(inmueble,LocalTime.of(10, 0), LocalTime.of(14, 30), MedioDePago.EFECTIVO,200d,politica);
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
		MedioDePago medioDePago = MedioDePago.DEBITO;
        alquiler.setMedioDePago(medioDePago);
		
		assertEquals(medioDePago, alquiler.getMedioDePago());
	}
	
	@Test
	void confirmarReservaTest(){
		when(reserva1.getfechaEntrada()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getfechaSalida()).thenReturn(LocalDate.of(2024, 11, 5));
		
		assertEquals(null, alquiler.getFechaDeEntrada());
		assertEquals(null, alquiler.getFechaDeSalida());
		
		alquiler.confirmarReserva(reserva1);
		
		assertEquals(reserva1.getfechaEntrada(), alquiler.getFechaDeEntrada());
		assertEquals(reserva1.getfechaSalida(), alquiler.getFechaDeSalida());
	}
}

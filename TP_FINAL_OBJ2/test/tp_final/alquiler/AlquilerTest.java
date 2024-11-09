package tp_final.alquiler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp_final.estado_de_alquiler.Alquilado;
import tp_final.estado_de_alquiler.EstadoDeAlquiler;
import tp_final.estado_de_alquiler.Libre;
import tp_final.inmueble.Inmueble;
import tp_final.politica_cancelacion.PoliticaDeCancelacion;
import tp_final.politica_cancelacion.SinCancelacion;
import tp_final.usuarios.Usuario;
import tp_final_extra.Reserva;

class AlquilerTest {
	Alquiler alquiler;
	private Inmueble inmueble;
	private Reserva reserva1;
	private Reserva reserva2;
	private Reserva reserva3;
	private PoliticaDeCancelacion politica;

	private Usuario propietario;
	private Usuario sub;

	@BeforeEach
	void setUp() throws Exception {
		inmueble = mock(Inmueble.class);

		propietario = mock(Usuario.class);

		sub = new Usuario();

		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reserva3 = mock(Reserva.class);

		politica = new SinCancelacion();

		alquiler = new Alquiler(propietario, inmueble, LocalTime.of(10, 0), LocalTime.of(14, 30), MedioDePago.EFECTIVO,
				200d, politica);
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

		assertEquals(entrada, alquiler.getFechaCheckIn());
	}

	@Test
	void getterYSetterFechaSalida() {
		LocalDate salida = LocalDate.of(2024, 11, 4);
		alquiler.setFechaDeSalida(salida);

		assertEquals(salida, alquiler.getFechaDeCheckOut());
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
	void confirmarReservaTest() {
		when(reserva1.getfechaEntrada()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getfechaSalida()).thenReturn(LocalDate.of(2024, 11, 5));

		assertEquals(null, alquiler.getFechaCheckIn());
		assertEquals(null, alquiler.getFechaDeCheckOut());

		alquiler.confirmarReserva(reserva1);

		assertEquals(reserva1.getfechaEntrada(), alquiler.getFechaCheckIn());
		assertEquals(reserva1.getfechaSalida(), alquiler.getFechaDeCheckOut());
	}

	@Test
	void getterYSetterEstadoTest() {
		EstadoDeAlquiler libre = new Libre();
		EstadoDeAlquiler alquilado = new Alquilado();

		alquiler.setEstadoDeAlquiler(alquilado);

		assertEquals(alquiler.getEstadoDeAlquiler(), alquilado);

		alquiler.setEstadoDeAlquiler(libre);

		assertEquals(alquiler.getEstadoDeAlquiler(), libre);
	}

	@Test
	void addYGetterPreciosTemporadas() {

		alquiler.addPrecioTemporada("Verano", 20000.0);

		Map<String, Double> precios = alquiler.getPreciosTemporadas();

		assertEquals(1, precios.size());

		alquiler.addPrecioTemporada("Carnaval", 15000.0);

		precios = alquiler.getPreciosTemporadas();

		assertEquals(2, precios.size());

		assertEquals(20000.0, precios.get("Verano"));
	}

	@Test
	void addReservaYGetColaDeEsperaTest() {
		assertEquals(alquiler.getcolaDeEspera().size(), 0);
		alquiler.addReserva(reserva1);
		assertEquals(alquiler.getcolaDeEspera().size(), 1);
		assertEquals(alquiler.getcolaDeEspera().get(0), reserva1);
	}

	@Test
	void subscriptoresTest() {

		when(reserva1.getfechaEntrada()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getfechaSalida()).thenReturn(LocalDate.of(2024, 11, 5));

		alquiler.reservar(reserva1);
		alquiler.confirmarReserva(reserva1);

		alquiler.addSubscriptor(sub);

		alquiler.cancelarReserva(reserva1);

		assertEquals("notificado", sub.getNotificacion());// va a tener que cambiar
		assertEquals(alquiler.getSubscriptores().size(), 1);
	}

	@Test
	void alquilarYCancelarReservaEnEstadoLibreTest() {
		when(reserva1.getfechaEntrada()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getfechaSalida()).thenReturn(LocalDate.of(2024, 11, 5));

		when(reserva3.getfechaEntrada()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva3.getfechaSalida()).thenReturn(LocalDate.of(2024, 11, 5));

		alquiler.reservar(reserva1);
		assertEquals(alquiler.getcolaDeEspera().get(0), reserva1);
		alquiler.reservar(reserva2);
		alquiler.reservar(reserva3);
		assertEquals(alquiler.getcolaDeEspera().size(), 3);

		alquiler.cancelarReserva(reserva1);
		assertEquals(alquiler.getcolaDeEspera().get(0), reserva2);

		alquiler.cancelarReserva(reserva3);
		assertEquals(alquiler.getcolaDeEspera().get(0), reserva2);
	}

	@Test
	void alquilarYCancelarReservaEnEstadoAlquiladoTest() {

		EstadoDeAlquiler estado = new Alquilado();

		alquiler.reservar(reserva1);

		alquiler.setEstadoDeAlquiler(estado);

		when(reserva1.getfechaEntrada()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getfechaSalida()).thenReturn(LocalDate.of(2024, 11, 5));

		when(reserva2.getfechaEntrada()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva2.getfechaSalida()).thenReturn(LocalDate.of(2024, 11, 5));

		when(reserva3.getfechaEntrada()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva3.getfechaSalida()).thenReturn(LocalDate.of(2024, 11, 5));

		alquiler.reservar(reserva2);
		alquiler.reservar(reserva3);

		assertEquals(alquiler.getcolaDeEspera().get(0), reserva1);
		assertEquals(alquiler.getcolaDeEspera().size(), 3);

		alquiler.cancelarReserva(reserva3);
		assertEquals(alquiler.getcolaDeEspera().get(0), reserva1);
		assertEquals(alquiler.getcolaDeEspera().size(), 2);
		assertEquals(alquiler.getEstadoDeAlquiler(), estado);

		alquiler.cancelarReserva(reserva1);
		assertEquals(alquiler.getcolaDeEspera().get(0), reserva2);
		assertEquals(alquiler.getcolaDeEspera().size(), 1);
		assertNotEquals(alquiler.getEstadoDeAlquiler(), estado);

		alquiler.setEstadoDeAlquiler(estado);

		alquiler.cancelarReserva(reserva2);
		assertEquals(alquiler.getcolaDeEspera().size(), 0);
		assertNotEquals(alquiler.getEstadoDeAlquiler(), estado);
	}// verify(mockObject,Mockito.times(1)) probar si la cancelacion fue llamada
}

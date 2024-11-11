package tp_final.alquiler;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import tp_final.politica_cancelacion.CancelacionGratuita;
import tp_final.politica_cancelacion.PoliticaDeCancelacion;
import tp_final.politica_cancelacion.SinCancelacion;
import tp_final.usuarios.Usuario;
import tp_final_extra.Reserva;

class AlquilerTest {
	Alquiler alquiler;
	private Inmueble inmueble;
	private Reserva reserva1;
	private Reserva reserva2;
	private PoliticaDeCancelacion cancelacionGratuita;

	private Usuario propietario;
	private Usuario sub;

	private MedioDePago medioPago;

	private EstadoDeAlquiler libre;
	private EstadoDeAlquiler alquilado;

	@BeforeEach
	void setUp() throws Exception {

		inmueble = mock(Inmueble.class);
		propietario = mock(Usuario.class);

		sub = mock(Usuario.class);

		libre = mock(Libre.class);
		alquilado = mock(Alquilado.class);

		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);

		medioPago = MedioDePago.EFECTIVO;

		cancelacionGratuita = mock(CancelacionGratuita.class);

		alquiler = new Alquiler(propietario, inmueble, LocalTime.of(10, 0), LocalTime.of(14, 30), medioPago, 200d,
				cancelacionGratuita);
	}

	@Test
	void getterYSetterPrecioBaseTest() {
		assertEquals(alquiler.getPrecioBase(), 200d);

		alquiler.setPrecioBase(500);
		assertEquals(alquiler.getPrecioBase(), 500d);
	}

	@Test
	void getterInmuebleTest() {
		assertEquals(alquiler.getInmueble(), inmueble);
	}

	@Test
	void getterPropietarioTest() {
		assertEquals(alquiler.getPropietario(), propietario);
	}

	@Test
	void getterCiudadTest() {

		when(inmueble.getCiudad()).thenReturn("Quilmes");

		assertEquals(alquiler.getCiudad(), "Quilmes");
	}

	@Test
	void getterYSetterEstadoTest() {
		EstadoDeAlquiler libre = new Libre();
		EstadoDeAlquiler alquilado = new Alquilado();

		assertTrue(alquiler.esLibre());

		alquiler.setEstadoDeAlquiler(alquilado);

		assertTrue(alquiler.esAlquilado());

		assertEquals(alquiler.getEstadoDeAlquiler(), alquilado);

		alquiler.setEstadoDeAlquiler(libre);

		assertTrue(alquiler.esLibre());

		assertEquals(alquiler.getEstadoDeAlquiler(), libre);
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
	void getterYSetterPoliticaDeCancelacionOut() {

		PoliticaDeCancelacion sinCancelacion = new SinCancelacion();

		assertEquals(alquiler.getPoliticaDeCancelacion(), cancelacionGratuita);

		alquiler.setPoliticaDeCancelacion(sinCancelacion);

		assertEquals(alquiler.getPoliticaDeCancelacion(), sinCancelacion);
	}

	@Test
	void getterYSetterMedioPago() {
		assertEquals(medioPago, alquiler.getMedioDePago());

		MedioDePago medioDePago = MedioDePago.TARJETA_DEBITO;
		alquiler.setMedioDePago(medioDePago);

		assertEquals(medioDePago, alquiler.getMedioDePago());
	}

	@Test
	void addGetterYDeleteSubscriptoresTest() {

		assertTrue(alquiler.getSubscriptores().isEmpty());

		alquiler.addSubscriptor(sub);

		assertEquals(alquiler.getSubscriptores().size(), 1);

		assertTrue(alquiler.getSubscriptores().contains(sub));

		alquiler.deleteSubscriptor(sub);

		assertTrue(alquiler.getSubscriptores().isEmpty());
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

		assertEquals("notificado", sub.getNotificacion());// va a tener que cambiar por el de usuario real
		assertEquals(alquiler.getSubscriptores().size(), 1);
	}

	@Test
	void ReservarTest() {

		alquiler.setEstadoDeAlquiler(alquilado);

		alquiler.reservar(reserva1);

		verify(alquilado).alquilar(reserva1, alquiler);

		alquiler.setEstadoDeAlquiler(libre);

		alquiler.reservar(reserva2);

		verify(libre).alquilar(reserva2, alquiler);

	}

	@Test
	void confirmarReservaTest() {
		when(reserva1.getfechaEntrada()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getfechaSalida()).thenReturn(LocalDate.of(2024, 11, 5));

		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Libre);

		assertEquals(null, alquiler.getFechaCheckIn());
		assertEquals(null, alquiler.getFechaDeCheckOut());

		alquiler.confirmarReserva(reserva1);

		assertEquals(reserva1.getfechaEntrada(), alquiler.getFechaCheckIn());
		assertEquals(reserva1.getfechaSalida(), alquiler.getFechaDeCheckOut());

		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Alquilado);

		verify(inmueble, times(1)).sumarCantAlquilado();
	}

	@Test
	void alquilarYCancelarReservaEnEstadoLibreTest() {

		when(reserva1.getfechaEntrada()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getfechaSalida()).thenReturn(LocalDate.of(2024, 11, 5));

		alquiler.setEstadoDeAlquiler(libre);

		alquiler.esLibre();

		alquiler.reservar(reserva1);

		alquiler.cancelarReserva(reserva1);

		verify(libre).cancelar(reserva1, alquiler);

		verify(cancelacionGratuita).aplicarPolitica(reserva1, alquiler.getPrecioBase());

	}

	@Test
	void alquilarYCancelarReservaEnEstadoAlquiladoTest() {

		when(reserva1.getfechaEntrada()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getfechaSalida()).thenReturn(LocalDate.of(2024, 11, 5));

		alquiler.setEstadoDeAlquiler(alquilado);

		alquiler.esAlquilado();

		alquiler.reservar(reserva1);

		alquiler.cancelarReserva(reserva1);

		verify(alquilado).cancelar(reserva1, alquiler);

		verify(cancelacionGratuita).aplicarPolitica(reserva1, alquiler.getPrecioBase());

	}
}
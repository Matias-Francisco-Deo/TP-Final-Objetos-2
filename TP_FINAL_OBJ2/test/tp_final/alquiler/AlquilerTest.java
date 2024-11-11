package tp_final.alquiler;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
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
import tp_final.politica_cancelacion.PoliticaDeCancelacion;
import tp_final.usuarios.Usuario;
import tp_final_extra.Reserva;

class AlquilerTest {
	Alquiler alquiler;
	private Inmueble inmueble;
	private Reserva reserva1;
	private Reserva reserva2;
	private PoliticaDeCancelacion cancelacion;

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

		LocalTime checkIn = LocalTime.of(10, 0);
		LocalTime checkOut = LocalTime.of(14, 30);

		cancelacion = mock(PoliticaDeCancelacion.class);

		alquiler = new Alquiler(inmueble, checkIn, checkOut, medioPago, 200d, cancelacion);
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
	void setterPrecioBaseDisminucionDePrecioTest() {

		alquiler.addSubscriptor(sub);

		alquiler.setPrecioBase(100);
		assertEquals(alquiler.getPrecioBase(), 100d);

		// verify(sub,times(1).mandarMensaje());

	}

	@Test
	void getterInmuebleTest() {
		assertEquals(alquiler.getInmueble(), inmueble);
	}

	@Test
	void getterPropietarioTest() {
		when(inmueble.getPropietario()).thenReturn(propietario);

		assertEquals(alquiler.getPropietario(), propietario);
	}

	@Test
	void getterCiudadTest() {

		when(inmueble.getCiudad()).thenReturn("Quilmes");

		assertEquals(alquiler.getCiudad(), "Quilmes");
	}

	@Test
	void getterEstadoTest() {

		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Libre);
	}

	@Test
	void setterEstadoTest() {

		alquiler.setEstadoDeAlquiler(alquilado);

		assertEquals(alquiler.getEstadoDeAlquiler(), alquilado);
	}

	@Test
	void getterYSetterFechaEntradaTest() {
		LocalDate entrada = LocalDate.of(2024, 11, 3);
		alquiler.setFechaDeEntrada(entrada);

		assertEquals(entrada, alquiler.getFechaCheckIn());
	}

	@Test
	void getterYSetterFechaSalidaTest() {
		LocalDate salida = LocalDate.of(2024, 11, 4);
		alquiler.setFechaDeSalida(salida);

		assertEquals(salida, alquiler.getFechaDeCheckOut());
	}

	@Test
	void getterPoliticaDeCancelacionTest() {

		assertEquals(alquiler.getPoliticaDeCancelacion(), cancelacion);

	}

	@Test
	void setterPoliticaDeCancelacionTest() {

		PoliticaDeCancelacion cancelacion2 = mock(PoliticaDeCancelacion.class);

		alquiler.setPoliticaDeCancelacion(cancelacion2);

		assertEquals(alquiler.getPoliticaDeCancelacion(), cancelacion2);
	}

	@Test
	void getterMedioPago() {

		assertEquals(medioPago, alquiler.getMedioDePago());

	}

	@Test
	void setterMedioPago() {

		MedioDePago medioDePago = MedioDePago.TARJETA_DEBITO;
		alquiler.setMedioDePago(medioDePago);

		assertEquals(medioDePago, alquiler.getMedioDePago());
	}

	@Test
	void esLibreTest() {

		assertTrue(alquiler.esLibre());

	}

	@Test
	void esAlquiladoTest() {

		alquiler.setEstadoDeAlquiler(alquilado);

		assertTrue(alquiler.esAlquilado());

	}

	@Test
	void addSubscriptoresTest() {

		alquiler.addSubscriptor(sub);

		assertEquals(alquiler.getSubscriptores().size(), 1);

	}

	@Test
	void DeleteSubscriptoresTest() {

		alquiler.addSubscriptor(sub);

		assertEquals(alquiler.getSubscriptores().size(), 1);

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

		alquiler.setEstadoDeAlquiler(alquilado);// se reemplazo por un mock para verificar si se llamo al estado

		alquiler.reservar(reserva1);

		verify(alquilado).alquilar(reserva1, alquiler);

	}

	@Test
	void confirmarReservaTest() {
		when(reserva1.getfechaEntrada()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getfechaSalida()).thenReturn(LocalDate.of(2024, 11, 5));

		alquiler.confirmarReserva(reserva1);

		assertEquals(reserva1.getfechaEntrada(), alquiler.getFechaCheckIn());
		assertEquals(reserva1.getfechaSalida(), alquiler.getFechaDeCheckOut());

		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Alquilado);

		verify(inmueble, times(1)).aumentarCantDeVecesAlquilado();
	}

	@Test
	void CancelarReservaEnEstadoAlquiladoConUnaSolaReservaTest() {

		doNothing().when(reserva1).desencolar();

		alquiler.reservar(reserva1);
		alquiler.confirmarReserva(reserva1);

		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Alquilado);

		alquiler.doCancelarAlquilado(reserva1);

		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Libre);

		// verificar la notificacion

	}

	@Test
	void CancelarPrimeraReservaEnEstadoAlquiladoConVariasReservasTest() {

		doNothing().when(reserva1).desencolar();

		alquiler.reservar(reserva1);
		alquiler.reservar(reserva2);
		alquiler.confirmarReserva(reserva1);

		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Alquilado);

		alquiler.doCancelarAlquilado(reserva1);

		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Libre);

		// verificar la notificacion

	}

	@Test
	void CancelarReservaNoPrimeraEnEstadoAlquiladoConVariasReservasTest() {

		doNothing().when(reserva2).desencolar();

		alquiler.reservar(reserva1);
		alquiler.reservar(reserva2);
		alquiler.confirmarReserva(reserva1);

		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Alquilado);

		alquiler.doCancelarAlquilado(reserva2);

		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Alquilado);// verificamos que no cambio el estado

	}

	@Test
	void CancelarReservaEnEstadoLibreConUnaSolaReservaTest() {

		alquiler.reservar(reserva1);

		assertEquals(alquiler.getcolaDeEspera().size(), 1);

		alquiler.doCancelarLibre(reserva1);

		assertTrue(alquiler.getcolaDeEspera().isEmpty());

	}

	@Test
	void CancelarPrimeraReservaEnEstadoLibreVariasReservasTest() {

		doNothing().when(reserva1).desencolar();

		alquiler.reservar(reserva1);

		alquiler.reservar(reserva2);

		alquiler.doCancelarLibre(reserva1);

		assertEquals(alquiler.getcolaDeEspera().size(), 1);

	}

	@Test
	void CancelarReservaNoPrimeraEnEstadoLibreConVariasReservasTest() {

		alquiler.reservar(reserva1);

		alquiler.reservar(reserva2);

		alquiler.doCancelarLibre(reserva2);

		assertEquals(alquiler.getcolaDeEspera().size(), 1);

	}
}
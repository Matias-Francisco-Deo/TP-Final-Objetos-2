package tp_final.alquiler;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
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
import tp_final.reserva.Reserva;
import tp_final.suscriptores.Suscriptor;
import tp_final.usuarios.Usuario;

class AlquilerTest {
	Alquiler alquiler;
	private Inmueble inmueble;
	private Reserva reserva1;
	private Reserva reserva2;
	private PoliticaDeCancelacion cancelacion;

	private Usuario propietario;
	private Suscriptor sub;

	private MedioDePago medioPago;

	private EstadoDeAlquiler estado;

	@BeforeEach
	void setUp() throws Exception {

		inmueble = mock(Inmueble.class);
		propietario = mock(Usuario.class);

		sub = mock(Suscriptor.class);

		estado = mock(EstadoDeAlquiler.class);

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
		when(inmueble.getTipoInmueble()).thenReturn("Departamento");

		alquiler.addSubscriptor(sub);

		alquiler.setPrecioBase(100);
		assertEquals(alquiler.getPrecioBase(), 100d);

		String mensaje = "No te pierdas\r\n esta oferta: Un inmueble Departamento a tan sólo 100.0 pesos.";

		verify(sub).mandarMensaje(mensaje);

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
	void getterCantHuespedesTest() {

		when(inmueble.getCapacidad()).thenReturn(5);

		assertEquals(alquiler.getCantidadHuespedes(), 5);
	}

	@Test
	void getterEstadoTest() {

		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Libre);
	}

	@Test
	void setterEstadoTest() {

		alquiler.setEstadoDeAlquiler(estado);

		assertEquals(alquiler.getEstadoDeAlquiler(), estado);
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

		assertEquals(salida, alquiler.getFechaCheckOut());
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
	void getterCheckInTest() {

		assertEquals(LocalTime.of(10, 0), alquiler.getCheckIn());

	}

	@Test
	void getterCheckOutTest() {

		assertEquals(LocalTime.of(14, 30), alquiler.getCheckOut());

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
	void noEsLibreTest() {

		alquiler.setEstadoDeAlquiler(estado);
		when(estado.esLibre()).thenReturn(false);

		assertFalse(alquiler.esLibre());

	}

	@Test
	void addSubscriptoresTest() {

		alquiler.addSubscriptor(sub);

		assertEquals(alquiler.getSubscriptores().size(), 1);

	}

	@Test
	void deleteSubscriptoresTest() {

		alquiler.addSubscriptor(sub);

		alquiler.deleteSubscriptor(sub);

		assertTrue(alquiler.getSubscriptores().isEmpty());
	}

	@Test
	void addYGetterPreciosTemporadas() {

		alquiler.addPrecioTemporada("Verano", 20000.0);

		Map<String, Double> precios = alquiler.getPreciosTemporadas();

		assertEquals(1, precios.size());
	}

	@Test
	void getterPrecioEnTemporada() {

		alquiler.addPrecioTemporada("Verano", 20000.0);
		alquiler.addPrecioTemporada("Carnaval", 15000.0);

		double precio = alquiler.getPrecioEnTemporada("Verano");

		assertEquals(20000.0, precio);
	}

	@Test
	void addReservaYGetColaDeEsperaTest() {

		alquiler.addReserva(reserva1);

		assertEquals(alquiler.getColaDeEspera().size(), 1);
	}

	@Test
	void notificarSubscriptoresTest() {

		alquiler.addSubscriptor(sub);

		alquiler.notificarSubs("");

		verify(sub).mandarMensaje("");
	}

	@Test
	void reservarTest() {

		alquiler.setEstadoDeAlquiler(estado);// se reemplazo por un mock para verificar si se llamo al estado

		alquiler.reservar(reserva1);

		verify(estado).alquilar(reserva1, alquiler);

	}

	@Test
	void cancelarReservarTest() {

		alquiler.setEstadoDeAlquiler(estado);// se reemplazo por un mock para verificar si se llamo al estado

//		alquiler.reservar(reserva1);

		alquiler.cancelarReserva(reserva1);

		verify(estado).cancelar(reserva1, alquiler);

	}

	@Test
	void confirmarReservaTest() {
		when(reserva1.getFechaCheckIn()).thenReturn(LocalDate.of(2024, 11, 1));
		when(reserva1.getFechaCheckOut()).thenReturn(LocalDate.of(2024, 11, 5));

		alquiler.confirmarReserva(reserva1);

		// alquiler e inmueble deben tener mismas fechas
		assertEquals(reserva1.getFechaCheckIn(), alquiler.getFechaCheckIn());
		assertEquals(reserva1.getFechaCheckOut(), alquiler.getFechaCheckOut());
		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Alquilado); // pasa a alquilado
		verify(inmueble, times(1)).aumentarCantDeVecesAlquilado();
	}

	@Test
	void CancelarReservaEnEstadoAlquiladoConUnaSolaReservaTest() {

		when(inmueble.getTipoInmueble()).thenReturn("Departamento");

//		doNothing().when(reserva1).desencolar();

		alquiler.addSubscriptor(sub);

		alquiler.reservar(reserva1);
		alquiler.confirmarReserva(reserva1);

//		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Alquilado);

		alquiler.doCancelarAlquilado(reserva1);

//		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Libre);

		verify(sub).mandarMensaje("El/la Departamento que te interesa se ha liberado! Corre a reservarlo");

	}

	@Test
	void CancelarPrimeraReservaEnEstadoAlquiladoConVariasReservasTest() {

		doNothing().when(reserva1).desencolar();

		alquiler.addSubscriptor(sub);

		alquiler.reservar(reserva1);
		alquiler.reservar(reserva2);
		alquiler.confirmarReserva(reserva1);

//		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Alquilado);

		alquiler.doCancelarAlquilado(reserva1);

//		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Libre);

		verify(sub).mandarMensaje(any());

	}

	@Test
	void CancelarReservaNoPrimeraEnEstadoAlquiladoConVariasReservasTest() {

		doNothing().when(reserva2).desencolar();

		alquiler.reservar(reserva1);
		alquiler.reservar(reserva2);
		alquiler.confirmarReserva(reserva1);

//		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Alquilado);

		alquiler.doCancelarAlquilado(reserva2);

//		assertTrue(alquiler.getEstadoDeAlquiler() instanceof Alquilado);// verificamos que no cambio el estado

	}

	@Test
	void CancelarReservaEnEstadoLibreConUnaSolaReservaTest() {

		alquiler.reservar(reserva1);

		alquiler.doCancelarLibre(reserva1);

		assertTrue(alquiler.getColaDeEspera().isEmpty());

	}

	@Test
	void CancelarPrimeraReservaEnEstadoLibreVariasReservasTest() {

		doNothing().when(reserva1).desencolar();

		alquiler.reservar(reserva1);

		alquiler.reservar(reserva2);

		alquiler.doCancelarLibre(reserva1);

		assertEquals(alquiler.getColaDeEspera().size(), 1);

	}

	@Test
	void CancelarReservaNoPrimeraEnEstadoLibreConVariasReservasTest() {

		alquiler.reservar(reserva1);

		alquiler.reservar(reserva2);

		alquiler.doCancelarLibre(reserva2);

		assertEquals(alquiler.getColaDeEspera().size(), 1);

	}
}
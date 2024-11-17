package tp_final.alquiler;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

	@BeforeEach
	void setUp() throws Exception {

		inmueble = mock(Inmueble.class);
		propietario = mock(Usuario.class);

		sub = mock(Suscriptor.class);

		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);

		medioPago = MedioDePago.EFECTIVO;

		LocalTime checkIn = LocalTime.of(10, 0);
		LocalTime checkOut = LocalTime.of(14, 30);

		LocalDate FechaEntrada = LocalDate.of(2024, 11, 1);
		LocalDate FechaSalida = LocalDate.of(2024, 12, 1);

		cancelacion = mock(PoliticaDeCancelacion.class);

		alquiler = new Alquiler(inmueble, checkIn, checkOut, FechaEntrada, FechaSalida, medioPago, 200d, cancelacion);
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
	void aplicarPoliticaDeCancelacionTest() {

		alquiler.aplicarPoliticaDeCancelacion(reserva1);

		verify(cancelacion).aplicarPolitica(reserva1, alquiler.getPrecioBase());
	}

	@Test
	void notificarCancelacionASubsTest() {

		alquiler.addSubscriptor(sub);

		alquiler.notificarCancelacion();

		verify(sub).mandarMensaje(alquiler.mensajeCancelacion());// ver como cambiarlo
	}

}
package tp_final;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class HomePagePublisherAdapterTest {

	private HomePagePublisherAdapter adapter;
	private HomePagePublisher homePagePublisher;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		homePagePublisher = mock(HomePagePublisher.class);
		adapter = new HomePagePublisherAdapter(homePagePublisher);
	}

	@Test
	void testGetHomePagePublisher() {
		// verify
		assertEquals(this.adapter.getHomePagePublisher(), homePagePublisher);
	}

	@Test
	void testEnviarMensaje() {

		// exercise
		String mensaje = "No te pierdas esta oferta: Un inmueble Quincho a tan sólo 800 pesos";
		adapter.mandarMensaje(mensaje);

		// verify
		Mockito.verify(homePagePublisher, Mockito.times(1)).publish(mensaje);
	}

}

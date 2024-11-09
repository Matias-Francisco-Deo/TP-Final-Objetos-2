package tp_final.suscriptores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import tp_final.suscriptores.PopUpWindow;
import tp_final.suscriptores.PopUpWindowAdapter;

class PopUpWindowAdapterTest {

	private PopUpWindowAdapter adapter;
	private PopUpWindow popUpWindow;

	@BeforeEach
	void setUp() throws Exception {
		// setup
		popUpWindow = mock(PopUpWindow.class);
		adapter = new PopUpWindowAdapter(popUpWindow);
	}

	@Test
	void testGetPopUpWindow() {
		// verify
		assertEquals(this.adapter.getPopUpWindow(), popUpWindow);
	}

	@Test
	void testEnviarMensaje() {

		// exercise
		String mensaje = "El/la Quincho que te interesa se ha liberado! Corre a reservarlo!";
		adapter.mandarMensaje(mensaje);

		// verify
		Mockito.verify(popUpWindow, Mockito.times(1)).popUp(mensaje, "#fffff", 12);
	}

}

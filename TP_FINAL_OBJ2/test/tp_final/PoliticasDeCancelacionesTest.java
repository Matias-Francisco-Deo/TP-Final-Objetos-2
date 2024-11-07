package tp_final;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PoliticasDeCancelacionesTest {
	
	private Reserva reserva1;
	private CancelacionGratuita politicaCancelacion;
	private CancelacionGratuita cancelacionMock;
	
	@BeforeEach
	void setUp() throws Exception {
		
		reserva1 = mock(Reserva.class);
		cancelacionMock = mock(CancelacionGratuita.class);
		politicaCancelacion = new CancelacionGratuita();
		
	}
	
	@Test
	void cancelacionGratuitaConMasDe10DiasTest() {
		
		String textoEsperado = "Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de 0.0 $";
		
		LocalDate fechaEntrada = LocalDate.now().plusDays(15);
        LocalDate fechaSalida = fechaEntrada.plusDays(5); 
		
        when(reserva1.getfechaEntrada()).thenReturn(fechaEntrada);
        when(reserva1.getfechaSalida()).thenReturn(fechaSalida);
        
        politicaCancelacion.aplicarPolitica(reserva1, 10000);
        
      //dentro del metodo ocurre lo siguiente y se testea
		
        double costo = politicaCancelacion.calcularCosto(fechaEntrada, fechaSalida, 10000);
        
        String textoGenerado = politicaCancelacion.generarMail(costo);
        
        assertEquals(0, costo);
        assertEquals(textoEsperado, textoGenerado);
        
        doNothing().when(cancelacionMock).enviarMail(textoGenerado); // No hace nada, pero verifica la llamada
        cancelacionMock.enviarMail(textoGenerado);
        
        verify(cancelacionMock).enviarMail(textoGenerado);
	}
	
	@Test
	void cancelacionGratuitaConMenosDe10DiasTest() {
		
		String textoEsperado = "Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de 20000.0 $";
		
		LocalDate fechaEntrada = LocalDate.now().plusDays(5);
        LocalDate fechaSalida = fechaEntrada.plusDays(5); 
		
        when(reserva1.getfechaEntrada()).thenReturn(fechaEntrada);
        when(reserva1.getfechaSalida()).thenReturn(fechaSalida);
        
        politicaCancelacion.aplicarPolitica(reserva1, 10000);
        
        //dentro del metodo ocurre lo siguiente y se testea
		
        double costo = politicaCancelacion.calcularCosto(fechaEntrada, fechaSalida, 10000);
        
        String textoGenerado = politicaCancelacion.generarMail(costo);
        
        assertEquals(20000, costo);
        assertEquals(textoEsperado, textoGenerado);
	}
	
	
} 

package tp_final;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PoliticasDeCancelacionesTest {
	
	private Reserva reserva1;
	
	private CancelacionGratuita politicaCancelacion;
	private CancelacionGratuita cancelacionMock;
	
	private SinCancelacion sinCancelacion;
	private SinCancelacion sincancelacionMock;
	
	private Intermedia cancelacionIntermedia;
	private Intermedia intermediaMock;
	
	@BeforeEach
	void setUp() throws Exception {
		
		reserva1 = mock(Reserva.class);
		
		politicaCancelacion = new CancelacionGratuita();
		sinCancelacion = new SinCancelacion();
		cancelacionIntermedia = new Intermedia();
		
		cancelacionMock = mock(CancelacionGratuita.class);
		sincancelacionMock = mock(SinCancelacion.class);
		intermediaMock = mock(Intermedia.class);
		
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
	void sinCancelacionGratuitaConMenosDe10DiasTest() {
		
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

		@Test
		void sinCancelacionTest() {
			
			String textoEsperado = "Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de 50000.0 $";
			
			LocalDate fechaEntrada = LocalDate.now().plusDays(15);
	        LocalDate fechaSalida = fechaEntrada.plusDays(5); 
			
	        when(reserva1.getfechaEntrada()).thenReturn(fechaEntrada);
	        when(reserva1.getfechaSalida()).thenReturn(fechaSalida);
	        
	        sinCancelacion.aplicarPolitica(reserva1, 10000);
	        
	      //dentro del metodo ocurre lo siguiente y se testea
			
	        double costo = sinCancelacion.calcularCosto(fechaEntrada, fechaSalida, 10000);
	        
	        String textoGenerado = sinCancelacion.generarMail(costo);
	        
	        assertEquals(50000, costo);
	        assertEquals(textoEsperado, textoGenerado);
	        
	        doNothing().when(sincancelacionMock).enviarMail(textoGenerado); // No hace nada, pero verifica la llamada
	        sincancelacionMock.enviarMail(textoGenerado);
	        
	        verify(sincancelacionMock).enviarMail(textoGenerado);
		}
		
		@Test
		void cancelacionIntermediaConMasDe20DiasTest() {
			
			String textoEsperado = "Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de 0.0 $";
			
			LocalDate fechaEntrada = LocalDate.now().plusDays(25);
	        LocalDate fechaSalida = fechaEntrada.plusDays(5); 
			
	        when(reserva1.getfechaEntrada()).thenReturn(fechaEntrada);
	        when(reserva1.getfechaSalida()).thenReturn(fechaSalida);
	        
	        cancelacionIntermedia.aplicarPolitica(reserva1, 10000);
	        
	      //dentro del metodo ocurre lo siguiente y se testea
			
	        double costo = cancelacionIntermedia.calcularCosto(fechaEntrada, fechaSalida, 10000);
	        
	        String textoGenerado = cancelacionIntermedia.generarMail(costo);
	        
	        assertEquals(0, costo);
	        assertEquals(textoEsperado, textoGenerado);
	        
	        doNothing().when(intermediaMock).enviarMail(textoGenerado); // No hace nada, pero verifica la llamada
	        intermediaMock.enviarMail(textoGenerado);
	        
	        verify(intermediaMock).enviarMail(textoGenerado);
		}
		
		@Test
		void CancelacionIntermediaConMenosDe20YMasDe10DiasTest() {
			
			String textoEsperado = "Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de 25000.0 $";
			
			LocalDate fechaEntrada = LocalDate.now().plusDays(15);
	        LocalDate fechaSalida = fechaEntrada.plusDays(5); 
			
	        when(reserva1.getfechaEntrada()).thenReturn(fechaEntrada);
	        when(reserva1.getfechaSalida()).thenReturn(fechaSalida);
	        
	        cancelacionIntermedia.aplicarPolitica(reserva1, 10000);
	        
	        //dentro del metodo ocurre lo siguiente y se testea
			
	        double costo = cancelacionIntermedia.calcularCosto(fechaEntrada, fechaSalida, 10000);
	        
	        String textoGenerado = cancelacionIntermedia.generarMail(costo);
	        
	        assertEquals(25000, costo);
	        assertEquals(textoEsperado, textoGenerado);
		}
		
		@Test
		void CancelacionIntermediaConMenos10DiasTest() {
			
			String textoEsperado = "Por las politicas de cancelacion aclaradas al momento de hacer la reserva se debera abonar un monto de 50000.0 $";
			
			LocalDate fechaEntrada = LocalDate.now().plusDays(7);
	        LocalDate fechaSalida = fechaEntrada.plusDays(5); 
			
	        when(reserva1.getfechaEntrada()).thenReturn(fechaEntrada);
	        when(reserva1.getfechaSalida()).thenReturn(fechaSalida);
	        
	        cancelacionIntermedia.aplicarPolitica(reserva1, 10000);
	        
	        //dentro del metodo ocurre lo siguiente y se testea
			
	        double costo = cancelacionIntermedia.calcularCosto(fechaEntrada, fechaSalida, 10000);
	        
	        String textoGenerado = cancelacionIntermedia.generarMail(costo);
	        
	        assertEquals(50000, costo);
	        assertEquals(textoEsperado, textoGenerado);
		}
		
} 

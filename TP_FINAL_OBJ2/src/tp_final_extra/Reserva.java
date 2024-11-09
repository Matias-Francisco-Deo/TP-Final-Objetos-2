package tp_final;
import java.time.LocalDate;
//temporal
public class Reserva {
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	
	public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }
	
	public LocalDate getfechaEntrada() {
        return fechaEntrada;
    }

    public LocalDate getfechaSalida() {
        return fechaSalida;
    }
}

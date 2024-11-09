package tp_final.varios;

public interface ServidorDeCorreo {
	public void enviar(String destinatario, String asunto, Object cuerpo);
}

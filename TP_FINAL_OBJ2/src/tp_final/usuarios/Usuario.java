package tp_final.usuarios;

public class Usuario {
	private String nombre;
	private String email;
	private String telefono;

	public Usuario(String nombre, String email, String telefono) {
		this.setNombre(nombre);
		this.setEmail(email);
		this.setTelefono(telefono);
	}

	// ------------------------------
	// GETTERS
	// ------------------------------

	public String getNombre() {
		return this.nombre;
	}

	public String getEmail() {
		return this.email;
	}

	public String getTelefono() {
		return this.telefono;
	}

	// ------------------------------
	// SETTERS
	// ------------------------------

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	private void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}

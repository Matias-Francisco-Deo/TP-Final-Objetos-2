package tp_final.sitioweb;

import java.util.ArrayList;
import java.util.List;

import tp_final.alquiler.Alquiler;
import tp_final.inmueble.Inmueble;
import tp_final.usuarios.Usuario;

public class SitioWeb {

	private List<Usuario> usuarios;

	public SitioWeb() {
		this.setUsuarios(new ArrayList<Usuario>());
	}

	public void registrarUsuario(Usuario usuario) {
		this.getUsuarios().add(usuario);

	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	private void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Alquiler> getAlquileres() {

		return this.getUsuarios().stream().flatMap(usuario -> usuario.getAlquileres().stream()).toList();
	}

	public List<Inmueble> getInmuebles() {

		return this.getUsuarios().stream().flatMap(usuario -> usuario.getInmuebles().stream()).toList();
	}

}

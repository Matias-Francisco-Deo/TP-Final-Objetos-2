package tp_final.sitioweb;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import tp_final.alquiler.Alquiler;
import tp_final.inmueble.Inmueble;
import tp_final.usuarios.Usuario;

public class RecolectorDeDatos {

	private SitioWeb sitioWeb;

	public RecolectorDeDatos(SitioWeb sitioWeb) {
		this.setSitioWeb(sitioWeb);
	}

	private SitioWeb getSitioWeb() {
		return sitioWeb;
	}

	private void setSitioWeb(SitioWeb sitioWeb) {
		this.sitioWeb = sitioWeb;
	}

	public List<Usuario> getTopTenInquilinos() {
		List<Usuario> inquilinos = getUsuarios().stream()
				.sorted(Comparator.comparingInt(usuario -> ((Usuario) usuario).contarInmueblesAlquilados()) // cómo
						// evitar
						// este
						// cast?
						.reversed())
				.limit(10).collect(Collectors.toList());

		return inquilinos;
	}

	private List<Usuario> getUsuarios() {
		return this.getSitioWeb().getUsuarios();
	}

	public List<Inmueble> getInmueblesLibres() {
		List<Inmueble> inmueblesLibres = getAlquileres().stream().filter(alquiler -> alquiler.esLibre())
				.map(alquiler -> alquiler.getInmueble()).toList();

		return inmueblesLibres;
	}

	private List<Alquiler> getAlquileres() {
		return sitioWeb.getAlquileres();
	}

	public double getTasaDeOcupación() {

		double inmueblesAlquilados = sitioWeb.getAlquileres().stream().filter(alquiler -> !alquiler.esLibre()).count();
		double inmueblesTotales = sitioWeb.getInmuebles().stream().count();

		return inmueblesAlquilados / inmueblesTotales;
	}

}

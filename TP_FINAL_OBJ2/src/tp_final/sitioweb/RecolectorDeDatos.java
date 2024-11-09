package tp_final.sitioweb;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import tp_final.AlquilerPlaceholder;
import tp_final.InmueblePlaceholder;
import tp_final.InquilinoPlaceholder;

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

	public List<InquilinoPlaceholder> getTopTenInquilinos() {
		List<InquilinoPlaceholder> inquilinos = getInquilinos().stream()
				.sorted(Comparator
						.comparingInt(inquilino -> ((InquilinoPlaceholder) inquilino).contarInmueblesAlquilados()) // cómo
						// evitar
						// este
						// cast?
						.reversed())
				.limit(10).collect(Collectors.toList());

		return inquilinos;
	}

	private List<InquilinoPlaceholder> getInquilinos() {
		return this.getSitioWeb().getInquilinos();
	}

	public List<InmueblePlaceholder> getInmueblesLibres() {
		List<InmueblePlaceholder> inmueblesLibres = getAlquileres().stream().filter(alquiler -> alquiler.esLibre())
				.map(alquiler -> alquiler.getInmueble()) // puedo evitar este
															// casteo?
				.toList();

		return inmueblesLibres;
	}

	private List<AlquilerPlaceholder> getAlquileres() {
		return sitioWeb.getAlquileres();
	}

	public double getTasaDeOcupación() {

		double inmueblesAlquilados = sitioWeb.getAlquileres().stream().filter(alquiler -> !alquiler.esLibre()).count();
		double inmueblesTotales = sitioWeb.getInmuebles().stream().count();

		return inmueblesAlquilados / inmueblesTotales;
	}

}

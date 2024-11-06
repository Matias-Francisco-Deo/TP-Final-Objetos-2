package tp_final;

import tp_final.SitioWeb.ManagerDeOpciones;

public class TasaDeOcupación implements ListadoDeGestión {

	@Override
	public String realizar(ManagerDeOpciones sitioWeb) {
		double inmueblesAlquilados = sitioWeb.getAlquileres().stream().filter(alquiler -> !alquiler.esLibre()).count();
		double inmueblesTotales = sitioWeb.getInmuebles().stream().count();

		return "Tasa de ocupación: " + inmueblesAlquilados / inmueblesTotales;
	}

}

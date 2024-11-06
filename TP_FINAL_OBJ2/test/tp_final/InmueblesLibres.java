package tp_final;

import java.util.List;

import tp_final.SitioWeb.ManagerDeOpciones;

public class InmueblesLibres implements ListadoDeGestión {

	@Override
	public Object realizar(ManagerDeOpciones sitioWeb) {
		List<Object> alquileresLibres = sitioWeb.getAlquileres().stream().filter(alquiler -> alquiler.esLibre())
				.map(alquiler -> (Object) alquiler) // puedo evitar este casteo?
				.toList();

		return alquileresLibres;
	}

}

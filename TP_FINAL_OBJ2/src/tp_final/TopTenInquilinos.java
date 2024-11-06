package tp_final;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import tp_final.SitioWeb.ManagerDeOpciones;

public class TopTenInquilinos implements ListadoDeGestión {

	@Override
	public Object realizar(ManagerDeOpciones sitioWeb) {
		List<InquilinoPlaceholder> inquilinos = sitioWeb.getInquilinos().stream()
				.sorted(Comparator
						.comparingInt(inquilino -> ((InquilinoPlaceholder) inquilino).contarInmueblesAlquilados()) // cómo
						// evitar
						// este
						// cast?
						.reversed())
				.limit(10).collect(Collectors.toList());

		return inquilinos;
	}

}

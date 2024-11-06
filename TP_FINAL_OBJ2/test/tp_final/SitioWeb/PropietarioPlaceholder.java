package tp_final.SitioWeb;

import java.util.List;

import tp_final.AlquilerPlaceholder;
import tp_final.InmueblePlaceholder;

public interface PropietarioPlaceholder {

	public List<AlquilerPlaceholder> getAlquileres();

	public List<InmueblePlaceholder> getInmuebles();

}

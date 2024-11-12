package tp_final.búsqueda;

import java.util.ArrayList;
import java.util.List;

import tp_final.alquiler.Alquiler;

public class FiltroDefault implements Filtro {

	private List<ParámetroDeBúsqueda> filtros;

	public FiltroDefault(FiltroCiudad ciudad, FiltroFechaEntrada fechaEntrada, FiltroFechaSalida fechaSalida) {
		this.setFiltros(new ArrayList<ParámetroDeBúsqueda>());
		this.añadirFiltro(ciudad);
		this.añadirFiltro(fechaEntrada);
		this.añadirFiltro(fechaSalida);
	}

	public void añadirFiltro(ParámetroDeBúsqueda filtro) {
		this.getFiltros().add(filtro);
	}

	private void setFiltros(List<ParámetroDeBúsqueda> filtros) {
		this.filtros = filtros;
	}

	@Override
	public List<Alquiler> filtrar(List<Alquiler> alquileres) {
		List<Alquiler> alquileresResultantes = alquileres;
		for (ParámetroDeBúsqueda filtro : this.getFiltros()) {
			alquileresResultantes = filtro.filtrar(alquileresResultantes);
		}
		return alquileresResultantes;
	}

	private List<ParámetroDeBúsqueda> getFiltros() {
		return filtros;
	}

}

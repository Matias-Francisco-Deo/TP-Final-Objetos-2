package tp_final.búsqueda;

import java.util.ArrayList;
import java.util.List;

import tp_final.alquiler.Alquiler;

public class ListaDeFiltrosPredeterminada {

	private List<ParámetroDeBúsqueda> filtros;

	public ListaDeFiltrosPredeterminada(FiltroCiudad ciudad, FiltroFechaEntrada fechaEntrada,
			FiltroFechaSalida fechaSalida) {
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

	public List<Alquiler> filtrar(List<Alquiler> alquileres) {
		return alquileres.stream().filter(alquiler -> this.cumpleConFiltros(alquiler)).toList();

	}

	private boolean cumpleConFiltros(Alquiler alquiler) {
		return this.getFiltros().stream().allMatch(filtro -> filtro.esVálido(alquiler));
	}

	private List<ParámetroDeBúsqueda> getFiltros() {
		return filtros;
	}

}

package tp_final.búsqueda;

import java.util.List;

import tp_final.alquiler.Alquiler;

public class FiltroCiudad implements ParámetroDeBúsqueda {

	private String ciudad;

	public FiltroCiudad(String ciudad) {
		this.setCiudad(ciudad);
	}

	@Override
	public List<Alquiler> filtrar(List<Alquiler> alquileres) {
		return alquileres.stream().filter(alquiler -> alquiler.getCiudad() == this.getCiudad()).toList();
	}

	private String getCiudad() {
		return ciudad;
	}

	private void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

}

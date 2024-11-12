package tp_final.búsqueda;

import tp_final.alquiler.Alquiler;

public class FiltroCiudad implements ParámetroDeBúsqueda {

	private String ciudad;

	public FiltroCiudad(String ciudad) {
		this.setCiudad(ciudad);
	}

	private String getCiudad() {
		return ciudad;
	}

	private void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public boolean esVálido(Alquiler alquiler) {
		// TODO Auto-generated method stub
		return alquiler.getCiudad() == this.getCiudad();
	}

}

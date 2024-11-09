package tp_final.SitioWeb;

import java.util.ArrayList;
import java.util.List;

public class ManagerDeOpciones {

	private List<String> categorías;
	private List<String> servicios;
	private List<String> tiposInmueble;

	public ManagerDeOpciones() {
		this.setCategorías(new ArrayList<String>());
		this.setServicios(new ArrayList<String>());
		this.setTiposInmueble(new ArrayList<String>());
	}

	public void añadirCategoría(String categoría) {
		this.getCategorías().add(categoría);

	}

	public List<String> getCategorías() {
		// TODO Auto-generated method stub
		return this.categorías;
	}

	private void setCategorías(List<String> categorías) {
		this.categorías = categorías;
	}

	public void eliminarCategoría(String categoría) {
		this.getCategorías().remove(categoría);
	}

	public void añadirServicio(String servicio) {
		this.getServicios().add(servicio);

	}

	public List<String> getServicios() {
		// TODO Auto-generated method stub
		return this.servicios;
	}

	private void setServicios(ArrayList<String> servicios) {
		this.servicios = servicios;
	}

	public void eliminarServicio(String servicio) {
		this.getServicios().remove(servicio);

	}

	public void añadirTipoInmueble(String tipo) {
		this.getTiposInmueble().add(tipo);

	}

	public List<String> getTiposInmueble() {
		return this.tiposInmueble;
	}

	private void setTiposInmueble(ArrayList<String> tiposInmueble) {
		this.tiposInmueble = tiposInmueble;
	}

	public void eliminarTipoInmueble(String tipo) {
		this.getTiposInmueble().remove(tipo);

	}

}

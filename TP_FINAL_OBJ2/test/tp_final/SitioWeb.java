package tp_final;

import java.util.ArrayList;
import java.util.List;

public class SitioWeb {

	private List<String> categorías;
	private List<String> servicios;
	private List<String> tiposInmueble;
	private List<UsuarioPlaceholder> usuarios;
	private SistemaDeBúsqueda sistemaDeBúsqueda;

	public SitioWeb(SistemaDeBúsqueda sistemaDeBúsqueda) {
		this.setCategorías(new ArrayList<String>());
		this.setServicios(new ArrayList<String>());
		this.setTiposInmueble(new ArrayList<String>());
		this.setUsuarios(new ArrayList<UsuarioPlaceholder>());
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

	public void registrar(UsuarioPlaceholder usuario) {
		this.getUsuarios().add(usuario);
	}

	public List<UsuarioPlaceholder> getUsuarios() {
		// TODO Auto-generated method stub
		return this.usuarios;
	}

	private void setUsuarios(ArrayList<UsuarioPlaceholder> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioPlaceholder loguear(String nombre) {
		// TODO Auto-generated method stub
		return this.getUsuarios().stream().filter(usuario -> usuario.getNombre() == nombre).findFirst().orElseThrow();
	}

	private SistemaDeBúsqueda getSistemaDeBúsqueda() {
		return sistemaDeBúsqueda;
	}

	private void setSistemaDeBúsqueda(SistemaDeBúsqueda sistemaDeBúsqueda) {
		this.sistemaDeBúsqueda = sistemaDeBúsqueda;
	}

}

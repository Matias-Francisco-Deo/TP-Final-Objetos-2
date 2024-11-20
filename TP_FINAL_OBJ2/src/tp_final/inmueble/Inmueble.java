package tp_final.inmueble;

import java.util.List;

import tp_final.ranking.GestorDeRanking;
import tp_final.usuarios.Usuario;

public class Inmueble {
	private String tipoInmueble;

	private Usuario propietario;

	private double superficie;

	private String pais;

	private String ciudad;

	private String direccion;

	private List<String> servicios;

	private int capacidad;

	private List<Foto> fotos;

	private int cantVecesEnAlquiler;

	private GestorDeRanking gestor;

	Inmueble(String tipo, Usuario propietario, double superficie, String pais, String ciudad, String direccion,
			List<String> servicios, int capacidad, List<Foto> fotos, GestorDeRanking gestor) {
		this.setTipoInmueble(tipo);
		this.setPropietario(propietario);
		this.setSuperficie(superficie);
		this.setPais(pais);
		this.setCiudad(ciudad);
		this.setDireccion(direccion);
		this.setServicios(servicios);
		this.setCapacidad(capacidad);
		this.setFotos(fotos);
		this.setCantVecesEnAlquiler(0);
		this.setGestor(gestor);

	}

	public String getTipoInmueble() {
		return tipoInmueble;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public double getSuperficie() {
		return superficie;
	}

	public String getPais() {
		return pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getCantVecesEnAlquiler() {
		return cantVecesEnAlquiler;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public List<String> getServicios() {
		return servicios;
	}

	public GestorDeRanking getGestorDeRanking() {
		return this.gestor;
	}

	public void setTipoInmueble(String tipoInmueble) {// cambiar string a tipoinmueble?
		this.tipoInmueble = tipoInmueble;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	private void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	private void setCantVecesEnAlquiler(int cantVecesEnAlquiler) {
		this.cantVecesEnAlquiler = cantVecesEnAlquiler;
	}

	private void setGestor(GestorDeRanking gestor) {
		this.gestor = gestor;
	}

	private void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	private void setServicios(List<String> servicios) {
		this.servicios = servicios;
	}

	public void addServicio(String servicio) {
		this.servicios.add(servicio);
	}

	public void addFoto(Foto foto) {
		this.fotos.add(foto);
	}

	public void aumentarCantDeVecesAlquilado() {
		this.setCantVecesEnAlquiler(this.getCantVecesEnAlquiler() + 1);
	}

}

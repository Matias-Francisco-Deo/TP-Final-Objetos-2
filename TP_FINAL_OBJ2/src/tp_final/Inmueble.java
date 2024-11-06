package tp_final;

import java.util.ArrayList;
import java.util.List;

public class Inmueble {
	private String tipoInmueble;//cambiar string a tipoinmueble
	
	private double superficie;
	
	private String pais;
	
	private String ciudad;
	
	private String direccion;
	
	private ArrayList<String> servicios;
	
	private int capacidad;
	
	private ArrayList<Foto> fotos;
	
	private int cantVecesEnAlquiler;
	
	Inmueble(String tipo, double superficie, String pais, String ciudad, String direccion, ArrayList<String> servicios, int capacidad, ArrayList<Foto> fotos){
		this.tipoInmueble = tipo;
		this.superficie =superficie;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.servicios = servicios;
		this.capacidad = capacidad;
		this.fotos = fotos;
		this.cantVecesEnAlquiler = 0;
	}
	
	public void setTipoInmueble(String tipoInmueble) {//cambiar string a tipoinmueble
		this.tipoInmueble = tipoInmueble;
	}
	
	public String getTipoInmueble() {
		return tipoInmueble;
	}
	
	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}
	
	public double getSuperficie() {
		return superficie;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String getPais() {
		return pais;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void addServicio(String servicio) {
		this.servicios.add(servicio);
	}
	
	public ArrayList<String> getServicios() {
		return servicios;
	}
	
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public void addFoto(Foto foto) {
		this.fotos.add(foto);
	}
	
	public ArrayList<Foto> getFotos() {
		return fotos;
	}
	
	public int getCantVecesEnAlquiler(){
		return cantVecesEnAlquiler;
	}
	
	public void sumarCantAlquilado() {
		this.cantVecesEnAlquiler+=1;
	}
}

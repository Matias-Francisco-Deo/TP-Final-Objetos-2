package tp_final;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inmueble {
	private TipoInmueble tipoInmueble;//revisar si tipo inmueble es una clase o un string
	
	private double superficie;
	
	private String pais;
	
	private String ciudad;
	
	private String direccion;
	
	private ArrayList<String> servicios;
	
	private int capacidad;
	
	private ArrayList<Foto> fotos;
	
	private int cantVecesEnAlquiler;
	
	private Map<String,Double> promedioPuntajePorCategoria;
	
	private Ranking ranking;
	
	Inmueble(TipoInmueble tipo, double superficie, String pais, String ciudad, String direccion, ArrayList<String> servicios, int capacidad, ArrayList<Foto> fotos){
		this.tipoInmueble = tipo;
		this.superficie =superficie;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.servicios = servicios;
		this.capacidad = capacidad;
		this.fotos = fotos;
		this.cantVecesEnAlquiler = 0;
		this.promedioPuntajePorCategoria = new HashMap<>();
		this.ranking = new Ranking();
	}
	

	public TipoInmueble getTipoInmueble() {
		return tipoInmueble;
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
	
	public int getCantVecesEnAlquiler(){
		return cantVecesEnAlquiler;
	}
	
	public Map<String, Double> getPuntajesPorCategoria() {
		return this.promedioPuntajePorCategoria;                                                    //revisar metodo
	}
	
	public Ranking getRanking() {
		return ranking;
	}
	
	public ArrayList<Foto> getFotos() {
		return fotos;
	}
	
	public ArrayList<String> getServicios() {
		return servicios;
	}
	
	public void setTipoInmueble(TipoInmueble tipoInmueble) {//cambiar string a tipoinmueble?
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
	
	public void addServicio(String servicio) {
		this.servicios.add(servicio);
	}
	
	public void addFoto(Foto foto) {
		this.fotos.add(foto);
	}
	
	public void addPuntajePorCategoria(String temporada, Double precio) {
		promedioPuntajePorCategoria.put(temporada, precio);
	}
	
	public void sumarCantAlquilado() {
		this.cantVecesEnAlquiler+=1;
	}
}

package tp_final.sitioweb;

import java.util.ArrayList;
import java.util.List;

import tp_final.AlquilerPlaceholder;
import tp_final.InmueblePlaceholder;
import tp_final.InquilinoPlaceholder;
import tp_final.PropietarioPlaceholder;

public class SitioWeb {

	private List<InquilinoPlaceholder> inquilinos;
	private List<PropietarioPlaceholder> propietarios;

	public SitioWeb() {
		this.setInquilinos(new ArrayList<InquilinoPlaceholder>());
		this.setPropietarios(new ArrayList<PropietarioPlaceholder>());
	}

	public void registrarInquilino(InquilinoPlaceholder inquilino) {
		this.getInquilinos().add(inquilino);

	}

	public List<InquilinoPlaceholder> getInquilinos() {
		return this.inquilinos;
	}

	private void setInquilinos(ArrayList<InquilinoPlaceholder> inquilinos) {
		this.inquilinos = inquilinos;
	}

	public void registrarPropietario(PropietarioPlaceholder propietario) {
		this.getPropietarios().add(propietario);

	}

	public List<PropietarioPlaceholder> getPropietarios() {
		return propietarios;
	}

	private void setPropietarios(ArrayList<PropietarioPlaceholder> propietarios) {
		this.propietarios = propietarios;
	}

	public List<AlquilerPlaceholder> getAlquileres() {

		return this.getPropietarios().stream().flatMap(propietario -> propietario.getAlquileres().stream()).toList();
	}

	public List<InmueblePlaceholder> getInmuebles() {

		return this.getPropietarios().stream().flatMap(propietario -> propietario.getInmuebles().stream()).toList();
	}

}

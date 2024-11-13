package tp_final.ranking;

import java.util.ArrayList;
import java.util.List;

public class GestorDeRanking {
	private List<Ranking> rankeos = new ArrayList<Ranking>();

	// ------------------------------------------------------------

	private List<Ranking> getRankeos() {
		return this.rankeos;
	}

	private List<Ranking> getRankeosDeCategoria(String categoria) {
		return this.getRankeos().stream().filter(r -> r.getCategoria().equals(categoria)).toList();
	}

	// ------------------------------------------------------------
	// RECIBIR RANKEO, PUNTAJES Y COMENTARIOS
	// ------------------------------------------------------------

	public void recibirRankeo(Ranking rankeo) {
		this.getRankeos().add(rankeo);
	}

	public List<Integer> getPuntajesDeCategoria(String categoria) {
		return this.getRankeosDeCategoria(categoria).stream().map(r -> r.getPuntaje()).toList();
	}

	public double getPuntajePromedioDeCategoria(String categoria) {
		return this.getRankeosDeCategoria(categoria).stream().mapToInt(r -> r.getPuntaje()).average().orElse(0);
	}

	public double getPuntajePromedioTotal() {
		return this.getRankeos().stream().mapToInt(r -> r.getPuntaje()).average().orElse(0);
	}

	public List<String> getComentariosDeCategoria(String categoria) {
		return this.getRankeosDeCategoria(categoria).stream().map(r -> r.getComentario()).toList();
	}

	public List<String> getTodosLosComentarios() {
		return this.getRankeos().stream().map(r -> r.getComentario()).toList();
	}

	// ------------------------------------------------------------
}

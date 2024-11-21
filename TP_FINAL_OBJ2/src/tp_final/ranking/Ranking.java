package tp_final.ranking;

import tp_final.usuarios.Usuario;

public class Ranking {
	private Usuario autor;
	private String categoria;
	private int puntaje; // 1 <= PUNTAJE <= 5
	private String comentario;

	// ------------------------------------------------------------

	public Ranking(Usuario autor, String categoria, int puntaje, String comentario) {
		this.setAutor(autor);
		this.setCategoria(categoria);
		this.setPuntaje(puntaje);
		this.setComentario(comentario);
	}

	// ------------------------------------------------------------
	// AUTOR, CATEGORIA, PUNTAJE Y COMENTARIO
	// ------------------------------------------------------------

	public Usuario getAutor() {
		return this.autor;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public int getPuntaje() {
		return this.puntaje;
	}

	public String getComentario() {
		return this.comentario;
	}

	private void setAutor(Usuario autor) {
		this.autor = autor;
	}

	private void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	private void setPuntaje(int puntaje) {
		puntaje = comprobarPuntaje(puntaje);
		this.puntaje = puntaje;
	}

	private int comprobarPuntaje(int puntaje) {
		// EVITA PUNTAJES MENORES A 1 Y MAYORES A 5
		if (puntaje < 1) {
			puntaje = 1;
		} else if (puntaje > 5) {
			puntaje = 5;
		}
		return puntaje;
	}

	private void setComentario(String comentario) {
		this.comentario = comentario;
	}

	// ------------------------------------------------------------
}

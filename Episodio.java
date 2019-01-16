import java.io.Serializable;

public class Episodio implements Serializable, Comparable<Episodio>{
	private int numero;
	private String titulo;
	private boolean visto;
	
	@Override
	public int compareTo(Episodio otro) {
		return this.numero-otro.numero;
	}
	
	public Episodio(int numero, String titulo, boolean visto) {
		this.numero = numero;
		this.titulo = titulo;
		this.visto = visto;
	}

	public Episodio(int numero) {
		this.numero = numero;
	}

	public void setVisto(boolean visto) {
		this.visto = visto;
	}

	public int getNumero() {
		return numero;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public boolean isVisto() {
		return this.visto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Episodio other = (Episodio) obj;
		if (numero != other.numero)
			return false;
		return true;
	}

}
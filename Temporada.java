import java.io.Serializable;
import java.util.TreeSet;

public class Temporada implements Serializable, Comparable<Temporada>{
	private int numero;
	private boolean vista;
	private String resumen;
	private TreeSet<Episodio> episodios;

	public Temporada(int numero, boolean vista, String resumen) {
		this.numero = numero;
		this.vista = vista;
		this.resumen = resumen;
		this.episodios=new TreeSet<Episodio>();
	}

	public Temporada(int numero) {
		this.numero=numero;
	}

	public void setVista(boolean vista) {
		this.vista = vista;
	}

	public int getNumero() {
		return numero;
	}

	public TreeSet<Episodio> getEpisodios() {
		return this.episodios;
	}

	public boolean isVista() {
		return this.vista;
	}

	public String getResumen() {
		return this.resumen;
	}

	@Override
	public int compareTo(Temporada otra) {
		return this.numero-otra.numero;
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
		Temporada other = (Temporada) obj;
		if (numero != other.numero)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Temporada número "+this.numero;
	}

	public void setVistaCompleta(boolean estado) {
		this.setVista(estado);
		for(Episodio episodio:this.episodios) 
			episodio.setVisto(estado);
	}
}

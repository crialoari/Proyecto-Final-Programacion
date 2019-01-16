import java.io.Serializable;
import java.util.HashMap;
import java.util.TreeSet;

public class Modelo extends HashMap<Serie,TreeSet<Temporada>> implements Serializable {
	private String cuenta;
	private boolean modificado;

	public Modelo(String cuenta) {
		this.cuenta=cuenta;
		this.modificado=true;
	}

	public String getCuenta() {
		return cuenta;
	}

	public boolean isModificado() {
		return modificado;
	}

	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	public String getInfo(Serie serie) {
		String informacion="Serie: "+serie.getNombre();
		for(Temporada temporada:this.get(serie)) {
			informacion+="\n   Temporada "+temporada.getNumero()+":";
			if(temporada.isVista()) 
				informacion+=" [vista]\n";
			else
				informacion+=" [no vista]\n";
			if(!temporada.getResumen().isEmpty())
				informacion+="   ("+temporada.getResumen()+")\n";
			for(Episodio episodio:temporada.getEpisodios()) {
				informacion+="     Episodio "+episodio.getNumero()+" - "+episodio.getTitulo();
				if(episodio.isVisto()) 
					informacion+=" [visto]\n";
				else
					informacion+=" [no visto]\n";
			}
		}
		return informacion;
	}

	public String getTextoExportacion(Serie serie) {
		String informacion="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<SERIE NOMBRE=\""+serie.getNombre()+"\">\n";
		for(Temporada temporada:this.get(serie)) {
			informacion+="<TEMPORADA NUMERO=\""+temporada.getNumero()+"\" resumen=\""+temporada.getResumen()+"\">\n";
			for(Episodio episodio:temporada.getEpisodios()) {
				informacion+="<EPISODIO NUMERO=\""+episodio.getNumero()+"\" TITULO=\""+episodio.getTitulo()+"\"/>\n";
			}
			informacion+="</TEMPORADA>\n";
		}
		informacion+="</SERIE>";
		return informacion;
	}

}

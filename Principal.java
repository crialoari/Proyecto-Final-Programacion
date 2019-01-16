import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Principal {
	private Modelo modelo;
	private Vista vista;

	public static void main(String[] args) {
		new Principal();
	}

	public Principal() {
		this.vista=new Vista(this);
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void crearCuenta(String cuenta) {
		this.modelo=new Modelo(cuenta);
	}

	public void iniciarSesion(File archivo) throws ClassNotFoundException, IOException {
		FileInputStream stream = new FileInputStream(archivo);
		ObjectInputStream lector = new ObjectInputStream(stream);
		Modelo modeloLeido = (Modelo)lector.readObject();
		this.modelo=modeloLeido;
		lector.close();
	}

	public boolean haycambios() {
		return this.modelo.isModificado();
	}

	public void guardarCuenta(File archivo) throws IOException {
		this.modelo.setModificado(false);
		FileOutputStream stream = new FileOutputStream(archivo);
		ObjectOutputStream escritor = new ObjectOutputStream(stream);
		escritor.writeObject(this.modelo);
		escritor.close();
	}

	public boolean exportarSerie(File fichero, Serie serie) throws IOException {
		if(this.modelo.containsKey(serie)) {
			FileWriter escritor=new FileWriter(fichero);
			escritor.write(this.modelo.getTextoExportacion(serie));
			escritor.close();
			return true;
		}
		else
			return false;
	}

	public boolean importarSerie(File archivo) {
		if (this.analizarXML(archivo)) {
			this.modelo.setModificado(true);
			return true;
		}
		else return false;
	}

	private boolean analizarXML(File file) {
		//suponemos que es un XML bien formado y que el usuario cargará un archivo exportado desde la aplicacion
		//solo controla que el nodo principal sera SERIE, puede fallar si los hijos no son TEMPORADA y los hijos de estos no son EPISODIO
		
		try {
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document archivo = dBuilder.parse(file);

			if(archivo.getDocumentElement().getNodeName()=="SERIE") {
				Serie serieImportada;
				TreeSet<Temporada> temporadasImportadas=new TreeSet<Temporada>();
				
				if (archivo.getDocumentElement().hasAttributes()) {
					Node atributoNombre=archivo.getDocumentElement().getAttributes().item(0);
					
					if(atributoNombre.getNodeValue().isEmpty()) 
						return false;
					
					serieImportada=new Serie(atributoNombre.getNodeValue());//SERIE
					
					if (archivo.hasChildNodes()) {//si serie tiene temporadas...
						NodeList temporadas=archivo.getFirstChild().getChildNodes();//los nodos de serie: TEMPORADA
						for (int i=0; i<temporadas.getLength(); i++) {//para cada temporada...
							Node temporada = temporadas.item(i);
							if (temporada.hasAttributes()) {//temporada tiene numero y resumen
								int numeroTemp=Integer.parseInt(temporada.getAttributes().item(0).getNodeValue());//primero, numero
								String resumen=temporada.getAttributes().item(1).getNodeValue();//segundo, resumen
								Temporada temporadaImportada=new Temporada(numeroTemp,false,resumen);

								if (temporada.hasChildNodes()) { //si temporada tiene episodios...
									NodeList episodios=temporada.getChildNodes();//los nodos de TEMPORADA: EPISODIOS
									for (int y=0; y<episodios.getLength(); y++) {
										Node episodio = episodios.item(y);
										if (episodio.hasAttributes()) { //el episodio tiene nombre y titulo
											int numeroEpi=Integer.parseInt(episodio.getAttributes().item(0).getNodeValue());//primero, numero
											String titulo=episodio.getAttributes().item(1).getNodeValue();//segundo, titulo
											Episodio episodioImportado=new Episodio(numeroEpi, titulo, false);
											temporadaImportada.getEpisodios().add(episodioImportado); //agregar episodio a temporada
										}
									} //fin del for: todos los episodios analizados
									
								}// fin del if: agregar la temporada
								temporadasImportadas.add(temporadaImportada);
							}
						}//fin for: analizadas todas las temporadas
						this.modelo.put(serieImportada, temporadasImportadas); //agregar serie con temporadas al modelo
						return true;
					}
					return this.agregarSerie(serieImportada.getNombre()); //fin del if: la SERIE no tiene TEMPORADAS
				} else return false; //fin del if: la SERIE no tiene atributos(nombre)
			}else return false; //fin del if: el nodo principal NO es una serie

		} catch (NumberFormatException e) { //cualquier excepcion hace fallar la exportacion
			return false;
		} catch (DOMException e) {
			return false;
		} catch (ParserConfigurationException e) {
			return false;
		} catch (SAXException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean agregarSerie(String nombreSerie) {
		if(!this.modelo.containsKey(new Serie(nombreSerie))) {
			this.modelo.put(new Serie(nombreSerie), new TreeSet<Temporada>());
			this.modelo.setModificado(true);
			return true;
		}
		else
			return false;
	}

	public boolean agregarTemporada(Serie serie, int numero, boolean vista, String resumen) {
		if(this.modelo.get(serie).add(new Temporada(numero, vista, resumen))) {
			this.modelo.setModificado(true);
			return true;
		}
		else
			return false;
	}

	public boolean agregarEpidosio(Serie serie, Temporada otraTemporada, int numero, boolean visto, String titulo) {
		Temporada temporada=this.getTemporada(serie, otraTemporada);
		if(temporada.getEpisodios().add(new Episodio(numero, titulo, visto))) {
			this.modelo.setModificado(true);
			this.comprobarTemporada(temporada);
			return true;
		}
		return false;
	}

	public void comprobarTemporada(Temporada temporada) {
		boolean temporadaVista=true;
		for(Episodio episodio:temporada.getEpisodios())
			if(!episodio.isVisto())
				temporadaVista=false;
		temporada.setVista(temporadaVista);
	}

	public Temporada getTemporada(Serie serie, Temporada otraTemporada) {
		for(Temporada temporada:this.modelo.get(serie))
			if(temporada.equals(otraTemporada)) 
				return temporada;
		return null;
	}

	public Episodio getEpisodio(Serie serie, Temporada temporada, Episodio otroEpisodio) {
		for(Episodio episodio:this.getTemporada(serie, temporada).getEpisodios())
			if(episodio.equals(otroEpisodio))
				return episodio;
		return null;
	}

	public void borrarEpisodio(Serie serie, Temporada temporada, Episodio episodio) {
		this.getTemporada(serie, temporada).getEpisodios().remove(episodio);
		this.modelo.setModificado(true);
	}

	public void borrarTemporada(Serie serie, Temporada temporada) {
		this.modelo.get(serie).remove(temporada);
		this.modelo.setModificado(true);
	}

	public void borrarSerie(String nombreSerie) {
		this.modelo.remove(new Serie(nombreSerie));
		this.modelo.setModificado(true);
	}

	public void setSerieVista(Serie serie, boolean estado) {
		for(Temporada temporada: this.modelo.get(serie))
			temporada.setVistaCompleta(estado);
	}

}

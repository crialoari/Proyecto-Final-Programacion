import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Vista {
	private Principal controlador;
	private VentanaPpal ventana;
	private DBienvenida dialogoBienvenida;
	private DAddSerie dialogoAddSerie;
	private DAddTemp dialogoAddTemp;
	private DAddEpisodios dialogoAddEpisodios;
	private DEditar dialogoEditar;
	private JFileChooser selectorFichero;
	private FileNameExtensionFilter filtroUsuario;
	private FileNameExtensionFilter filtroXML;

	public Vista(Principal controlador) {
		this.controlador = controlador;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error en el UIManager","Error",JOptionPane.ERROR_MESSAGE);
		} catch (InstantiationException e) {
			JOptionPane.showMessageDialog(null, "Error en el UIManager","Error",JOptionPane.ERROR_MESSAGE);
		} catch (IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error en el UIManager","Error",JOptionPane.ERROR_MESSAGE);
		} catch (UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null, "Error en el UIManager","Error",JOptionPane.ERROR_MESSAGE);
		}
		this.selectorFichero=new JFileChooser();
		this.filtroUsuario=new FileNameExtensionFilter("Cuenta Cuarta Pared", "4puser");
		this.filtroXML=new FileNameExtensionFilter("Archivos XML","xml");
		this.selectorFichero.addChoosableFileFilter(this.filtroUsuario);
		this.selectorFichero.addChoosableFileFilter(this.filtroXML);
		this.ventana=new VentanaPpal(this);
	}

	public Modelo getModelo() {
		return this.controlador.getModelo();
	}

	public void error(String mensaje, Window padre)
	{
		JOptionPane.showMessageDialog(padre, mensaje,"Error",JOptionPane.ERROR_MESSAGE);
	}

	public int confirmacion(String mensaje, Window padre, String titulo)
	{
		return JOptionPane.showConfirmDialog(padre, mensaje, titulo, JOptionPane.YES_NO_CANCEL_OPTION);
	}

	public int yesno(String mensaje, Window padre, String titulo) {
		return JOptionPane.showConfirmDialog(padre, mensaje, titulo, JOptionPane.YES_NO_OPTION);
	}

	public void msg(String mensaje, Window padre)
	{
		JOptionPane.showMessageDialog(padre, mensaje,"Correcto",JOptionPane.INFORMATION_MESSAGE);
	}

	public void mostrarDialogoBienvenida() {
		this.dialogoBienvenida=new DBienvenida(this);
		this.dialogoBienvenida.setVisible(true);
	}

	public void crearCuenta(String cuenta) {
		this.controlador.crearCuenta(cuenta);
	}

	public boolean iniciarSesion() {
		this.selectorFichero.setFileFilter(this.filtroUsuario);
		this.selectorFichero.setSelectedFile(new File(""));
		int opcion = this.selectorFichero.showOpenDialog(this.dialogoBienvenida);
		if(opcion == JFileChooser.APPROVE_OPTION)
		{
			if(!this.selectorFichero.getSelectedFile().getAbsolutePath().endsWith(".4puser")) {
				this.error("Archivo no válido", this.dialogoBienvenida);
				return false; 
			}
			else {
				try {
					this.controlador.iniciarSesion(this.selectorFichero.getSelectedFile());
					this.msg("¡Bienvenid@ "+this.controlador.getModelo().getCuenta()+"!", this.dialogoBienvenida);
					return true;
				} catch (ClassNotFoundException e) {
					this.error("Error al cargar cuenta.", this.dialogoBienvenida);
					return false;
				} catch (IOException e) {
					this.error("Error al cargar cuenta.", this.dialogoBienvenida);
					return false;
				}
			}
		}
		else {
			this.error("Para iniciar sesión necesita cargar un archivo de cuenta", this.dialogoBienvenida);
			return false;
		}
	}

	public boolean hayCambios() {
		return this.controlador.haycambios();
	}

	public void guardarCuenta() {
		this.selectorFichero.setFileFilter(this.filtroUsuario);
		this.selectorFichero.setSelectedFile(new File(this.controlador.getModelo().getCuenta()+".4puser"));
		int opcion = this.selectorFichero.showSaveDialog(this.ventana); 
		if(opcion == JFileChooser.APPROVE_OPTION){
			if(!this.selectorFichero.getSelectedFile().getAbsolutePath().endsWith(".4puser"))
				this.selectorFichero.setSelectedFile(new File(this.selectorFichero.getSelectedFile().getAbsolutePath()+".4puser"));
			try {
				this.controlador.guardarCuenta(this.selectorFichero.getSelectedFile());
				this.msg("Cuenta guardada.", this.ventana);
			} catch (IOException e) {
				this.error("Error al guardar cuenta.", this.ventana);
			}
		}
	}

	public void exportarSerie(String nombreSerie) {
		this.selectorFichero.setFileFilter(this.filtroXML);
		this.selectorFichero.setSelectedFile(new File(nombreSerie+".xml"));
		int opcion = this.selectorFichero.showSaveDialog(this.ventana);
		if(opcion == JFileChooser.APPROVE_OPTION){
			if(!this.selectorFichero.getSelectedFile().getAbsolutePath().endsWith(".xml"))
				this.selectorFichero.setSelectedFile(new File(this.selectorFichero.getSelectedFile().getAbsolutePath()+".xml"));
			try {
				if(this.controlador.exportarSerie(this.selectorFichero.getSelectedFile(),new Serie(nombreSerie)))
					this.msg("Serie exportada.", this.ventana);
				else
					this.error("Error al exportar.", this.ventana);
			} catch (IOException e) {
				this.error("Error al exportar.", this.ventana);
			}
		}
	}

	public void importarSerie() {
		this.selectorFichero.setFileFilter(this.filtroXML);
		this.selectorFichero.setSelectedFile(new File(""));
		int opcion = this.selectorFichero.showOpenDialog(this.ventana);
		if(opcion == JFileChooser.APPROVE_OPTION){
			if(this.selectorFichero.getSelectedFile().getAbsolutePath().endsWith(".xml"))
				if(this.controlador.importarSerie(this.selectorFichero.getSelectedFile()))
					this.msg("Serie añadida.", this.ventana);
				else
					this.error("Error al importar la serie.", this.ventana);

			else
				this.error("Archivo no válido.", this.ventana);
		}
	}

	public boolean agregarSerie(String nombre) {
		return this.controlador.agregarSerie(nombre);
	}

	public boolean agregarTemporada(Serie serie, int numero, boolean vista, String resumen) {
		return this.controlador.agregarTemporada(serie, numero, vista, resumen);
	}

	public void mostrarDialogoAgregarSerie() {
		this.dialogoAddSerie=new DAddSerie(this);
		this.dialogoAddSerie.setVisible(true);
	}

	public void mostrarDialogoAgregarTemporada() {
		this.dialogoAddTemp=new DAddTemp(this);
		this.dialogoAddTemp.setVisible(true);
	}

	public void mostrarDialogoAgregarEpisodios() {
		this.dialogoAddEpisodios=new DAddEpisodios(this);
		this.dialogoAddEpisodios.setVisible(true);
	}

	public void mostrarDialogoEditar() {
		this.dialogoEditar=new DEditar(this);
		this.dialogoEditar.setVisible(true);
	}

	public void borrarSerie(String nombreSerie) {
		this.controlador.borrarSerie(nombreSerie);
	}

	public boolean agregarEpisodio(Serie serie, Temporada temporada, int numero, boolean visto, String titulo) {
		return this.controlador.agregarEpidosio(serie, temporada, numero, visto, titulo);
	}

	public Temporada getTemporada(Serie serie, Temporada temporada) {
		return this.controlador.getTemporada(serie, temporada);
	}

	public Episodio getEpisodio(Serie serie, Temporada temporada, int numero) {
		return this.controlador.getEpisodio(serie, temporada, new Episodio(numero));
	}

	public void comprobarTemporada(Temporada temporada) {
		this.controlador.comprobarTemporada(temporada);
	}

	public void borrarEpisodio(Serie serie, Temporada temporada, Episodio episodio) {
		this.controlador.borrarEpisodio(serie, temporada, episodio);
	}

	public boolean haySeries() {
		return !this.getModelo().isEmpty();
	}

	public void borrarTemporada(Serie serie, Temporada temporada) {
		this.controlador.borrarTemporada(serie, temporada);
	}

	public void setSerieVista(Serie serie, boolean estado) {
		this.controlador.setSerieVista(serie,estado);
	}

}

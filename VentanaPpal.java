import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import java.awt.Cursor;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaPpal extends JFrame {
	private Vista vista;
	private JPanel contentPane;
	private JList listaSeries;
	private JTextArea areaInfo;
	public VentanaPpal(Vista vista) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				salir();
			}
		});
		this.vista=vista;
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setMinimumSize(new Dimension(700, 500));
		setTitle("Cuarta Pared - Gestiona tus series");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPpal.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(300, 100, 695, 490);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCuenta = new JMenu("Cuenta");
		menuBar.add(mnCuenta);

		JMenuItem mntmGuardarCambios = new JMenuItem("Guardar cuenta");
		mntmGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarCuenta();
			}
		});
		mnCuenta.add(mntmGuardarCambios);

		JMenuItem mntmEntrar = new JMenuItem("Cerrar sesi\u00F3n");
		mntmEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		mnCuenta.add(mntmEntrar);

		JSeparator separator = new JSeparator();
		mnCuenta.add(separator);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		mnCuenta.add(mntmSalir);

		JMenu mnGestin = new JMenu("Gesti\u00F3n");
		menuBar.add(mnGestin);

		JMenuItem mntmAgregarSerie = new JMenuItem("Agregar serie");
		mntmAgregarSerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarSerie();
			}
		});

		JMenuItem mntmEditar = new JMenuItem("Editar series");
		mntmEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		mnGestin.add(mntmEditar);

		JSeparator separator_1 = new JSeparator();
		mnGestin.add(separator_1);
		mnGestin.add(mntmAgregarSerie);

		JMenuItem mntmExportarSerie = new JMenuItem("Exportar serie");
		mntmExportarSerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exportarSerie();
			}
		});
		
				JMenuItem mntmAgregarTemporada = new JMenuItem("Agregar temporada");
				mntmAgregarTemporada.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						agregarTemporada();
					}
				});
				mnGestin.add(mntmAgregarTemporada);
		
				JMenuItem mntmAgregarEpisodios = new JMenuItem("Agregar episodios");
				mntmAgregarEpisodios.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						agregarEpisodios();
					}
				});
				mnGestin.add(mntmAgregarEpisodios);
		
		JSeparator separator_2 = new JSeparator();
		mnGestin.add(separator_2);
		
		JMenuItem mntmImportarSerie = new JMenuItem("Importar serie");
		mntmImportarSerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importarSerie();
			}
		});
		mnGestin.add(mntmImportarSerie);
		mnGestin.add(mntmExportarSerie);

		JMenuItem mntmBorrarSerie = new JMenuItem("Borrar serie");
		mntmBorrarSerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarSerie();
			}
		});
		
		JSeparator separator_3 = new JSeparator();
		mnGestin.add(separator_3);
		mnGestin.add(mntmBorrarSerie);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setForeground(SystemColor.controlShadow);
		scrollPane.setBackground(SystemColor.controlShadow);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setMinimumSize(new Dimension(250, 23));
		splitPane.setLeftComponent(scrollPane);

		listaSeries = new JList();
		listaSeries.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listaSeries.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				cambioSeleccion();
			}
		});
		listaSeries.setSelectionBackground(new Color(0, 51, 51));
		listaSeries.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaSeries.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listaSeries.setBackground(SystemColor.menu);
		listaSeries.setForeground(SystemColor.desktop);
		scrollPane.setViewportView(listaSeries);

		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);

		areaInfo = new JTextArea();
		areaInfo.setSelectionColor(SystemColor.controlShadow);
		areaInfo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		areaInfo.setEditable(false);
		areaInfo.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		areaInfo.setBackground(new Color(248, 248, 255));
		scrollPane_1.setViewportView(areaInfo);
		this.setVisible(true);
		this.vista.mostrarDialogoBienvenida();
		this.actualizaListaSeries();
	}

	protected void importarSerie() {
		this.vista.importarSerie();
		this.actualizaListaSeries();
	}

	protected void exportarSerie() {
		if(this.vista.haySeries()) {
			if(!this.listaSeries.isSelectionEmpty())
				this.vista.exportarSerie((String)this.listaSeries.getSelectedValue());
			else
				this.vista.error("Selecciona una serie para exportar.", this);
		}
		else
			this.vista.error("No tienes ninguna serie para exportar.", this);
	}

	protected void editar() {
		if(this.vista.haySeries()) {
			this.vista.mostrarDialogoEditar();
			this.actualizaListaSeries();
		}
		else
			this.vista.error("No tienes ninguna serie para editar.", this);
	}

	protected void agregarSerie() {
		this.vista.mostrarDialogoAgregarSerie();
		this.actualizaListaSeries();
	}

	protected void borrarSerie() {
		if(this.vista.haySeries()) {
			if(!this.listaSeries.isSelectionEmpty()) {
				int opcion=this.vista.confirmacion("Vas a borrar completamente la serie "+(String)this.listaSeries.getSelectedValue()+". ¿Estas segur@?", this, "Borrar serie...");
				if(opcion == JOptionPane.OK_OPTION) {
					this.vista.borrarSerie((String)this.listaSeries.getSelectedValue());
					this.vista.msg("Serie borrada.", this);
					this.actualizaListaSeries();
				}
			}
			else
				this.vista.error("Selecciona una serie para borrar.", this);
		}
		else
			this.vista.error("No tienes ninguna serie para borrar.", this);

	}

	protected void agregarTemporada() {
		if(this.vista.haySeries()) {
			this.vista.mostrarDialogoAgregarTemporada();
			this.actualizaListaSeries();
		}
		else
			this.vista.error("No tienes ninguna serie.", this);
	}

	protected void agregarEpisodios() {
		if(this.vista.haySeries()) {
			this.vista.mostrarDialogoAgregarEpisodios();
			this.actualizaListaSeries();
		}
		else
			this.vista.error("No tienes ninguna serie.", this);
	}

	protected void guardarCuenta() {
		this.vista.guardarCuenta();
	}

	protected void cerrarSesion() {
		if(this.vista.hayCambios()) {
			int opcion=this.vista.confirmacion("Has hecho modificaciones, ¿quieres salvar los cambios?", this, "Vas a cerrar sesión...");
			switch (opcion) {
			case JOptionPane.OK_OPTION:
				this.vista.guardarCuenta();
				this.vista.mostrarDialogoBienvenida();
				this.actualizaListaSeries();
				break;
			case JOptionPane.NO_OPTION:
				this.vista.mostrarDialogoBienvenida();
				this.actualizaListaSeries();
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
			}
		}
		else {
			this.vista.mostrarDialogoBienvenida();
			this.actualizaListaSeries();
		}
	}

	protected void salir() {
		if(this.vista.hayCambios()) {
			int opcion=this.vista.confirmacion("Has hecho modificaciones, ¿quieres salvar los cambios?", this, "Vas a salir...");
			switch (opcion) {
			case JOptionPane.OK_OPTION:
				this.vista.guardarCuenta();
				System.exit(0);
				break;
			case JOptionPane.NO_OPTION:
				System.exit(0);
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
			}
		}
		else {
			System.exit(0);
		}
	}

	protected void cambioSeleccion() {
		this.areaInfo.setText("");
		if(this.listaSeries.getSelectedValue()!=null)
			this.areaInfo.setText(this.vista.getModelo().getInfo(new Serie((String)this.listaSeries.getSelectedValue())));
	}

	protected void actualizaListaSeries() {
		DefaultListModel<String> model = new DefaultListModel<>();
		for(Serie serie:this.vista.getModelo().keySet())
			model.addElement(serie.getNombre());
		this.listaSeries.setModel(model);
	}
}

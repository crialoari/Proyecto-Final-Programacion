import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DEditar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Vista vista;
	private JTextArea areaInfo;
	private JComboBox<Integer> cbEpisodios;
	private JButton btverEpisodio;
	private JButton btquitarEpisodio;
	private JButton btborrarEpisodio;
	private JComboBox<Integer> cbTemporadas;
	private JButton btverTemporada;
	private JButton btborrarTemporada;
	private JButton btquitarTemporada;
	private JButton btborrarSerie;
	private JButton btquitarSerie;
	private JComboBox<String> cbSeries;
	private JButton btverSerie;

	public DEditar(Vista vista) {
		this.vista=vista;
		setMinimumSize(new Dimension(675, 470));
		setTitle("Editar series");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DEditar.class.getResource("/img/serie_small.png")));
		setModal(true);
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setBounds(310, 110, 675, 470);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel label = new JLabel("Serie:");

		cbSeries = new JComboBox<String>();
		cbTemporadas = new JComboBox<Integer>();
		cbEpisodios = new JComboBox<Integer>();

		cbTemporadas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				temporadaSeleccionada();
			}
		});

		cbSeries.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				serieSeleccionada();
			}
		});

		JLabel lblTemporada = new JLabel("Temporadas:");

		JLabel lblEpisodiosVistos = new JLabel("Episodios:");

		JLabel lblInfo = new JLabel("Informaci\u00F3n de la serie:");

		JScrollPane scrollPane = new JScrollPane();

		btverSerie = new JButton("");
		btverSerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serieVista(true);
			}
		});
		btverSerie.setIcon(new ImageIcon(DEditar.class.getResource("/img/visto.png")));

		btquitarSerie = new JButton("");
		btquitarSerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serieVista(false);
			}
		});
		btquitarSerie.setIcon(new ImageIcon(DEditar.class.getResource("/img/no_visto.png")));

		btverTemporada = new JButton("");
		btverTemporada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temporadaVista(true);
			}
		});
		btverTemporada.setIcon(new ImageIcon(DEditar.class.getResource("/img/visto.png")));

		btquitarTemporada = new JButton("");
		btquitarTemporada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temporadaVista(false);
			}
		});
		btquitarTemporada.setIcon(new ImageIcon(DEditar.class.getResource("/img/no_visto.png")));

		btverEpisodio = new JButton("");
		btverEpisodio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				episodioVisto(true);
			}
		});
		btverEpisodio.setIcon(new ImageIcon(DEditar.class.getResource("/img/visto.png")));

		btquitarEpisodio = new JButton("");
		btquitarEpisodio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				episodioVisto(false);
			}
		});
		btquitarEpisodio.setIcon(new ImageIcon(DEditar.class.getResource("/img/no_visto.png")));

		btborrarSerie = new JButton("");
		btborrarSerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarSerie();
			}
		});
		btborrarSerie.setIcon(new ImageIcon(DEditar.class.getResource("/img/borrar.png")));

		btborrarTemporada = new JButton("");
		btborrarTemporada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarTemporada();
			}
		});
		btborrarTemporada.setIcon(new ImageIcon(DEditar.class.getResource("/img/borrar.png")));

		btborrarEpisodio = new JButton("");
		btborrarEpisodio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarEpisodio();
			}
		});
		btborrarEpisodio.setIcon(new ImageIcon(DEditar.class.getResource("/img/borrar.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(cbEpisodios, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblTemporada, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblEpisodiosVistos, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
												.addComponent(cbSeries, Alignment.LEADING, 0, 238, Short.MAX_VALUE)
												.addComponent(cbTemporadas, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(btverTemporada, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
												.addComponent(btverSerie, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
												.addComponent(btverEpisodio, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(btquitarEpisodio, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
												.addComponent(btquitarTemporada, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
												.addComponent(btquitarSerie, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btborrarSerie, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(btborrarTemporada, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(btborrarEpisodio, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGap(14)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblInfo, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
						.addContainerGap())
				);
		gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(lblInfo))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addComponent(btborrarSerie, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btborrarTemporada, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(cbSeries, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(btquitarSerie, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
														.addGap(18)
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
																.addGroup(gl_contentPanel.createSequentialGroup()
																		.addComponent(lblTemporada)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(cbTemporadas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addComponent(btquitarTemporada, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addComponent(btverSerie, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btverTemporada, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
										.addGap(18)
										.addComponent(lblEpisodiosVistos)
										.addGap(6)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(btborrarEpisodio, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
												.addComponent(cbEpisodios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(btverEpisodio, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
												.addComponent(btquitarEpisodio, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
						.addContainerGap())
				);

		areaInfo = new JTextArea();
		areaInfo.setEditable(false);
		areaInfo.setSelectionColor(SystemColor.controlShadow);
		areaInfo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(areaInfo);
		contentPanel.setLayout(gl_contentPanel);
		actualizaComboSerie();
	}

	protected void actualizaComboSerie() {
		if(this.vista.haySeries()) {
			this.cbSeries.removeAllItems();
			for(Serie serie:this.vista.getModelo().keySet()) 
				this.cbSeries.addItem(serie.getNombre());
			this.actualizaInfo();
		}
		else {
			this.vista.error("No hay series que editar. Volviendo a ventana principal", this);
			this.setVisible(false);
		}
	}

	protected void serieSeleccionada() {
		this.actualizaComboTemporadas();
		this.actualizaInfo();
	}

	protected void actualizaComboTemporadas() {
		this.cbTemporadas.removeAllItems();
		if(this.cbSeries.getSelectedItem()!=null) {
			for(Temporada temporada:this.vista.getModelo().get(new Serie((String)this.cbSeries.getSelectedItem())))
				this.cbTemporadas.addItem(temporada.getNumero());
		}
	}

	protected void actualizaInfo() {
		if(this.cbSeries.getSelectedItem()!=null) {
			this.areaInfo.setText("");
			this.areaInfo.setText(this.vista.getModelo().getInfo(new Serie((String)this.cbSeries.getSelectedItem())));
		}
	}

	protected void temporadaSeleccionada() {
		this.cbEpisodios.removeAllItems();
		if(this.cbTemporadas.getSelectedItem()!=null) {
			Serie serie=new Serie((String)this.cbSeries.getSelectedItem());
			Temporada temporada=new Temporada((int)this.cbTemporadas.getSelectedItem());
			for(Episodio episodio:this.vista.getTemporada(serie, temporada).getEpisodios())
				this.cbEpisodios.addItem(episodio.getNumero());
		}
	}

	protected void episodioVisto(boolean visto) {
		if(this.cbEpisodios.getSelectedItem()!=null) {
			Serie serie=new Serie((String)this.cbSeries.getSelectedItem());
			Temporada temporada=new Temporada((int)this.cbTemporadas.getSelectedItem());
			this.vista.getEpisodio(serie, temporada,(int)this.cbEpisodios.getSelectedItem()).setVisto(visto);
			this.vista.comprobarTemporada(this.vista.getTemporada(serie, temporada));
			this.vista.getModelo().setModificado(true);
			this.actualizaInfo();
		}
		else
			this.vista.error("No hay episodios que modificar.", this);
	}

	protected void borrarEpisodio() {
		if(this.cbEpisodios.getSelectedItem()!=null) {
			int opcion=this.vista.yesno("Vas a borrar completamente un episodio. ¿Estas segur@?", this, "Borrar episodio...");
			if(opcion == JOptionPane.OK_OPTION) {
				Serie serie=new Serie((String)this.cbSeries.getSelectedItem());
				Temporada temporada=new Temporada((int)this.cbTemporadas.getSelectedItem());
				Episodio episodio=this.vista.getEpisodio(serie, temporada,(int)this.cbEpisodios.getSelectedItem());
				this.vista.borrarEpisodio(serie, temporada, episodio);
				this.actualizaComboSerie();
			}
		}
		else
			this.vista.error("No hay episodios que modificar.", this);
	}

	protected void temporadaVista(boolean estado) {
		if(this.cbTemporadas.getSelectedItem()!=null) {
			Serie serie=new Serie((String)this.cbSeries.getSelectedItem());
			Temporada temporada=new Temporada((int)this.cbTemporadas.getSelectedItem());
			this.vista.getTemporada(serie, temporada).setVistaCompleta(estado);
			this.vista.getModelo().setModificado(true);
			this.actualizaInfo();
		}
		else
			this.vista.error("No hay temporadas que modificar.", this);
	}

	protected void borrarTemporada() {
		if(this.cbTemporadas.getSelectedItem()!=null) {
			int opcion=this.vista.yesno("Vas a borrar completamente la temporada. ¿Estas segur@?", this, "Borrar temporada...");
			if(opcion == JOptionPane.OK_OPTION) {
				Serie serie=new Serie((String)this.cbSeries.getSelectedItem());
				Temporada temporada=new Temporada((int)this.cbTemporadas.getSelectedItem());
				this.vista.borrarTemporada(serie, temporada);
				this.actualizaComboSerie();
			}

		}
		else
			this.vista.error("No hay temporadas que modificar.", this);
	}
	
	protected void serieVista(boolean estado) {
		Serie serie=new Serie((String)this.cbSeries.getSelectedItem());
		this.vista.setSerieVista(serie, estado);
		this.vista.getModelo().setModificado(true);
		this.actualizaInfo();
		
	}

	protected void borrarSerie() {
		int opcion=this.vista.yesno("Vas a borrar completamente la serie "+(String)this.cbSeries.getSelectedItem()+". ¿Estas segur@?", this, "Borrar serie...");
		if(opcion == JOptionPane.OK_OPTION) {
			this.vista.borrarSerie((String)this.cbSeries.getSelectedItem());
			this.vista.msg("Serie borrada.", this);
			this.actualizaComboSerie();
		}
	}



}

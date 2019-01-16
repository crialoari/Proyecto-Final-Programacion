import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DAddEpisodios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Vista vista;
	private JTextField campoNumero;
	private JTextField campoTitulo;
	private JComboBox<String> cbSeries;
	private JComboBox<Integer> cbTemporada;
	private JCheckBox chbVisto;
	public DAddEpisodios(Vista vista) {
		setModal(true);
		setTitle("Agregar episodios");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DAddEpisodios.class.getResource("/img/serie_small.png")));
		setResizable(false);
		this.vista=vista;
		setBounds(450, 180, 400, 310);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel label = new JLabel("Serie:");
		cbSeries = new JComboBox<String>();
		cbTemporada = new JComboBox<Integer>();
		cbSeries.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				actualizaComboTemporadas();
			}
		});

		JLabel lblTemporada = new JLabel("Temporada:");
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel label_1 = new JLabel("N\u00FAmero:");

		campoNumero = new JTextField();
		campoNumero.setSelectionColor(new Color(0, 51, 51));
		campoNumero.setColumns(10);
		campoNumero.setBackground(new Color(245, 245, 245));

		chbVisto = new JCheckBox("Visto");

		JLabel lblTtulo = new JLabel("T\u00EDtulo:");

		campoTitulo = new JTextField();
		campoTitulo.setSelectionColor(new Color(0, 51, 51));
		campoTitulo.setColumns(10);
		campoTitulo.setBackground(new Color(245, 245, 245));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1)
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(campoNumero, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(chbVisto))
								.addComponent(lblTtulo, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(campoTitulo, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(168, Short.MAX_VALUE))
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(label_1)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(campoNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(chbVisto))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblTtulo)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(campoTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(43, Short.MAX_VALUE))
				);
		panel.setLayout(gl_panel);

		JButton btnAgregarEspisodio = new JButton("Agregar espisodio");
		btnAgregarEspisodio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarEpisodio();
			}
		});
		btnAgregarEspisodio.setMargin(new Insets(5, 5, 5, 5));
		btnAgregarEspisodio.setIconTextGap(5);
		btnAgregarEspisodio.setForeground(Color.BLACK);
		btnAgregarEspisodio.setBackground(SystemColor.menu);

		JButton btnAgregarVariosEspisodios = new JButton("Importar desde archivo");
		btnAgregarVariosEspisodios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				importarDesdeArchivo();
			}
		});
		btnAgregarVariosEspisodios.setMargin(new Insets(5, 5, 5, 5));
		btnAgregarVariosEspisodios.setIconTextGap(5);
		btnAgregarVariosEspisodios.setForeground(SystemColor.controlDkShadow);
		btnAgregarVariosEspisodios.setBackground(SystemColor.menu);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(btnAgregarEspisodio, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnAgregarVariosEspisodios, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTemporada, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbTemporada, 0, 364, Short.MAX_VALUE)
								.addComponent(cbSeries, 0, 364, Short.MAX_VALUE))
						.addContainerGap())
				);
		gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(label)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cbSeries, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblTemporada)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cbTemporada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAgregarEspisodio, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAgregarVariosEspisodios, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(57, Short.MAX_VALUE))
				);
		contentPanel.setLayout(gl_contentPanel);
		actualizaComboSerie();
	}

	protected void importarDesdeArchivo() {
		this.setVisible(false);
		this.vista.importarSerie();
	}

	protected void agregarEpisodio() {
		if(this.cbTemporada.getSelectedItem()==null)
			this.vista.error("Esta serie no tiene temporadas.", this);
		else
			try {
				int numero=Integer.parseInt(this.campoNumero.getText());
				if(this.vista.agregarEpisodio(new Serie((String)this.cbSeries.getSelectedItem()), new Temporada((int)this.cbTemporada.getSelectedItem()),
						numero, this.chbVisto.isSelected(), this.campoTitulo.getText())) {
					this.vista.msg("Episodio agregado.", this);
					this.cbSeries.setSelectedIndex(0);
					this.campoNumero.setText("");
					this.campoTitulo.setText("");
					this.chbVisto.setSelected(false);
				}
				else
					this.vista.error("El episodio ya está almacenado.", this);
			} catch (NumberFormatException e) {
				this.vista.error("Debe introducir un dígito en el campo Número.", this);
			}

	}

	private void actualizaComboSerie() {
		this.cbSeries.removeAllItems();
		for(Serie serie:this.vista.getModelo().keySet())
			this.cbSeries.addItem(serie.getNombre());
	}

	private void actualizaComboTemporadas() {
		this.cbTemporada.removeAllItems();
		for(Temporada temporada:this.vista.getModelo().get(new Serie((String)this.cbSeries.getSelectedItem())))
			this.cbTemporada.addItem(temporada.getNumero());
	}

}

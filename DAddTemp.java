import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DAddTemp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField campoNumero;
	private Vista vista;
	private JButton btAgregarTemp;
	private JButton btAgregarTempArchivo;
	private JCheckBox chbVista;
	private JTextArea areaResumen;
	private JComboBox<String> cbSeries;

	public DAddTemp(Vista vista) {
		this.vista=vista;
		setModal(true);
		setResizable(false);
		setTitle("Agregar temporada");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DAddTemp.class.getResource("/img/serie_small.png")));
		setBounds(395, 180, 510, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		cbSeries = new JComboBox<String>();
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JScrollPane scrollPane = new JScrollPane();
		JLabel label = new JLabel("N\u00FAmero:");
		campoNumero = new JTextField();
		campoNumero.setSelectionColor(new Color(0, 51, 51));
		campoNumero.setColumns(10);
		campoNumero.setBackground(SystemColor.window);
		chbVista = new JCheckBox("Vista");
		
		JLabel lblResumenopcional = new JLabel("Resumen (opcional):");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
							.addContainerGap())
						.addComponent(label)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(campoNumero, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(chbVista)
							.addContainerGap(168, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblResumenopcional)
							.addContainerGap(414, Short.MAX_VALUE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(campoNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chbVista))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblResumenopcional)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		areaResumen = new JTextArea();
		areaResumen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		areaResumen.setSelectionColor(new Color(0, 51, 51));
		areaResumen.setBackground(new Color(245, 245, 245));
		scrollPane.setViewportView(areaResumen);
		panel.setLayout(gl_panel);
		btAgregarTemp = new JButton("Agregar temporada");
		btAgregarTemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarTemporada();
			}
		});
		btAgregarTemp.setMargin(new Insets(5, 5, 5, 5));
		btAgregarTemp.setIconTextGap(5);
		btAgregarTemp.setForeground(Color.BLACK);
		btAgregarTemp.setBackground(SystemColor.menu);
		btAgregarTempArchivo = new JButton("Importar desde archivo");
		btAgregarTempArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				importarDesdeArchivo();
			}
		});
		btAgregarTempArchivo.setMargin(new Insets(5, 5, 5, 5));
		btAgregarTempArchivo.setIconTextGap(5);
		btAgregarTempArchivo.setForeground(SystemColor.controlDkShadow);
		btAgregarTempArchivo.setBackground(SystemColor.menu);
		JLabel lblSerie = new JLabel("Serie:");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSerie, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbSeries, GroupLayout.PREFERRED_SIZE, 474, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 474, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btAgregarTemp, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btAgregarTempArchivo, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSerie)
					.addGap(6)
					.addComponent(cbSeries, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btAgregarTemp, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(btAgregarTempArchivo, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		actualizaComboSerie();
	}

	protected void importarDesdeArchivo() {
		this.setVisible(false);
		this.vista.importarSerie();
	}

	protected void agregarTemporada() {
		try {
			int numero=Integer.parseInt(this.campoNumero.getText());
			if(this.vista.agregarTemporada(new Serie((String)this.cbSeries.getSelectedItem()),numero,this.chbVista.isSelected(),this.areaResumen.getText())) {
				this.vista.msg("Temporada agregada.", this);
				this.cbSeries.setSelectedIndex(0);
				this.campoNumero.setText("");
				this.areaResumen.setText("");
				this.chbVista.setSelected(false);
			}
			else
				this.vista.error("La temporada ya está almacenada.", this);
		} catch (NumberFormatException e) {
			this.vista.error("Debe introducir un dígito en el campo Número.", this);
		}
		
	}

	private void actualizaComboSerie() {
		this.cbSeries.removeAllItems();
		for(Serie serie:this.vista.getModelo().keySet()) {
			this.cbSeries.addItem(serie.getNombre());
		}
	}
	
	
}

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
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Insets;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DAddSerie extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField campoNombre;
	private Vista vista;
	private JButton btAgregarSerie;
	private JButton btAgregarSerieArchivo;

	public DAddSerie(Vista vista) {
		setModal(true);
		this.vista=vista;
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setResizable(false);
		setTitle("Agregar serie");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DAddSerie.class.getResource("/img/serie_small.png")));
		setBounds(520, 200, 240, 215);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		campoNombre = new JTextField();
		campoNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarSerie();
			}
		});
		campoNombre.setSelectionColor(new Color(0, 51, 51));
		campoNombre.setColumns(10);
		campoNombre.setBackground(new Color(245, 245, 245));
		
		btAgregarSerie = new JButton("Agregar serie");
		btAgregarSerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarSerie();
			}
		});
		btAgregarSerie.setMargin(new Insets(5, 5, 5, 5));
		btAgregarSerie.setIconTextGap(5);
		btAgregarSerie.setBackground(SystemColor.menu);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre)
						.addComponent(campoNombre, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
						.addComponent(btAgregarSerie, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNombre)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(campoNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btAgregarSerie)
					.addContainerGap(53, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		btAgregarSerieArchivo = new JButton("Importar desde archivo");
		btAgregarSerieArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				importarDesdeArchivo();
			}
		});
		btAgregarSerieArchivo.setMargin(new Insets(5, 5, 5, 5));
		btAgregarSerieArchivo.setIconTextGap(5);
		btAgregarSerieArchivo.setForeground(SystemColor.controlDkShadow);
		btAgregarSerieArchivo.setBackground(SystemColor.menu);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(btAgregarSerieArchivo, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addComponent(btAgregarSerieArchivo, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	protected void agregarSerie() {
		if(!this.campoNombre.getText().isEmpty())
			if(this.vista.agregarSerie(this.campoNombre.getText().toUpperCase())) {
				this.vista.msg("Serie agregada.", this);
				this.campoNombre.setText("");
			}
			else
				this.vista.error("La serie ya está almacenada.", this);
		else
			this.vista.error("Escriba el nombre de la serie que quiera agregar.", this);
	}
	
	protected void importarDesdeArchivo() {
		this.setVisible(false);
		this.vista.importarSerie();
	}
}

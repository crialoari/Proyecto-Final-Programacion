import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DBienvenida extends JDialog {
	private Vista vista;
	private final JPanel contentPanel = new JPanel();
	private JTextField campoUsuario;
	private JButton btCrearCuenta;

	public DBienvenida(Vista vista) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.vista=vista;
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DBienvenida.class.getResource("/img/usuario_small.png")));
		setTitle("Bienvenido");
		setBounds(520, 200, 240, 275);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnIniciarSesin = new JButton("Iniciar sesi\u00F3n");
		btnIniciarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();
			}
		});
		btnIniciarSesin.setIcon(new ImageIcon(DBienvenida.class.getResource("/img/usuario_small.png")));
		btnIniciarSesin.setMargin(new Insets(5, 5, 5, 5));
		btnIniciarSesin.setIconTextGap(5);
		btnIniciarSesin.setBackground(SystemColor.menu);
		
		JButton btnCancelar = new JButton("cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCancelar.setForeground(SystemColor.controlDkShadow);
		btnCancelar.setIcon(new ImageIcon(DBienvenida.class.getResource("/img/cancelar_small.png")));
		btnCancelar.setMargin(new Insets(5, 5, 5, 5));
		btnCancelar.setIconTextGap(5);
		btnCancelar.setBackground(SystemColor.menu);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnIniciarSesin, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnIniciarSesin, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		
		campoUsuario = new JTextField();
		campoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCuenta();
			}
		});
		
		campoUsuario.setSelectionColor(new Color(0, 51, 51));
		campoUsuario.setBackground(new Color(245, 245, 245));
		campoUsuario.setColumns(10);
		
		btCrearCuenta = new JButton("Crear cuenta");
		btCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCuenta();
			}
		});
		btCrearCuenta.setIconTextGap(5);
		btCrearCuenta.setBackground(SystemColor.menu);
		btCrearCuenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btCrearCuenta.setMargin(new Insets(5, 5, 5, 5));
		btCrearCuenta.setIcon(new ImageIcon(DBienvenida.class.getResource("/img/crear_usuario_small.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUsuario)
						.addComponent(campoUsuario, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addComponent(btCrearCuenta, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUsuario)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(campoUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addComponent(btCrearCuenta)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
	}


	protected void iniciarSesion() {
		if(this.vista.iniciarSesion())
			this.setVisible(false);
	}


	protected void crearCuenta() {
		if(this.campoUsuario.getText().isEmpty())
			this.vista.error("Rellene el campo usuario para crear una cuenta", this);
		else {
			this.vista.crearCuenta(this.campoUsuario.getText());
			this.vista.msg("Cuenta creada.", this);
			this.setVisible(false);
		}
	}
}

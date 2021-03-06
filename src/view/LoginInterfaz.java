package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import model.Usuario;
import service.UsuarioService;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class LoginInterfaz {

	private JFrame frame; 
	private JTextField userField;
	private JPasswordField passwordField;
	private UsuarioService usuarioService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				LoginInterfaz window = new LoginInterfaz();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the application.
	 */
	private LoginInterfaz() {
		usuarioService = UsuarioService.getSingletonInstance();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Login de usuario");
		frame.setResizable(false);
		frame.setBounds(100, 100, 453, 187);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel user = new JLabel("Usuario:");
		user.setHorizontalAlignment(SwingConstants.RIGHT);
		user.setBounds(10, 29, 110, 22);
		frame.getContentPane().add(user);
		
		JLabel pass = new JLabel("Contraseña:");
		pass.setHorizontalAlignment(SwingConstants.RIGHT);
		pass.setBounds(10, 60, 110, 22);
		frame.getContentPane().add(pass);
		
		userField = new JTextField();
		userField.setBounds(130, 30, 126, 20);
		frame.getContentPane().add(userField);
		userField.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> logearse());
		btnAceptar.setBounds(309, 29, 89, 23);
		frame.getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(arg0 -> frame.dispose());
		btnCancelar.setBounds(309, 60, 89, 23); 
		frame.getContentPane().add(btnCancelar);
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.addActionListener(e -> new RegistroInterfaz());
		btnRegistro.setBounds(272, 115, 126, 23);
		frame.getContentPane().add(btnRegistro);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(130, 61, 126, 20);
		frame.getContentPane().add(passwordField);
	}
	
	private void logearse(){
		String user = userField.getText();
		String pass = new String(passwordField.getPassword());

		if(user.isEmpty() || pass.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Complete los campos por favor", "", JOptionPane.ERROR_MESSAGE);
		} else {
			Usuario usuario = new Usuario(user, pass);

			if(usuarioService.loginValido(usuario)) {
				new ABMInterfaz().setVisible(true);
				frame.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Usuario no valido", "", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}

package entidad;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {


	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtContrasena;
	
	public static String usuarioIngresado = "";
	public static String usuarioIngresadoRut = "";
	
	public static String usuarioNombre = "";
	public static String usuarioApellido = "";
	public static int usuarioTelefono = 0;
	public static String usuarioFechaIngreso = "";
	
	private void limpiarCuadros(){
		txtUsuario.setText(null);
		txtContrasena.setText(null);
		
	}

	Connection conexion = null;
	PreparedStatement preparedStatemnet = null;
	ResultSet resultSet = null;
	
	private void limpiarTextos() {


		txtUsuario.setText(null);
		txtContrasena.setText(null);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 204);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(116, 26, 56, 16);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(116, 61, 56, 16);
		contentPane.add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(193, 23, 116, 22);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContrasena = new JTextField();
		txtContrasena.setBounds(193, 58, 116, 22);
		contentPane.add(txtContrasena);
		txtContrasena.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaR();
				}
		});
		

		
		btnIngresar.setBounds(94, 103, 97, 25);
		contentPane.add(btnIngresar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salir();
			
			}

			private void salir() {
					System.exit(0);		
			}
		});
		btnSalir.setBounds(231, 103, 97, 25);
		contentPane.add(btnSalir);
	}

	public void consultaR() {
		conexion = TestBD.conectar();
		try {
			preparedStatemnet = conexion
					.prepareStatement("select*from usuario where nombreUsuario = ? and password =  ? ");
			preparedStatemnet.setString(1, txtUsuario.getText());
			preparedStatemnet.setString(2, txtContrasena.getText());

			resultSet = preparedStatemnet.executeQuery();
			if(resultSet.next()){	

				JOptionPane.showMessageDialog(null, "USUARIO ENCONTRADO!" +" " + (resultSet.getString("nombreUsuario")));
				usuarioIngresado = txtUsuario.getText();
				
				Solicitud_de_Hora solicitudHora = new Solicitud_de_Hora();
				solicitudHora.setVisible(true);

				
			}else {
				JOptionPane.showMessageDialog(null, "Usuario incorrecto, verifique usuario y contraseña");
			}
			conexion.close();
		}catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error con el acceso a BD");
		}
		}
}

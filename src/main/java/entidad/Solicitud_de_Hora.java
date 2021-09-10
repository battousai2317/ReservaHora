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

public class Solicitud_de_Hora extends JFrame {


	private JPanel contentPane;
	private JTextField txtIdMascota;
	private JTextField txtRutDueno;
	private JTextField txtNombreMascota;
	private JTextField txtFecha;
	private JTextField txtHora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Solicitud_de_Hora frame = new Solicitud_de_Hora();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	Connection conexion = null;
	PreparedStatement preparedStatemnet = null;
	ResultSet resultSet = null;
	
	/**
	 * Create the frame.
	 */
	public Solicitud_de_Hora() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdmascota = new JLabel("Id Mascota");
		lblIdmascota.setBounds(38, 49, 112, 16);
		contentPane.add(lblIdmascota);
		
		JLabel lblRutDueo = new JLabel("Rut Dueño");
		lblRutDueo.setBounds(38, 78, 112, 16);
		contentPane.add(lblRutDueo);
		
		JLabel lblNombreMascota = new JLabel("Nombre Mascota");
		lblNombreMascota.setBounds(38, 107, 112, 16);
		contentPane.add(lblNombreMascota);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(38, 164, 56, 16);
		contentPane.add(lblHora);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(38, 136, 56, 16);
		contentPane.add(lblFecha);
		
		txtIdMascota = new JTextField();
		txtIdMascota.setBounds(179, 46, 193, 22);
		contentPane.add(txtIdMascota);
		txtIdMascota.setColumns(10);
		
		txtRutDueno = new JTextField();
		txtRutDueno.setBounds(179, 75, 193, 22);
		contentPane.add(txtRutDueno);
		txtRutDueno.setColumns(10);
		
		txtNombreMascota = new JTextField();
		txtNombreMascota.setBounds(179, 104, 193, 22);
		contentPane.add(txtNombreMascota);
		txtNombreMascota.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(179, 133, 193, 22);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
		
		txtHora = new JTextField();
		txtHora.setBounds(179, 161, 193, 22);
		contentPane.add(txtHora);
		txtHora.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarP();
			}
			public void insertarP() {
				conexion = TestBD.conectar();
				try {
					preparedStatemnet = conexion
							.prepareStatement("INSERT INTO agenda(idMascota,rutDueño,NombreMascota,hora,Fecha) VALUES (?,?,?,?,?");
					

					preparedStatemnet.setString(1, txtIdMascota.getText());
					preparedStatemnet.setString(2, txtRutDueno.getText());
					preparedStatemnet.setString(3, txtNombreMascota.getText());
					preparedStatemnet.setString(4, txtFecha.getText());
					preparedStatemnet.setString(5, txtHora.getText());


					resultSet = preparedStatemnet.executeQuery();
					if(resultSet.next()){	

						JOptionPane.showMessageDialog(null, "Hora agendada correctamente");
		
					}else {
						JOptionPane.showMessageDialog(null, "No se pudo agendar Hora");
					}
					conexion.close();
				}catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Ocurrio un error con el acceso a BD");
				}
				}
		});
		
		
		
		
		
		btnAceptar.setBounds(84, 215, 97, 25);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();
			}
			private void salir() {
				System.exit(0);		
		}
		});
		btnCancelar.setBounds(233, 215, 97, 25);
		contentPane.add(btnCancelar);
		
		JLabel label = new JLabel("New label");
		label.setBounds(38, 20, 56, 16);
		contentPane.add(label);
	}

}

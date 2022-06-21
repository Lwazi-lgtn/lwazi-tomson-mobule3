


import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Color;
import java.awt.Window.Type;
import java.sql.ResultSet;
import java.sql.SQLException;


public class login {

	protected static Component frmLoginSystem = null;
	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;
	Connection connection=null;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public login() {
		initialize();
	}


	private void initialize() {
		try {
			Class.forName("org.h2.Driver");
			connection= DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
					} 
		catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
		}
		frmLogin = new JFrame();
		frmLogin.setType(Type.POPUP);
		frmLogin.setTitle("Login");
		frmLogin.setBackground(Color.RED);
		frmLogin.getContentPane().setBackground(new Color(106, 90, 205));
		frmLogin.setForeground(Color.BLACK);
		frmLogin.getContentPane().setForeground(Color.DARK_GRAY);
		frmLogin.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
		frmLogin.setBounds(200, 200, 500, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Systems");
		lblNewLabel.setBounds(167, 11, 186, 26);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		frmLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(66, 70, 90, 30);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		frmLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(66, 135, 75, 30);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		frmLogin.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(151, 71, 161, 30);
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(151, 136, 161, 30);
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 15));
		frmLogin.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.BLUE);
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setBounds(10, 210, 89, 23);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				
				try{
					String validate= "SELECT * FROM USERTABLE WHERE USER=? AND PASS=? ";
					PreparedStatement statement = connection.prepareStatement(validate);
					statement.setString(1, textField.getText());
					statement.setString(2, passwordField.getText());
					ResultSet set = statement.executeQuery();
				if(set.next()){
					
                    frmLogin.setVisible(false);
					FrameScreen info= new FrameScreen();
					//FrameScreen.main(null);
				}
				else{
					JOptionPane.showMessageDialog(null, "Invalid Login Details","Login System Error",JOptionPane.ERROR_MESSAGE);
					passwordField.setText(null);
					textField.setText(null);
				}
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		frmLogin.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setForeground(Color.BLUE);
		btnReset.setBackground(Color.LIGHT_GRAY);
		btnReset.setBounds(135, 210, 89, 23);
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				passwordField.setText(null);
				textField.setText(null);	
			}
		});
		frmLogin.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(Color.BLUE);
		btnExit.setBackground(Color.LIGHT_GRAY);
		btnExit.setBounds(385, 210, 89, 23);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLoginSystem= new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLoginSystem,"Confirm if you want to exit","Login Systems",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
					System.exit(1);
				}
			}
		});
		frmLogin.getContentPane().add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 188, 464, 2);
		frmLogin.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 35, 464, 2);
		frmLogin.getContentPane().add(separator_1);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(Color.LIGHT_GRAY);
		btnRegister.setForeground(Color.BLUE);
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register info = new register();
				register.main(null);
				frmLogin.setVisible(false);
			}
		});
		btnRegister.setBounds(256, 210, 97, 23);
		frmLogin.getContentPane().add(btnRegister);
	}
}
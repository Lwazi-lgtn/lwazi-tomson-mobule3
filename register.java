

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;




import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;

public class register {

	private JFrame frmRegister;
	private JTextField mailField;
	private JTextField nameField;
	private JTextField phoneField;
	private JLabel lblUsername;
	private JTextField userField;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JButton btnSubmit;
	private JButton btnCanel;
	private JSeparator separator;
	private JSeparator separator_1;
	private JButton btnExit;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register window = new register();
					window.frmRegister.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public register() {
		initialize();
	}
	Connection connection=null;
	public void createTableNew(){

		try {
			DatabaseMetaData dmd=connection.getMetaData();
			ResultSet set=dmd.getTables(null, null, "USERTABLE", null);
			 if(set.next()){
				 
			 }
			 else{
				 String creat_table="create table USERTABLE ("
						 +"email varchar2(20),"
						 +"name varchar2(30), "
						 +"phone varchar2(12), "
						 +"user varchar2(30), "
						 +"pass varchar2 (20))";
				 PreparedStatement statement = connection.prepareStatement(creat_table);
				 statement.executeUpdate();
				 JOptionPane.showMessageDialog(null, "Table create sucessfull");
			 }
		}
		catch (Exception e){
			
		}
		
	}
 

	private void initialize() {
		try {
			Class.forName("org.h2.Driver");
			connection= DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
		} 
		catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frmRegister = new JFrame();
		frmRegister.setTitle("REGISTER");
		frmRegister.setType(Type.POPUP);
		frmRegister.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
		frmRegister.getContentPane().setBackground(new Color(30, 144, 255));
		frmRegister.setBounds(200, 200, 400, 400);
		frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegister.getContentPane().setLayout(null);
		
		JLabel lblRegisterSystem = new JLabel("Register System");
		lblRegisterSystem.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRegisterSystem.setBounds(116, 11, 176, 35);
		frmRegister.getContentPane().add(lblRegisterSystem);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(24, 60, 46, 14);
		frmRegister.getContentPane().add(lblEmail);
		
		mailField = new JTextField();
		mailField.setBounds(120, 57, 170, 20);
		frmRegister.getContentPane().add(mailField);
		mailField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(24, 100, 46, 14);
		frmRegister.getContentPane().add(lblName);
		
		nameField = new JTextField();
		nameField.setBounds(120, 100, 170, 20);
		frmRegister.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPhone.setBounds(24, 140, 46, 14);
		frmRegister.getContentPane().add(lblPhone);
		
		phoneField = new JTextField();
		phoneField.setBounds(120, 140, 170, 20);
		frmRegister.getContentPane().add(phoneField);
		phoneField.setColumns(10);
		
		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(24, 180, 86, 14);
		frmRegister.getContentPane().add(lblUsername);
		
		userField = new JTextField();
		userField.setBounds(120, 180, 170, 20);
		frmRegister.getContentPane().add(userField);
		userField.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(24, 220, 73, 14);
		frmRegister.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(120, 220, 170, 20);
		frmRegister.getContentPane().add(passwordField);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String insert_data=" insert into USERTABLE values(?,?,?,?,?)";
					PreparedStatement statement= connection.prepareStatement(insert_data);
					statement.setString(1, mailField.getText());
					statement.setString(2, nameField.getText());
					statement.setString(3, phoneField.getText());
					statement.setString(4, userField.getText());
					statement.setString(5, passwordField.getText());
					int data_entered= statement.executeUpdate();
					if (data_entered>0)
					{
						JOptionPane.showMessageDialog(null, "Data inserted into table sucessfull");
					}
					else 
						JOptionPane.showMessageDialog(null, "Data insert false");
						
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnSubmit.setBounds(21, 286, 89, 23);
		frmRegister.getContentPane().add(btnSubmit);
		
		btnCanel = new JButton("Canel");
		btnCanel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordField.setText(null);
				mailField.setText(null);
				nameField.setText(null);
				phoneField.setText(null);
				userField.setText(null);
			}
		});
		btnCanel.setBounds(149, 286, 89, 23);
		frmRegister.getContentPane().add(btnCanel);
		
		separator = new JSeparator();
		separator.setBounds(10, 47, 364, 2);
		frmRegister.getContentPane().add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(10, 273, 364, 2);
		frmRegister.getContentPane().add(separator_1);
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frmRegisterSystem = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmRegisterSystem,"Confirm if you want to change login","Register Systems",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
					frmRegister.setVisible(false);
					login info= new login();
					login.main(null);
				}
			}
			
		});
		btnExit.setBounds(271, 286, 89, 23);
		frmRegister.getContentPane().add(btnExit);
		createTableNew();
		
	}

}
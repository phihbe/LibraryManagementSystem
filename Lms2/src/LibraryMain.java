import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;


import javax.swing.SwingConstants;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;


import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *-1.Söka utan inlogg 
 *-2.Låna, reservera, återlämna
 * 3.Kvitto(titel, id, datum, Lämna tillbaka)
 *-5.Logga in och registrera ny användare
 * 6.Se lån & lånhistorik
 *x7.Hantera Böcker, tidsskrifter, filmer.
 *x8.Lägga till nya objekt
 *x9.Lägga till ändra och ta bort objekt
 *x10.Lägga till änra ta bort låntagare 
 * @author Philip
 *
 */





public class LibraryMain extends javax.swing.JFrame {
	//Variabler
	private static String password = "100Recrec";
	private static String username = "root";
	private static String connectionString = "jdbc:mysql://localhost:3306/lms";
	private static Connection connection;
	private static Statement command;
	private JFrame frame;
	private JTextField textFieldUserName;
	private JPasswordField passwordField;
	private JButton btnLoggIn;
	private JButton btnRegistreraNyAnvndare;
	private JButton btnTillSkfuntionen;
	private JButton btnExit;
	private JLabel lblLoggIn;
	private JLabel lblUsername;
	private JLabel lblPassword;
	
	//Visar fönstret samt sätter igång metoden initialize
	public LibraryMain() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new javax.swing.JFrame();
		lblLoggIn = new javax.swing.JLabel();
		lblUsername = new javax.swing.JLabel();
		lblPassword = new javax.swing.JLabel();
		btnLoggIn = new javax.swing.JButton();
		btnRegistreraNyAnvndare = new javax.swing.JButton();
		btnTillSkfuntionen = new javax.swing.JButton();
		btnExit = new javax.swing.JButton();
		textFieldUserName = new javax.swing.JTextField();
		passwordField = new javax.swing.JPasswordField();
		
		
		frame.setBounds(100, 100, 458, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblLoggIn.setText("LOGG IN");
		lblLoggIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoggIn.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblLoggIn.setBounds(10, 11, 414, 61);
		frame.getContentPane().add(lblLoggIn);
		
		lblUsername.setText("UserName:");
		lblUsername.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		lblUsername.setBounds(21, 101, 115, 42);
		frame.getContentPane().add(lblUsername);
		
		lblPassword.setText("Password:");
		lblPassword.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		lblPassword.setBounds(21, 181, 115, 42);
		frame.getContentPane().add(lblPassword);
		
		
		textFieldUserName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textFieldUserName.setBounds(146, 101, 255, 42);
		frame.getContentPane().add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		
		passwordField.setBounds(146, 181, 255, 42);
		frame.getContentPane().add(passwordField);
		
		btnLoggIn.setText("LOGG IN");
		btnLoggIn.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnLoggIn.setBounds(146, 258, 255, 42);
		frame.getContentPane().add(btnLoggIn);
		btnLoggIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLoggInActionPerformed(evt);
			}
		});
		
		btnRegistreraNyAnvndare.setText("REGISTRERA NY ANV\u00C4NDARE");
		btnRegistreraNyAnvndare.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnRegistreraNyAnvndare.setBounds(146, 311, 255, 42);
		frame.getContentPane().add(btnRegistreraNyAnvndare);
		btnRegistreraNyAnvndare.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRegisterUserActionPerformed(evt);
			}
		});
		
		btnTillSkfuntionen.setText("TILL S\u00D6KFUNTIONEN");
		btnTillSkfuntionen.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnTillSkfuntionen.setBounds(146, 364, 255, 42);
		frame.getContentPane().add(btnTillSkfuntionen);
		btnTillSkfuntionen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTillSkfuntionenActionPerformed(evt);
			}
		});
		
		btnExit.setText("EXIT");
		btnExit.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnExit.setBounds(146, 417, 255, 42);
		frame.getContentPane().add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExitActionPerformed(evt);
			}
		});
	}
	//Stänger av programet
	private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}
	//1.Söka utan inlogg 
	private void btnTillSkfuntionenActionPerformed(java.awt.event.ActionEvent evt) {
		Search ObjectSearch = new Search();
		this.frame.setVisible(false);
	}
	//5.registrera ny användare
	private void btnRegisterUserActionPerformed(java.awt.event.ActionEvent evt) {	
		RegisterUser ObjectRegisterUser = new RegisterUser();
		this.frame.setVisible(false);
	}
	//Om användaren finns i databasen ges åtkomst till den inloggade menyn
	//5.Logga in
	private void btnLoggInActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			connect();
			String sql ="SELECT * from user where UserName='" + textFieldUserName.getText() + "'and Password='"+passwordField.getText().toString()+"';";
			ResultSet rs = command.executeQuery(sql);
			if(rs.next())
			{
				UserLoggedIn ObjectUserLoggedIn = new UserLoggedIn();
				this.frame.setVisible(false);

			}else {
				JOptionPane.showMessageDialog(null, "försök igen!");
			}
			disconnect();
			} catch (SQLException e) { System.out.println(e);
		}
		
	}
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new LibraryMain().frame.setVisible(true);
			}
		});
	}
	public void connect() {
		try {
		connection = DriverManager.getConnection(connectionString, username, password);
		command = connection.createStatement();
		}
		catch(Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(btnLoggIn, this,"connection error", 0);
			
		}
		
	}
	 public void disconnect()
	    {
	        try
	        {
	        	connection.close(); 
	        }
	        catch(Exception e)
	        {}

	    }
	
}

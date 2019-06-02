import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class RegisterUser extends javax.swing.JFrame{

	//Variabler
	private JLabel lblRegistreraNyAnvndare;
	private JLabel lblNamn;
	private JLabel lblEfternamn;
	private JLabel lblAnvndarid;
	private JLabel lblLsenord;
	private JButton btnRegistreraAnvändare;
	private JButton btnTillbaka;
	private JPasswordField passwordField;
	private JFrame frame;
	private JTextField textFieldNamn;
	private JTextField textFieldEfterN;
	private JTextField textFieldUserName;
	private static String password = "100Recrec";
	private static String username = "root";
	private static String connectionString = "jdbc:mysql://localhost:3306/lms";
	private static Connection connection;
	private static Statement command;
	
	//Visar fönstret samt sätter igång metoden initialize
	public RegisterUser() {
		initialize();
		frame.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new javax.swing.JFrame();
		textFieldNamn = new javax.swing.JTextField();
		textFieldEfterN = new javax.swing.JTextField();
		textFieldUserName = new javax.swing.JTextField();
		passwordField = new JPasswordField();
		lblNamn = new javax.swing.JLabel();
		lblEfternamn = new javax.swing.JLabel();
		lblAnvndarid = new javax.swing.JLabel();
		lblLsenord = new javax.swing.JLabel();
		btnRegistreraAnvändare = new javax.swing.JButton();
		btnTillbaka = new JButton();
		lblRegistreraNyAnvndare = new javax.swing.JLabel();
		
		frame.setBounds(100, 100, 450, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblRegistreraNyAnvndare.setText("REGISTRERA NY USER");
		lblRegistreraNyAnvndare.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		lblRegistreraNyAnvndare.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistreraNyAnvndare.setBounds(10, 11, 414, 43);
		frame.getContentPane().add(lblRegistreraNyAnvndare);
		
		
		textFieldNamn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textFieldNamn.setColumns(10);
		textFieldNamn.setBounds(155, 69, 255, 42);
		frame.getContentPane().add(textFieldNamn);
		
		lblNamn.setText("F\u00F6rnamn");
		lblNamn.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		lblNamn.setHorizontalAlignment(SwingConstants.CENTER);
		lblNamn.setBounds(10, 69, 135, 42);
		frame.getContentPane().add(lblNamn);
		
		
		textFieldEfterN.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textFieldEfterN.setColumns(10);
		textFieldEfterN.setBounds(155, 122, 255, 42);
		frame.getContentPane().add(textFieldEfterN);
		
		lblEfternamn.setText("Efternamn");
		lblEfternamn.setHorizontalAlignment(SwingConstants.CENTER);
		lblEfternamn.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		lblEfternamn.setBounds(10, 122, 135, 42);
		frame.getContentPane().add(lblEfternamn);
		
		
		textFieldUserName.setBounds(155, 175, 255, 43);
		frame.getContentPane().add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		lblAnvndarid.setText("Anv\u00E4ndarnamn");
		lblAnvndarid.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnvndarid.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		lblAnvndarid.setBounds(10, 176, 135, 42);
		frame.getContentPane().add(lblAnvndarid);
		
		lblLsenord.setText("L\u00F6senord");
		lblLsenord.setHorizontalAlignment(SwingConstants.CENTER);
		lblLsenord.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		lblLsenord.setBounds(10, 229, 135, 42);
		frame.getContentPane().add(lblLsenord);
		
		btnRegistreraAnvändare.setText("RegistreraAnv\u00E4ndare");
		btnRegistreraAnvändare.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		btnRegistreraAnvändare.setBounds(155, 282, 255, 43);
		frame.getContentPane().add(btnRegistreraAnvändare);
		
		
		
		btnTillbaka.setText("TILLBAKA");
		btnTillbaka.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnTillbaka.setBounds(155, 336, 255, 42);
		frame.getContentPane().add(btnTillbaka);
		btnTillbaka.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnTillbakaActionPerformed(evt);
			}
		});
		
		
		passwordField.setBounds(155, 229, 255, 43);
		frame.getContentPane().add(passwordField);
		btnRegistreraAnvändare.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRegistreraAnvändareActionPerformed(evt);
			}
		});
	}
	//Registrerar användare enligt input.
	private void btnRegistreraAnvändareActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			connect();
			String sql ="INSERT into user(`UserName`, `Password`, `FName`, `EName`) VALUES ('" 
			+ textFieldUserName.getText() + "','"+ passwordField.getText().toString() + "','"+  
			textFieldNamn.getText() + "','"+ textFieldEfterN.getText() + "');";
			command.execute(sql);
			UserLoggedIn ObjectUserLoggedIn = new UserLoggedIn();
				this.frame.setVisible(false);
				disconnect();
			}catch (SQLException e) { System.out.println(e);
	}
}
	//Tillbaka till grundmenyn
	public void btnTillbakaActionPerformed(ActionEvent evt) {
		LibraryMain ObjectLibraryMain = new LibraryMain();
		this.frame.setVisible(false);
	}
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				//new RegisterUser().frame.setVisible(true);
				new RegisterUser();
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
			JOptionPane.showMessageDialog(btnRegistreraAnvändare, this,"connection error", 0);
			
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

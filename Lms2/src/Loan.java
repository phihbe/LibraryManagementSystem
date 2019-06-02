import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

/**
 * 1.Söka utan inlogg 
 * 2.Låna, reservera, återlämna
 *-3.Kvitto(titel, id, datum, Lämna tillbaka)
 * 5.Logga in och registrera ny användare
 * 6.Se lån & lånhistorik
 *x7.Hantera Böcker, tidsskrifter, filmer.
 *x8.Lägga till nya objekt
 *x9.Lägga till ändra och ta bort objekt
 *x10.Lägga till änra ta bort låntagare 
 * @author Philip
 *
 */

public class Loan {
	//Variabler
	private static String password = "100Recrec";
	private static String username = "root";
	private static String connectionString = "jdbc:mysql://localhost:3306/lms";
	private static Connection connection;
	private static Statement command;
	private JFrame frame;
	private JTextField textFieldBokid;
	private JLabel lblBokid;
	private JTextField textFieldAnvändarid;
	private JLabel lblAnvndarid;
	private JLabel lblLnaBok;
	private JButton btnLna;
	private JButton btnTillbaka;

	//Visar fönstret samt sätter igång metoden initialize
	public Loan() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		lblLnaBok = new JLabel();
		lblBokid = new JLabel();
		textFieldAnvändarid = new JTextField();
		lblAnvndarid = new JLabel();
		btnLna = new JButton();
		btnTillbaka = new JButton();
		textFieldBokid = new JTextField();
		
		frame.setBounds(100, 100, 451, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		textFieldBokid.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textFieldBokid.setColumns(10);
		textFieldBokid.setBounds(170, 83, 255, 42);
		frame.getContentPane().add(textFieldBokid);
		
		
		lblLnaBok.setText("L\u00C5NA BOK/FILM");
		lblLnaBok.setHorizontalAlignment(SwingConstants.CENTER);
		lblLnaBok.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblLnaBok.setBounds(11, 11, 414, 61);
		frame.getContentPane().add(lblLnaBok);
		
		
		lblBokid.setHorizontalAlignment(SwingConstants.CENTER);
		lblBokid.setText("ID:");
		lblBokid.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		lblBokid.setBounds(45, 83, 115, 42);
		frame.getContentPane().add(lblBokid);
		textFieldAnvändarid.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textFieldAnvändarid.setColumns(10);
		textFieldAnvändarid.setBounds(170, 136, 255, 42);
		frame.getContentPane().add(textFieldAnvändarid);
		
		
		lblAnvndarid.setText("Anv\u00E4ndarid:");
		lblAnvndarid.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnvndarid.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		lblAnvndarid.setBounds(45, 136, 115, 42);
		frame.getContentPane().add(lblAnvndarid);
		
		
		btnLna.setText("L\u00C5NA");
		btnLna.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnLna.setBounds(170, 189, 255, 42);
		frame.getContentPane().add(btnLna);
		btnLna.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnLnaActionPerformed(evt);
			}
		});
		
		
		btnTillbaka.setText("TILLBAKA");
		btnTillbaka.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnTillbaka.setBounds(170, 242, 255, 42);
		frame.getContentPane().add(btnTillbaka);
		btnTillbaka.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnTillbakaActionPerformed(evt);
			}
		});
	}
	private void btnLnaActionPerformed (java.awt.event.ActionEvent evt) {
		try {
			connect();
			//lägger till ett lån
			String sql ="Insert into loan (userid,ISBN, DateOut) value (" + textFieldAnvändarid.getText() + ","
					+ textFieldBokid.getText() +", CURRENT_TIMESTAMP);";
			command.execute(sql);			
			disconnect();
			//3.Kvitto
		} catch (SQLException e) { System.out.println(e);
		}  
	}
	//Tillbaka till inloggade menyn
	private void btnTillbakaActionPerformed(java.awt.event.ActionEvent evt) {
		UserLoggedIn ObjectUserLoggedIn = new UserLoggedIn();
		this.frame.setVisible(false);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Loan window = new Loan();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
			JOptionPane.showMessageDialog(btnLna, this,"connection error", 0);
			
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

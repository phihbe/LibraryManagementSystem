import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ReturnBook {

	private JFrame frame;
	private JTextField textField;
	private JButton btnReturnbook;
	private JButton btnTillbaka;
	private JLabel lblEnterIsbn;
	private static String password = "100Recrec";
	private static String username = "root";
	private static String connectionString = "jdbc:mysql://localhost:3306/lms";
	private static Connection connection;
	private static Statement command;
	public ReturnBook() {
		initialize();
		this.frame.setVisible(true);
	}
	
	private void initialize() {
		frame = new JFrame();
		btnReturnbook = new JButton();
		lblEnterIsbn = new JLabel();
		textField = new JTextField();
		
		frame.setBounds(100, 100, 425, 328);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField.setColumns(10);
		textField.setBounds(80, 90, 255, 42);
		frame.getContentPane().add(textField);
		
		btnReturnbook.setText("ReturnBook");
		btnReturnbook.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		btnReturnbook.setBounds(80, 155, 255, 43);
		frame.getContentPane().add(btnReturnbook);
		btnReturnbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnReturnbookActionPerformed(evt);
				
			}
		});
		
		lblEnterIsbn.setText("Enter Loanid");
		lblEnterIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterIsbn.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		lblEnterIsbn.setBounds(80, 37, 255, 42);
		frame.getContentPane().add(lblEnterIsbn);
		
		btnTillbaka = new JButton();
		btnTillbaka.setText("Tillbaka");
		btnTillbaka.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		btnTillbaka.setBounds(80, 209, 255, 43);
		frame.getContentPane().add(btnTillbaka);
		btnTillbaka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnTillbakaActionPerformed(evt);
			}
		});
	}
	private void btnTillbakaActionPerformed(ActionEvent evt) {
		UserLoggedIn UserLoggedIn = new UserLoggedIn();
		this.frame.setVisible(false);
	}
	private void btnReturnbookActionPerformed(ActionEvent evt) {
		try {
		connect();
		String sql ="update loan set DateIn = CURRENT_TIMESTAMP where loanid="+ textField.getText() +";";
		command.execute(sql);
	}catch (SQLException e) { System.out.println(e);
	}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook window = new ReturnBook();
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
			JOptionPane.showMessageDialog(btnReturnbook, this,"connection error", 0);
			
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

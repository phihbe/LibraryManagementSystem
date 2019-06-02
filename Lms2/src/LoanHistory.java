import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class LoanHistory {

	private static String password = "100Recrec";
	private static String username = "root";
	private static String connectionString = "jdbc:mysql://localhost:3306/lms";
	private static Connection connection;
	private static Statement command;
	String [] columnNames = {"loanid","ISBN","DateOut", "DateIn", "userid"};
	String ISBN, loanid, DateOut, DateIn, userid;
	private JFrame frame;
	private JTextField textField;
	private JButton buttonShow;
	private JButton buttonTillbaka;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public LoanHistory() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 456, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel();
		label.setText("ENTER USERiD");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		label.setBounds(10, 11, 414, 53);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField.setColumns(10);
		textField.setBounds(10, 75, 419, 42);
		frame.getContentPane().add(textField);
		
		buttonTillbaka = new JButton();
		buttonTillbaka.setText("TILLBAKA");
		buttonTillbaka.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		buttonTillbaka.setBounds(10, 128, 210, 42);
		frame.getContentPane().add(buttonTillbaka);
		buttonTillbaka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonTillbakaActionPerformed(evt);
			}
		});
		
		buttonShow = new JButton();
		buttonShow.setText("VISA L\u00C5N");
		buttonShow.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		buttonShow.setBounds(219, 128, 210, 42);
		frame.getContentPane().add(buttonShow);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 181, 417, 293);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		buttonShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonShowActionPerformed(evt);
			}
		});
	}
	private void buttonShowActionPerformed(ActionEvent evt) {
		DefaultTableModel model = new DefaultTableModel();
		model.setRowCount(0);		
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		try{
			connect();
			String sql = "Select * from loan where DateIn and userid = " + textField.getText() + ";";
			ResultSet rs = command.executeQuery(sql);
			while(rs.next()){
				ISBN = rs.getString("ISBN");
				loanid = rs.getString("loanid");
				DateOut = rs.getString("DateOut");
				DateIn = rs.getString("DateIn");
				userid = rs.getString("userid");
				Object[] row ={loanid, ISBN, DateOut, DateIn, userid};
				model.addRow(row);
			}
		}catch(SQLException e) { System.out.println(e);
		}
	}
	private void buttonTillbakaActionPerformed(ActionEvent evt) {
		UserLoggedIn UserLoggedIn = new UserLoggedIn();
		this.frame.setVisible(false);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoanHistory window = new LoanHistory();
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
			JOptionPane.showMessageDialog(buttonShow, this,"connection error", 0);
			
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

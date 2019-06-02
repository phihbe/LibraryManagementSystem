import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class MyLoans {
	//Variabler
	private JFrame frame;
	private static String password = "100Recrec";
	private static String username = "root";
	private static String connectionString = "jdbc:mysql://localhost:3306/lms";
	private static Connection connection;
	private static Statement command;
	private JTable table;
	private JLabel lblMinaLn;
	private JButton button;
	private JButton btnShowLoans;
	String [] columnNames = {"loanid","ISBN","DateOut", "userid"};
	String ISBN, loanid, DateOut, userid;
	private JTextField textFieldEnterUserid;
	private JScrollPane scrollPane_1;
	
	//Visar fönstret samt sätter igång metoden initialize
	public MyLoans() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		button = new JButton();
		lblMinaLn = new JLabel();
		btnShowLoans = new JButton();
		textFieldEnterUserid = new JTextField();
		table = new JTable();
		scrollPane_1 = new JScrollPane();
		
		frame.setBounds(100, 100, 455, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		button.setText("TILLBAKA");
		button.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		button.setBounds(10, 128, 210, 42);
		frame.getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				buttonActionPerformed(evt);
			}
		});
		
		
		lblMinaLn.setText("ENTER USERiD");
		lblMinaLn.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinaLn.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblMinaLn.setBounds(10, 11, 414, 53);
		frame.getContentPane().add(lblMinaLn);
		
		
		btnShowLoans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnShowLoansActionPerformed(evt);
			}
		});
		btnShowLoans.setText("VISA L\u00C5N");
		btnShowLoans.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnShowLoans.setBounds(219, 128, 210, 42);
		frame.getContentPane().add(btnShowLoans);
		
		
		textFieldEnterUserid.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textFieldEnterUserid.setColumns(10);
		textFieldEnterUserid.setBounds(10, 75, 419, 42);
		frame.getContentPane().add(textFieldEnterUserid);
		
		
		scrollPane_1.setBounds(10, 181, 419, 299);
		frame.getContentPane().add(scrollPane_1);
		scrollPane_1.setViewportView(table);
	}
	//Tillbaka till inloggade menyn
	public void buttonActionPerformed(ActionEvent evt){
		UserLoggedIn UserLoggedIn = new UserLoggedIn();
		this.frame.setVisible(false);
	}
	//Visar lån för angiven användare
	public void btnShowLoansActionPerformed(ActionEvent evt){
		DefaultTableModel model = new DefaultTableModel();
		model.setRowCount(0);		
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		
		try {
			connect();
			String sql = "Select * from loan where COALESCE(DateIn, 0) =0 and userid = " + textFieldEnterUserid.getText() + ";";
			ResultSet rs = command.executeQuery(sql);
			while(rs.next()) {
				ISBN = rs.getString("ISBN");
				loanid = rs.getString("loanid");
				DateOut = rs.getString("DateOut");
				userid = rs.getString("userid");
				Object[] row ={loanid, ISBN, DateOut, userid};
				model.addRow(row);

			}
			
		}catch(SQLException e) { System.out.println(e);
	}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MyLoans window = new MyLoans();
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
			JOptionPane.showMessageDialog(btnShowLoans, this,"connection error", 0);
			
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

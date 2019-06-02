import java.awt.EventQueue;  

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

	public class Search {

	private static String password = "100Recrec";
	private static String username = "root";
	private static String connectionString = "jdbc:mysql://localhost:3306/lms";
	private static Connection connection;
	private static Statement command;
	private JLabel lblSk;
	private JButton btnSk;
	private JScrollPane scrollPane;
	private JFrame frame;
	private JTextField textFieldSearch;
	private JButton btnTillbaka;
	static JTable table;
	String [] columnNames = {"id","Titel","Author","Genre","LoanTime"};
	String id, Titel, Author, Genre, LoanTime;
	private javax.swing.JComboBox<String> comboObjectType;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Search() {
		initialize();
		
		frame.setVisible(true);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		lblSk = new JLabel();
		textFieldSearch = new JTextField();
		btnTillbaka = new JButton();
		btnSk = new JButton();
		comboObjectType = new JComboBox<>();
		scrollPane = new JScrollPane();
		table = new JTable();
		
		frame.setBounds(100, 100, 573, 531);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		textFieldSearch.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textFieldSearch.setColumns(10);
		textFieldSearch.setBounds(86, 11, 338, 42);
		frame.getContentPane().add(textFieldSearch);
		
		
		lblSk.setText("S\u00F6k titel:");
		lblSk.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		lblSk.setBounds(10, 11, 97, 42);
		frame.getContentPane().add(lblSk);
		btnSk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnSkActionPerformed(evt);
			}
		});
		
		
		btnTillbaka.setText("TILLBAKA");
		btnTillbaka.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnTillbaka.setBounds(10, 441, 539, 42);
		frame.getContentPane().add(btnTillbaka);
		btnTillbaka.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnTillbakaActionPerformed(evt);
			}
		});
		
		
		btnSk.setText("S\u00F6k");
		btnSk.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnSk.setBounds(427, 11, 122, 42);
		frame.getContentPane().add(btnSk);
		
		
		scrollPane.setBounds(10, 94, 539, 341);
		frame.getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(table);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		comboObjectType.setModel(new DefaultComboBoxModel(new String[] {"Select ObjectType", "Bok", "Dvd"}));
		comboObjectType.setBounds(10, 63, 539, 20);
		frame.getContentPane().add(comboObjectType);
		comboObjectType.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
            	comboBookTypeActionPerformed(evt);
            }
        });
	            
	}
	public void comboBookTypeActionPerformed(ActionEvent evt){
		
	}
	public void btnTillbakaActionPerformed(ActionEvent evt) {
		LibraryMain ObjectLibraryMain = new LibraryMain();
		this.frame.setVisible(false);
	}
	public void addRow(Object[] rowData) {
		
	}
	public void setRowCount(int rowCount) {
		
	}
	public void btnSkActionPerformed(java.awt.event.ActionEvent evt) {
		DefaultTableModel model = new DefaultTableModel();
		model.setRowCount(0);		
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		
		try {
		connect();
		if(comboObjectType.getSelectedItem().equals("Bok")){
		String sql ="select * from book where Titel Like '%" + textFieldSearch.getText() +"%';";
		ResultSet rs = command.executeQuery(sql);
		while(rs.next()) {
			id = rs.getString("id");
			Titel = rs.getString("Titel");
			Author = rs.getString("Author");
			Genre = rs.getString("Genre");
			LoanTime = rs.getString("LoanTime");
			Object[] row ={id, Titel,Author,Genre,LoanTime};
			model.addRow(row); 
			}
		}
		else if(comboObjectType.getSelectedItem().equals("Dvd")){
			String sql ="select * from movies where Titel Like '%" + textFieldSearch.getText() +"%';";
			ResultSet rs = command.executeQuery(sql);
			while(rs.next()) {
				id = rs.getString("id");
				Titel = rs.getString("Titel");
				Author = rs.getString("Author");
				Genre = rs.getString("Genre");
				LoanTime = rs.getString("LoanTime");
				Object[] row ={id, Titel,Author,Genre,LoanTime};
				model.addRow(row);
				}
			}
		else if(comboObjectType.getSelectedItem().equals("Select ObjectType")){
			String sql ="select * from book where Titel Like '%" + textFieldSearch.getText() + "%' UNION SELECT * FROM movies where Titel like '%" + textFieldSearch.getText() + "%';";
			ResultSet rs = command.executeQuery(sql);
			while(rs.next()) {
				id = rs.getString("id");
				Titel = rs.getString("Titel");
				Author = rs.getString("Author");
				Genre = rs.getString("Genre");
				LoanTime = rs.getString("LoanTime");
				Object[] row ={id, Titel,Author,Genre,LoanTime};
				model.addRow(row);
			}
		}
        disconnect();
		
		//table_1.
		
		} catch (SQLException e) { System.out.println(e);
		}        
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Search window = new Search();
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
			JOptionPane.showMessageDialog(btnSk, this,"connection error", 0);
			
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

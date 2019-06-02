package anställdUI.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class authentication {
	private static String password = "100Recrec";
	private static String username = "root";
	private static String connectionString = "jdbc:mysql://localhost:3306/lms";
	private static Connection connection;
	private static Statement command;
	private void btnLoggInActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			connect();
			String sql ="SELECT UsertType from user where UserName= and Password= ;";
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

package com.learnJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCInsertRecord {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String URL ="jdbc:mysql://localhost:3306/JDBCMySQL";
		String Username="root";
		String Password ="Jayasri30";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			System.out.println("In catch"+e.getMessage());
		}
		try {
			con = DriverManager.getConnection(URL,Username,Password);
			stmt = con.createStatement();
			stmt.execute("insert into students values (4, 'Jayasri','CSD')");
			stmt.execute("insert into students values (5, 'Madhusri','CSE')");
			stmt.execute("insert into students values (6, 'Nikitha','CSM')");
			stmt.execute("insert into students values (7, 'praneeth','ECE')");
			System.out.println("Sucessfully inserted");
			//rs.close();
			stmt.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("In catch"+e.getMessage());
		}
		
		finally
		{
			if (con!=null) {
				try {
					//rs.close();
					stmt.close();
					con.close();
					System.out.println("Terminated Successfully");
				}
				catch(Exception e) {
					System.out.println("Oops ! some serious issue ");
				}
			}
		}


	}

}

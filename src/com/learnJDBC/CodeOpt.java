package com.learnJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CodeOpt {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String URL ="jdbc:mysql://localhost:3306/JDBCMySQL";
		String Username="root";
		String Password ="Jayasri30";
		String query ="select * from Students";
		try {
			Class.forName("com.mysql.jdbc.driver");
			
		}catch(ClassNotFoundException e)
		{
			System.out.println("In catch "+e.getMessage());		
		}
		try {
			con = DriverManager.getConnection(URL,Username,Password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next())
		    {
		    System.out.println("___________________");
		    int id = rs.getInt("id");
		    String name = rs.getString("name");
		    String dept = rs.getString("dept");
	        System.out.println("Student ID : "+id);
	        System.out.println("Student Name : "+name);
	        System.out.println("Student Department: "+dept);
	        System.out.println("_____________________");
		    }
			
			rs.close();
		    con.close();
		    stmt.close();
			
		}catch(SQLException e)
		{
			System.out.println("In catch"+e);
		}
		
		finally {
			if(con != null) {
				try {
					rs.close();
					stmt.close();
					con.close();
					
					System.out.println("Terminated Successfully");
				}
				
				catch(Exception e) {
					System.out.println("Oops ! some serious Issue");
				}
			}
		}

	}

}

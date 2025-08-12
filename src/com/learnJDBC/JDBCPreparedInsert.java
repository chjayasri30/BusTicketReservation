package com.learnJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCPreparedInsert {

	public static void main(String[] args) {
		Connection con = null;
		//Statement stmt = null;
		ResultSet rs = null;
		
		String URL ="jdbc:mysql://localhost:3306/JDBCMySQL";
		String Username="root";
		String Password ="Jayasri30";
		String Query = "insert into students(id,name,dept) values (?,?,?)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			System.out.println("In catch"+e.getMessage());
		}
		try {
			con = DriverManager.getConnection(URL,Username,Password);
			PreparedStatement preparedStatement = con.prepareStatement(Query);
			preparedStatement.setInt(1, 9);
			preparedStatement.setString(2, "Ram");
			preparedStatement.setString(3,"CSD");
			preparedStatement.execute();
			//stmt = con.createStatement();
			//stmt.execute(UpdateRecord);
			
			//rs.close();
			//stmt.close();
			con.close();
			System.out.println("Inserted Sucessfully");
		}catch(SQLException e) {
			System.out.println("In catch"+e.getMessage());
		}
		
		finally
		{
			if (con!=null) {
				try {
					//rs.close();
					//stmt.close();
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

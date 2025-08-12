package com.learnJDBC;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatementJDBC1 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBCMySQL", "root", "Jayasri30");

		
		Statement stmt=con.createStatement();
	    ResultSet rs = stmt.executeQuery("select * from students");
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

	}

}

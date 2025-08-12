package com.learnJDBC;
import java.sql.*;

public class Doctor {
	
	// Connection object to connect to the database
	private Connection connection;
	public Doctor(Connection connection)
	{
		this.connection = connection;
		
	}
	
	//method to view all Doctors
	public void viewDoctors()
	{
		String query = "select * from doctors";
		try {
			 // Prepare and execute the SQL query
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultset = preparedStatement.executeQuery();
			// Print table header
			System.out.println("Doctors:");
			System.out.println("+----+---------+---------------+");
			System.out.println("| id | name    | Specilization |");
			System.out.println("+----+---------+---------------+");
			
			// Iterate through the result set and print each doctor’s details
			while(resultset.next())
			{
				int id = resultset.getInt("id");
				String name = resultset.getString("name");
				String Specilization = resultset.getString("Specilization");
				
				 // Print formatted row
				System.out.printf("|%-13s|%-21s|%-65%-10s|\n", id, name, Specilization);
				System.out.println("+----------+------------+------+------+");
			}
			}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		}
	//method to view all Doctors By Id
	public boolean getDoctorByID(int id)
	{
	String query = "select * from doctors where ID = ?";
	try {
	PreparedStatement preparedstatement = connection.prepareStatement(query); 
	preparedstatement.setInt(1,id);
	ResultSet resultset = preparedstatement.executeQuery();
	if(resultset.next()) {
		return true;
		}
	else {
		return false;
		}
	}
	catch(SQLException e) {
		e.printStackTrace();
		}
	return false;
		}
	
	}


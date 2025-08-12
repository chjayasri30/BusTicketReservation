package com.learnJDBC;
import java.sql.*;
import java.util.Scanner;

//create a class and name it as Patient
public class Patient {
	private Connection connection; // JDBC Connection to the database
	private Scanner scanner ; // Scanner for user input
	
	// Initialize connection and scanner
	public Patient(Connection connection, Scanner scanner)
	{
		this.connection = connection;
		this.scanner = scanner;
	}
	
	// Method to add a new patient to the database
	public void addPatient()
	{
		System.out.println("Enter Patient's Name");
		String name = scanner.next();
		System.out.println("Enter Patient's Age");
		int age = scanner.nextInt();
		System.out.println("Enter Patient's Gender");
		String gender = scanner.next();
		try {
			// SQL query to insert patient details
			String Query = "Insert into patients (name, age, gender) values (?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(Query);
			// Setting parameter values
			preparedStatement.setString(1,name);
			preparedStatement.setInt(2,age);
			preparedStatement.setString(3,gender);
			 // Execute the query and check rows affected
			int affectedRows = preparedStatement.executeUpdate();
			if(affectedRows>0)
			{
				System.out.println("Patient Added Successfully");
			}
			else {
				System.out.println("Failed to add Patient!!");
			}
			}
		catch(SQLException e)
			{
			e.printStackTrace();

			}

			}
	// Method to view all patients from the database
	public void viewPatients()
	{
		String query = "select * from patients";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultset = preparedStatement.executeQuery();
			// Display table header
			System.out.println("Patients:");
			System.out.println("+----------+------------+------+------+");
			System.out.println("|Patient ID|Name        |Age   |Gender|");
			System.out.println("+----------+------------+------+------+");
			while(resultset.next())
			{
				int id = resultset.getInt("id");
				String name = resultset.getString("name");
				int age = resultset.getInt("age");
				String gender = resultset.getString("gender");
				
				// Display patient data in table format
				System.out.printf("|%-13s|%-21s|%-65%-10s|\n", id, name, age, gender);
				System.out.println("+----------+------------+------+------+");
			}
			}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		}
	//getPatientById
	// Method to check if a patient exists by their ID
	public boolean getPatientByID(int id)
	{
	String query = "select * from patients where ID = ?";
	try {
	PreparedStatement preparedstatement = connection.prepareStatement(query); 
	preparedstatement.setInt(1,id);
	ResultSet resultset = preparedstatement.executeQuery();
	// If a record exists with the given ID, return true
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



package com.learnJDBC;

import java.sql.*;


public class passenger {
	private Connection connection;

    public passenger(Connection connection) {
        this.connection = connection;
    }

    // Insert a new passenger
    public boolean addPassenger(String name, String gender, String phone, String email) throws Exception {
        String query = "INSERT INTO passenger (name, gender, phone, email) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, gender); // gender should be "male", "female", or "other"
        ps.setString(3, phone);
        ps.setString(4, email);
        int rowsInserted = ps.executeUpdate();
        ps.close();
        return rowsInserted > 0;
    }

    // View all passengers
    public void viewPassengers() throws Exception {
        String query = "SELECT * FROM passenger";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        System.out.println("Passenger Details:");
        System.out.println("+--------------+----------------------+----------+-----------------+----------------------+");
        System.out.printf("| %-12s | %-20s | %-8s | %-15s | %-20s |\n", "passenger_id", "name", "gender", "phone", "email");
        System.out.println("+--------------+----------------------+----------+-----------------+----------------------+");

        while (rs.next()) {
            int passengerId = rs.getInt("passenger_id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            String phone = rs.getString("phone");
            String email = rs.getString("email");

            System.out.printf("| %-12d | %-20s | %-8s | %-15s | %-20s |\n", passengerId, name, gender, phone, email);
        }

        System.out.println("+--------------+----------------------+----------+-----------------+----------------------+");
        rs.close();
        ps.close();
    }

    // Get passenger details by ID
    public void getPassengerById(int passengerId) throws Exception {
        String query = "SELECT * FROM passenger WHERE passenger_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, passengerId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("Passenger Details:");
            System.out.println("ID: " + rs.getInt("passenger_id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Gender: " + rs.getString("gender"));
            System.out.println("Phone: " + rs.getString("phone"));
            System.out.println("Email: " + rs.getString("email"));
        } else {
            System.out.println("Passenger not found with ID: " + passengerId);
        }
        rs.close();
        ps.close();
    }
}

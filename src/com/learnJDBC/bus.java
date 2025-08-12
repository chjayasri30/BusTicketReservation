package com.learnJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;

public class bus {
	private Connection connection;
	public bus(Connection connection)
	{
		this.connection = connection;
	}
	public void viewBus() throws Exception {
        String query = "SELECT * FROM bus";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println("Bus Details:");
        System.out.println("+--------+------------+----------------------+------------+----------------+--------------+--------------+-------------+");
        System.out.printf("| %-6s | %-10s | %-20s | %-10s | %-14s | %-12s | %-12s | %-11s |\n",
                "bus_id", "bus_number", "route_from", "route_to", "departure_time", "arrival_time", "ticket_price", "total_seats");
        System.out.println("+--------+------------+----------------------+------------+----------------+--------------+--------------+-------------+");

        while (resultSet.next()) {
            int bus_id = resultSet.getInt("bus_id");
            String bus_number = resultSet.getString("bus_number");
            String route_from = resultSet.getString("route_from");
            String route_to = resultSet.getString("route_to");
            Time departure_time = resultSet.getTime("departure_time");
            Time arrival_time = resultSet.getTime("arrival_time");
            double ticket_price = resultSet.getDouble("ticket_price");
            int total_seats = resultSet.getInt("total_seats");

            System.out.printf("| %-6d | %-10s | %-20s | %-10s | %-14s | %-12s | %-12.2f | %-11d |\n",
                    bus_id, bus_number, route_from, route_to, departure_time.toString(), arrival_time.toString(), ticket_price, total_seats);
        }

        System.out.println("+--------+------------+----------------------+------------+----------------+--------------+--------------+-------------+");

        resultSet.close();
        preparedStatement.close();
    }
}
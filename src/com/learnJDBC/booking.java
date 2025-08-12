package com.learnJDBC;

import java.sql.*;

public class booking {
	private Connection connection;

    public booking(Connection connection) {
        this.connection = connection;
    }

    // Make a new booking
    public boolean makeBooking(int busId, int passengerId, int seatNumber) throws Exception {
        // Check if seat already booked & confirmed
        String checkQuery = "SELECT 1 FROM booking WHERE bus_id = ? AND seat_number = ? AND status = 'Confirmed'";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, busId);
            checkStmt.setInt(2, seatNumber);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Seat " + seatNumber + " on bus " + busId + " is already booked.");
                    return false;
                }
            }
        }

        // Insert booking with status = Confirmed (default)
        String insertQuery = "INSERT INTO booking (bus_id, passenger_id, seat_number) VALUES (?, ?, ?)";
        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
            insertStmt.setInt(1, busId);
            insertStmt.setInt(2, passengerId);
            insertStmt.setInt(3, seatNumber);
            int rows = insertStmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Booking confirmed: Bus " + busId + ", Passenger " + passengerId + ", Seat " + seatNumber);
                return true;
            } else {
                System.out.println("Booking failed.");
                return false;
            }
        }
    }

    // Cancel booking by booking_id
    public boolean cancelBooking(int bookingId) throws Exception {
        String updateQuery = "UPDATE booking SET status = 'Cancelled' WHERE booking_id = ? AND status = 'Confirmed'";
        try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
            updateStmt.setInt(1, bookingId);
            int rows = updateStmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Booking " + bookingId + " cancelled successfully.");
                return true;
            } else {
                System.out.println("Booking " + bookingId + " not found or already cancelled.");
                return false;
            }
        }
    }

    // View bookings for a bus
    public void viewBookingsForBus(int busId) throws Exception {
        String query = "SELECT b.booking_id, p.name, b.seat_number, b.booking_date, b.status " +
                       "FROM booking b JOIN passenger p ON b.passenger_id = p.passenger_id " +
                       "WHERE b.bus_id = ? ORDER BY b.seat_number";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, busId);
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("Bookings for Bus ID: " + busId);
                System.out.println("+------------+----------------------+-------------+---------------------+-----------+");
                System.out.printf("| %-10s | %-20s | %-11s | %-19s | %-9s |\n",
                                  "booking_id", "passenger_name", "seat_number", "booking_date", "status");
                System.out.println("+------------+----------------------+-------------+---------------------+-----------+");

                while (rs.next()) {
                    int bookingId = rs.getInt("booking_id");
                    String passengerName = rs.getString("name");
                    int seatNumber = rs.getInt("seat_number");
                    String bookingDate = rs.getString("booking_date");
                    String status = rs.getString("status");

                    System.out.printf("| %-10d | %-20s | %-11d | %-19s | %-9s |\n",
                                      bookingId, passengerName, seatNumber, bookingDate, status);
                }
                System.out.println("+------------+----------------------+-------------+---------------------+-----------+");
            }
        }
    }

    // View bookings by passenger
    public void viewBookingsByPassenger(int passengerId) throws Exception {
        String query = "SELECT b.booking_id, bus.bus_number, b.seat_number, b.booking_date, b.status " +
                       "FROM booking b JOIN bus ON b.bus_id = bus.bus_id " +
                       "WHERE b.passenger_id = ? ORDER BY b.booking_date DESC";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, passengerId);
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("Bookings for Passenger ID: " + passengerId);
                System.out.println("+------------+------------+-------------+---------------------+-----------+");
                System.out.printf("| %-10s | %-10s | %-11s | %-19s | %-9s |\n",
                                  "booking_id", "bus_number", "seat_number", "booking_date", "status");
                System.out.println("+------------+------------+-------------+---------------------+-----------+");

                while (rs.next()) {
                    int bookingId = rs.getInt("booking_id");
                    String busNumber = rs.getString("bus_number");
                    int seatNumber = rs.getInt("seat_number");
                    String bookingDate = rs.getString("booking_date");
                    String status = rs.getString("status");

                    System.out.printf("| %-10d | %-10s | %-11d | %-19s | %-9s |\n",
                                      bookingId, busNumber, seatNumber, bookingDate, status);
                }
                System.out.println("+------------+------------+-------------+---------------------+-----------+");
            }
        }
    }
}


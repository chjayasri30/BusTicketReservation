package com.learnJDBC;

import java.sql.*;
import java.util.Scanner;


public class main {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // 1. Connect to database (change URL, user, password as needed)
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bus_reservation", "root", "Jayasri30");

            bus busDAO = new bus(connection);
            passenger passengerDAO = new passenger(connection);
            booking bookingDAO = new booking(connection);

            boolean running = true;
            while (running) {
                System.out.println("\n===== Bus Ticket Reservation System =====");
                System.out.println("1. View all buses");
                System.out.println("2. View all passengers");
                System.out.println("3. Add a new passenger");
                System.out.println("4. Book a ticket");
                System.out.println("5. Cancel a booking");
                System.out.println("6. View bookings by bus");
                System.out.println("7. View bookings by passenger");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        busDAO.viewBus();
                        break;

                    case 2:
                        passengerDAO.viewPassengers();
                        break;

                    case 3:
                        System.out.print("Enter passenger name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter gender (male/female/other): ");
                        String gender = scanner.nextLine();

                        System.out.print("Enter phone number: ");
                        String phone = scanner.nextLine();

                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();

                        boolean added = passengerDAO.addPassenger(name, gender, phone, email);
                        if (added) System.out.println("Passenger added successfully!");
                        else System.out.println("Failed to add passenger.");
                        break;

                    case 4:
                        System.out.print("Enter bus ID: ");
                        int busId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter passenger ID: ");
                        int passengerId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter seat number: ");
                        int seatNumber = Integer.parseInt(scanner.nextLine());

                        bookingDAO.makeBooking(busId, passengerId, seatNumber);
                        break;

                    case 5:
                        System.out.print("Enter booking ID to cancel: ");
                        int bookingId = Integer.parseInt(scanner.nextLine());

                        bookingDAO.cancelBooking(bookingId);
                        break;

                    case 6:
                        System.out.print("Enter bus ID to view bookings: ");
                        int busViewId = Integer.parseInt(scanner.nextLine());

                        bookingDAO.viewBookingsForBus(busViewId);
                        break;

                    case 7:
                        System.out.print("Enter passenger ID to view bookings: ");
                        int passengerViewId = Integer.parseInt(scanner.nextLine());

                        bookingDAO.viewBookingsByPassenger(passengerViewId);
                        break;

                    case 0:
                        running = false;
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }

            connection.close();
            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.online_reservation_system;

/**
 *
 * @author NEELIMA
 */
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Reservation {
    private String trainName;
    private String source;
    private String destination;

    public Reservation(String trainName, String source, String destination) {
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}

public class OnlineReservationSystem {
    private static User loggedInUser;
    private static Reservation currentReservation;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                loggedInUser = login(scanner);
                if (loggedInUser != null) {
                    System.out.println("Logged in as: " + loggedInUser.getUsername());
                    handleReservation(scanner);
                }
            } else if (choice == 2) {
                System.out.println("Exiting the application. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        scanner.close();
    }

    private static User login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        // Validate login credentials (Dummy data for demonstration)
        if (username.equals("mahesh") && password.equals("vijay@123")) {
            return new User(username, password);
        } else {
            System.out.println("Invalid credentials. Login failed.");
            return null;
        }
    }

    private static void handleReservation(Scanner scanner) {
        while (true) {
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                makeReservation(scanner);
            } else if (choice == 2) {
                cancelReservation(scanner);
            } else if (choice == 3) {
                System.out.println("Logging out.");
                loggedInUser = null;
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter train name: ");
        String trainName = scanner.next();
        System.out.print("Enter source: ");
        String source = scanner.next();
        System.out.print("Enter destination: ");
        String destination = scanner.next();

        currentReservation = new Reservation(trainName, source, destination);
        System.out.println("Reservation successful!");
    }

    private static void cancelReservation(Scanner scanner) {
        if (currentReservation == null) {
            System.out.println("No reservation to cancel.");
        } else {
            System.out.println("Reservation canceled for " + currentReservation.getTrainName());
            currentReservation = null;
        }
    }
}


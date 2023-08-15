/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.atm_working;

/**
 *
 * @author NEELIMA
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String userId;
    private String pin;
    public User(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
    }
    public String getUserId() {
        return userId;
    }
    public String getPin() {
        return pin;
    }
}

class Transaction {
    private String type_of_transaction;
    private double amount;
    public Transaction(String transactionType, double amount) {
        this.type_of_transaction = transactionType;
        this.amount = amount;
    }
    public String getTransactionType() {
        return type_of_transaction;
    }
    public double getAmount() {
        return amount;
    }
    
}

class TransactionHistory {
    private User user;
    private List<Transaction> transactions;

    public TransactionHistory(User user) {
        this.user = user;
        this.transactions = new ArrayList<>();
    }
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void displayTransactionHistory() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getTransactionType() + ": " + transaction.getAmount());
        }
    }
}


interface atm_functions {
    void withdraw(double amount);
    void deposit(double amount);
    void transfer(String targetUserId, double amount);
}

public class atm_working implements atm_functions {
    private User user;
    private double balance;
    private TransactionHistory transactionHistory;

    public atm_working(User user, double initialBalance) {
        this.user = user;
        this.balance = initialBalance;
        this.transactionHistory = new TransactionHistory(user);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.addTransaction(new Transaction("Withdraw", amount));
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        transactionHistory.addTransaction(new Transaction("Deposit", amount));
        System.out.println("Deposited: " + amount);
    }

    @Override
    public void transfer(String targetUserId, double amount) {
        balance -= amount;
        transactionHistory.addTransaction(new Transaction("Transfer to " + targetUserId, amount));
        System.out.println("Transferred: " + amount + " to " + targetUserId);
    }
    public void displaybalance() {
        System.out.println("Balance: " + balance);
    }

    public void displayTransactionHistory() {
        transactionHistory.displayTransactionHistory();
    }

    public static void main(String[] args) {
        
        
        User user = new User("neelima", "5262");
        atm_working atm = new atm_working(user, 1000.0);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to ATM Interface!");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (userId.equals(user.getUserId()) && pin.equals(user.getPin())) {
            System.out.println("Login successful!");
            boolean quit = false;

            while (!quit) {
                System.out.println("\nSelect an option:");
                System.out.println("1. Transaction History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        atm.displayTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter target User ID: ");
                        String targetUserId = scanner.next();
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        atm.transfer(targetUserId, transferAmount);
                        break;
                    case 5:
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
        scanner.close();
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.digital_library_management;

/**
 *
 * @author NEELIMA
 */
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

public class Digitallibrarymanagement{
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book("Gitanjali", "Rabindranath Tagore"));
        books.add(new Book("Revolution 2020", "Chetan Bhagat"));
        books.add(new Book("Naked Triangle", "Balwant Gargi"));
        books.add(new Book("An Autobiography", "Jawaharlal Nehru"));
        books.add(new Book("By God’s Decree", "Kapil Dev"));
        books.add(new Book("Gita Rahasya", "Bal Gangadhar Tilak"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Admin Module");
            System.out.println("2. User Module");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    adminModule(books, scanner);
                    break;
                case 2:
                    userModule(books);
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public static void adminModule(ArrayList<Book> books, Scanner scanner) {
        System.out.println("Admin Module - Add Book");
        System.out.print("Enter book title: ");
        scanner.nextLine(); // Consume newline
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        books.add(new Book(title, author));
        System.out.println("Book added successfully.");
    }

    public static void userModule(ArrayList<Book> books) {
        System.out.println("User Module - View Books");
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("List of books:");
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                System.out.println((i + 1) + ". Title: " + book.getTitle() + ", Author: " + book.getAuthor());
            }
        }
    }
}

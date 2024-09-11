package com.library.ui;

import com.library.utils.UI;

import java.util.Scanner;

public class MainMenu {

    private static Scanner scanner = new Scanner(System.in);

    public static void display() {
        System.out.println("===== Library Management System =====");
        System.out.println("1. Manage Documents");
        System.out.println("2. Manage Users");
        System.out.println("3. Manage Reservations");
        System.out.println("4. Exit");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
//                UI.clear();
                DocumentMenu.display();
                break;
            case 2:
//                UI.clear();
                UserMenu.display();
                break;
            case 3:
                ReservationMenu.display();
                break;
            case 4:
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
                display();
                break;
        }
    }
}

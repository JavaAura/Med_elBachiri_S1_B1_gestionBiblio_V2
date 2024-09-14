package com.library.ui;

import com.library.service.Library;
import com.library.service.UserService;
import com.library.utils.InputValidator;
import com.library.utils.UI;

import java.util.Scanner;

public class SubMenu {

    private static Library library = new Library();
    private static UserService userService = new UserService();
    private static Scanner scanner = new Scanner(System.in);

    public static void display(){
        System.out.println("===== Library Management System =====");
        System.out.println("1. View All Documents.");
        System.out.println("2. Search Documents.");
        System.out.println("3. View All Users");
        System.out.println("4. Manage Reservations");
        System.out.println("5. Exit");
        String choice = getUserInput();

        switch (choice){
            case "1":
                UI.clear();
                library.viewAllDocs();
                display();
                break;
            case "2":
                UI.clear();
                library.search();
                display();
                break;
            case "3":
                UI.clear();
                userService.viewAll();
                display();
                break;
            case "4":
                UI.clear();
                ReservationMenu.display();
                display();
                break;
            case "5":
                System.out.println("Goodbye");
                return;
            default:
                UI.clear();
                System.out.println("[-] invalid choice.");
                display();
                break;
        }

    }

    private static String getUserInput(){
        while (true){
            System.out.println("Enter your choice: ");
            String choice = scanner.nextLine();
            if (InputValidator.isValidNumber(choice)){
                return choice;
            } else System.out.println("[-] invalid number format, try again.\n");
        }

    }
}

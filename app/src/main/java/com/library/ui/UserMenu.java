package com.library.ui;

import com.library.service.UserService;
import com.library.utils.InputValidator;
import com.library.utils.UI;

import java.util.Scanner;

public class UserMenu {

    private static UserService userService = new UserService();
    private static Scanner scanner = new Scanner(System.in);

    public static void display(){
        System.out.println("===== User Management =====");
        System.out.println("1. Add User");
        System.out.println("2. View All Users");
        System.out.println("3. Update User");
        System.out.println("4. Delete User");
        System.out.println("5. Back to Main Menu");


        String choice = scanner.nextLine();
        if (!InputValidator.isValidNumber(choice)) {
            System.out.println("Invalid choice, try again.");
            display();
            return;
        }
        switch (choice) {
            case "1":
                UI.clear();
                add();
                break;
            case "2":
                UI.clear();
                viewAll();
                break;
            case "3":
                UI.clear();
                update();
                break;
            case "4":
                UI.clear();
                delete();
                break;
            case "5":
                MainMenu.display();
                return;
            default:
                System.out.println("Invalid choice.");
                display();
        }
    }

    private static void add() {
        userService.create();
        display();
    }

    private static void viewAll(){
        userService.viewAll();
        display();
    }
    private static void update(){
        userService.update();
        display();
    }
    private static void delete(){
        userService.delete();
        display();
    }
}

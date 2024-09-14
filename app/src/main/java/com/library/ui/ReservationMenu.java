package com.library.ui;

import com.library.service.ReservationService;
import com.library.utils.InputValidator;
import com.library.utils.UI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ReservationMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static ReservationService reservation = new ReservationService();


    public static void display(){
        System.out.println("===== Reservation Management =====");
        System.out.println("1. View Borrowed Documents");
        System.out.println("2. Reserve Document");
        System.out.println("3. Cancel Reservation");
        System.out.println("4. Back to Menu");


        try {
            String choice = scanner.nextLine();
            if (!InputValidator.isValidNumber(choice)) {
                System.out.println("Invalid choice, try again.");
                display();
                return;
            }
            switch (choice) {
                case "1":
                    UI.clear();
                    reservation.viewBorrowedDocs();
                    display();
                    break;
                case "2":
                    UI.clear();
                    reservation.reserveDoc();
                    display();
                    break;
                case "3":
                    reservation.cancelReservation();
                    break;
                case "4":
                    MainMenu.display();
                default:
                    System.out.println("Invalid choice. Please try again.");
                    display();
                    break;
            }
        } catch (InputMismatchException e){
            System.out.println("Invalid choice, try again.");
            display();
        }
    }
}

package com.library.ui;

import com.library.utils.InputValidator;
import com.library.utils.UI;

import java.util.Scanner;

public class ConsoleUI {
    private static Scanner scan = new Scanner(System.in);
    public static void start(){
        String mode = getAppMode();
        switch (mode){
            case "1":
                MainMenu.display();
                break;
            case "2":
                SubMenu.display();
                break;
            default:
                System.out.println("invalid mode.");
                break;
        }
    }

    private static String getAppMode(){
        while (true){
            UI.clear();
            System.out.println("Choose your mode, What are you: ");
            System.out.println("1. Professor");
            System.out.println("2. Student");
            String choice = scan.nextLine();
            if (InputValidator.isValidNumber(choice)) return choice;
        }

    }
}

package com.library.service;

import com.library.daoImpl.UserDaoImpl;
import com.library.model.Professor;
import com.library.model.Student;
import com.library.model.User;
import com.library.utils.DateUtils;
import com.library.utils.InputValidator;
import com.library.utils.UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserService {

    private static Scanner scanner = new Scanner(System.in);
    private static UserDaoImpl userDao = new UserDaoImpl();

    public void create() {
        int choice = getUserType();
        switch (choice) {
            case 1:
                userDao.create(getStudentInp());
                break;
            case 2:
                userDao.create(getProfessorInp());
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public void update() {
        int choice = getUserType();
        switch (choice){
            case 1:
                int studentId = Integer.parseInt(getUserID());
                Student student = getStudentInp();
                student.setId(studentId);
                userDao.update(student);
                break;
            case 2:
                int profId = Integer.parseInt(getUserID());
                Professor prof = getProfessorInp();
                prof.setId(profId);
                userDao.update(prof);
                break;
        }
    }

    public void delete() {
        int choice =  getUserType();
        String id = getUserID();
        userDao.delete(id, choice);
    }

    public void viewAll() {
        UI.underline();
        userDao.getAll().forEach(User::display);
    }

    private static String getUserID() {
        while (true) {
            System.out.println("Enter user ID: ");
            String choice = scanner.nextLine();
            if (InputValidator.isValidNumber(choice)) {
                return choice;
            } else {
                System.out.println("Invalid number format, try again.");
            }
        }
    }

    private static int getUserType() {
        while (true) {
            System.out.println("Choose the Role of the User: ");
            System.out.println("1. Student");
            System.out.println("2. Professor");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= 2) return choice;
                else System.out.println("Invalid choice, please enter a number between 1 and 2.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static Student getStudentInp() {
        System.out.println("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Student Email: ");
        String email = scanner.nextLine();

        System.out.println("Enter Student Age: ");
        String age = scanner.nextLine();
        while (!InputValidator.isValidNumber(age)) {
            System.out.println("Invalid age, please enter a valid number: ");
            age = scanner.nextLine();
        }
        int studentAge = Integer.parseInt(age);

        System.out.println("Enter Integration Date (DD/MM/YYYY): ");
        String integrationDate = scanner.nextLine();
        while (!DateUtils.isValidDate(integrationDate)) {
            System.out.println("Invalid date, please enter a valid date (DD/MM/YYYY): ");
            integrationDate = scanner.nextLine();
        }
        LocalDate date = LocalDate.parse(integrationDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return new Student(name, email, studentAge, date);
    }

    private static Professor getProfessorInp() {
        System.out.println("Enter Professor Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Professor Email: ");
        String email = scanner.nextLine();

        System.out.println("Enter Professor Age: ");
        String age = scanner.nextLine();
        while (!InputValidator.isValidNumber(age)) {
            System.out.println("Invalid age, please enter a valid number: ");
            age = scanner.nextLine();
        }
        int professorAge = Integer.parseInt(age);

        System.out.println("Enter Department: ");
        String department = scanner.nextLine();

        return new Professor(name, email, professorAge, department);
    }
}

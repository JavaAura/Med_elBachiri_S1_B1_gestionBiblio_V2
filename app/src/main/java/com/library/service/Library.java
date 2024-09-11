package com.library.service;

import com.library.daoImpl.BookDaoImpl;
import com.library.daoImpl.DocumentDaoImpl;
import com.library.model.Book;
import com.library.model.Magazine;
import com.library.model.ScientificJournal;
import com.library.model.UniversityThesis;
import com.library.ui.MainMenu;
import com.library.utils.DateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Library {

    private static DocumentDaoImpl docDao = new DocumentDaoImpl();
    private static Scanner scanner = new Scanner(System.in);

    public void addDocument() {
        int choice =  getDocType();
        System.out.println("Enter Title: ");
        String title = scanner.next();
        System.out.println("Enter Author: ");
        String author = scanner.next();
        System.out.println("Enter Publication Date (DD/MM/YYYY): ");
        String pubDate = scanner.next();
        scanner.nextLine();
        while (!DateUtils.isValidDate(pubDate)){
            System.out.println("Invalid Date, Enter Publication Date (DD/MM/YYYY): ");
            pubDate = scanner.next();
        }
        LocalDate date = LocalDate.parse(pubDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Enter number of pages: ");
        String numPages = scanner.next();


        switch (choice) {
            case 1:
                System.out.println("Enter ISBN: ");
                String isbn = scanner.next();
                Book book = new Book(title, author, date, Integer.parseInt(numPages), isbn);
                docDao.create(book);
            break;
            case 2:
                System.out.println("Enter Number: ");
                String number = scanner.next();
                Magazine magazine = new Magazine(title, author, date, Integer.parseInt(numPages), Integer.parseInt(number));
                docDao.create(magazine);
                MainMenu.display();
            break;
            case 3:
                System.out.println("Enter Impact Factor: ");
                String impactFactor = scanner.next();
                ScientificJournal scJou = new ScientificJournal(title, author, date, Integer.parseInt(numPages), Double.parseDouble(impactFactor));
                docDao.create(scJou);
            break;
            case 4:
                System.out.println("Enter Degree Program : ");
                String degreeProgram = scanner.next();
                UniversityThesis uniThe = new UniversityThesis(title, author, date, Integer.parseInt(numPages), degreeProgram);
                docDao.create(uniThe);
            break;

        }
    }


    private static int getDocType(){
        System.out.println("Choose the type of the document : ");
        System.out.println("1. Book");
        System.out.println("2. Magazine");
        System.out.println("3. Scientific Journal");
        System.out.println("4. University Thesis");
        return scanner.nextInt();
    }

}

package com.library.service;

import com.library.daoImpl.*;
import com.library.model.*;
import com.library.ui.DocumentMenu;
import com.library.ui.MainMenu;
import com.library.utils.DateUtils;
import com.library.utils.InputValidator;
import com.library.utils.UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Library {

    private static DocumentDaoImpl docDao = new DocumentDaoImpl();
    private static Scanner scanner = new Scanner(System.in);

    private static List<Document> docs = new ArrayList<Document>();

    public Library(){
        docDao.getAll().forEach((k, v) -> docs.add(v));
    }

    public void addDocument() {
//        UI.clear();
        int choice =  getDocType();
        String title;
        title = getCleanStr("Title");
        String author = getCleanStr("Author");
        System.out.println("Enter Publication Date (DD/MM/YYYY): ");
        String pubDate = scanner.nextLine();
        while (!DateUtils.isValidDate(pubDate)){
            System.out.println("Invalid Date, Enter Publication Date Like (DD/MM/YYYY): ");
            pubDate = scanner.nextLine();
        }
        LocalDate date = LocalDate.parse(pubDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Enter number of pages: ");
        String numPages = scanner.nextLine();

        try {
            int pages = Integer.parseInt(numPages);
            switch (choice) {
                case 1:
                    System.out.println("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    Book book = new Book(title, author, date, pages, isbn);
                    docDao.create(book);
                break;
                case 2:
                    System.out.println("Enter Number: ");
                    String number = scanner.nextLine();
                    if (InputValidator.isValidNumber(number)){
                        Magazine magazine = new Magazine(title, author, date, pages, Integer.parseInt(number));
                        docDao.create(magazine);
                    } else System.out.println("Invalid number format, Try again.");
                break;
                case 3:
                    System.out.println("Enter Impact Factor: ");
                    String impactFactor = scanner.nextLine();
                    if (InputValidator.isValidDouble(impactFactor)){
                        ScientificJournal scJou = new ScientificJournal(title, author, date, pages, Double.parseDouble(impactFactor));
                        docDao.create(scJou);
                    } else System.out.println("Invalid number format, Try again.");
                break;
                case 4:
                    System.out.println("Enter Degree Program : ");
                    String degreeProgram = scanner.nextLine();
                    UniversityThesis uniThe = new UniversityThesis(title, author, date,pages, degreeProgram);
                    docDao.create(uniThe);
                break;

            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format, Try again.");
        }


    }

    public void viewAllDocs(){
        UI.underline();
        for (Document doc : docDao.getAll().values()){
            doc.display();
            UI.underline();
        }
    }


    private static List<Document> sort(List<Document> docs, Comparator<Document> comparator){
        return docs.stream().sorted(comparator).collect(Collectors.toList());
    }

    public void search(){
        try {
            int choice = getSearchType();
            scanner.nextLine();
            switch (choice){
                case 1:
                    System.out.println("Enter Title: ");
                    String title = scanner.nextLine();
                    searchByTitle(title).forEach(Document::display);
                    break;
                case 2:
                    System.out.println("Enter Author: ");
                    String author = scanner.nextLine();
                    searchByAuthor(author).forEach(Document::display);
                    break;
                case 3:
                    System.out.println("Enter ID: ");
                    String id = scanner.nextLine();
                    if (InputValidator.isValidNumber(id)){
                        searchByID(id);
                    } else System.out.println("Invalid number format, Try again.");
                    break;
                case 4:
                    MainMenu.display();
                    return;
                default:
                    System.out.println("Invalid choice, Try again.");

            }
        } catch (NumberFormatException e){
            System.out.println("Invalid number format, Try again.");
        }

    }

    private static List<Document> searchByTitle(String query) {
        return docs.stream()
                .filter(doc -> doc.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
    private static List<Document> searchByAuthor(String query){
        return docs.stream()
                .filter(doc -> doc.getAuthor().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
    private static void searchByID(String id){
        int docType = getDocType();
        if (docType == 1 || docType == 2 || docType == 3 || docType ==4){
            docDao.get(id, docType);
        } else {
            System.out.println("Invalid option, try again.");
        }
    }

    public void delete(){
        int choice = getDocType();
        String id = getDocId();
        if (InputValidator.isValidNumber(id)){
            docDao.delete(id, choice);
        } else {
            System.out.println("Invalid input, try again.");
        }
    }

    private static String getDocId(){
        while (true){
            System.out.println("Enter Document ID: ");
            String id = scanner.nextLine();
            if (InputValidator.isValidNumber(id)){
                return id;
            }
        }
    }
    private static String getUserID(){
        while (true){
            System.out.println("Enter user ID: ");
            String id = scanner.nextLine();
            if (InputValidator.isValidNumber(id)){
                return id;
            } else System.out.println("Invalid number format, try again");

        }
    }

    public void borrowDoc(){
        int choice = getDocType();
        switch (choice){
            case 1:
                docDao.borrow(Integer.parseInt(getDocId()), getUserType(), Integer.parseInt(getUserID()), "book");
                DocumentMenu.display();
                break;
            case 2:
                docDao.borrow(Integer.parseInt(getDocId()), getUserType(), Integer.parseInt(getUserID()), "magazine");
                DocumentMenu.display();
                break;
            case 3:
                docDao.borrow(Integer.parseInt(getDocId()), getUserType(), Integer.parseInt(getUserID()), "scientific_journal");
                DocumentMenu.display();
                break;
            case 4:
                docDao.borrow(Integer.parseInt(getDocId()), getUserType(), Integer.parseInt(getUserID()), "university_thesis");
                DocumentMenu.display();
                break;
        }
    }

    public void returnDoc(){
        int choice = getDocType();
        String id = getDocId();
        String docType = (choice == 1) ? "books" : (choice == 2) ? "magazines" : (choice == 3) ? "scientific_journals" : "university_thesis";
        docDao.returnDocument(Integer.parseInt(id), docType);
        DocumentMenu.display();
    }

    private static int getDocType() {
        while (true) {
            UI.clear();
            System.out.println("Choose the type of the document: ");
            System.out.println("1. Book");
            System.out.println("2. Magazine");
            System.out.println("3. Scientific Journal");
            System.out.println("4. University Thesis");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= 4) return choice; else System.out.println("Invalid choice, please enter a number between 1 and 4.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    public void update(){
        int choice = getDocType();
        String docId = getDocId();
        String title = getCleanStr("New Title");
        String author = getCleanStr("New Author");
        System.out.println("Enter New Publication Date (DD/MM/YYYY): ");
        String pubDate = scanner.nextLine();
        while (!DateUtils.isValidDate(pubDate)){
            System.out.println("Invalid Date, Enter Publication Date Like (DD/MM/YYYY): ");
            pubDate = scanner.nextLine();
        }
        LocalDate date = LocalDate.parse(pubDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Enter New number of pages: ");
        String numPages = scanner.nextLine();


        try {
            int pages = Integer.parseInt(numPages);
            switch (choice) {
                case 1:
                    System.out.println("Enter New ISBN: ");
                    String isbn = scanner.nextLine();
                    Book book = new Book(title, author, date, pages, isbn);
                    book.setId(docId);
                    docDao.update(book);
                    break;
                case 2:
                    System.out.println("Enter New Number: ");
                    String number = scanner.nextLine();
                    if (InputValidator.isValidNumber(number)){
                        Magazine magazine = new Magazine(title, author, date, pages, Integer.parseInt(number));
                        magazine.setId(docId);
                        docDao.update(magazine);
                    } else System.out.println("Invalid number format, Try again.");
                    break;
                case 3:
                    System.out.println("Enter New Impact Factor: ");
                    String impactFactor = scanner.nextLine();
                    if (InputValidator.isValidDouble(impactFactor)){
                        ScientificJournal scJou = new ScientificJournal(title, author, date, pages, Double.parseDouble(impactFactor));
                        scJou.setId(docId);
                        docDao.update(scJou);
                    } else System.out.println("Invalid New number format, Try again.");
                    break;
                case 4:
                    System.out.println("Enter New Degree Program : ");
                    String degreeProgram = scanner.nextLine();
                    UniversityThesis uniThe = new UniversityThesis(title, author, date,pages, degreeProgram);
                    uniThe.setId(docId);
                    docDao.update(uniThe);
                    break;

            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format, Try again.");
        }

    }

    private static String getCleanStr(String type){
        while (true){
            System.out.println("Enter " + type + ": ");
            String input = scanner.nextLine();
            if (InputValidator.isCleanStr(input)){
                return input;
            } else System.out.println("[-] Please type a clean " + type);
        }
    }

    private static int getSearchType(){
        System.out.println("Choose Search type : ");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Author");
        System.out.println("3. Search by ID");
        System.out.println("4. Back to Home");
        return scanner.nextInt();
    }

    private static String getUserType() {
        while (true) {
            System.out.println("Enter User Type: ");
            System.out.println("1. Student");
            System.out.println("2. Professor");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    return "student";
                case "2":
                    return "professor";
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }


}

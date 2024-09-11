package com.library.service;

import com.library.daoImpl.BookDaoImpl;
import com.library.daoImpl.DocumentDaoImpl;
import com.library.model.*;
import com.library.ui.MainMenu;
import com.library.utils.DateUtils;
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
        int choice =  getDocType();
        scanner.nextLine();
        System.out.println("Enter Title: ");
        String title = scanner.nextLine();
        System.out.println("Enter Author: ");
        String author = scanner.nextLine();
        System.out.println("Enter Publication Date (DD/MM/YYYY): ");
        String pubDate = scanner.nextLine();
        while (!DateUtils.isValidDate(pubDate)){
            System.out.println("Invalid Date, Enter Publication Date (DD/MM/YYYY): ");
            pubDate = scanner.nextLine();
        }
        LocalDate date = LocalDate.parse(pubDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Enter number of pages: ");
        String numPages = scanner.nextLine();


        switch (choice) {
            case 1:
                System.out.println("Enter ISBN: ");
                String isbn = scanner.nextLine();
                Book book = new Book(title, author, date, Integer.parseInt(numPages), isbn);
                docDao.create(book);
            break;
            case 2:
                System.out.println("Enter Number: ");
                String number = scanner.nextLine();
                Magazine magazine = new Magazine(title, author, date, Integer.parseInt(numPages), Integer.parseInt(number));
                docDao.create(magazine);
            break;
            case 3:
                System.out.println("Enter Impact Factor: ");
                String impactFactor = scanner.nextLine();
                ScientificJournal scJou = new ScientificJournal(title, author, date, Integer.parseInt(numPages), Double.parseDouble(impactFactor));
                docDao.create(scJou);
            break;
            case 4:
                System.out.println("Enter Degree Program : ");
                String degreeProgram = scanner.nextLine();
                UniversityThesis uniThe = new UniversityThesis(title, author, date, Integer.parseInt(numPages), degreeProgram);
                docDao.create(uniThe);
            break;

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
                searchByID(id);
                break;
            case 4:
                MainMenu.display();
                return;
            default:
                System.out.println("Invalid choice, Try again.");

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
        docDao.get(id);
    }

    private static int getDocType(){
        System.out.println("Choose the type of the document : ");
        System.out.println("1. Book");
        System.out.println("2. Magazine");
        System.out.println("3. Scientific Journal");
        System.out.println("4. University Thesis");
        return scanner.nextInt();
    }
    private static int getSearchType(){
        System.out.println("Choose Search type : ");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Author");
        System.out.println("3. Search by ID");
        System.out.println("4. Back to Home");
        return scanner.nextInt();
    }

}

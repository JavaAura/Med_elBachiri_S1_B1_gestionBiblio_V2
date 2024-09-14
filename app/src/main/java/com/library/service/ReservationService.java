package com.library.service;

import com.library.daoImpl.DocumentDaoImpl;
import com.library.model.Document;
import com.library.utils.InputValidator;
import com.library.utils.UI;
import com.library.utils.db.DbConnection;
import java.util.Date;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ReservationService {
    private static Scanner scanner = new Scanner(System.in);
    private Connection cn = DbConnection.connect();
    private DocumentDaoImpl docDao = new DocumentDaoImpl();
    private static List<Document> docs = new ArrayList<Document>();

    public void viewBorrowedDocs(){
        DocumentDaoImpl docDao = new DocumentDaoImpl();
        for (Document doc : docDao.getAll().values()){
            if (doc.getBorrowed()) docs.add(doc);
        }
        UI.underline();
        docs.forEach(doc -> {doc.display(); UI.underline();});
    }
    public void reserveDoc(){
        Date now = new Date();
        String docType = getDocType();
        String id = getDocId();
        String user_type = getUserType();
        int user_id = Integer.parseInt(getUserID());
        if (docDao.docExist(Integer.parseInt(id), false)){
            String query = "INSERT INTO reservations (user_id, user_type, document_id, doc_type, reservation_date) VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement stm = cn.prepareStatement(query);
                stm.setInt(1, user_id);
                stm.setString(2, user_type);
                stm.setInt(3, Integer.parseInt(id));
                stm.setString(4, docType);
                stm.setDate(5, new java.sql.Date(now.getTime()));
                stm.executeUpdate();
                System.out.println("[+] Document reserved.");
            } catch (SQLException e) {
                System.out.println("[-] SQL error: " + e);
            }
        } else System.out.println("[-] book does not exist.");

    }

    public void cancelReservation(){
        String doc_id = getDocId();
        cancelReservation(Integer.parseInt(doc_id), true);
    }

    public void cancelReservation(int doc_id, boolean thoughMsg){
        String query = "DELETE FROM reservations WHERE document_id = ?";
        try (PreparedStatement preStm = cn.prepareStatement(query)) {
            preStm.setInt(1, doc_id);
            preStm.executeUpdate();
            if (thoughMsg){
                System.out.println("[+] Reservation canceled.");
            }
        } catch (SQLException e){
            System.out.println("[-] SQl error: " + e);
        }
    }

    private static String getDocType() {
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
                if (choice >= 1 && choice <= 4) {
                    return choice == 1 ? "book" : (choice == 2 ? "magazine" : (choice == 3 ? "scientific_journal" : "university_thesis"));
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a valid number.");
                scanner.nextLine();
            }
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
    private static String getUserID(){
        while (true){
            System.out.println("Enter user ID: ");
            String id = scanner.nextLine();
            if (InputValidator.isValidNumber(id)){
                return id;
            } else System.out.println("Invalid number format, try again");

        }
    }
}

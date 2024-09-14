package com.library.daoImpl;

import com.library.dao.DocumentDAO;
import com.library.model.*;
import com.library.service.Library;
import com.library.service.ReservationService;
import com.library.utils.UI;
import com.library.utils.db.DbConnection;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class DocumentDaoImpl implements DocumentDAO {

    private Connection cn;
    private HashMap<String, Document> docs;

    private BookDaoImpl bookDao = new BookDaoImpl();
    private MagazineDaoImpl magazineDao = new MagazineDaoImpl();
    private ScientificJournalDaoImpl scJouDao = new ScientificJournalDaoImpl();
    private UniversityThesisDaoImpl uniTheDao = new UniversityThesisDaoImpl();
    private UserDaoImpl  userDao = new UserDaoImpl();

    public DocumentDaoImpl(){
        this.cn = DbConnection.connect();
        this.docs = new HashMap<String, Document>();
    }

    @Override
    public HashMap<String, Document> getAll() {
        docs.putAll(bookDao.getAll());
        docs.putAll(magazineDao.getAll());
        docs.putAll(scJouDao.getAll());
        docs.putAll(uniTheDao.getAll());
        return docs;
    }

    @Override
    public void get(String id, int docType) {
        switch (docType){
            case 1:
                Book book = bookDao.get(id);
                if (book != null) book.display(); else System.out.println("[-] Book Not found.");
                break;
            case 2:
                Magazine magazine = magazineDao.get(id);
                if (magazine != null) magazine.display(); else System.out.println("[-] Magazine Not found.");
                break;
            case 3:
                ScientificJournal scJou = scJouDao.get(id);
                if (scJou != null) scJou.display(); else System.out.println("[-] Journal Not found.");
                break;
            case 4:
                UniversityThesis uniThe = uniTheDao.get(id);
                if (uniThe != null) uniThe.display(); else System.out.println("[-] Thesis Not found.");
                break;
            default:
                System.out.println("Invalid choice, Try again.");
                break;
        }

    }

    @Override
    public void create(Book book) {
        bookDao.create(book);
    }

    @Override
    public void create(Magazine magazine) {
        magazineDao.create(magazine);
    }

    @Override
    public void create(ScientificJournal scientificJournal) {
        scJouDao.create(scientificJournal);
    }

    @Override
    public void create(UniversityThesis universityThesis) {
        uniTheDao.create(universityThesis);
    }
    @Override
    public void delete(String id, int docType) {
        switch (docType){
            case 1:
                bookDao.delete(id);
                break;
            case 2:
                magazineDao.delete(id);
                break;
            case 3:
                scJouDao.delete(id);
                break;
            case 4:
                uniTheDao.delete(id);
                break;
            default:
                System.out.println("Invalid choice, try again.");
                break;
        }
    }


    @Override
    public void update(Book book) {
        bookDao.update(book);
    }

    @Override
    public void update(Magazine magazine) {
        magazineDao.update(magazine);
    }

    @Override
    public void update(ScientificJournal scientificJournal) {
        scJouDao.update(scientificJournal);
    }

    @Override
    public void update(UniversityThesis universityThesis) {
        uniTheDao.update(universityThesis);
    }

    public void borrow(int doc_id, String user_type, int user_id, String doc_type, boolean thoughMsgs){
        Date now = new Date();


        if (!userDao.userExist(user_id)) { System.out.println("[-] User does not exists."); return;}
        if (!docExist(doc_id, false)){ System.out.println("[-] Doc does not exists."); return;}

        String query = "INSERT INTO borrows (user_id, document_id, borrow_date, doc_type, user_type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stm = cn.prepareStatement(query)) {
            stm.setInt(1, user_id);
            stm.setInt(2, doc_id);
            stm.setDate(3, new java.sql.Date(now.getTime()));
            stm.setString(4, doc_type);
            stm.setString(5, user_type);
            stm.executeUpdate();

            if (borrowDoc(doc_id, true) && thoughMsgs) System.out.println("[+] Document borrowed.");

        } catch (SQLException e){
            if (e.getMessage().contains("duplicate key value") && thoughMsgs){
                System.out.println("[-] Document already borrowed by user with ID: " + user_id);
            }
        }
    }

    private boolean borrowDoc(int id, boolean borrow){
        String query = "UPDATE documents SET borrowed = ? WHERE id = ?";
        try {
            PreparedStatement stm = cn.prepareStatement(query);
            stm.setBoolean(1, borrow);
            stm.setInt(2, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("[-] SQL error: " + e);
        }
        return false;
    }

    public boolean docExist(int id, boolean borrowedDocs){
        String query = "SELECT FROM documents WHERE id = ? " + (borrowedDocs ? "AND borrowed = true" : "");
        try (PreparedStatement stm = cn.prepareStatement(query)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e){
            System.out.println("SQL error : " + e);
        }
        return false;
    }
    public void returnDocument(int doc_id, String doc_type) {
        if (!docExist(doc_id, true)) {
            System.out.println("[-] Document does not exist or is not currently borrowed.");
            return;
        }

        String query = "DELETE FROM borrows WHERE document_id = ? AND doc_type = ?";
        try (PreparedStatement stm = cn.prepareStatement(query)) {
            stm.setInt(1, doc_id);
            stm.setString(2, doc_type);
            stm.executeUpdate();

            System.out.println("[+] Document returned.");
            if (checkIfDocReserved(doc_id)) {
                ReservationService reservationService = new ReservationService();
                reservationService.cancelReservation(doc_id, false);

                Object[] reservation = getBorrowedDoc(doc_id);

                if (reservation != null) {
                    int reservedUserId = (int) reservation[0];
                    String reservedUserType = (String) reservation[1];
                    borrow(doc_id, reservedUserType, reservedUserId, doc_type, false);
                    System.out.println("[+] Document automatically borrowed for user with ID: " + reservedUserId);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e);
        }
    }

    private Object[] getBorrowedDoc(int doc_id){
        Object[] borrowInfos = null;
        String query = "SELECT * FROM borrows WHERE document_id = ?";
        try (PreparedStatement stm = cn.prepareStatement(query)) {
            stm.setInt(1, doc_id);
            ResultSet result = stm.executeQuery();
            if (result.next()){
                int userId = result.getInt("user_id");
                String userType = result.getString("user_type");
                int docId = result.getInt("document_id");
                String docType = result.getString("doc_type");
                borrowInfos = new Object[]{userId, userType, docId, docType};
                return borrowInfos;
            }

        } catch (SQLException e){
            System.out.println("SQL error : " + e);
        }
        return null;
    }

    private boolean checkIfDocReserved(int id){
        String query = "SELECT * FROM reservation WHERE document_id = ?";
        try (PreparedStatement stm = cn.prepareStatement(query)) {
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();
            return result.next();

        } catch (SQLException e){
            System.out.println("SQL error : " + e);
        }
        return false;
    }
}

package com.library.daoImpl;

import com.library.dao.DocumentDAO;
import com.library.model.*;
import com.library.utils.UI;
import com.library.utils.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DocumentDaoImpl implements DocumentDAO {

    private Connection cn;
    private HashMap<String, Document> docs;

    private BookDaoImpl bookDao = new BookDaoImpl();
    private MagazineDaoImpl magazineDao = new MagazineDaoImpl();
    private ScientificJournalDaoImpl scJouDao = new ScientificJournalDaoImpl();
    private UniversityThesisDaoImpl uniTheDao = new UniversityThesisDaoImpl();

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

}

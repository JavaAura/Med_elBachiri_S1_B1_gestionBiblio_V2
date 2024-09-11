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
    private ScientificJournalDaoImpl scientificJournalDao = new ScientificJournalDaoImpl();
    private UniversityThesisDaoImpl universityThesisDao = new UniversityThesisDaoImpl();

    public DocumentDaoImpl(){
        this.cn = DbConnection.connect();
        this.docs = new HashMap<String, Document>();
    }

    @Override
    public HashMap<String, Document> getAll() {
        docs.putAll(bookDao.getAll());
        docs.putAll(magazineDao.getAll());
        docs.putAll(scientificJournalDao.getAll());
        docs.putAll(universityThesisDao.getAll());
        return docs;
    }

    @Override
    public void get(String id) {

    }

    @Override
    public void create(Book book) {
        BookDaoImpl bookDao = new BookDaoImpl();
        bookDao.create(book);
    }

    @Override
    public void create(Magazine magazine) {
        MagazineDaoImpl magazineDao = new MagazineDaoImpl();
        magazineDao.create(magazine);
    }

    @Override
    public void create(ScientificJournal scientificJournal) {
        ScientificJournalDaoImpl scJouDao = new ScientificJournalDaoImpl();
        scJouDao.create(scientificJournal);
    }

    @Override
    public void create(UniversityThesis universityThesis) {
        UniversityThesisDaoImpl uniTheDao = new UniversityThesisDaoImpl();
        uniTheDao.create(universityThesis);
    }
    @Override
    public void delete(Book book) {
        BookDaoImpl bookDao = new BookDaoImpl();
        bookDao.delete(book);
    }

    @Override
    public void delete(Magazine magazine) {
        MagazineDaoImpl magazineDao = new MagazineDaoImpl();
        magazineDao.delete(magazine);
    }

    @Override
    public void delete(ScientificJournal scientificJournal) {
        ScientificJournalDaoImpl scJouDao = new ScientificJournalDaoImpl();
        scJouDao.delete(scientificJournal);
    }

    @Override
    public void delete(UniversityThesis universityThesis) {
        UniversityThesisDaoImpl uniTheDao = new UniversityThesisDaoImpl();
        uniTheDao.delete(universityThesis);
    }


    @Override
    public void update(Book book) {
        BookDaoImpl bookDao = new BookDaoImpl();
        bookDao.update(book);
    }

    @Override
    public void update(Magazine magazine) {
        MagazineDaoImpl magazineDao = new MagazineDaoImpl();
        magazineDao.update(magazine);
    }

    @Override
    public void update(ScientificJournal scientificJournal) {
        ScientificJournalDaoImpl scJouDao = new ScientificJournalDaoImpl();
        scJouDao.update(scientificJournal);
    }

    @Override
    public void update(UniversityThesis universityThesis) {
        UniversityThesisDaoImpl uniTheDao = new UniversityThesisDaoImpl();
        uniTheDao.update(universityThesis);
    }

}

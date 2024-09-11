package com.library.daoImpl;

import com.library.dao.DocumentDAO;
import com.library.model.*;

import java.util.ArrayList;

public class DocumentDaoImpl implements DocumentDAO {
    @Override
    public ArrayList<Document> getAll() {

        return null;
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

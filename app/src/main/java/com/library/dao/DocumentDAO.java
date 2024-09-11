package com.library.dao;

import com.library.model.*;

import java.util.ArrayList;

public interface DocumentDAO {
    public ArrayList<Document> getAll();

    public void get(String id);

    public void create(Book book);
    public void create(Magazine magazine);
    public void create(ScientificJournal scientificJournal);
    public void create(UniversityThesis universityThesis);

    public void delete(Book book);
    public void delete(Magazine magazine);
    public void delete(ScientificJournal scientificJournal);
    public void delete(UniversityThesis universityThesis);

    public void update(Book book);
    public void update(Magazine magazine);
    public void update(ScientificJournal scientificJournal);
    public void update(UniversityThesis universityThesis);
}

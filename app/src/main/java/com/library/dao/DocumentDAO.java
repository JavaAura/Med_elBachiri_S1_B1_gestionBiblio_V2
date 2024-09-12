package com.library.dao;

import com.library.model.*;

import java.util.HashMap;

public interface DocumentDAO {
    public HashMap<String, Document> getAll();

    public void get(String id, int docType);

    public void create(Book book);
    public void create(Magazine magazine);
    public void create(ScientificJournal scientificJournal);
    public void create(UniversityThesis universityThesis);

    public void delete(String id, int docType);

    public void update(Book book);
    public void update(Magazine magazine);
    public void update(ScientificJournal scientificJournal);
    public void update(UniversityThesis universityThesis);
}
